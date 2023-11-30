package Client.GUI;

import Client.ClientGameState.ClientGameState;
import Enums.Category;
import javax.swing.*;
import java.awt.*;
import java.io.PrintWriter;

/**
 * Manages the graphical user interface (GUI) for the quiz game.
 * This class is responsible for displaying various screens and handling user interactions.
 */
public class GUIManager {

    private final JFrame frame; // Main frame of the application
    private final PrintWriter clientOutputStream; // Stream to send data to the server

    /**
     * Constructs a GUIManager with a given output stream for client-server communication.
     * Initializes the main frame of the application.
     *
     * @param clientOutputStream The output stream to send data to the server.
     */
    public GUIManager(PrintWriter clientOutputStream) {
        this.clientOutputStream = clientOutputStream;
        frame = new JFrame("Quizkampen!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(540, 740);
        frame.setVisible(true);
    }

    /**
     * Clears the frame by removing all components, repainting, and revalidating.
     */
    private void clear() {
        frame.getContentPane().removeAll(); // Remove all components
        frame.repaint();  // Repaint the frame
        frame.revalidate(); // Revalidate the frame layout
    }

    /**
     * Displays a screen to get the player's name.
     * After the name is entered, it sends the name to the server
     */
    public void getPlayerName() {
        SwingUtilities.invokeLater(() -> {
            clear(); // Clear previous components

            // Holder for the panel to access it inside the lambda expression
            final PlayerNamePanel[] playerNamePanelHolder = new PlayerNamePanel[1];

            playerNamePanelHolder[0] = new PlayerNamePanel(e -> {
                String playerName = playerNamePanelHolder[0].getPlayerName();
                clientOutputStream.println("NAME " + playerName);
                clear();
            });

            frame.getContentPane().add(playerNamePanelHolder[0], BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        });
    }

    /**
     * Displays a screen for category selection.
     * After a category is selected, it sends the selected category to the server.
     */
    public void selectCategory() {
        SwingUtilities.invokeLater(() -> {
            clear();

            final CategoryViewPanel[] categorySelectPanelHolder = new CategoryViewPanel[1];

            categorySelectPanelHolder[0] = new CategoryViewPanel(e -> {
                Category selectedCategory = categorySelectPanelHolder[0].getSelectedCategory(e);
                clientOutputStream.println("CATEGORY_SELECTED " + selectedCategory);
                clear();
            });

            frame.getContentPane().add(categorySelectPanelHolder[0], BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        });
    }

    /**
     * Displays the game state screen.
     * Shows the current state of the game including rounds and answers.
     *
     * @param gameState The current state of the game to be displayed.
     */
    public void showGameStateScreen(ClientGameState gameState) {
        SwingUtilities.invokeLater(() -> {
            clear();

            GameStatePanel gameStatePanel = new GameStatePanel(gameState);

            frame.getContentPane().add(gameStatePanel, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        });
    }

    /**
     * Displays a screen for answering a question.
     * After an answer is selected, it sends the selected answer to the server.
     *
     * @param category The category of the question.
     * @param question The question to be answered.
     * @param answers The possible answers for the question.
     */
    public void answerQuestion(String category, String question, String[] answers, int correctAnswer) {
        // Använder SwingUtilities.invokeLater för att se till att GUI-uppdateringar körs på rätt tråd
        SwingUtilities.invokeLater(() -> {
            clear(); // Rensar det nuvarande gränssnittet

            // Skapar en behållare för frågepanelen
            final QuestionViewPanel[] questionPanelHolder = new QuestionViewPanel[1];

            // Initierar frågepanelen med kategorin, frågan, svarsalternativen och en händelsehanterare för knapptryck
            questionPanelHolder[0] = new QuestionViewPanel(category, question, answers, e -> {
                JButton clickedButton = (JButton) e.getSource(); // Hämtar den klickade knappen
                questionPanelHolder[0].changeButtonColor(clickedButton, correctAnswer); // Ändrar färgen på knappen till grön

                // Avaktiverar alla svarsknappar för att förhindra ytterligare inmatning
                questionPanelHolder[0].setAnswerButtonsEnabled(false);

                // Skapar en timer för att skapa en fördröjning
                Timer delayTimer = new Timer(1000, ev -> {
                    int selectedAnswer = questionPanelHolder[0].getSelectedAnswerIndex(e); // Hämtar index för det valda svaret
                    clientOutputStream.println("ANSWER " + selectedAnswer); // Skickar det valda svaret till servern
                    clear(); // Rensar gränssnittet
                });
                delayTimer.setRepeats(false); // Ser till att timern inte upprepar sig
                delayTimer.start(); // Startar timern
            });

            // Lägger till frågepanelen i huvudfönstrets innehållspanel
            frame.getContentPane().add(questionPanelHolder[0], BorderLayout.CENTER);
            frame.revalidate(); // Uppdaterar och validerar fönstrets layout
            frame.repaint(); // Omritar fönstret
        });
    }



    /**
     * Displays a screen indicating that the game is waiting for the other player.
     * This screen is shown during the times when the player needs to wait for the opponent's actions.
     */
    public void showWaitingForOtherPlayer() {
        SwingUtilities.invokeLater(() -> {
            clear();

            WaitingForPlayerPanel waitingPanel = new WaitingForPlayerPanel();

            frame.getContentPane().add(waitingPanel, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        });
    }

    public void showGameOverResult(ClientGameState gameState, String winner){
        SwingUtilities.invokeLater(() -> {
            clear();
            String gameOverText = "Game Over!" + winner;

            GameStatePanel gameStatePanel = new GameStatePanel(gameState);
            JLabel gameOverLabel = new JLabel(gameOverText);
            frame.getContentPane().add(gameStatePanel, BorderLayout.CENTER);
            frame.add(gameOverLabel, BorderLayout.NORTH);
            frame.revalidate();
            frame.repaint();
        });

    }
}
