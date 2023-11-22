import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) throws Exception {
        try (ServerSocket listener = new ServerSocket(8901)) {
            System.out.println("Quizkampservern rullar!");
                // Kopplar till en klient från spelare 1
                ServerPlayer player1 = new ServerPlayer(listener.accept(), 1);
                // Kopplar till en klient från spelare 2
                ServerPlayer player2 = new ServerPlayer(listener.accept(), 2);
                // Skapar ett spel med spelare 1 och 2
                ServerSideGame game = new ServerSideGame(player1, player2);
                // Sätter igång spelet!
                game.doGame();
        }
    }
}

