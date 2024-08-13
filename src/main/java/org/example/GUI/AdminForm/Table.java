package org.example.GUI.AdminForm;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Table extends JTable {
    private JPopupMenu popupMenu;
    private JTabbedPane userTappedpane;
    private AdminForm adminForm;

    public Table(DefaultTableModel model) {
        super(model);

        setShowHorizontalLines(true);
        setShowVerticalLines(false);
        setGridColor(new Color(240, 240, 240));
        setRowHeight(40);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                TableHeader header = new TableHeader(o + "");
                if (i1 == 4) {
                    header.setHorizontalAlignment(JLabel.CENTER);
                }
                return header;
            }
        });

        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                if (i1 != 4) {
                    Component com = super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
                    com.setBackground(Color.white);
                    setBorder(noFocusBorder);
                    if (bln) {
                        com.setForeground(new Color(13, 113, 182));
                    } else {
                        com.setForeground(new Color(102, 102, 102));
                    }
                    return com;
                }

                // Use TableAction for the "Actions" column
                TableAction actionsLabel = new TableAction();
                actionsLabel.setText("Actions");
                actionsLabel.setHorizontalAlignment(JLabel.CENTER); // Center the label

                return actionsLabel;
            }
        });

        // Create the popup menu
        popupMenu = new JPopupMenu();
        JMenuItem editItem = new JMenuItem("Edit");
        JMenuItem deleteItem = new JMenuItem("Delete");
        popupMenu.setBackground(new Color(163, 24, 75));
        deleteItem.setBackground(new Color(163, 24, 75));
        editItem.setBackground(new Color(163, 24, 75));
        editItem.setForeground(new Color( 255, 255, 255));
        deleteItem.setForeground(new Color( 255, 255, 255));
        popupMenu.add(editItem);
        popupMenu.add(deleteItem);

        // Add action listener to the "Edit" menu item
        editItem.addActionListener(e -> {
            int row = getSelectedRow();
            if (row != -1) {
                String username = String.valueOf( getValueAt(row, 0) );
                String email = String.valueOf( getValueAt(row, 1) );
                String fullName = String.valueOf( getValueAt(row, 2) );
                String role = String.valueOf( getValueAt(row, 3));


                adminForm.setUserData(username, email, fullName, role);
                adminForm.showUserTappedPane(1);
            }
        });

        // Add a mouse listener to the table
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                int column = columnAtPoint(e.getPoint());

                if (column == 4) { // "Actions" column
                    setRowSelectionInterval(row, row); // Select the row
                    popupMenu.show(Table.this, e.getX(), e.getY());
                }
            }
        });
    }

    public void setUserTappedpane(JTabbedPane userTappedpane) {
        this.userTappedpane = userTappedpane;
    }

    public void setAdminForm(AdminForm adminForm) {
        this.adminForm = adminForm;
    }
}