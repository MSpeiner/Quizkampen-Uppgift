public class GameState {
    private Answer[] player1Answers;
    private Answer[] player2Answers;

    public GameState() {
        // Initialize each player's answers with nulls (unanswered)
        player1Answers = new Answer[4];
        player2Answers = new Answer[4];
    }

    // Method to update an answer for a player
    public void updateAnswer(int player, int question, Answer answer) {
        if (player == 1) {
            player1Answers[question] = answer;
        } else if (player == 2) {
            player2Answers[question] = answer;
        }
    }


    // Method to check if the game is over (no null values in either player's arrays)
    public boolean gameIsOver() {
        return allAnswersFilled(player1Answers) && allAnswersFilled(player2Answers);
    }

    // Method to check if player one has more "CORRECT" answers than player two
    public boolean hasWinner() {
        int player1CorrectCount = countCorrectAnswers(player1Answers);
        int player2CorrectCount = countCorrectAnswers(player2Answers);

        return player1CorrectCount > player2CorrectCount;
    }

    public String getWinner() {
        int player1CorrectCount = countCorrectAnswers(player1Answers);
        int player2CorrectCount = countCorrectAnswers(player2Answers);

        return player1CorrectCount > player2CorrectCount ? "1" : "2";
    }

    // Helper method to count "CORRECT" answers in a player's answer array
    private int countCorrectAnswers(Answer[] answers) {
        int correctCount = 0;
        for (Answer answer : answers) {
            if (answer == Answer.CORRECT) {
                correctCount++;
            }
        }
        return correctCount;
    }

    private boolean allAnswersFilled(Answer[] answers) {
        for (Answer answer : answers) {
            if (answer == null) {
                return false;
            }
        }
        return true;
    }
}