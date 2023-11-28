package Client.ClientGameState;

import Enums.Answer;

/**
 * Represents the state of a round in a quiz game.
 * This class holds the information about the current category of questions,
 * the number of questions, and the answers given by both the player and their opponent.
 */
public class ClientRoundState {
    // Category of the current round
    private final String category;

    // Array to store answers given by the player
    private final Answer[] playerAnswers;

    // Array to store answers given by the opponent
    private final Answer[] opponentAnswers;

    /**
     * Constructs a new round state with a specified category and number of questions.
     *
     * @param category The category of questions for this round.
     * @param numberOfQuestions The total number of questions in this round.
     */
    public ClientRoundState(String category, int numberOfQuestions) {
        this.category = category;
        this.playerAnswers = new Answer[numberOfQuestions]; // Initialize arrays with the number of questions
        this.opponentAnswers = new Answer[numberOfQuestions];
    }

    /**
     * Gets the category of the current round.
     *
     * @return The category of the round.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Retrieves the answers given by the player.
     *
     * @return An array of player's answers.
     */
    public Answer[] getPlayerAnswers() {
        return playerAnswers;
    }

    /**
     * Retrieves the answers given by the opponent.
     *
     * @return An array of opponent's answers.
     */
    public Answer[] getOpponentAnswers() {
        return opponentAnswers;
    }

    /**
     * Records an answer given by the player.
     * This method finds the first empty slot in the playerAnswers array and assigns the answer to it.
     *
     * @param answer The answer to be added for the player.
     */
    public void addPlayerAnswer(Answer answer) {
        for (int i = 0; i < playerAnswers.length; i++) {
            if (playerAnswers[i] == null) {
                playerAnswers[i] = answer;
                break; // Exit the loop after adding the answer
            }
        }
    }

    /**
     * Records an answer given by the opponent.
     * This method finds the first empty slot in the opponentAnswers array and assigns the answer to it.
     *
     * @param answer The answer to be added for the opponent.
     */
    public void addOpponentAnswer(Answer answer) {
        for (int i = 0; i < opponentAnswers.length; i++) {
            if (opponentAnswers[i] == null) {
                opponentAnswers[i] = answer;
                break; // Exit the loop after adding the answer
            }
        }
    }
}
