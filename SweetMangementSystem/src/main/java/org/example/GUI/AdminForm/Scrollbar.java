package org.example.GUI.AdminForm;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class Scrollbar extends JScrollBar {

    public Scrollbar() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(8, 8));
        setForeground(new Color(48, 144, 216));
        setBackground(Color.WHITE);
    }
}


