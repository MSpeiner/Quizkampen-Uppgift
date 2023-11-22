import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * A client for the TicTacToe game, modified and extended from the
 * class presented in Deitel and Deitel "Java How to Program" book.
 * I made a bunch of enhancements and rewrote large sections of the
 * code. In particular I created the TTTP (Tic Tac Toe Protocol)
 * which is entirely text based. Here are the strings that are sent:
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
public class Client {


    private static int PORT = 8901;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    private boolean blocked = false;

    /**
     * Constructs the client by connecting to a server, laying out the
     * GUI and registering GUI listeners.
     */
    public Client(String serverAddress) throws Exception {

        socket = new Socket(serverAddress, PORT);
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        // Layout GUI

    }

    /**
     * The main thread of the client will listen for messages
     * from the server. The first message will be a "WELCOME"
     * message in which we receive our mark. Then we go into a
     * loop listening for "VALID_MOVE", "OPPONENT_MOVED", "VICTORY",
     * "DEFEAT", "TIE", "OPPONENT_QUIT or "MESSAGE" messages,
     * and handling each message appropriately. The "VICTORY",
     * "DEFEAT" and "TIE" ask the user whether or not to play
     * another game. If the answer is no, the loop is exited and
     * the server is sent a "QUIT" message. If an OPPONENT_QUIT
     * message is recevied then the loop will exit and the server
     * will be sent a "QUIT" message also.
     */
    public void play() throws Exception {
        String response;
        char playerNumber = 'S';
        char opponentPlayerNumber = 'P';
        try {
            System.out.println("Väntar på svar från servern");
            response = in.readLine();

            if (response.startsWith("WELCOME")) {
                playerNumber = response.charAt(8);
                if (playerNumber == '2') blocked = true;
                opponentPlayerNumber = (playerNumber == '1' ? '2' : '1');

            }
            while (true) {
                response = in.readLine();
                System.out.println(response);
                if(response.equals("QUIT")){
                    break;
                }
            }
            out.println("QUIT");
        }
        finally {
            socket.close();
        }
    }

    //private boolean wantsToPlayAgain() {
        // int response = JOptionPane.showConfirmDialog(frame,
        //        "Want to play again?",
        //        "Tic Tac Toe is Fun Fun Fun",
        //        JOptionPane.YES_NO_OPTION);
        //frame.dispose();
        //return response == JOptionPane.YES_OPTION;
    //}


    /**
     * Runs the client as an application.
     */
    public static void main(String[] args) throws Exception {
        while (true) {
            String serverAddress = (args.length == 0) ? "localhost" : args[1];
            Client client = new Client(serverAddress);

            client.play();
            //if (!client.wantsToPlayAgain()) {
            //    break;
            //}
        }
    }
}