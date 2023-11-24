import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultGUI extends JFrame implements ActionListener {
    JPanel jpR = new JPanel();
    JPanel south = new JPanel();
    JPanel center = new JPanel();
    JPanel north = new JPanel();

    JLabel roundOne = new JLabel("Round 1");
    JLabel roundTwo = new JLabel("Round 2");

    JButton continueGame = new JButton("Continue");


    JLabel playerOne = new JLabel("Player one");
    JLabel playerTwo = new JLabel("Player two");

    JLabel wins1 = new JLabel("Player 1 amount of wins: ");
    JLabel wins2 = new JLabel("Player 2 amount of wins: ");

    JButton round1Question1Player1 = new JButton();
    JButton round1Question1Player2 = new JButton();

    JButton round1Question2Player1 = new JButton();
    JButton round1Question2Player2 = new JButton();

    JButton round2Question1Player1 = new JButton();
    JButton round2Question1Player2 = new JButton();

    JButton round2Question2Player1 = new JButton();
    JButton round2Question2Player2 = new JButton();


    public ResultGUI() {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;

        setTitle("Quizkampen");
        this.add(jpR);
        jpR.setLayout(new BorderLayout());

        jpR.add(south, BorderLayout.SOUTH);
        jpR.add(center, BorderLayout.CENTER);
        jpR.add(north, BorderLayout.NORTH);

        north.setLayout(new GridLayout(1, 2));

        north.add(playerOne);
        north.add(playerTwo);

        center.setLayout(new GridLayout(2, 6));
        center.add(round1Question1Player1);
        center.add(round1Question2Player1);
        center.add(roundOne);
        center.add(round1Question1Player2);
        center.add(round1Question2Player2);

        center.add(round2Question1Player1);
        center.add(round2Question2Player1);
        center.add(roundTwo);
        center.add(round2Question1Player2);
        center.add(round2Question2Player2);

        south.setLayout(new FlowLayout());
        south.add(wins1);
        south.add(continueGame);
        south.add(wins2);

        continueGame.addActionListener(this);

        pack();
        setSize(450, 300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == continueGame) {
            dispose();
        }
    }
}
