public class GameState {
    public String getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(String currentCategory) {
        this.currentCategory = currentCategory;
    }

    private String currentCategory;
    private final Answer[] player1Answers;
    private final Answer[] player2Answers;
    String answer1;
    String answer2;
    String answer3;
    String answer4;

    public GameState() {
        // Initialize each player's answers with nulls (unanswered)
        player1Answers = new Answer[4];
        player2Answers = new Answer[4];
    }

    // Method to update an answer for a player
    public void updateAnswer(int playerNumber, Answer answer) {
        if (playerNumber == 1) {
            int playerOneAnswerCount = countAnswers(player1Answers);
            player1Answers[playerOneAnswerCount] = answer;
        } else if (playerNumber == 2) {
            int playerTwoAnswerCount = countAnswers(player2Answers);
            player2Answers[playerTwoAnswerCount] = answer;
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

    public int getWinner() {
        int player1CorrectCount = countCorrectAnswers(player1Answers);
        int player2CorrectCount = countCorrectAnswers(player2Answers);
        if (player1CorrectCount > player2CorrectCount) {
            return 1;
        }
        if (player2CorrectCount > player1CorrectCount) {
            return 2;
        }
        return 0;
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

    public int getCorrectForPlayer1() {
        return countCorrectAnswers(player1Answers);
    }

    public int getCorrectForPlayer2() {
        return countCorrectAnswers(player2Answers);
    }

    public boolean isNewRound() {
        int player1AnswerCount = countAnswers(player1Answers);
        int player2AnswerCount = countAnswers(player2Answers);
        if (player1AnswerCount != player2AnswerCount) {
            return false; // Om dom inte har lika många svar är det inte en ny runda
        }
        // Om deras svar är jämnt delbart med 2 är det ny runda
        // Dvs om båda har 0 eller 2 svar
        return player1AnswerCount % 2 == 0;
    }

    // Helper method to count "CORRECT" answers in a player's answer array
    private int countAnswers(Answer[] answers) {
        int count = 0;
        for (Answer answer : answers) {
            if (answer != null) {
                count++;
            }
        }
        return count;
    }

    private boolean allAnswersFilled(Answer[] answers) {
        for (Answer answer : answers) {
            if (answer == null) {
                return false;
            }
        }
        return true;
    }

    //Metod som konverterar arrayen svar till string array som kan skickas mellan server och klient
    public String[] convertAnswersToStringArray(Answer[] answers) {
        String[] stringArray = new String[answers.length];
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] != null) {
                stringArray[i] = answers[i].toString();
            }
        }
        return stringArray;
    }

    //Metod som gör om String Array till 4 separata strängar som kan skickas till klienten
    public void sendableAnswers(String[] Answers) {
        answer1 = Answers[0];
        answer2 = Answers[1];
        answer3 = Answers[2];
        answer4 = Answers[3];
    }

    //Getters för metoder och variabler
    public Answer[] getPlayer1Answers() {
        return player1Answers;
    }

    public Answer[] getPlayer2Answers() {
        return player2Answers;
    }
}