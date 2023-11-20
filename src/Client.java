import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// Denna Client-klass kan enbart startas efter att ServerListerner är igång.
public class Client extends JFrame {
    public Client() {
        try (
                Socket s = new Socket("127.0.0.1", 44444);
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Client Start!");
            String send;

            while ((send = userInput.readLine()) != null) {
                System.out.println("Send Message to Server: " + send);
                out.println(send);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Client c = new Client();
    }
}

