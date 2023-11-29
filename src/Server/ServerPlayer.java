package Server;

import Game.GameState;

import java.io.*;
import java.net.Socket;

public class ServerPlayer extends Thread {
    int playerNumber;
    ServerPlayer opponent;
    Socket socket;
    BufferedReader input;
    PrintWriter output;
    String playerName;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

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

    @Override
    public void run() {
        // Implement the logic for your thread here
    }
}
