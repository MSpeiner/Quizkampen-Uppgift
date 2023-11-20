public class Questions {

    private String question;
    private String[] answers;
    private int correctAnswer; // index i answers!
    private String Category;

    public String getQuestion() {
        return question;
    }


    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswerIndex) {
        this.correctAnswer = correctAnswerIndex;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public void printAllAnswers (){          // Ta bort sen
        for (int i=0; i<answers.length; i++){
            System.out.println(answers[i]);
        }
    }
}