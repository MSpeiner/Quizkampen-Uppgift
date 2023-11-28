package Game;

import Enums.Answer;
import Utils.PropertiesManager;
import Enums.Category;
import java.io.Serializable;

public class GameState implements Serializable {

    private final PropertiesManager propertiesManager = new PropertiesManager();
    private Category currentCategory;
    protected Answer[] player1Answers;
    protected Answer[] player2Answers;

    public Category getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(Category currentCategory) {
        this.currentCategory = currentCategory;
    }

    public GameState() {
        // Initialize each player's answers with nulls (unanswered)
        int antalFragorTillSpelare = propertiesManager.antalFragor() * propertiesManager.antalOmgangar();
        player1Answers = new Answer[antalFragorTillSpelare];
        player2Answers = new Answer[antalFragorTillSpelare];
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

    public boolean isNewRound() {
        int player1AnswerCount = countAnswers(player1Answers);
        int player2AnswerCount = countAnswers(player2Answers);
        if (player1AnswerCount != player2AnswerCount) {
            return false; // Om dom inte har lika många svar är det inte en ny runda
        }

        int antalFragorPerRunda = propertiesManager.antalFragor();
        // player1AnswerCount % antalFragorPerRunda == 0 OM totalAnswer == 0
        // returnerar true om player1AnswerCount % antalFragorPerRunda == 0
        // annars returnerar vi false.
        // Vi kan göra detta genom att direkt returnera
        // player1AnswerCount % antalFragorPerRunda == 0
        return player1AnswerCount % antalFragorPerRunda == 0;
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

    public boolean playerHasFinishedAnswering() {
        int player1AnswerCount = countAnswers(player1Answers);
        int player2AnswerCount = countAnswers(player2Answers);
        int antalFragorPerRunda = propertiesManager.antalFragor();
        int totalAnswersSoFar = player1AnswerCount + player2AnswerCount;
        // Om spelet inte börjat än ska vi inte byta spelare
        if(totalAnswersSoFar == 0){
            return false;
        }
        // Om totala mängden besvarade frågor är jämnt delbart med antal frågor per runda
        // har en spelare precis avslutat sin delrunda
        return totalAnswersSoFar % antalFragorPerRunda == 0;
    }

    public boolean shouldChangePlayer() {
        int player1AnswerCount = countAnswers(player1Answers);
        int player2AnswerCount = countAnswers(player2Answers);
        int antalFragorPerRunda = propertiesManager.antalFragor();
        int totalAnswersSoFar = player1AnswerCount + player2AnswerCount;
        System.out.println();
        System.out.println("shouldChangePlayer");
        System.out.println(totalAnswersSoFar);
        System.out.println(antalFragorPerRunda);
        System.out.println(playerHasFinishedAnswering());
        // Om en spelare precis avslutat sin delrunda
        if(playerHasFinishedAnswering()){
            // Vi kan få vilken "delrunda" vi är på genom att dela totala mängden besvarade frågor
            // på antal frågor per runda
            int subRound = totalAnswersSoFar / antalFragorPerRunda;
            System.out.println(subRound);
            // om vi är på en udda delrunda är det dags att byta spelare
            return subRound % 2 != 0;
        }
        return false;
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