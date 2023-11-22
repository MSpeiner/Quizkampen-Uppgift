import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class GameViewGUI extends JFrame {
    private final JPanel mainPanel = new JPanel(new BorderLayout());
    private final JLabel questionLabel = new JLabel("Waiting for question...");
    private final JLabel categoryLabel = new JLabel("Category: ");
    private final List<JButton> answerButtons = new ArrayList<>();

    private final String category;
    private final PrintWriter clientOutputStream;

    // Tar in clientOutputStream för att kunna skicka meddelanden
    // till servern när man klickar på ett svarsalternativ
    public GameViewGUI(PrintWriter clientOutputStream, String category) {
        this.category = category;
        this.clientOutputStream = clientOutputStream;
        setupUI();
    }

    private void setupUI() {
        setupNorthPanel();
        setupSouthPanel();

        this.add(mainPanel);
        pack();
        setSize(600, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setupNorthPanel() {
        JPanel northPanel = new JPanel(new GridLayout(2, 1));
        northPanel.add(categoryLabel);
        northPanel.add(questionLabel);
        mainPanel.add(northPanel, BorderLayout.NORTH);
    }

    private void setupSouthPanel() {
        JPanel southPanel = new JPanel(new GridLayout(2, 2));
        IntStream.rangeClosed(1, 4).forEach(i -> {
            JButton button = new JButton("Alt " + i);
            button.addActionListener(this::buttonClicked);
            answerButtons.add(button);
            southPanel.add(button);
        });
        mainPanel.add(southPanel, BorderLayout.SOUTH);
    }

    public void displayNextQuestion(String question, String[] answers) {
        questionLabel.setText(question);
        categoryLabel.setText("Category: " + this.category);
        for (int i = 0; i < answerButtons.size(); i++) {
            answerButtons.get(i).setText(answers[i]);
            answerButtons.get(i).putClientProperty("answer_index", i);
        }
    }

    private void buttonClicked(ActionEvent event) {
        JButton clickedButton = (JButton) event.getSource();
        int selectedAnswer = (int) clickedButton.getClientProperty("answer_index");
        clientOutputStream.println("ANSWER " + selectedAnswer);
        setVisible(false);
    }

}
