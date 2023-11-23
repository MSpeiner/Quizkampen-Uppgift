class ServerSideGame {

    ServerPlayer player1;
    ServerPlayer player2;
    ServerPlayer currentPlayer;
    // Vi använder för att hämta frågor
    private final QuestionManager questionManager = new QuestionManager();
    // Håller reda på spelets nuvarande tillstånd
    private final GameState gameState = new GameState();

    public ServerSideGame(ServerPlayer player1, ServerPlayer player2){
        this.player1=player1;
        this.player2=player2;
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

    public void doGame(){
        setPlayerName(player1);
        setPlayerName(player2);

        while (true) {
            // Om vi är på en ny runda!
            if(gameState.isNewRound()){
                // Kollar vi först om spelet är slut
                if(gameState.gameIsOver()){
                    // LOGIK FÖR NÄR SPELET ÄR SLUT
                    int player1Score = gameState.getCorrectForPlayer1();
                    int player2Score = gameState.getCorrectForPlayer2();
                    System.out.println(player1.getPlayerName() + " fick " + player1Score + " rätt!");
                    System.out.println(player2.getPlayerName() + " fick " + player2Score + " rätt!");
                    int winner = gameState.getWinner();
                    if(winner == 1){
                        System.out.println("Vinnaren är " + player1.getPlayerName());
                    }
                    else if(winner == 2){
                        System.out.println("Vinnaren är " + player2.getPlayerName());
                    } else {
                        System.out.println("Det blev lika!");
                    }
                    player1.send("QUIT");
                    player2.send("QUIT");
                    break;
                } else {
                    // Om spelet INTE är slut OCH vi är på en ny runda!
                    // DAGS ATT VÄLJA NY KATEGORI
                    currentPlayer.send("SELECT_CATEGORY");
                    String categoryMessage = currentPlayer.receive();  // ta emot från klient
                    // Eftersom meddelandet börjar med CATEGORY_SELECTED kommer kategorin börja på index 18
                    String category =  categoryMessage.substring(18);
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