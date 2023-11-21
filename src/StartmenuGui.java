import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartmenuGui extends JFrame implements ActionListener {
    private JPanel jps = new JPanel();
    private JPanel center = new JPanel();
    private JPanel south = new JPanel();
    private JButton newGame = new JButton("New game");
    private JLabel welcomeText = new JLabel("Welcome to Quizkampen!");


    public StartmenuGui() {
        setTitle("Quizkampen");

        this.add(jps);
        jps.setLayout(new BorderLayout());

        //Sätter welcomeText position
        jps.add(center);
        center.setLayout(new FlowLayout(FlowLayout.CENTER));
        center.add(welcomeText);

        jps.add(south, BorderLayout.SOUTH);
        south.setLayout(new FlowLayout(FlowLayout.CENTER));
        south.add(newGame);
        newGame.addActionListener(this);

        pack();
        setSize(300,150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void newGameMethod(){
        jps.removeAll();
        jps.revalidate();
        jps.repaint();
        CategoryViewGUI cg = new CategoryViewGUI();
        setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGame){
            newGameMethod();
        }

    }
}
