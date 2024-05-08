package Utilisateur;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedPanel extends JPanel {

    private int cornerRadius = 20;
    private Color backgroundColor = new Color(201, 233, 246, 200); // Light Blue with 200/255 opacity
    private Color shadowColor = new Color(0, 0, 0, 50); // Black with 50/255 opacity
    private int shadowSize = 5;

    public RoundedPanel(LayoutManager layout) {
        super(layout);
        setOpaque(false);
        setBorder(new EmptyBorder(shadowSize, shadowSize, shadowSize, shadowSize));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        Graphics2D g2d = (Graphics2D) g.create();

        // Draw shadow
        g2d.setColor(shadowColor);
        g2d.fillRoundRect(shadowSize, shadowSize, width - shadowSize * 2, height - shadowSize * 2, cornerRadius, cornerRadius);

        // Draw rounded rectangle panel
        g2d.setColor(backgroundColor);
        g2d.fillRoundRect(0, 0, width - shadowSize, height - shadowSize, cornerRadius, cornerRadius);

        g2d.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // Don't paint border
    }

    @Override
    public Dimension getPreferredSize() {
        return super.getPreferredSize();
    }
}
