package Client.GUI;

import Enums.Category;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

public class CategoryViewGUI extends JPanel {
    private JPanel northPanel = new JPanel(new GridLayout(2,1));
    private JPanel southPanel = new JPanel(new GridLayout(2,2));
    private JLabel questionLabel = new JLabel("Vilken kategori vill du välja?");
    private JButton sportButton = new JButton(Category.Sport.name());
    private JButton historyButton = new JButton(Category.History.name());
    private JButton religionButton = new JButton(Category.Religion.name());
    private JButton scienceButton = new JButton(Category.Science.name());
    private JLabel chosenCategoryLabel = new JLabel(""); // variabel för att visa vald kategori i GUI. Den här variabeln kan tas bort sen

    private PrintWriter clientOutputStream;

    public CategoryViewGUI(ActionListener actionListener){
        createPanel();
        createButtons(actionListener);
    }

    private void createButtons(ActionListener actionListener) {
        sportButton.addActionListener(actionListener);
        historyButton.addActionListener(actionListener);
        religionButton.addActionListener(actionListener);
        scienceButton.addActionListener(actionListener);
    }

    private void createPanel() {
        this.setLayout(new BorderLayout());
        this.add(northPanel, BorderLayout.NORTH);
        this.add(southPanel, BorderLayout.CENTER);

        northPanel.add(questionLabel);
        northPanel.add(chosenCategoryLabel);

        southPanel.add(sportButton);
        southPanel.add(historyButton);
        southPanel.add(religionButton);
        southPanel.add(scienceButton);
    }

    private void selectCategory(String category){
        clientOutputStream.println("CATEGORY_SELECTED " + category);
        setVisible(false);
    }


    public Category getSelectedCategory(ActionEvent e) {
        if (e.getSource() == sportButton) {
            return Category.Sport;
        } else if (e.getSource() == historyButton) {
            return Category.History;
        } else if (e.getSource() == religionButton) {
            return Category.Religion;
        } else if (e.getSource() == scienceButton) {
            return Category.Science;
        }
        return null; // or throw an exception
    }
}
