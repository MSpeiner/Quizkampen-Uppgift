import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class QuestionManager {

    private ArrayList<Questions> questionsArray = new ArrayList<>();
    private Random random = new Random();

    public void loadQuestions(String path) {
        Path filePath = Paths.get(path);

        try (Scanner file = new Scanner(filePath)) {
            ArrayList<String> questionList = new ArrayList<>();
            while (file.hasNext()) {
                questionList.add(file.nextLine());
            }

            if (questionList.isEmpty()) {
                throw new IOException("The file is empty or not formatted correctly");
            }

            String category = questionList.get(0).trim();

            for (int i = 1; i < questionList.size(); i++) {
                String[] tempArray = questionList.get(i).split(";");
                if (tempArray.length != 6) {
                    System.out.println("Skipping line: " + (i + 1) + " due to incorrect format.");
                    continue;
                }

                Questions tempQuestion = new Questions();
                tempQuestion.setCategory(category);
                tempQuestion.setQuestion(tempArray[0].trim());
                tempQuestion.setCorrectAnswer(Integer.parseInt(tempArray[5].trim()));

                String[] answers = new String[4];
                System.arraycopy(tempArray, 1, answers, 0, 4);
                tempQuestion.setAnswers(answers);

                questionsArray.add(tempQuestion);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Number format error in file: " + e.getMessage());
        }
    }

    public Questions getRandomQuestion() {
        if (questionsArray.isEmpty()) {
            return null;
        }
        int randomIndex = random.nextInt(questionsArray.size());
        return questionsArray.get(randomIndex);
    }


    public ArrayList<Questions> getQuestions() {
        return new ArrayList<>(questionsArray);
    }

    public static void main(String[] args) {
        String myPath = "src/test.txt";
        QuestionManager questionManager = new QuestionManager();
        questionManager.loadQuestions(myPath);

        Questions randomQuestion = questionManager.getRandomQuestion();
        if (randomQuestion != null) {
            System.out.println(randomQuestion.getQuestion());
            for (String answer : randomQuestion.getAnswers()) {
                System.out.println(answer);
            }
            System.out.println("Correct answer index: " + randomQuestion.getCorrectAnswer());
        }
    }
}
