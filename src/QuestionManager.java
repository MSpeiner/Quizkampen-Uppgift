import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class QuestionManager {

    private ArrayList<Questions> questionsArray = new ArrayList<>();

    public QuestionManager(){

    }
    //
    public ArrayList<String> readFile (String path) {
        Path filePath = Paths.get(path);
        ArrayList<String> result = new ArrayList<>();

        try (Scanner file = new Scanner(filePath);) {
            while (file.hasNext()) {
                result.add(file.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Fel path!");
            e.printStackTrace();
        }
        return result;
    }

    public void loadQuestions (String path){
        ArrayList<String> questionList = readFile(path);

        String tempString = "";
        String category = questionList.get(0).trim();

        for (int i=1; i<questionList.size(); i++){
            tempString = questionList.get(i);
            String[] tempArray = tempString.split(";");

            Questions tempQuestion = new Questions();
            String question = tempArray[0].trim();

            tempQuestion.setQuestion(question);
            tempQuestion.setCategory(category);

            try{
                String s = tempArray[5].trim();
                int correctAnswer = Integer.parseInt(s);
                tempQuestion.setCorrectAnswer(correctAnswer);
            }catch (NumberFormatException e){
                System.out.println("Ingen siffra i filen!");
                e.printStackTrace();
            }
            String[] answers = new String[4];
            for (int j=1; j<tempArray.length-1; j++){
                answers[j-1] = tempArray[j].trim();
            }
            tempQuestion.setAnswers(answers);
            questionsArray.add(tempQuestion);
        }
    }

    public static void main(String[] args) {

        String myPath = "C:\\Users\\harry\\IdeaProjects\\Quizkampen-Uppgift\\src\\test.txt";
        QuestionManager test = new QuestionManager();
        test.loadQuestions(myPath);

        /*
        for(int i=0; i<test.questionsArray.size(); i++) {
            System.out.println("A1: " + test.questionsArray.get(i).getAnswers()[0]);
            System.out.println("A2: "+test.questionsArray.get(i).getAnswers()[1]);
            System.out.println("A3: "+test.questionsArray.get(i).getAnswers()[2]);
            System.out.println("A4: "+test.questionsArray.get(i).getAnswers()[3]);
            System.out.println(test.questionsArray.get(i).getQuestion());
        }
        */

    }
}