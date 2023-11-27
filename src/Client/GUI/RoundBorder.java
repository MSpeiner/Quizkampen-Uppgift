package Client.GUI;

import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundBorder implements Border {
    private int radius;
    private Color borderColor;

    public RoundBorder(int radius, Color borderColor) {
        this.radius = radius;
        this.borderColor = borderColor;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        RoundRectangle2D roundRect = new RoundRectangle2D.Float(x, y, width - 1, height - 1, radius, radius);

        Paint paint = new RadialGradientPaint(
                new Point(x + width / 2, y + height / 2),
                width / 2,
                new float[]{0.0f, 1.0f},
                new Color[]{new Color(255, 255, 255, 0), borderColor}
        );

        g2d.setPaint(paint);
        g2d.draw(roundRect);
        g2d.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(radius, radius, radius, radius);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
}
