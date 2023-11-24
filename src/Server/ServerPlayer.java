    package Server;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.io.PrintWriter;
    import java.net.Socket;

    public class ServerPlayer extends Thread {
        int playerNumber;
        ServerPlayer opponent;
        Socket socket;
        BufferedReader input;
        PrintWriter output;

        public String getPlayerName() {
            return playerName;
        }

        public void setPlayerName(String playerName) {
            this.playerName = playerName;
        }

        String playerName;

        public ServerPlayer(Socket socket, int playerNumber) {
            this.socket = socket;
            this.playerNumber = playerNumber;
            try {
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                output = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                System.out.println("Player died: " + e);
            }
        }

        public void setPlayerNumber(int playerNumber) {
            this.playerNumber = playerNumber;
        }

        public int getPlayerNumber() {
            return playerNumber;
        }

        public void send(String message) {
            output.println(message);
        }

        public String receive() {
            try {
                return input.readLine();
            } catch (IOException e) {
                System.out.println("Player " + playerNumber + " could not receive data " + e);
                throw new RuntimeException(e);
            }
        }

        public void setOpponent(ServerPlayer opponent) {
            this.opponent = opponent;
        }

        public ServerPlayer getOpponent() {
            return opponent;
        }

        public void handleMessageFromGUI(String message) {
            switch (message) {
                case "GET_NAME":
                    send(playerName);
                    break;
                // Add other cases as needed
            }
        }

        @Override
        public void run() {
            // Implement the logic for your thread here
        }
    }
