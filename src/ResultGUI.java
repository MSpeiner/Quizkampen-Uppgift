import javax.swing.*;
import javax.swing.border.Border;
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

    JButton continueGame = new JButton("Continue Game");


    JLabel player1 = new JLabel("Player one" + " Victories: ");
    JLabel player2 = new JLabel("\t" + "Player two" + " Victories: ");

    int player1WinCounter = 0;
    String winCounterP1String = Integer.toString(player1WinCounter);
    int player2WinCounter = 0;
    String winCounterP2String = Integer.toString(player2WinCounter);

   JButton  round1Question1Player1 =new JButton();
   JButton  round1Question1Player2 =new JButton();

   JButton  round1Question2Player1 =new JButton();
   JButton round1Question2Player2 = new JButton();

   JButton round2Question1Player1 = new JButton();
   JButton round2Question1Player2 = new JButton();

   JButton round2Question2Player1 = new JButton();
   JButton round2Question2Player2 = new JButton();


    public ResultGUI() {

        setTitle("Quizkampen");
        this.add(jpR);
        jpR.setLayout(new BorderLayout());

        jpR.add(south, BorderLayout.SOUTH);
        jpR.add(center, BorderLayout.CENTER);
        jpR.add(north, BorderLayout.NORTH);

        north.setLayout(new GridLayout(1, 2));
        player1.setText("Player one" + " Victories: " + winCounterP1String);
        player2.setText("Player Two" + " Victories: " + winCounterP2String);

        north.add(player1);
        north.add(player2);

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
        south.add(continueGame);

        continueGame.addActionListener(this);

        pack();
        setSize(350, 300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == continueGame) {
            CategoryViewGUI cvg = new CategoryViewGUI();
            setVisible(false);

            /*jpR.removeAll();
            jpR.revalidate();
            jpR.repaint();
             */

            //lägg till funktionallitet för att känna av när det är min tur, återuppta då tidigare spel via protokoll
        }
    }
}
