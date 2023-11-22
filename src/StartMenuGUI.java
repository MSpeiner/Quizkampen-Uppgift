import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenuGUI extends JFrame implements ActionListener {
    private JPanel jps = new JPanel();
    private JPanel center = new JPanel();
    private JPanel south = new JPanel();
    private JButton newGame = new JButton("New game");
    private JLabel welcomeText = new JLabel();
    private ServerPlayer serverPlayer; // ServerPlayer instance

    public StartMenuGUI(ServerPlayer serverPlayer) {
        this.serverPlayer = serverPlayer;

        setTitle("Quizkampen");
        this.add(jps);
        jps.setLayout(new BorderLayout());

        jps.add(center);
        center.setLayout(new FlowLayout(FlowLayout.CENTER));
        center.add(welcomeText);

        jps.add(south, BorderLayout.SOUTH);
        south.setLayout(new FlowLayout(FlowLayout.CENTER));
        south.add(newGame);

        setWelcomeText();
        newGame.addActionListener(this);

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

    public void setWelcomeText() {
        // Send a request to get the player's name
        serverPlayer.handleMessageFromGUI("GET_NAME");
         }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGame) {
            newGameMethod();
        }
    }
}
