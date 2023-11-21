import java.util.ArrayList;

public class Databas {

    private ArrayList<QuestionManager> questionList = new ArrayList<>();

    public Databas() {

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

    public static void main(String[] args) {
        /* Endast test

        Databas d = new Databas();

        System.out.println(d.questionList.get(0).getRandomQuestion().getQuestion());
        System.out.println(d.questionList.get(1).getRandomQuestion().getQuestion());
        System.out.println(d.questionList.get(2).getRandomQuestion().getQuestion());
        System.out.println(d.questionList.get(3).getRandomQuestion().getQuestion());

         */
    }
}