
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {

    public ServerListener() {
        try (ServerSocket ss = new ServerSocket(44444)) {

            while (true) {
                Socket s = ss.accept();
                Server serv = new Server(s);
                serv.start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ServerListener ss = new ServerListener();
    }
}
