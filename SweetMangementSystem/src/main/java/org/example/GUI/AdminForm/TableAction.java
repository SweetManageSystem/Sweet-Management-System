package org.example.GUI.AdminForm;

import javax.swing.*;
import java.awt.*;

public class TableAction extends JLabel {
    public TableAction() {
        setForeground(Color.WHITE);
    }
    @Override
    protected void paintComponent(Graphics grphcs) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint g;
            g = new GradientPaint(0, 0, new Color(216, 13, 91), 0, getHeight(), new Color(120, 15, 55));
            g2.setPaint(g);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 1, 1);
        super.paintComponent(grphcs);
    }
}