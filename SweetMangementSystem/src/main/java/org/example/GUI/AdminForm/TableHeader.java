package org.example.GUI.AdminForm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TableHeader extends JLabel {
    public TableHeader(String text) {
        super(text);
        setOpaque(true);
        setBackground(Color.WHITE);
        setFont(new Font("sansserif", Font.BOLD, 12));
        setForeground(new Color(102, 102, 102));
        setBorder(new EmptyBorder(10,5,10,5));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(230,230,230));
        g.drawLine(0,getHeight() - 1, getWidth(), getHeight() - 1);
    }
}
