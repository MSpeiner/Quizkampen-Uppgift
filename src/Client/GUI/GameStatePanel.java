package Client.GUI;

import Client.ClientGameState.ClientGameState;
import Client.ClientGameState.ClientRoundState;
import Enums.Answer;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Represents a graphical panel to display the state of a game.
 * This panel includes the player and opponent names, the category of each round,
 * and visual representations of the answers.
 */
public class GameStatePanel extends JPanel {
    private final ClientGameState gameState;

    /**
     * Constructs a GameStatePanel with a given game state.
     * Sets up the border and layout, and initializes the user interface components.
     *
     * @param gameState The game state to be displayed on this panel.
     */
    public GameStatePanel(ClientGameState gameState) {
        this.gameState = gameState;
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(new GridLayout(0, 3)); // Flexible grid layout with 3 columns
        initializeUI();
    }

    /**
     * Initializes the user interface components of the panel.
     * Adds headers and round details based on the game state.
     */
    private void initializeUI() {
        String playerName = gameState.getPlayerName();
        String opponentName = gameState.getOpponentName();

        // Adding headers for player, category, and opponent
        add(createCenteredLabel(playerName));
        add(createCenteredLabel("Category"));
        add(createCenteredLabel(Objects.requireNonNullElse(opponentName, "Opponent")));

        // Adding each round's details
        for (ClientRoundState round : gameState.getRounds()) {
            addRoundDetails(round);
        }
    }

    /**
     * Adds the details of a single round to the panel.
     * For each round, it displays the player's and opponent's answers and the category.
     *
     * @param round The round whose details are to be displayed.
     */
    private void addRoundDetails(ClientRoundState round) {
        if (round != null) {
            // Displaying actual round data
            add(createAnswerPanel(round.getPlayerAnswers()));
            add(createCenteredLabel(round.getCategory()));
            add(createAnswerPanel(round.getOpponentAnswers()));
        } else {
            // Displaying placeholders for an empty round
            add(createEmptyAnswerPanel());
            add(new JLabel("")); // Empty category
            add(createEmptyAnswerPanel());
        }
    }

    /**
     * Creates a panel showing the answers as colored boxes.
     * Each answer is represented by a colored box: green for correct, red for incorrect, gray for no answer.
     *
     * @param answers Array of answers to be displayed.
     * @return A JPanel containing the visual representation of answers.
     */
    private JPanel createAnswerPanel(Answer[] answers) {
        JPanel panel = new JPanel(new GridLayout(1, answers.length));
        for (Answer answer : answers) {
            RoundedBoxPanel roundedPanel = new RoundedBoxPanel(10); // Rounded corners with a radius of 10
            roundedPanel.setBackground(getColorForAnswer(answer));
            panel.add(roundedPanel);
        }
        return panel;
    }

    /**
     * Creates a centered label with the given text.
     *
     * @param text The text to be displayed in the label.
     * @return A JLabel with centered text.
     */
    private JLabel createCenteredLabel(String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.CENTER); // Center the text horizontally
        return label;
    }

    /**
     * Creates a panel representing an empty answer slot.
     * Used for rounds that have not been played yet.
     *
     * @return A JPanel representing an empty answer slot.
     */
    private JPanel createEmptyAnswerPanel() {
        JPanel panel = new JPanel(new GridLayout(1, gameState.getNumberOfQuestions()));
        for (int i = 0; i < gameState.getNumberOfQuestions(); i++) {
            RoundedBoxPanel roundedPanel = new RoundedBoxPanel(10); // Rounded corners with a radius of 10
            roundedPanel.setBackground(Color.GRAY);
            panel.add(roundedPanel);
        }
        return panel;
    }

    /**
     * Determines the color to be used for representing an answer.
     * Green for correct answers, red for incorrect, and gray for no answer.
     *
     * @param answer The answer to determine the color for.
     * @return The color corresponding to the answer's correctness.
     */
    private Color getColorForAnswer(Answer answer) {
        if (answer == null) return Color.GRAY;
        return answer == Answer.CORRECT ? Color.GREEN : Color.RED;
    }
}
