package org.example;


import org.example.Database.ProductDataBase;
import org.example.Database.UserDataBase;
import org.example.GUI.LogIn.LogInForm;
import org.example.StateController.Context;
import org.example.StateController.WelcomeState;

public class Main {
    public static void main(String[] args) {
        UserDataBase.initialUsers();
        ProductDataBase.initialProduct();
        new WelcomeState(new Context()).handleInput();
        //new LogInForm().setVisible(true);
    }
}