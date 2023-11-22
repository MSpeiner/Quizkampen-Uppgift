import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class QuestionManager {

    protected ArrayList<Question> questionArray = new ArrayList<>();
    private Random random = new Random();

    public void loadQuestions(String path) {
        Path filePath = Paths.get(path);
        questionArray.clear();

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

                Question tempQuestion = new Question();
                tempQuestion.setCategory(category);
                tempQuestion.setQuestion(tempArray[0].trim());
                tempQuestion.setCorrectAnswer(Integer.parseInt(tempArray[5].trim()));

                String[] answers = new String[4];
                System.arraycopy(tempArray, 1, answers, 0, 4);
                tempQuestion.setAnswers(answers);

                questionArray.add(tempQuestion);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Number format error in file: " + e.getMessage());
        }
    }

    public Question getQuestionByCategory(String category) {
        if(category.equals("HISTORY")){
            loadQuestions("src/TextFiles/History.txt");
        }
        if(category.equals("RELIGION")){
            loadQuestions("src/TextFiles/Religion.txt");
        }
        if(category.equals("SCIENCE")){
            loadQuestions("src/TextFiles/Science.txt");
        }
        if(category.equals("SPORT")){
            loadQuestions("src/TextFiles/Sport.txt");
        }
        return getRandomQuestion();
    }

    public Question getRandomQuestion() {
        if (questionArray.isEmpty()) {
            return null;
        }
        int randomIndex = random.nextInt(questionArray.size());
        return questionArray.get(randomIndex);
    }

    public ArrayList<Question> getQuestions() {
        return new ArrayList<>(questionArray);
    }

}
