package org.example.Account;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private static List<Person> db = new ArrayList<>();

    public static List<Person> getDb() {
        return db;
    }

    public static void addPerson(Person person) {
        db.add(person);
    }

    public static void removePerson(Person person) {
        db.remove(person);
    }

    public static Person getPerson(String email) {
        for(Person person : db) {
            if(person.getEmail().equals(email))
                return person;
        }
        JOptionPane.showMessageDialog(null, "Email not found");
        return null;
    }
}
