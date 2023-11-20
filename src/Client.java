import javax.swing.*;

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
