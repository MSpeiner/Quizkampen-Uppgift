package Client.GUI;

import javax.swing.*;
import java.awt.*;

class WaitingForPlayerPanel extends JPanel {
    public WaitingForPlayerPanel() {
        setLayout(new GridBagLayout());
        JLabel waitingLabel = new JLabel("Waiting for other player...");
        waitingLabel.setHorizontalAlignment(JLabel.CENTER);
        add(waitingLabel);
    }
}
