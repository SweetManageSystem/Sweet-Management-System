package org.example.GUI;

import org.example.Account.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpForm extends JFrame {
    public SignUpForm() {
        setTitle("Sign Up Form");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(385, 0, 300, 500);
        panel1.setBackground(new Color(140, 13, 63));

        JLabel signUpLabel = new JLabel("Sign Up");
        signUpLabel.setBounds(160, 30, 200, 30);
        signUpLabel.setForeground(new Color(163, 24, 75));
        signUpLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(signUpLabel);

        JLabel fullNameLabel = new JLabel("Full Name");
        fullNameLabel.setBounds(85, 65, 100, 25);
        fullNameLabel.setForeground(new Color(163, 24, 75));
        panel.add(fullNameLabel);

        JTextField fullNameField = new JTextField();
        fullNameField.setBounds(85, 90, 225, 25);
        fullNameField.setForeground(new Color(66, 4, 28));
        fullNameField.setBackground(new Color(163, 24, 75));
        panel.add(fullNameField);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(85, 130, 100, 25);
        emailLabel.setForeground(new Color(163, 24, 75));
        panel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(85, 155, 225, 25);
        emailField.setForeground(new Color(66, 4, 28));
        emailField.setBackground(new Color(163, 24, 75));
        panel.add(emailField);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(85, 195, 100, 25);
        usernameLabel.setForeground(new Color(163, 24, 75));
        panel.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(85, 220, 225, 25);
        usernameField.setForeground(new Color(66, 4, 28));
        usernameField.setBackground(new Color(163, 24, 75));
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(85, 260, 100, 25);
        passwordLabel.setForeground(new Color(163, 24, 75));
        panel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(85, 285, 225, 25);
        passwordField.setForeground(new Color(66, 4, 28));
        passwordField.setBackground(new Color(163, 24, 75));
        panel.add(passwordField);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setBounds(85, 325, 150, 25);
        confirmPasswordLabel.setForeground(new Color(163, 24, 75));
        panel.add(confirmPasswordLabel);

        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(85, 350, 225, 25);
        confirmPasswordField.setForeground(new Color(66, 4, 28));
        confirmPasswordField.setBackground(new Color(163, 24, 75));
        panel.add(confirmPasswordField);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(85, 400, 225, 30);
        signUpButton.setBackground(new Color(163, 24, 75));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFocusable(false);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //todo check all scenarios
                Person person = new User();
                person.setPassword(passwordField.getText());
                person.setUsername(usernameField.getText());
                person.setEmail(emailField.getText());
                person.setFullname(fullNameField.getText());
                DataBase.addPerson(person);
                JOptionPane.showMessageDialog(null,"Account added successfully");
            }
        });
        panel.add(signUpButton);
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(new ImageIcon("D:\\Projects\\Sweet-Management-System\\SweetMangementSystem\\src\\main\\java\\org\\example\\GUI\\LogIn\\sweet-food.png"));
        iconLabel.setBounds(125, 50, 300, 100);
        JLabel label1 = new JLabel("Hello ,Friend!");
        label1.setBounds(105,100,200,100);
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel label2 = new JLabel("Enter your personal derails and start a");
        label2.setBounds(50,140,250,100);
        label2.setForeground(Color.WHITE);
        JLabel label3 = new JLabel("journey with us");
        label3.setBounds(110,160,250,100);
        label3.setForeground(Color.WHITE);
        JLabel label4 = new JLabel("Already have an account ? To keep");
        label4.setBounds(53,200,250,100);
        label4.setForeground(Color.WHITE);
        JLabel label5 = new JLabel("contacted with us please login");
        label5.setBounds(65,220,250,100);
        label5.setForeground(Color.WHITE);
        JButton signupButton = new JButton("SignIn");
        signupButton.setBounds(40,300,225, 30);
        signupButton.setBackground(new Color(120, 15, 55));
        signupButton.setForeground(Color.WHITE);
        signupButton.setFocusable(false);
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel1.add(signupButton);
        panel1.add(iconLabel);
        panel1.add(label1);
        panel1.add(label2);
        panel1.add(label3);
        panel1.add(label4);
        panel1.add(label5);
        panel.add(panel1);
        add(panel);
    }
}