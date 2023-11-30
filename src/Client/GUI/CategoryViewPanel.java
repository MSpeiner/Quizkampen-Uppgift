package Client.GUI;

import Enums.Category;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryViewPanel extends JPanel {
    private final JPanel northPanel = new JPanel(new GridLayout(2, 1));
    private final JPanel southPanel = new JPanel(new GridLayout(2, 2));
    private final JLabel questionLabel = new JLabel("Pick a category:");
    private final JButton sportButton = new JButton(Category.Sport.name());
    private final JButton historyButton = new JButton(Category.History.name());
    private final JButton religionButton = new JButton(Category.Religion.name());
    private final JButton scienceButton = new JButton(Category.Science.name());
    private final JLabel chosenCategoryLabel = new JLabel(""); // variabel för att visa vald kategori i GUI. Den här variabeln kan tas bort sen

    public CategoryViewPanel(ActionListener actionListener) {
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

        questionLabel.setHorizontalAlignment(JLabel.CENTER); // Center the question label

        // Set font size to 24px
        Font labelFont = questionLabel.getFont();
        questionLabel.setFont(new Font(labelFont.getName(), labelFont.getStyle(), 24));

        northPanel.add(questionLabel);
        northPanel.add(chosenCategoryLabel);

        southPanel.add(sportButton);
        southPanel.add(historyButton);
        southPanel.add(religionButton);
        southPanel.add(scienceButton);
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
