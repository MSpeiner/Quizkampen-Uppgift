package Client.GUI;

import javax.swing.*;
import java.awt.*;

class RoundedCornerButton extends JButton {
    private static final int RADIUS = 16; // Radius for rounded corners

    public RoundedCornerButton(String text) {
        super(text);
        setMargin(new Insets(0, 0, 0, 0));
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!isOpaque() && getBackground() != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw rounded rectangle filled with background color
            g2d.setColor(getBackground());
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), RADIUS, RADIUS);

            g2d.dispose();
        }
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(getForeground());
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, RADIUS, RADIUS);
        g2d.dispose();
    }
}