import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartmenuGui extends JFrame implements ActionListener {
    private JPanel jps = new JPanel();
    private JPanel center = new JPanel();
    private JPanel south = new JPanel();
    private JButton newGame = new JButton("New game");
    private JLabel welcomeText = new JLabel();
    private Players players;


    public StartmenuGui(Players players) {
        this.players = players;
        setTitle("Quizkampen");


        this.add(jps);
        jps.setLayout(new BorderLayout());

        //Sätter welcomeText position
        jps.add(center);
        center.setLayout(new FlowLayout(FlowLayout.CENTER));
        center.add(welcomeText);
        setWelcomeText();

        jps.add(south, BorderLayout.SOUTH);
        south.setLayout(new FlowLayout(FlowLayout.CENTER));
        south.add(newGame);



        setWelcomeText();

        //Knappstil
        newGame.addActionListener(this);
        int cornerRadius = 15;
        Color glowColor = new Color(0, 0, 200);
        newGame.setBorder(new RoundBorder(cornerRadius, glowColor));


        pack();
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void newGameMethod() {
        jps.removeAll();
        jps.revalidate();
        jps.repaint();
        CategoryViewGUI cg = new CategoryViewGUI();
        setVisible(false);
    }

    public void setColorOfNGButton(int r, int g, int b, int a) {
        Color transparent = new Color(r,g,b,a);
    newGame.setBackground(transparent);
    }

    public void setWelcomeTextProperties() {
        // Change the size, color, and font style of the welcomeText
        welcomeText.setFont(new Font("Impact", Font.BOLD, 20)); // Adjust font size and style
        welcomeText.setForeground(Color.BLUE); // Adjust color
    }

    public void setWelcomeText() {
        welcomeText.setText("Welcome to Quizkampen " + players.getName());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGame) {
            newGameMethod();
        }

    }
}
