package org.example;


import org.example.Account.Admin;
import org.example.Account.DataBase;
import org.example.Account.Person;
import org.example.Account.User;
import org.example.GUI.LogIn.LogInForm;


import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataBase.initialUsers();
        new LogInForm().setVisible(true);
    }
}