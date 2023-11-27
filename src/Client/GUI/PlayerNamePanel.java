package Client.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PlayerNamePanel extends JPanel {

    private final JTextField inputField;

    public PlayerNamePanel(ActionListener submitAction) {
        setLayout(new FlowLayout());

        JLabel label = new JLabel("Enter your name:");
        inputField = new JTextField(20);
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(submitAction);

        add(label);
        add(inputField);
        add(submitButton);
    }

    public String getPlayerName() {
        return inputField.getText();
    }
}