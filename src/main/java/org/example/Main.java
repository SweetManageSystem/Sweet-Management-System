package org.example;


import org.example.Database.UserDataBase;
import org.example.GUI.LogIn.LogInForm;

public class Main {
    public static void main(String[] args) {
        UserDataBase.initialUsers();
        new LogInForm().setVisible(true);
    }
}