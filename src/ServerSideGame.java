/**
 * A two-player game.
 */
class ServerSideGame {

    ServerPlayer player1;
    ServerPlayer player2;
    ServerPlayer currentPlayer;



    public ServerSideGame(ServerPlayer player1, ServerPlayer player2){
        this.player1=player1;
        this.player2=player2;
        this.currentPlayer = player1;
        this.player1.setOpponent(player2);
        this.player2.setOpponent(player1);
    }

    /**
     * A game state has two arrays representing the answers from the two players
     */
    private GameState gameState = new GameState();


    /**
     * Returns whether the current state of the board is such that one
     * of the players is a winner.
     */
    public boolean hasWinner() {
        return gameState.hasWinner();
    }

    /**
     * Returns whether there are no more empty squares.
     */
    public boolean gameIsOver() {
        return gameState.gameIsOver();
    }

    /*
    The game loop, receiving the squares clicked by clients
    Checking if a client has won, lost or if there is a tie.
     */

    public void doGame(){

        player1.send("WELCOME PLAYER " + player1.playerNumber);
        player1.send("MESSAGE Waiting for opponent to connect");

        player2.send("WELCOME PLAYER " + player2.playerNumber);
        player2.send("MESSAGE All players connected");

        player2.send("MESSAGE Your move");

        String command;
        currentPlayer = player1;

        while (true) {
            command = currentPlayer.receive();  //ta emot från klient

            if (command.startsWith("X")) { // VALIDATE INPUT
                int location = Integer.parseInt(command.substring(5)); // GET DATA FROM MESSAGE

                if (location == 0) {  // VALIDATE DATA

                    // LOGIC FOR UPDATING GAME STATE BASED ON DATA

                    currentPlayer = currentPlayer.getOpponent();  //BYTER SPELARE

                } else {
                    currentPlayer.send("MESSAGE ?");
                }

            } else if (command.startsWith("QUIT")) {
                return;
            }
        }
    }

}