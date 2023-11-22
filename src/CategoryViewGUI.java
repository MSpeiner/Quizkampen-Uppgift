import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryViewGUI extends JFrame implements ActionListener {
    private JPanel northPanel = new JPanel(new GridLayout(2,1));
    private JPanel southPanel = new JPanel(new GridLayout(2,2));

    private JLabel questionLabel = new JLabel("Vilken kategori vill du välja?");

    private JButton sportButton = new JButton("Sport");
    private JButton historiaButton = new JButton("History");
    private JButton religionButton = new JButton("Religion");
    private JButton naturvetenskapButton = new JButton("Science");
    private PropertiesManager propertiesManager = new PropertiesManager();

    public CategoryViewGUI(){
        createPanel();
        createButtons();
        createFrame();
    }

    private void createFrame() {
        pack();
        setSize(300,150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createButtons() {
        sportButton.addActionListener(this);
        historiaButton.addActionListener(this);
        religionButton.addActionListener(this);
        naturvetenskapButton.addActionListener(this);
    }

    private void createPanel() {
        this.add(northPanel, BorderLayout.NORTH);
        this.add(southPanel, BorderLayout.SOUTH);

        northPanel.add(questionLabel);

        southPanel.add(sportButton);
        southPanel.add(historiaButton);
        southPanel.add(religionButton);
        southPanel.add(naturvetenskapButton);
    }

    private void switchToGameView(String category){
        GameViewGUI gameGUI = new GameViewGUI(category);
        setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sportButton){
            switchToGameView("Sport");
        }
        if (e.getSource() == historiaButton){
            switchToGameView("History");
        }
        if (e.getSource() == religionButton){
            switchToGameView("Religion");
        }
        if (e.getSource() == naturvetenskapButton){
            switchToGameView("Science");
        }
    }
}
