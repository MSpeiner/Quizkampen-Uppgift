package Client.GUI;

import javax.swing.*;
import java.awt.*;

class WaitingForPlayerPanel extends JPanel {
    public WaitingForPlayerPanel() {
        setLayout(new GridBagLayout());
        setBackground(Color.BLUE);
        JLabel waitingLabel = new JLabel("Waiting for other player...");
        waitingLabel.setHorizontalAlignment(JLabel.CENTER);
        waitingLabel.setForeground(Color.WHITE);
        add(waitingLabel);
    }
}
