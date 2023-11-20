import java.io.*;
import java.net.Socket;

public class Server extends Thread {
    Socket s;

    public Server(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try (
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()))
        ) {
            StartmenuGui sMG = new StartmenuGui();
            System.out.println("Client connected");
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