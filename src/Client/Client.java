package Client;

import Client.ClientGameState.ClientGameState;
import Client.GUI.GUIManager;
import Client.GUI.ResultGUI;
import Enums.Answer;
import Game.GameState;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

/**
 * The Client class manages the client-side logic for the quiz game.
 * It handles server communication, user interactions, and updates the game state.
 */
public class Client {

    private static int PORT = 8901; // Server port
    private Socket socket; // Socket for connecting to the server
    private BufferedReader in; // Reader for incoming messages
    private PrintWriter out; // Writer for outgoing messages
    private ClientGameState gameState;

    private final GUIManager guiManager; // Manager for the graphical user interface

    ObjectInputStream objectInputStream; // Object input stream for receiving game state objects

    /**
     * Constructs a Client and initializes the connection to the server.
     * Sets up the GUI and displays the initial waiting screen.
     *
     * @param serverAddress The address of the server to connect to.
     * @throws Exception If an error occurs during setup.
     */
    public Client(String serverAddress) throws Exception {

        // Initialize connection to the server
        socket = new Socket(serverAddress, PORT);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        objectInputStream = new ObjectInputStream(socket.getInputStream());

        // Set up the GUI manager
        guiManager = new GUIManager(out);
        guiManager.showWaitingForOtherPlayer(); // Show initial waiting screen
    }

    /**
     * Starts the game loop, processing responses from the server.
     * Handles different types of server messages and updates the game state and GUI accordingly.
     *
     * @throws Exception If an error occurs during the game.
     */
    public void play() throws Exception {
        String response;
        String playerName = "";
        String opponentName = "";
        String currentCategory = "";

        try {
            while (true) {
                response = in.readLine();
                if (response.startsWith("GAME_INFORMATION")) {
                    int rounds;
                    int numberOfQuestions;
                    String roundsMessage = in.readLine();
                    if (roundsMessage.startsWith("ROUNDS")) {
                        rounds = Integer.parseInt(roundsMessage.substring(7));
                    } else {
                        throw new IllegalArgumentException("Invalid message format. Expected message to start with 'ROUNDS'.\nmessage: " + roundsMessage);
                    }

                    String numberOfQuestionsMessage = in.readLine();
                    if (numberOfQuestionsMessage.startsWith("QUESTIONS")) {
                        numberOfQuestions = Integer.parseInt(numberOfQuestionsMessage.substring(10));
                    } else {
                        throw new IllegalArgumentException("Invalid message format. Expected message to start with 'QUESTIONS'.\nmessage: " + numberOfQuestionsMessage);
                    }
                    gameState = new ClientGameState(numberOfQuestions, rounds);
                }
                // Handle different types of server messages
                if (response.startsWith("ENTER_NAME")) {
                    guiManager.getPlayerName();
                } else if (response.startsWith("YOUR_NAME")) {
                    playerName = response.substring(10);
                    gameState.setPlayerName(playerName);
                    guiManager.showGameStateScreen(gameState);
                } else if (response.startsWith("OPPONENT_NAME")) {
                    opponentName = response.substring(14);
                    gameState.setOpponentName(opponentName);
                    guiManager.showGameStateScreen(gameState);
                } else if (response.startsWith("CATEGORY_SELECTED")) {
                    currentCategory = response.substring(18);
                    gameState.addRound(currentCategory);
                    guiManager.showGameStateScreen(gameState);
                } else if (response.startsWith("QUESTION")) {
                    // Handle question display
                    String question = response.substring(8);
                    String[] answers = new String[4];
                    for (int i = 0; i < 4; i++) {
                        answers[i] = in.readLine();
                    }
                    int correctAnswer = Integer.parseInt(in.readLine());
                    String category = gameState.getCurrentCategory();
                    guiManager.answerQuestion(category, question, answers, correctAnswer);


                } else if (response.startsWith("SELECT_CATEGORY")) {
                    guiManager.selectCategory();
                } else if (response.startsWith("ANSWER_RESULT")) {
                    Answer answer = Answer.valueOf(response.substring(14));
                    gameState.addPlayerAnswer(answer);
                    guiManager.showGameStateScreen(gameState);
                } else if (response.startsWith("OPPONENT_RESULT")) {
                    Answer answer = Answer.valueOf(response.substring(16));
                    gameState.addOpponentAnswer(answer);
                    guiManager.showGameStateScreen(gameState);
                } else if (response.startsWith("GAME_OVER")) {
                    String winner = response.substring(9);
                    guiManager.showGameOverResult(gameState, winner);

                } else if (response.equals("QUIT")) {
                    break; // Exit the loop if the server sends a quit command
                    //Om en av spelarna stänger ner programmet stängs server och clienter ner för båda spelarna.
                } else if (response.equals("GIVE_UP")) {
                    JOptionPane.showMessageDialog(null, "The game was exited by your opponent." +
                            "\n - Please close the server or start a new game.");
                    System.exit(0);
                }
            }
        } finally {
            socket.close(); // Ensure the socket is closed on exit
        }
    }

    /**
     * Main method to start the client application.
     * Connects to the server and starts the game.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) throws Exception {
        try {
            String serverAddress = (args.length == 0) ? "localhost" : args[1];
            Client client = new Client(serverAddress);
            client.play();
        } catch (ConnectException ce) {
            JOptionPane.showMessageDialog(null, "Start the server before you start the client!");
        }
    }
}
