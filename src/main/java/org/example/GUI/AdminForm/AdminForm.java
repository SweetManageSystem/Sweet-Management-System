package org.example.GUI.AdminForm;

import org.example.Account.Admin;
import org.example.Database.UserDataBase;
import org.example.Account.Person;
import org.example.GUI.LogIn.LogInForm;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class AdminForm extends JFrame {

    private DefaultTableModel tableModel;
    private TextField usernameField;
    private TextField emailField;
    private TextField fullNameField;
    private Combobox<String> roleComboBox;
    private JTabbedPane userTappedpane;
    private Table table;
    private int index;

    public AdminForm(Admin account) {
        setSize(1400, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel header = new JPanel();
        header.setLayout(null);
        header.setBounds(0, 0, 1400, 65);
        header.setBackground(new Color(140, 13, 63));

        JPanel userPanel = new JPanel();
        userPanel.setLayout(null);
        userPanel.setBackground(new Color(140, 13, 63));
        userPanel.setBounds(1200, 10, 150, 45);

        JLabel userNameLabel = new JLabel(account.getUsername());
        userNameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        userNameLabel.setForeground(new Color(255, 255, 255));
        userNameLabel.setBounds(25, 5, 100, 20);
        userPanel.add(userNameLabel);
        JLabel userEmailLabel = new JLabel(account.getEmail());
        userEmailLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        userEmailLabel.setForeground(new Color(216, 149, 171));
        userEmailLabel.setBounds(25, 20, 100, 20);
        userPanel.add(userEmailLabel);

        // Create the popup menu
        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.setBackground(new Color(140, 13, 63));
        JMenuItem manageUserItem = new JMenuItem("Manage Account");
        JMenuItem feedbackItem = new JMenuItem("Feedback");
        JMenuItem logOutItem = new JMenuItem("Log Out");
        customizeMenuItem(manageUserItem);
        customizeMenuItem(feedbackItem);
        customizeMenuItem(logOutItem);

        logOutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LogInForm().setVisible(true);
                dispose();
            }
        });

        // Add menu items to the popup menu
        popupMenu.add(manageUserItem);
        popupMenu.add(feedbackItem);
        popupMenu.add(logOutItem);

        userPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) { // Left mouse button
                    popupMenu.show(userPanel, 0, userPanel.getHeight());
                }
            }
        });

        Icon icon = new ImageIcon("D:\\Projects\\Sweet-Management-System\\SweetMangementSystem\\src\\main\\resources\\sweet-food.png");
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(icon);
        iconLabel.setBounds(15, 0, icon.getIconWidth(), icon.getIconHeight());
        JLabel titleLabel = new JLabel("AJ Sweet");
        titleLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        titleLabel.setForeground(new Color(255, 255, 255));
        titleLabel.setBounds((icon.getIconWidth() + 25), 23, 150, 20);
        header.add(titleLabel);
        header.add(userPanel);
        header.add(iconLabel);
        add(header);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(0, 63, 1600, 33);

        JLabel home = new JLabel("Home");
        JLabel users = new JLabel("Users");
        JLabel dashboard = new JLabel("Dashboard");
        Icon homeIcon = new ImageIcon("D:\\Projects\\Sweet-Management-System\\SweetMangementSystem\\src\\main\\resources\\home.png");
        home.setIcon(homeIcon);
        home.setIconTextGap(8);
        home.setBounds(65, 8, 100, 20);
        home.setFont(new Font("Arial", Font.PLAIN, 13));
        home.setForeground(new Color(0, 0, 0));
        Icon userIcon = new ImageIcon("D:\\Projects\\Sweet-Management-System\\SweetMangementSystem\\src\\main\\resources\\user.png");
        users.setIcon(userIcon);
        users.setIconTextGap(8);
        users.setBounds(145, 8, 100, 20);
        users.setFont(new Font("Arial", Font.PLAIN, 13));
        users.setForeground(new Color(0, 0, 0));
        Icon dashboardIcon = new ImageIcon("D:\\Projects\\Sweet-Management-System\\SweetMangementSystem\\src\\main\\resources\\dashboard.png");
        dashboard.setIcon(dashboardIcon);
        dashboard.setIconTextGap(8);
        dashboard.setBounds(225, 8, 100, 20);
        dashboard.setFont(new Font("Arial", Font.PLAIN, 13));
        dashboard.setForeground(new Color(0, 0, 0));
        panel.add(home);
        panel.add(users);
        panel.add(dashboard);
        add(panel);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Create panels for each tab
        JPanel homePanel = new JPanel();
        homePanel.setLayout(null);
        homePanel.setBackground(new Color(255, 247, 253));
        JPanel usersPanel = new JPanel();
        usersPanel.setLayout(null);
        JPanel panel1 = new JPanel();

        userTappedpane = new JTabbedPane();
        panel1.setLayout(null);
        panel1.setBackground(new Color(255, 255, 255));
        panel1.setBounds(0, 180, 1200, 550);
        String[] columnNames = {"UserName", "Email", "Full Name", "Role", "Actions"};
        tableModel = new DefaultTableModel(columnNames, 0);
        for (Person p : UserDataBase.getDb()) {
            switch (p.getRole()) {
                case 0:
                    tableModel.addRow(new Object[]{p.getUsername(), p.getEmail(), p.getFullname(), "User",});
                    break;
                case 1:
                    tableModel.addRow(new Object[]{p.getUsername(), p.getEmail(), p.getFullname(), "Store Owner",});
                    break;
                case 2:
                    tableModel.addRow(new Object[]{p.getUsername(), p.getEmail(), p.getFullname(), "Admin",});
                    break;
            }
        }
        table = new Table(tableModel);
        table.setAdminForm(this);
        table.setUserTappedpane(userTappedpane);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 185, 1100, 350);
        JLabel label = new JLabel("Users");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setForeground(new Color(0, 0, 0));
        label.setBounds(50, 20, 100, 20);
        JLabel label1 = new JLabel("Authenticate and authorize users");
        TextField usernameSearch = new TextField();
        TextField emailSearch = new TextField();
        emailSearch.setLabelText("Email");
        usernameSearch.setLabelText("Username");
        usernameSearch.setBounds(70, 125, 140, 40);
        emailSearch.setBounds(325, 125, 140, 40);
        panel1.add(emailSearch);
        panel1.add(usernameSearch);
        label1.setFont(new Font("Arial", Font.PLAIN, 18));
        label1.setForeground(new Color(86, 100, 106));
        label1.setBounds(160, 22, 275, 20);
        Combobox<String> filter = new Combobox<>();
        filter.setLabeText("Fillter");
        filter.addItem("All Accounts");
        filter.addItem("Store Owners");
        filter.addItem("Users");
        filter.addItem("Admins");
        filter.setBounds(970, 125, 140, 40);
        filter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFilter = (String) filter.getSelectedItem();
                updateTableModel(selectedFilter);
            }
        });
        JButton searchButton = new JButton();
        Icon searchIcon = new ImageIcon("D:\\Projects\\Sweet-Management-System\\SweetMangementSystem\\src\\main\\resources\\search-interface-symbol.png");
        searchButton.setIcon(searchIcon);
        searchButton.setBounds(475, 138, 30, 30);
        searchButton.setBackground(new Color(163, 24, 75));
        searchButton.setFocusable(false);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (emailSearch.getText().equals("") && usernameSearch.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid email or username");
                } else {
                    for (Person p : UserDataBase.getDb()) {
                        if (emailSearch.getText().equals(p.getEmail()) || usernameSearch.getText().equals(p.getUsername())) {
                            tableModel.setRowCount(0);
                            String r = null;
                            switch (p.getRole()) {
                                case 0:
                                    r = "User";
                                    break;
                                case 1:
                                    r = "Store Owner";
                                    break;
                                case 2:
                                    r = "Admin";
                                    break;
                            }
                            tableModel.addRow(new Object[]{p.getUsername(), p.getEmail(), p.getFullname(), r,});
                        }
                    }
                }
            }
        });
        panel1.add(searchButton);
        panel1.add(filter);
        panel1.add(label1);
        panel1.add(label);
        panel1.add(scrollPane);

        JPanel panel2 = new JPanel();
        panel2.setLayout(null);
        panel2.setBackground(new Color(255, 255, 255));
        panel2.setBounds(0, 180, 1200, 550);
        JLabel label3 = new JLabel("Update user account");
        label3.setFont(new Font("Arial", Font.PLAIN, 18));
        label3.setForeground(new Color(86, 100, 106));
        label3.setBounds(50, 20, 275, 20);

        JPanel menuePanel = new JPanel();
        menuePanel.setLayout(null);
        menuePanel.setBackground(Color.WHITE);
        menuePanel.setBounds(50,100,250,400);
        JLabel manageLabel = new JLabel("Manage Account");
        manageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        manageLabel.setBounds(20,20,150,40);
        JLabel accountDetails = new JLabel("Account Details");
        accountDetails.setHorizontalAlignment(SwingConstants.CENTER);
        accountDetails.setBounds(50,100,150,20);
        JLabel posts = new JLabel("Posts");
        posts.setHorizontalAlignment(SwingConstants.CENTER);
        posts.setBounds(50,150,130,20);
        JLabel informations = new JLabel("Informations");
        informations.setHorizontalAlignment(SwingConstants.CENTER);
        informations.setBounds(50,200,150,20);
        JLabel delete = new JLabel("Delete");
        delete.setHorizontalAlignment(SwingConstants.CENTER);
        delete.setBounds(50,300,150,20);
        delete.setForeground(new Color(255, 0, 0));
        menuePanel.setBorder(new LineBorder(new Color(239, 239, 239)));
        menuePanel.add(manageLabel);
        menuePanel.add(accountDetails);
        menuePanel.add(posts);
        menuePanel.add(informations);
        menuePanel.add(delete);

        JLabel backLabel = new JLabel("Back");
        backLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backLabel.setBounds(50,350,150,20);
        menuePanel.add(backLabel);


        JTabbedPane manageTappedpane = new JTabbedPane();
        manageTappedpane.setBounds(350,-30,700,530);
        menuePanel.add(manageTappedpane);
        panel2.add(menuePanel);

        JPanel postPanel = new JPanel();
        postPanel.setLayout(null);
        postPanel.setBackground(new Color(255, 255, 255));
        postPanel.setBounds(50,200,250,400);

        JLabel p = new JLabel("Account Posts");
        p.setBounds(30,125,200,40);
        p.setFont(new Font("Arial", Font.BOLD, 14));

        JTextArea postsArea = new JTextArea();
        postsArea.setEditable(true);
        postsArea.setLineWrap(true);
        postsArea.setBounds(50,180,650,300);
       // for(String ps : DataBase.getPerson(index).getPosts())
        //postsArea.append("-" + ps);

        postPanel.add(postsArea);
        postPanel.add(p);

        JPanel informationPanel = new JPanel();
        informationPanel.setLayout(null);
        informationPanel.setBackground(new Color(0, 0, 0));
        informationPanel.setBounds(50,200,250,400);


        // Add fields to panel2 for editing user data
        JPanel userDetails = new JPanel();
        userDetails.setLayout(null);
        userDetails.setBackground(new Color(255, 255, 255));
        userDetails.setBounds(50,200,250,400);
        JLabel label2 = new JLabel("Account Details");
        label2.setBounds(30,125,200,40);
        label2.setFont(new Font("Arial", Font.BOLD, 14));

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(50,450,75,40);
        saveButton.setBackground(new Color(163, 24, 75));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusable(false);


        usernameField = new TextField();
        emailField = new TextField();
        fullNameField = new TextField();
        roleComboBox = new Combobox<>();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserDataBase.getPerson(index).setUsername(usernameField.getText());
                UserDataBase.getPerson(index).setEmail(emailField.getText());
                UserDataBase.getPerson(index).setFullname(fullNameField.getText());
                UserDataBase.getPerson(index).setRole(roleComboBox.getSelectedIndex());
                JOptionPane.showMessageDialog(null, "Account details updated successfully");
            }
        });

        usernameField.setBounds(50, 180, 400, 45);
        emailField.setBounds(50, 250, 400, 45);
        fullNameField.setBounds(50, 320, 400, 45);
        roleComboBox.setBounds(50, 380, 400, 40);

        usernameField.setLabelText("UserName");
        emailField.setLabelText("Email");
        fullNameField.setLabelText("Full Name");
        roleComboBox.setLabeText("Role");

        roleComboBox.addItem("User");
        roleComboBox.addItem("Store Owner");
        roleComboBox.addItem("Admin");

        userDetails.add(saveButton);
        userDetails.add(label2);
        userDetails.add(usernameField);
        userDetails.add(emailField);
        userDetails.add(fullNameField);
        userDetails.add(roleComboBox);

        manageTappedpane.addTab("UserDetalis",userDetails);
        manageTappedpane.addTab("Posts",postPanel);
        manageTappedpane.addTab("Informations", informationPanel);
        panel2.add(manageTappedpane);

        panel2.add(label3);
        userTappedpane.addTab("Users", panel1);
        userTappedpane.addTab("Manage", panel2);
        userTappedpane.setBounds(80, 45, 1200, 580);
        usersPanel.add(userTappedpane);

        usersPanel.setBackground(new Color(253, 247, 252));
        JPanel dashboardPanel = new JPanel();
        dashboardPanel.setBackground(new Color(253, 251, 252));
        JPanel manageAccountPanel = new JPanel();
        manageAccountPanel.setBackground(new Color(253, 251, 252));
        JPanel feedbackPanel = new JPanel();
        feedbackPanel.setBackground(new Color(253, 251, 252));

        // Add panels to the tabbed pane
        tabbedPane.addTab("Home", homePanel);
        tabbedPane.addTab("Users", usersPanel);
        tabbedPane.addTab("Dashboard", dashboardPanel);
        tabbedPane.addTab("Manage Account", manageAccountPanel);
        tabbedPane.addTab("Feedback", feedbackPanel);

        home.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabbedPane.setSelectedIndex(0); // Home tab
            }
        });

        users.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabbedPane.setSelectedIndex(1); // Users tab
            }
        });

        dashboard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabbedPane.setSelectedIndex(2); // Dashboard tab
            }
        });

        manageUserItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(3);
            }
        });

        feedbackItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedIndex(4);
            }
        });

        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                userTappedpane.setSelectedIndex(0);
                refreshTable();
            }
        });

        accountDetails.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                manageTappedpane.setSelectedIndex(0);
            }
        });


        posts.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                manageTappedpane.setSelectedIndex(1);
            }
        });

        informations.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                manageTappedpane.setSelectedIndex(2);

            }
        });

        // Set bounds for the tabbed pane
        tabbedPane.setBounds(0, 100, 1400, 600);

        // Add the tabbed pane to the frame
        add(tabbedPane);
    }

    private void customizeMenuItem(JMenuItem menuItem) {
        menuItem.setBackground(new Color(140, 13, 63));
        menuItem.setForeground(Color.WHITE);
        menuItem.setFont(new Font("Arial", Font.PLAIN, 12));
        menuItem.setOpaque(true);
    }

    private void updateTableModel(String filter) {
        tableModel.setRowCount(0);
        int role = 3;
        switch (filter) {
            case "Users":
                role = 0;
                break;
            case "Admins":
                role = 2;
                break;
            case "Store Owners":
                role = 1;
                break;
        }
        if (role != 3)
            for (Person p : UserDataBase.getDb()) {
                if (p.getRole() == role) {
                    tableModel.addRow(new Object[]{p.getUsername(), p.getEmail(), p.getFullname(), filter,});
                }
            }
        else {
            refreshTable();
        }
    }
    public void refreshTable(){
        tableModel.setRowCount(0);
        for (Person p : UserDataBase.getDb()) {
                String r = null;
                switch (p.getRole()){
                    case 0: r = "User";
                        break;
                    case 1: r = "Store Owner";
                        break;
                    case 2: r = "Admin";
                        break;
                }
                tableModel.addRow(new Object[]{p.getUsername(), p.getEmail(), p.getFullname(),r,});
        }
    }

    public void setUserData(String username, String email, String fullName, String role) {
        usernameField.setText(username);
        emailField.setText(email);
        fullNameField.setText(fullName);
        roleComboBox.setSelectedItem(role);
        index = UserDataBase.getIndex(email);
    }

    public void showUserTappedPane(int index) {
        userTappedpane.setSelectedIndex(index);
    }
}