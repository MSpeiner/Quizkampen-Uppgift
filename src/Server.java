import java.net.ServerSocket;

/**
 * A server for a network multi-player tic tac toe game.
 * Modified by Sigrun from a solution fround on the Internet.
 * Modification is using only one (implicit) thread to handle the game server side.
 * The strings that are sent in TTTP (tic tac toe protocol) are:
 *
 * Client -> Server        Server -> Client
 * ----------------------  ----------
 * MOVE <n> (0 <= n <= 8)  WELCOME <char> (char in {X, O})
 * QUIT                    VALID_MOVE
 *                         OTHER_PLAYER_MOVED <n>
 *                         VICTORY
 *                         DEFEAT
 *                         TIE
 *                         MESSAGE <text>
 *
 */
public class Server {

    /**
     * Runs the application. Pairs up clients that connect.
     */
    public static void main(String[] args) throws Exception {
        try (ServerSocket listener = new ServerSocket(8901)) {
            System.out.println("Quizkampservern rullar!");
            while (true) {
                System.out.println("HEJ 1");
                ServerPlayer player1 = new ServerPlayer(listener.accept(), '1');
                System.out.println("HEJ 2");
                ServerPlayer player2 = new ServerPlayer(listener.accept(), '2');
                System.out.println("HEJ 3");
                ServerSideGame game = new ServerSideGame(player1, player2);
                System.out.println("HEJ 4");
                game.doGame();
            }
        }
    }
}