package Client.ClientGameState;

import Enums.Answer;

/**
 * Represents the state of an entire game in a quiz context.
 * This class holds information about the players, the number of questions per round,
 * and the state of each round in the game.
 */
public class ClientGameState {

    private String playerName; // Name of the player
    private String opponentName; // Name of the opponent
    private final int numberOfQuestions; // Number of questions per round
    private final ClientRoundState[] rounds; // Array to store the state of each round

    /**
     * Constructs a new game state with a specified number of questions per round and total number of rounds.
     *
     * @param numberOfQuestions The number of questions in each round.
     * @param numberOfRounds The total number of rounds in the game.
     */
    public ClientGameState(int numberOfQuestions, int numberOfRounds) {
        this.numberOfQuestions = numberOfQuestions;
        this.rounds = new ClientRoundState[numberOfRounds];
    }

    /**
     * Gets the name of the player.
     *
     * @return The name of the player.
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Sets the name of the player.
     *
     * @param playerName The name to be set for the player.
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Gets the name of the opponent.
     *
     * @return The name of the opponent.
     */
    public String getOpponentName() {
        return opponentName;
    }

    /**
     * Sets the name of the opponent.
     *
     * @param opponentName The name to be set for the opponent.
     */
    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    /**
     * Retrieves all rounds of the game.
     *
     * @return An array of rounds in the game.
     */
    public ClientRoundState[] getRounds() {
        return rounds;
    }

    /**
     * Gets the number of questions per round.
     *
     * @return The number of questions per round.
     */
    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    /**
     * Gets the category of the most recent round.
     * If no rounds have been played, returns null.
     *
     * @return The category of the current round, or null if no round is present.
     */
    public String getCurrentCategory() {
        ClientRoundState currentRound = getCurrentRound();
        return (currentRound != null) ? currentRound.getCategory() : null;
    }

    /**
     * Adds an answer to the player's answers for the current round.
     *
     * @param answer The answer to be added for the player.
     */
    public void addPlayerAnswer(Answer answer) {
        ClientRoundState currentRound = getCurrentRound();
        if(currentRound != null){
            currentRound.addPlayerAnswer(answer);
        }
    }

    /**
     * Adds an answer to the opponent's answers for the current round.
     *
     * @param answer The answer to be added for the opponent.
     */
    public void addOpponentAnswer(Answer answer) {
        ClientRoundState currentRound = getCurrentRound();
        if(currentRound != null){
            currentRound.addOpponentAnswer(answer);
        }
    }

    /**
     * Retrieves the current (most recent) round state.
     * If no rounds have been played yet, returns null.
     *
     * @return The current round state, or null if no rounds have been added.
     */
    private ClientRoundState getCurrentRound() {
        for (int i = rounds.length - 1; i >= 0; i--) {
            if (rounds[i] != null) {
                return rounds[i];
            }
        }
        return null;
    }

    /**
     * Adds a new round to the game.
     * The round is created with the given category and the predefined number of questions.
     *
     * @param category The category for the new round.
     */
    public void addRound(String category) {
        ClientRoundState newRound = new ClientRoundState(category, numberOfQuestions);
        for (int i = 0; i < rounds.length; i++) {
            if (rounds[i] == null) {
                rounds[i] = newRound;
                break;
            }
        }
    }
}
