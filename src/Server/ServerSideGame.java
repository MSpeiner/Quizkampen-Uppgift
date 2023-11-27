package Server;

import Client.GUI.ResultGUI;
import Enums.Answer;
import Enums.Category;
import Game.GameState;
import Game.Question;
import Game.QuestionManager;
import Utils.PropertiesManager;

public class ServerSideGame {

    ServerPlayer player1;
    ServerPlayer player2;
    ServerPlayer currentPlayer;
    // Vi använder för att hämta frågor
    private final QuestionManager questionManager = new QuestionManager();
    private final PropertiesManager propertiesManager = new PropertiesManager();
    // Håller reda på spelets nuvarande tillstånd
    private final GameState gameState = new GameState();


    public ServerSideGame(ServerPlayer player1, ServerPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.player1.setOpponent(player2);
        this.player2.setOpponent(player1);
    }

    private void informPlayerAboutGameProperties(ServerPlayer player) {
        int numberOfQuestions = propertiesManager.antalFragor();
        int rounds = propertiesManager.antalOmgangar();
        player.send("GAME_INFORMATION");
        player.send("ROUNDS " + rounds);
        player.send("QUESTIONS " + numberOfQuestions);
    }

    private void setPlayerName(ServerPlayer player) {
        // Promptar klienten till att be om användarens namn
        player.send("ENTER_NAME");
        // Tar emot användarens meddelande
        String command = player.receive();
        // Eftersom det kommer börja med NAME så börjar namnet på index 5
        String playerName = command.substring(5);
        // Sparar spelarens namn
        player.setPlayerName(playerName);
        player.send("YOUR_NAME " + playerName);
        // Skickar spelarens namn till motståndarens klient för deras GUIs skull
        player.getOpponent().send("OPPONENT_NAME " + playerName);
    }

    private void askQuestion() {
        // Förutsätter att en kategori är vald
        Category selectedCategory = gameState.getCurrentCategory();
        // Hämta en slumpmässig fråga från vald kategori
        Question question = questionManager.getQuestionByCategory(selectedCategory);
        // Skicka frågan till spelaren
        currentPlayer.send("QUESTION " + question.getQuestion());
        // Hämta ut frågans svarsalternativ
        String[] answers = question.getAnswers();
        for (String answer : answers) {
            // Skicka varje alternativ till spelaren som separata meddelanden
            currentPlayer.send(answer);
        }
        int correctAnswer = question.getCorrectAnswer();
        currentPlayer.send(String.valueOf(correctAnswer));
        // Nu väntar vi på spelarens svar
        String answerMessage = currentPlayer.receive();
        // Eftersom spelarens svar kommer börja med ANSWER vet vi att svaret är på index 7
        int answerIndex = Integer.parseInt(answerMessage.substring(7));
        // Nu kollar vi om spelarens svar (0, 1, 2 eller 3) stämmer med facit
        Answer answer = answerIndex == question.getCorrectAnswer() ? Answer.CORRECT : Answer.INCORRECT;
        // Uppdaterar spelet med användarens svar så att det sparas undan till senare
        gameState.updateAnswer(currentPlayer.getPlayerNumber(), answer);
        currentPlayer.send("ANSWER_RESULT " + answer);
        currentPlayer.getOpponent().send("OPPONENT_RESULT " + answer);
    }

    //Skapar upp
    public void doGame() {
        informPlayerAboutGameProperties(player1);
        informPlayerAboutGameProperties(player2);
        setPlayerName(player1);
        setPlayerName(player2);

        int numberOfQuestionAsked = 0;
        while (true) {
            // Om vi är på en ny runda!
            if(gameState.isNewRound()) {
                // Kollar vi först om spelet är slut
                if (gameState.gameIsOver()) {
                    // LOGIK FÖR NÄR SPELET ÄR SLUT
                    int winner = gameState.getWinner();
                    if (winner == 1) {
                        currentPlayer.send("GAME_OVER" + " Winner is: " + player1.getPlayerName());
                        currentPlayer.getOpponent().send("GAME_OVER" + " Winner is: " + player1.getPlayerName());
                    } else if (winner == 2) {
                        currentPlayer.send("GAME_OVER" + " Winner is: " + player2.getPlayerName());
                        currentPlayer.getOpponent().send("GAME_OVER" + " Winner is: " + player2.getPlayerName());
                    } else {
                        currentPlayer.send("GAME_OVER" + " It's a tie!");
                        currentPlayer.getOpponent().send("GAME_OVER" + " It's a tie!");
                    }
                    player1.send("QUIT");
                    player2.send("QUIT");
                    break;
                } else {
                    // Om spelet INTE är slut OCH vi är på en ny runda!
                    // DAGS ATT VÄLJA NY KATEGORI
                    //numberOfQuestionAsked = 1;
                    currentPlayer.send("SELECT_CATEGORY");
                    String categoryMessage = currentPlayer.receive();  // ta emot från klient
                    // Eftersom meddelandet börjar med CATEGORY_SELECTED kommer kategorin börja på index 18
                    Category category = Category.valueOf(categoryMessage.substring(18));
                    // Informera current player om att kategorin är vald
                    currentPlayer.send("CATEGORY_SELECTED " + category);
                    // Informera motståndaren om att kategorin är vald
                    currentPlayer.getOpponent().send("CATEGORY_SELECTED " + category);
                    // Uppdaterar gamestate med vald kategori
                    // kommer att behöva ha koll på detta när vi ska hämta frågor
                    gameState.setCurrentCategory(category);
                    askQuestion();
                }
            } else {
                // PRESENTERA EN FRÅGA
                askQuestion();
            }
            if(!gameState.isNewRound()) {
                currentPlayer = currentPlayer.getOpponent();
            }
            // int antalFragor = propertiesManager.antalFragor() * 2; // vi har två spelare tar därför gånger 2
            //
            //                while (numberOfQuestionAsked <= antalFragor ) {
            //                    numberOfQuestionAsked++;
            //                    askQuestion();
            //                    currentPlayer = currentPlayer.getOpponent();
            //                }
        }
    }
}

