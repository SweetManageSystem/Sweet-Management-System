package org.example.Database;

import org.example.Account.Admin;
import org.example.Account.Person;
import org.example.Account.StoreOwner;
import org.example.Account.User;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class UserDataBase {
    private static List<Person> db = new ArrayList<>();
    private static Person loggedInUser;

    public static void setLoggedIn(Person user) {
        loggedInUser = user;
    }

    public static Person getLogedIn() {
        return loggedInUser;
    }

    public static List<Person> getDb() {
        return db;
    }

    public static void addPerson(Person person) {
        db.add(person);
    }

    public static void removePerson(Person person) {
        db.remove(person);
    }

    public static Person getPerson(Person person) {
        return db.get(db.indexOf(person));
    }

    public static Person getPerson(String email) {
        for(Person person : db) {
            if(person.getEmail().equals(email))
                return person;
        }
        JOptionPane.showMessageDialog(null, "Email not found");
        return null;
    }

    public static Person getPersonByUsername(String username) {
        for(Person person : db) {
            if (person.getUsername().equals(username))
                return person;
        }
        return null;
    }

    public static Person getPerson(int index) {
        return db.get(index);
    }

    public static int getIndex(String email) {
        for(int i = 0; i < db.size(); i++) {
            if(db.get(i).getEmail().equals(email))
                return i;
        }
        JOptionPane.showMessageDialog(null, "Email not found");
        return -1;
    }

    public static void initialUsers(){
        Person p1 = new Admin();
        p1.setEmail("admin@gmail.com");
        p1.setPassword("admin");
        p1.setFullname("admin");
        p1.setUsername("admin");
        addPerson(p1);
        Person p2 = new StoreOwner();
        p2.setEmail("Nablus_Store@gmail.com");
        p2.setPassword("store");
        p2.setFullname("store");
        p2.setUsername("Nablus Store");
        addPerson(p2);
        Person p3 = new StoreOwner();
        p3.setEmail("Jenin_Store@gmail.com");
        p3.setPassword("store");
        p3.setFullname("store");
        p3.setUsername("Jenin Store");
        addPerson(p3);
        Person p4 = new User();
        p4.setEmail("Khalid123@gmail.com");
        p4.setPassword("123");
        p4.setFullname("Khalid ahmed");
        p4.setUsername("Khalid");
        addPerson(p4);
        Person p5 = new User();
        p5.setEmail("nasser123@gmail.com");
        p5.setPassword("123");
        p5.setFullname("nasser moner");
        p5.setUsername("Nasser");
        addPerson(p5);
        Person p6 = new User();
        p6.setEmail("momen123@gmail.com");
        p6.setPassword("123");
        p6.setFullname("momen wasef");
        p6.setUsername("Momen");
        addPerson(p6);
        Person p7 = new User();
        p7.setEmail("janna123@gmail.com");
        p7.setPassword("123");
        p7.setFullname("Janna Noor");
        p7.setUsername("Janna");
        addPerson(p7);
    }
}
