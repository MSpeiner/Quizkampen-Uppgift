package Client;

import Enums.Answer;
import Game.GameState;

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

    JButton playAgainButton = new JButton("Play again");


    JLabel playerOne = new JLabel("Player one");
    JLabel playerTwo = new JLabel("Player two");

    public JLabel wins1 = new JLabel("Player 1 amount of wins: ");
    public JLabel wins2 = new JLabel("Player 2 amount of wins: ");

    public JButton round1Question1Player1 = new JButton();
    public JButton round1Question1Player2 = new JButton();

    public JButton round1Question2Player1 = new JButton();
    public JButton round1Question2Player2 = new JButton();

    public JButton round2Question1Player1 = new JButton();
    public JButton round2Question1Player2 = new JButton();

    public JButton round2Question2Player1 = new JButton();
    public JButton round2Question2Player2 = new JButton();

    GameState gameState; // GameState gameState = new GameState();

    public ResultGUI(GameState gameState) {
        this.gameState = gameState;
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
        south.add(playAgainButton);
        south.add(wins2);
        setResultBoard(gameState.getPlayer1Answers(), gameState.getPlayer2Answers());
        playAgainButton.addActionListener(this);

        pack();
        setSize(450, 300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void setResultBoard(Answer[] player1Result, Answer[] player2Result) {
        int scoreCounter1 = 0;
        int scoreCounter2 = 0;
        int winCounterPlayer1 = 0;
        int winCounterPlayer2 = 0;

        if (player1Result == gameState.getPlayer1Answers()) {
            String[] stringArrayAnswers = gameState.convertAnswersToStringArray(player1Result);
            gameState.sendableAnswers(stringArrayAnswers);

            if (gameState.answer1 == "CORRECT" ) {
                round1Question1Player1.setBackground(Color.green);
                scoreCounter1++;
            } else if (gameState.answer1 == "INCORRECT") {
                round1Question1Player1.setBackground(Color.red);
            }
            if (gameState.answer2 == "CORRECT") {
                round1Question2Player1.setBackground(Color.green);
                scoreCounter1++;
            } else if (gameState.answer2 == "INCORRECT") {
                round1Question2Player1.setBackground(Color.red);
            }
            if (gameState.answer3 == "CORRECT") {
                round2Question1Player1.setBackground(Color.green);
                scoreCounter1++;
            } else if (gameState.answer3 == "INCORRECT") {
                round2Question1Player1.setBackground(Color.red);
            }
            if (gameState.answer4 == "CORRECT") {
                round2Question2Player1.setBackground(Color.green);
                scoreCounter1++;
            } else if (gameState.answer4 == "INCORRECT") {
                round2Question2Player1.setBackground(Color.red);
            }
        }
        if (player2Result == gameState.getPlayer2Answers()) {
            String[] stringArrayAnswers2 = gameState.convertAnswersToStringArray(player2Result);
            gameState.sendableAnswers(stringArrayAnswers2);

            if (gameState.answer1 == "CORRECT") {
                round1Question1Player2.setBackground(Color.green);
                scoreCounter2++;
            } else if (gameState.answer1 == "INCORRECT") {
                round1Question1Player2.setBackground(Color.red);
            }
            if (gameState.answer2 == "CORRECT") {
                round1Question2Player2.setBackground(Color.green);
                scoreCounter2++;
            } else if (gameState.answer2 == "INCORRECT") {
                round1Question2Player2.setBackground(Color.red);
            }
            if (gameState.answer3 == "CORRECT") {
                round2Question1Player2.setBackground(Color.green);
                scoreCounter2++;
            } else if (gameState.answer3 == "INCORRECT") {
                round2Question1Player2.setBackground(Color.red);
            }
            if (gameState.answer4 == "CORRECT") {
                round2Question2Player2.setBackground(Color.green);
                scoreCounter2++;
            } else if (gameState.answer4 == "INCORRECT") {
                round2Question2Player2.setBackground(Color.red);
            }
        }
        if (scoreCounter1 > scoreCounter2) {
            JOptionPane.showMessageDialog(null, "Player One has won!");
            winCounterPlayer1++;
        } else if (scoreCounter2 > scoreCounter1) {
            JOptionPane.showMessageDialog(null, "Player Two has won!");
            winCounterPlayer2++;
        } else {
            JOptionPane.showMessageDialog(null, "It's a tie!");
        }
        String wins1String = Integer.toString(winCounterPlayer1);
        wins1.setText("Player 1 amount of wins: " + wins1String);
        String wins2String = Integer.toString(winCounterPlayer2);
        wins2.setText("Player 2 amount of wins: " + wins2String);
        this.revalidate();
        this.repaint();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playAgainButton) {
            dispose();
        }
    }
}
