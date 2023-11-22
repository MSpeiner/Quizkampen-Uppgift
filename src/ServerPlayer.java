import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


class ServerPlayer extends Thread {
    char playerNumber;
    ServerPlayer opponent;
    Socket socket;
    BufferedReader input;
    PrintWriter output;

    /**
     * Constructs a handler thread for a given socket and mark
     * initializes the stream fields, displays the first two
     * welcoming messages.
     */
    public ServerPlayer(Socket socket, char playerNumber) {
        this.socket = socket;
        this.playerNumber = playerNumber;
        try {
            input = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Player died: " + e);
        }
    }

    /*
    Sends data to client
     */
    public void send(String mess){
        output.println(mess);
    }

    /*
    Receives data from client
     */
    public String receive()  {
        try {
            return input.readLine();
        } catch (IOException e) {
            System.out.println("Player "+playerNumber+" could not receive data " + e);
            throw new RuntimeException(e);
        }
    }


    /**
     * Accepts notification of who the opponent is.
     */
    public void setOpponent(ServerPlayer opponent) {
        this.opponent = opponent;
    }

    /**
     * Returns the opponent.
     */
    public ServerPlayer getOpponent() {
        return opponent;
    }

}