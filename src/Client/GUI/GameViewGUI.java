package Client.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameViewGUI extends JPanel {
    private final JButton[] answerButtons = new JButton[4];

    public GameViewGUI(String category, String question, String[] answers, ActionListener answerAction) {
        setLayout(new BorderLayout());

        // Category label
        JLabel categoryLabel = new JLabel(category, JLabel.CENTER);

        // Question label
        JLabel questionLabel = new JLabel("<html>" + question + "</html>"); // HTML for word-wrap
        questionLabel.setHorizontalAlignment(JLabel.LEFT);

        // Answer buttons panel
        JPanel answersPanel = new JPanel(new GridLayout(2, 2));

        for (int i = 0; i < 4; i++) {
            answerButtons[i] = new JButton(answers[i]);
            answerButtons[i].addActionListener(answerAction);
            answersPanel.add(answerButtons[i]);
        }

        add(categoryLabel, BorderLayout.NORTH);
        add(questionLabel, BorderLayout.CENTER);
        add(answersPanel, BorderLayout.SOUTH);
    }

    public void changeButtonColor(JButton button, Color color) {
        button.setBackground(color);
        button.setOpaque(true);
        button.setBorderPainted(false);
    }

    public void setAnswerButtonsEnabled(boolean enabled) {
        for (JButton button : answerButtons) {
            button.setEnabled(enabled);
        }
    }

    public int getSelectedAnswerIndex(ActionEvent e) {
        for (int i = 0; i < answerButtons.length; i++) {
            if (e.getSource() == answerButtons[i]) {
                return i;
            }
        }
        return -1; // or throw an exception
    }
}
