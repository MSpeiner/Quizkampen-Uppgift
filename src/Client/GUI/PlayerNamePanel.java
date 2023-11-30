package Client.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PlayerNamePanel extends JPanel {
    private final JTextField inputField;

    public PlayerNamePanel(ActionListener submitAction) {
        setLayout(new BorderLayout()); // Change layout to BorderLayout

        // Welcome Label
        JLabel welcomeLabel = new JLabel("Welcome to Quizkampen", JLabel.CENTER);
        // Set font size to 24px
        Font welcomeLabelFont = welcomeLabel.getFont();
        welcomeLabel.setFont(new Font(welcomeLabelFont.getName(), welcomeLabelFont.getStyle(), 24));
        add(welcomeLabel, BorderLayout.NORTH); // Add welcome label at the top

        // Center Panel for Name Input and Submit
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical alignment

        // Label and Input Field
        JLabel nameLabel = new JLabel("Enter your name:", JLabel.CENTER);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputField = new JTextField(20); // Columns parameter for approximate width
        inputField.setAlignmentX(Component.CENTER_ALIGNMENT); // Align text field to the center
        inputField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); // Set height to 30px

        // Submit Button
        JButton submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(submitAction);
        // Add components to the center panel
        centerPanel.add(Box.createVerticalGlue()); // This pushes everything down
        centerPanel.add(nameLabel);
        centerPanel.add(inputField);
        centerPanel.add(submitButton);
        centerPanel.add(Box.createVerticalGlue()); // This pushes everything up

        // Add center panel to main panel
        add(centerPanel, BorderLayout.CENTER);
    }

    public String getPlayerName() {
        return inputField.getText();
    }
}