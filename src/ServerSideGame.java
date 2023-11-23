import javax.swing.*;
import java.awt.*;

class ServerSideGame {

    ServerPlayer player1;
    ServerPlayer player2;
    ServerPlayer currentPlayer;
    // Vi använder för att hämta frågor
    private final QuestionManager questionManager = new QuestionManager();
    // Håller reda på spelets nuvarande tillstånd
    private final GameState gameState = new GameState();
    public int winCounterPlayer1 = 0;
    public int winCounterPlayer2 = 0;

    public ServerSideGame(ServerPlayer player1, ServerPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.player1.setOpponent(player2);
        this.player2.setOpponent(player1);
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
        // Skickar spelarens namn till motståndarens klient för deras GUIs skull
        player.getOpponent().send("OPPONENT_NAME " + playerName);
    }

    private void askQuestion() {
        // Förutsätter att en kategori är vald
        String selectedCategory = gameState.getCurrentCategory();
        // Hämta en slumpmässig fråga från vald kategori
        Question question = questionManager.getQuestionByCategory(selectedCategory);
        // Skicka frågan till spelaren
        currentPlayer.send("QUESTION + " + question.getQuestion());
        // Hämta ut frågans svarsalternativ
        String[] answers = question.getAnswers();
        for (String answer : answers) {
            // Skicka varje alternativ till spelaren som separata meddelanden
            currentPlayer.send(answer);
        }
        // Nu väntar vi på spelarens svar
        String answerMessage = currentPlayer.receive();
        // Eftersom spelarens svar kommer börja med ANSWER vet vi att svaret är på index 7
        int answerIndex = Integer.parseInt(answerMessage.substring(7));
        // Nu kollar vi om spelarens svar (0, 1, 2 eller 3) stämmer med facit
        Answer answer = answerIndex == question.getCorrectAnswer() ? Answer.CORRECT : Answer.INCORRECT;
        // Uppdaterar spelet med användarens svar så att det sparas undan till senare
        gameState.updateAnswer(currentPlayer.getPlayerNumber(), answer);
        currentPlayer.send("ANSWER_RESULT " + (answer == Answer.CORRECT ? "Correct" : "Incorrect"));

        // TODO: informera spelaren och motståndaren om huruvida spelaren svarade rätt
        // Kom ihåg att hantera det meddelandet hos klienten med något protokoll.
        // T.ex. OPPONENT_ANSWERED CORRECT och YOU_ANSWERED INCORRECT
    }

    //Skapar upp
    private void setResultBoard(Answer[] player1Result, Answer[] player2Result) {
        ResultGUI rG = new ResultGUI();
        int scoreCounter1 = 0;
        int scoreCounter2 = 0;

        if (player1Result == gameState.getPlayer1Answers()) {
            String[] stringArrayAnswers = gameState.convertAnswersToStringArray(player1Result);
            gameState.sendableAnswers(stringArrayAnswers);

            if (gameState.answer1 == "CORRECT") {
                rG.round1Question1Player1.setBackground(Color.green);
                scoreCounter1++;
            } else if (gameState.answer1 == "INCORRECT") {
                rG.round1Question1Player1.setBackground(Color.red);
            }
            if (gameState.answer2 == "CORRECT") {
                rG.round1Question2Player1.setBackground(Color.green);
                scoreCounter1++;
            } else if (gameState.answer2 == "INCORRECT") {
                rG.round1Question2Player1.setBackground(Color.red);
            }
            if (gameState.answer3 == "CORRECT") {
                rG.round2Question1Player1.setBackground(Color.green);
                scoreCounter1++;
            } else if (gameState.answer3 == "INCORRECT") {
                rG.round2Question1Player1.setBackground(Color.red);
            }
            if (gameState.answer4 == "CORRECT") {
                rG.round2Question2Player1.setBackground(Color.green);
                scoreCounter1++;
            } else if (gameState.answer4 == "INCORRECT") {
                rG.round2Question2Player1.setBackground(Color.red);
            }
        }
        if (player2Result == gameState.getPlayer2Answers()) {
            String[] stringArrayAnswers2 = gameState.convertAnswersToStringArray(player2Result);
            gameState.sendableAnswers(stringArrayAnswers2);

            if (gameState.answer1 == "CORRECT") {
                rG.round1Question1Player2.setBackground(Color.green);
                scoreCounter2++;
            } else if (gameState.answer1 == "INCORRECT") {
                rG.round1Question1Player2.setBackground(Color.red);
            }
            if (gameState.answer2 == "CORRECT") {
                rG.round1Question2Player2.setBackground(Color.green);
                scoreCounter2++;
            } else if (gameState.answer2 == "INCORRECT") {
                rG.round1Question2Player2.setBackground(Color.red);
            }
            if (gameState.answer3 == "CORRECT") {
                rG.round2Question1Player2.setBackground(Color.green);
                scoreCounter2++;
            } else if (gameState.answer3 == "INCORRECT") {
                rG.round2Question1Player2.setBackground(Color.red);
            }
            if (gameState.answer4 == "CORRECT") {
                rG.round2Question2Player2.setBackground(Color.green);
                scoreCounter2++;
            } else if (gameState.answer4 == "INCORRECT") {
                rG.round2Question2Player2.setBackground(Color.red);
            }
        }
        if (scoreCounter1 > scoreCounter2) {
            JOptionPane.showMessageDialog(null, "Player One has won!");
            winCounterPlayer1++;
        } else if (scoreCounter2 > scoreCounter1) {
            JOptionPane.showMessageDialog(null, "Player Two has won!");
            winCounterPlayer2++;
        } else if (scoreCounter1 == scoreCounter2) {
            JOptionPane.showMessageDialog(null, "It's a tie!");
        }
        String wins1String = Integer.toString(winCounterPlayer1);
        rG.wins1.setText("Player 1 amount of wins: " + wins1String);
        String wins2String = Integer.toString(winCounterPlayer2);
        rG.wins2.setText("Player 2 amount of wins: " + wins2String);
    }

    public void doGame() {
        setPlayerName(player1);
        setPlayerName(player2);

        while (true) {
            // Om vi är på en ny runda!
            if (gameState.isNewRound()) {
                // Kollar vi först om spelet är slut
                if (gameState.gameIsOver()) {
                    // LOGIK FÖR NÄR SPELET ÄR SLUT
                    int player1Score = gameState.getCorrectForPlayer1();
                    int player2Score = gameState.getCorrectForPlayer2();
                    System.out.println(player1.getPlayerName() + " fick " + player1Score + " rätt!");
                    System.out.println(player2.getPlayerName() + " fick " + player2Score + " rätt!");
                    int winner = gameState.getWinner();
                    if (winner == 1) {
                        System.out.println("Vinnaren är " + player1.getPlayerName());
                    } else if (winner == 2) {
                        System.out.println("Vinnaren är " + player2.getPlayerName());
                    } else {
                        System.out.println("Det blev lika!");
                    }

                    setResultBoard(gameState.getPlayer1Answers(), gameState.getPlayer2Answers());

                    player1.send("QUIT");
                    player2.send("QUIT");
                    break;
                } else {
                    // Om spelet INTE är slut OCH vi är på en ny runda!
                    // DAGS ATT VÄLJA NY KATEGORI
                    currentPlayer.send("SELECT_CATEGORY");
                    String categoryMessage = currentPlayer.receive();  // ta emot från klient
                    // Eftersom meddelandet börjar med CATEGORY_SELECTED kommer kategorin börja på index 18
                    String category = categoryMessage.substring(18);
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
            currentPlayer = currentPlayer.getOpponent();
        }
    }
}