import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameViewGUI extends JFrame implements ActionListener {
    private JPanel jp = new JPanel(new BorderLayout());
    private JPanel northPanel = new JPanel(new GridLayout(2,1));
    private JPanel southPanel = new JPanel(new GridLayout(2,2));

    private JLabel questionLabel = new JLabel("Blablabla hur många blabla?");
    private JLabel categoryLabel = new JLabel();

    private JButton alternative1 = new JButton("Alt 1");
    private JButton alternative2 = new JButton("Alt 2");
    private JButton alternative3 = new JButton("Alt 3");
    private JButton alternative4 = new JButton("Alt 4");

    private String category = "Category: ";

    GameViewGUI(JPanel jp, String category){
        this.jp = jp;
        this.category = category;
    }

    public GameViewGUI(){
        this.add(jp);
        jp.add(northPanel, BorderLayout.NORTH);
        jp.add(southPanel, BorderLayout.SOUTH);

        northPanel.add(categoryLabel);
        northPanel.add(questionLabel);

        southPanel.add(alternative1);
        southPanel.add(alternative2);
        southPanel.add(alternative3);
        southPanel.add(alternative4);
        categoryLabel.setText(category + " ");

        alternative1.addActionListener(this);
        alternative2.addActionListener(this);
        alternative3.addActionListener(this);
        alternative4.addActionListener(this);

        pack();
        setSize(300,150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    public static void main(String[] args) {
        GameViewGUI g = new GameViewGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == alternative1){
            //checkIfWinmetod
        }
        if (e.getSource() == alternative2){
            //checkIfWinmetod
        }
        if (e.getSource() == alternative3){
            //checkIfWinmetod
        }
        if (e.getSource() == alternative4){
            //checkIfWinmetod
        }

    }
}
