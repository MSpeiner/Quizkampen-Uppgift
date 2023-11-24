package Client;

import Enums.Category;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

public class CategoryViewGUI extends JFrame implements ActionListener {
    private JPanel northPanel = new JPanel(new GridLayout(2,1));
    private JPanel southPanel = new JPanel(new GridLayout(2,2));

    private JLabel questionLabel = new JLabel("Vilken kategori vill du välja?");

    private JButton sportButton = new JButton(Category.Sport.name());
    private JButton historiaButton = new JButton(Category.History.name());
    private JButton religionButton = new JButton(Category.Religion.name());
    private JButton naturvetenskapButton = new JButton(Category.Science.name());

    private Category chosenCategory = null; // anger spelarens valda kategori, och variabeln ska används av spelmotor
    private JLabel chosenCategoryLabel = new JLabel(""); // variabel för att visa vald kategori i GUI. Den här variabeln kan tas bort sen

    private PrintWriter clientOutputStream;

    public CategoryViewGUI(PrintWriter clientOutputStream){
        this.clientOutputStream =  clientOutputStream;
        createPanel();
        createButtons();
        createFrame();
    }

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
        northPanel.add(chosenCategoryLabel);

        southPanel.add(sportButton);
        southPanel.add(historiaButton);
        southPanel.add(religionButton);
        southPanel.add(naturvetenskapButton);
    }

    private void selectCategory(String category){
        clientOutputStream.println("CATEGORY_SELECTED " + category);
        setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sportButton){
            chosenCategory = Category.Sport;
            chosenCategoryLabel.setText(Category.Sport.name());
            selectCategory("SPORT");
        }
        if (e.getSource() == historiaButton){
            chosenCategory = Category.History;
            chosenCategoryLabel.setText(Category.History.name());
            selectCategory("HISTORY");
        }
        if (e.getSource() == religionButton){
            chosenCategory = Category.Religion;
            chosenCategoryLabel.setText(Category.Religion.name());
            selectCategory("RELIGION");
        }
        if (e.getSource() == naturvetenskapButton){
            chosenCategory = Category.Science;
            chosenCategoryLabel.setText(Category.Science.name());
            selectCategory("SCIENCE");
        }
    }
}
