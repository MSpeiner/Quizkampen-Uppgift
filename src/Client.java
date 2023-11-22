import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client {


    private static int PORT = 8901;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    private boolean blocked = false;


    public Client(String serverAddress) throws Exception {

        socket = new Socket(serverAddress, PORT);
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        // out är precis som System.out, båda är printwriters, en är till terminalen, den andra är till servern
        // vi är kopplade till
    }

    public void play() throws Exception {
        // Meddelanden från servern
        String response;
        // Används till GUI:t
        String playerName = "";
        String opponentName = "";
        // Används till GUI:t
        String currentCategory = "";
        try {
            while (true) {
                // TA EMOT MEDDELANDE FRÅN SERVERN
                response = in.readLine();
                // OM DET BÖRJAR MED ENTER NAME
                if(response.startsWith("ENTER_NAME")){
                    // Ber vi användaren skriva in sitt namn
                    playerName = JOptionPane.showInputDialog(null, "Enter your username");
                    // Sen skickar vi det till servern med "NAME" först
                    out.println("NAME " + playerName);
                }
                // OM DET BÖRJAR MED OPPONENT_NAME
                if(response.startsWith("OPPONENT_NAME")){
                    // Sparar vi motståndarens namn så att vi kan använda det i vårat GUI
                    opponentName = response.substring(14);
                }
                // OM DET BÖRJAR MED CATEGORY_SELECTED
                if(response.startsWith("CATEGORY_SELECTED")){
                    // Hämtar vi ut den valda kategorin (vid index 18)
                    // och sparar ner den så att vi kan använda den till GUIt
                    currentCategory = response.substring(18);
                }
                // OM DET BÖRJAR MED QUESTION
                if(response.startsWith("QUESTION")){
                    // NU HÄNDER DET GREJER!
                    // Först tar vi själva frågesträngen som börjar vid index 9
                    String question = response.substring(9);
                    // Sen kommer servern DIREKT skicka 4 svarsalternativ som vi sparar ner
                    String answer1 = in.readLine();
                    String answer2 = in.readLine();
                    String answer3 = in.readLine();
                    String answer4 = in.readLine();
                    // Sen skapar vi en GameViewGUI som ansvarar för att presentera frågor till användaren
                    // Vi stoppar in vår out så att GUI:t kan skicka meddelande till Servern om vilket svar vi
                    // valde
                    GameViewGUI gameView = new GameViewGUI(out, currentCategory);
                    // Skapa en array av våra svarsalternativ
                    String[] answers = {answer1, answer2, answer3, answer4};
                    // Skicka in frågan tillsammans med svarsalternativen
                    // till metoden som ansvarar för att presentera frågan till användaren
                    gameView.displayNextQuestion(question, answers);
                }
                if(response.startsWith("SELECT_CATEGORY")){
                    // Stoppar in out så att GUI:t kan skicka meddelanden till servern
                    CategoryViewGUI viewGUI = new CategoryViewGUI(out);
                }
                // TODO: Skriva if-satser för att hantera meddelanden från servern som
                // informerar oss om huruvida vi svarade rätt eller fel på frågan användaren
                // samt information om huruvida motståndaren svarade rätt eller fel på sin fråga
                // TODO: Skriva en if-sats där servern meddelar användaren att den väntar på svar
                // från motståndaren, så att klienten kan rita ut någonting att titta på sålänge
                // t.ex. hur det står mellan motståndaren och användaren (ResultGUI)
                if(response.equals("QUIT")){
                    System.out.println("Vi slutar nu");
                    break;
                }
            }
        }
        finally {
            socket.close();
        }
    }



    /**
     * Runs the client as an application.
     */
    public static void main(String[] args) throws Exception {
        String serverAddress = (args.length == 0) ? "localhost" : args[1];
        Client client = new Client(serverAddress);
        client.play();
    }
}