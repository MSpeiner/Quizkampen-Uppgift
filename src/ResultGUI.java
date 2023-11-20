import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class ResultGUI extends JFrame{
    JPanel jpR = new JPanel();
    JPanel west = new JPanel();
    JPanel east = new JPanel();
    JPanel south = new JPanel();

    JLabel banner = new JLabel("Resultat!");

    JLabel roundOne = new JLabel("Round 1");
    JLabel roundTwo = new JLabel("Round 2");

    JButton surrender = new JButton("Surrender!");
    JButton continueGame = new JButton("Continue Game");

    JLabel player1TotalWins = new JLabel();
    JLabel player2TotalWins = new JLabel();

    int player1WinCounter = 0;
    int player2WinCounter = 0;

    JRadioButton question1 = new JRadioButton();
    JRadioButton question2 = new JRadioButton();
    ButtonGroup roundResult = new ButtonGroup();

    public ResultGUI() {
     this.add(jpR);
     jpR.setLayout(new BorderLayout());


    }
}
