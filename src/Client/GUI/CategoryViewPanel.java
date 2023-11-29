package Client.GUI;

import Enums.Category;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryViewPanel extends JPanel {
    private final JPanel northPanel = new JPanel(new GridLayout(2, 1)); // skapar norra panelen med rut-layout
    private final JPanel southPanel = new JPanel(new GridLayout(2, 2)); // skapar södra panelen med rut-layout
    private final JLabel questionLabel = new JLabel("Vilken kategori vill du välja?"); // skapar fråga som ska visas i gui
    private final JButton sportButton = new JButton(Category.Sport.name()); // skapar knappar med text från category enum
    private final JButton historyButton = new JButton(Category.History.name());
    private final JButton religionButton = new JButton(Category.Religion.name());
    private final JButton scienceButton = new JButton(Category.Science.name());
    private final JLabel chosenCategoryLabel = new JLabel(""); // variabel för att visa vald kategori i GUI

    // konstruktor som skapar panel och knappar, från in action listener från GuiManager
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
        this.setLayout(new BorderLayout()); // sätter layout till border för min categoryViewGui panel
        this.add(northPanel, BorderLayout.NORTH); // addar norra panelen
        this.add(southPanel, BorderLayout.CENTER); // addar södra panelen

        northPanel.add(questionLabel); // lägger till fråga label (norra)
        northPanel.add(chosenCategoryLabel); // lägger till svar label (norra)

        southPanel.add(sportButton); // lägger till knappar (södra)
        southPanel.add(historyButton);
        southPanel.add(religionButton);
        southPanel.add(scienceButton);
    }

    // anropas av actionlistener(GuiManager) och får reda på vilket kategori/knapp som blivit vald
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
        return null;
    }
}
