package Client.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuestionViewPanel extends JPanel {
    private final JButton[] answerButtons = new JButton[4];

    public QuestionViewPanel(String category, String question, String[] answers, ActionListener answerAction) {
        setLayout(new BorderLayout());

        // Category label
        JLabel categoryLabel = new JLabel(category, JLabel.CENTER);

        // Question label
        JLabel questionLabel = new JLabel("<html><div style='text-align: center;'>" + question + "</div></html>");
        questionLabel.setHorizontalAlignment(JLabel.CENTER);

        // Answer buttons panel
        JPanel answersPanel = new JPanel(new GridLayout(2, 2));
        Dimension buttonSize = new Dimension(100, 50);

        for (int i = 0; i < 4; i++) {
            answerButtons[i] = new RoundedCornerButton(answers[i]);
            answerButtons[i].addActionListener(answerAction);
            answerButtons[i].setPreferredSize(buttonSize); // Set the preferred size for each button
            answersPanel.add(answerButtons[i]);
        }

        add(categoryLabel, BorderLayout.NORTH);
        add(questionLabel, BorderLayout.CENTER);
        add(answersPanel, BorderLayout.SOUTH);
    }

    public void changeButtonColor(JButton button, int correctAnswer) {
        if (button.equals(answerButtons[correctAnswer])) {
            button.setBackground(Color.green);
        } else {
            button.setBackground(Color.red);
        }

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
