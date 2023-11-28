package Database;

import Enums.Category;
import Game.Question;
import Game.QuestionManager;

import java.util.ArrayList;

public class Database {

    private final ArrayList<QuestionManager> questionList = new ArrayList<>();

    public Database() {

        QuestionManager history = new QuestionManager();
        history.loadQuestions("src/TextFiles/History.txt");
        questionList.add(history);

        QuestionManager religion = new QuestionManager();
        religion.loadQuestions("src/TextFiles/Religion.txt");
        questionList.add(religion);

        QuestionManager science = new QuestionManager();
        science.loadQuestions("src/TextFiles/Science.txt");
        questionList.add(science);

        QuestionManager sport = new QuestionManager();
        sport.loadQuestions("src/TextFiles/Sport.txt");
        questionList.add(sport);
    }
    public Question getQuestionByCategory(Category category) {
        String tempCategory = category.toString();

        for (int i=0; i<questionList.size(); i++){
            if(questionList.get(i).getCategory().equals(tempCategory)){
                return questionList.get(i).getRandomQuestion();
            }
        }

        return null;
    }

    public void removeQuestion (Question question){
        for (int i=0; i<questionList.size(); i++){
            QuestionManager tempManager = questionList.get(i);
            for(int j=0; j<tempManager.getArraySize(); j++){
                if(tempManager.getQuestion(j).equals(question)){
                    questionList.get(i).removeQuestion(j);
                }
            }
        }
    }

    public static void main(String[] args) {


        Database d = new Database();
        /*
        System.out.println(d.questionList.get(0).getRandomQuestion().getQuestion());
        System.out.println(d.questionList.get(1).getRandomQuestion().getQuestion());
        System.out.println(d.questionList.get(2).getRandomQuestion().getQuestion());
        System.out.println(d.questionList.get(3).getRandomQuestion().getQuestion());
         */

    }
}