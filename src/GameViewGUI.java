import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class GameViewGUI extends JFrame {
    private final JPanel mainPanel = new JPanel(new BorderLayout());
    private final JLabel questionLabel = new JLabel("Waiting for question...");
    private final JLabel categoryLabel = new JLabel("Category: ");
    private final List<JButton> answerButtons = new ArrayList<>();
    private final QuestionManager questionManager = new QuestionManager();
    private Questions currentQuestion;

    private String category;
    private int numberOfQuestions = 1;

    public GameViewGUI(String category) {
        this.category = category;
        loadQuestions();
        setupUI();
        displayNextQuestion();
    }

    public GameViewGUI() {
    }

    private void loadQuestions() {
        String myPath = "src/TextFiles/" + getCategory() + ".txt";
        questionManager.loadQuestions(myPath);
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

    private void displayNextQuestion() {
        currentQuestion = questionManager.getRandomQuestion();
        if (numberOfQuestions <= 2) {
            if (currentQuestion != null) {
                questionLabel.setText(currentQuestion.getQuestion());
                categoryLabel.setText("Category: " + currentQuestion.getCategory());

                for (int i = 0; i < answerButtons.size(); i++) {
                    answerButtons.get(i).setText(currentQuestion.getAnswers()[i]);
                    answerButtons.get(i).putClientProperty("answer_index", i);
                }
            }
            numberOfQuestions++;
        } else {
            JOptionPane.showMessageDialog(this, "End of Quiz!");
            dispose();
            ResultGUI r = new ResultGUI();
        }
    }

    private void buttonClicked(ActionEvent event) {
        JButton clickedButton = (JButton) event.getSource();
        int selectedAnswer = (int) clickedButton.getClientProperty("answer_index");

        if (currentQuestion.getCorrectAnswer() == selectedAnswer) {
            JOptionPane.showMessageDialog(this, "Correct!");
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect! The correct answer was: "
                    + currentQuestion.getAnswers()[currentQuestion.getCorrectAnswer()]);
        }
        displayNextQuestion();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameViewGUI::new);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
