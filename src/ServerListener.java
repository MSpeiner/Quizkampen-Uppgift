import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {
    public int serverCounter = 0;

    public ServerListener() {
        try (ServerSocket ss = new ServerSocket(44444)) {
            while (true) {
                Socket s = ss.accept();
                serverCounter++;
                Server serv = new Server(s);
                serv.start();
                System.out.println(serverCounter);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ServerListener ss = new ServerListener();
    }
}