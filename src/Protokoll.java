import javax.swing.*;

public class Protokoll {
    private static final int WAITING_FOR_PLAYER = 0;
    private static final int PLAYERSCONNECTED = 1;
    private int state = WAITING_FOR_PLAYER;
    String playerOne;
    String PlayerTwo;
    int playerCounter = 0;


    public Protokoll() {
        if (state == WAITING_FOR_PLAYER) {
             playerOne = JOptionPane.showInputDialog(null, "Enter your username");
            JOptionPane.showMessageDialog(null,"Waiting for player to connect...");

        } else if (state == PLAYERSCONNECTED) {
            PlayerTwo = JOptionPane.showInputDialog(null, "Enter your username");
        }
    }
}
