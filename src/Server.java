import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class Server extends Thread {
    Socket s;
    String playerOne;
    String playerTwo;
    static int playerCounter = 0;



    public Server(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try (
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()))
        ) {
            System.out.println("Client connected");
            playerCounter++;

            if (playerCounter == 1) {
                playerOne = JOptionPane.showInputDialog(null, "Enter your username");
                Players p = new Players();
                p.setName(playerOne);
                JOptionPane.showMessageDialog(null, "Waiting for player to connect...");
                StartmenuGui sMG = new StartmenuGui(p);
            } else if (playerCounter >= 2) {
                playerTwo = JOptionPane.showInputDialog(null, "Enter your username");
                Players p = new Players();
                p.setName(playerTwo);
                StartmenuGui sMG = new StartmenuGui(p);
            }

            String fromClient;
            while ((fromClient = in.readLine()) != null) {
                System.out.println("From Client: " + fromClient);
                out.println("Got: " + fromClient + " from client");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}