package org.example.GUI.LogIn;

import org.example.Account.Admin;
import org.example.Database.UserDataBase;
import org.example.Account.Person;
import org.example.GUI.AdminForm.AdminForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInForm extends JFrame {
    public JButton loginButton;
    private JTextField emailField;
    private JPasswordField passwordField;
    private String message;
    public LogInForm() {
        setTitle("Login Form");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(0,0,300,500);
        panel1.setBackground(new Color(140, 13, 63));
        panel.add(panel1);

        JLabel loginLabel = new JLabel("Log In");
        loginLabel.setBounds(470, 90, 200, 30);
        loginLabel.setForeground(new Color(163, 24, 75));
        loginLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(loginLabel);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(385, 140, 100, 25);
        emailLabel.setForeground(new Color(163, 24, 75));
        panel.add(emailLabel);

         emailField = new JTextField();
        emailField.setName("emailField"); // Set name for the email field
        emailField.setBounds(385, 170, 225, 25);
        emailField.setForeground(new Color(66, 4, 28));
        emailField.setBackground(new Color(163, 24, 75));
        panel.add(emailField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(385, 210, 100, 25);
        passwordLabel.setForeground(new Color(163, 24, 75));
        panel.add(passwordLabel);

         passwordField = new JPasswordField();
        passwordField.setName("passwordField"); // Set name for the password field
        passwordField.setBounds(385, 240, 225, 25);
        passwordField.setForeground(new Color(66, 4, 28));
        passwordField.setBackground(new Color(163, 24, 75));
        panel.add(passwordField);

        loginButton = new JButton();
        loginButton.setName("loginButton"); // Set name for the login button
        loginButton.setBounds(385, 300, 225, 30);
        loginButton.setBackground(new Color(163, 24, 75));
        loginButton.setForeground(Color.WHITE);
        Icon buttonIcon = new ImageIcon("D:\\Projects\\Sweet-Management-System\\SweetMangementSystem\\src\\main\\resources\\LogIn.png");
        loginButton.setIcon(buttonIcon);
        loginButton.setFocusable(false);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        panel.add(loginButton);
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(new ImageIcon("D:\\Projects\\Sweet-Management-System\\SweetMangementSystem\\src\\main\\resources\\sweet-food.png"));
        iconLabel.setBounds(125, 50, 300, 100);
        JLabel label1 = new JLabel("Welcome Back!");
        label1.setBounds(95,100,200,100);
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel label2 = new JLabel("To keep contacted with us please login");
        label2.setBounds(45,140,250,100);
        label2.setForeground(Color.WHITE);
        JLabel label3 = new JLabel("with you personal info");
        label3.setBounds(90,160,250,100);
        label3.setForeground(Color.WHITE);
        JLabel label4 = new JLabel("Dont have an account ? enter your personal");
        label4.setBounds(30,200,250,100);
        label4.setForeground(Color.WHITE);
        JLabel label5 = new JLabel("details and start a journey with us");
        label5.setBounds(55,220,250,100);
        label5.setForeground(Color.WHITE);
        JButton signUpButton = new JButton("SignUp");
        signUpButton.setBounds(40,300,225, 30);
        signUpButton.setBackground(new Color(120, 15, 55));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFocusable(false);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignUpForm().setVisible(true);
            }
        });
        panel1.add(signUpButton);
        panel1.add(iconLabel);
        panel1.add(label1);
        panel1.add(label2);
        panel1.add(label3);
        panel1.add(label4);
        panel1.add(label5);
        add(panel);
    }

    private void checkRole(Person person){
        switch(person.getRole()){
            case 0:
                break;
            case 1:

                break;
            case 2:
                AdminForm adminForm = new AdminForm(new Admin(person));
                adminForm.setVisible(true);
                break;
        }
    }


   public JTextField getEmailField() {
        return emailField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
            for (Person user : UserDataBase.getDb()) {
                if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                    message = "Login successful";
                    checkRole(user);
                    this.dispose();
                    JOptionPane.showMessageDialog(null,  message);
                    return;
                }
            }
            message = "Invalid username or password";
            JOptionPane.showMessageDialog(null, message);
        }


    public String getDisplayedMessage() {

        return message;
    }

    public JButton getLoginButton() {
        return loginButton;
    }
}




