
import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;

public class ServerListener {
    public int serverCounter = 0;
    private String playerOne;
    private String playerTwo;


    public ServerListener() {
        try (ServerSocket ss = new ServerSocket(44444)) {

            while (true) {
                Socket s = ss.accept();
                serverCounter++;
                if (serverCounter == 2) {
                    Server serv = new Server(s);
                    serv.start();
                    playerTwo = JOptionPane.showInputDialog(null, "Enter your username");


                } else if (serverCounter < 2) {
                    Server serv = new Server(s);
                    playerOne = JOptionPane.showInputDialog(null, "Enter your username");
                    JOptionPane.showMessageDialog(null, "Waiting for player to connect, please wait!");
                    serv.start();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ServerListener ss = new ServerListener();
    }
}
