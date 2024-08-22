package org.example.Database;

import org.example.Account.Admin;
import org.example.Account.Person;
import org.example.Account.StoreOwner;
import org.example.Account.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDataBase {
    private static List<Person> db = new ArrayList<>();
    private static Map<String, User> users = new HashMap<>();
    private static UserDataBase instance;
    private static Person loggedInUser;



    public static List<Person> getDb() {
        return db;
    }

    public static void addPerson(Person person) {
        db.add(person);
    }

    public static void removePerson(Person person) {
        db.remove(person);
    }

    public static boolean removePersonByEmail(String email) {
        return db.removeIf(person -> person.getEmail().equals(email));
    }

    public static Person getPerson(String email) {
        for (Person person : db) {
            if (person.getEmail().equals(email)) {
                return person;
            }
        }
        System.err.println("Email not found: " + email);
        return null;
    }

    public static Person getPerson(int index) {
        if (index >= 0 && index < db.size()) {
            return db.get(index);
        }
        System.err.println("Index out of bounds: " + index);
        return null;
    }

    public static int getIndex(String email) {
        for (int i = 0; i < db.size(); i++) {
            if (db.get(i).getEmail().equals(email)) {
                return i;
            }
        }
        System.err.println("Email not found: " + email);
        return -1;
    }

    public static Person getPersonByUsername(String username) {
        for (Person person : db) {
            if (person.getUsername().equals(username)) {
                return person;
            }
        }
        return null;
    }

    public static User getUserByEmail(String email) {
        return users.get(email);
    }

    public static UserDataBase getInstance() {
        if (instance == null) {
            synchronized (UserDataBase.class) {
                if (instance == null) {
                    instance = new UserDataBase();
                }
            }
        }
        return instance;
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public void saveUser(User user) {
        users.put(user.getUsername(), user); // Update or add the user
    }

    public void removeUser(String userName) {
        users.remove(userName);
    }

    public boolean userExists(String userName) {
        return users.containsKey(userName);
    }

    public static Map<String, User> getAllUsers() {
        return users;
    }

    public static User findByUsername(String username) {
        return users.values().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public static void save(User user) {
        users.put(user.getEmail(), user);
    }

    public static void deleteByUsername(String username) {
        users.remove(username);
    }

    public static String deleteUser(String email) {
        if (users.containsKey(email)) {
            users.remove(email);
            return "User Deleted Successfully";
        } else {
            return "User not found";
        }
    }

    public static boolean updatePerson(String originalUsername, String newUsername, int role, String newEmail, String password) {
        User user = users.get(originalUsername);
        if (user != null) {
            users.remove(originalUsername);  // Remove the old entry
            user.setUsername(newUsername);
            user.setRole(role);
            user.setEmail(newEmail);
            user.setPassword(password);
            users.put(newUsername, user);  // Add the updated entry with the new username
            return true;
        }
        return false;
    }

    public static void setLoggedIn(Person user) {
        loggedInUser = user;
    }

    public static Person getLoggedIn() {
        return loggedInUser;
    }

    public static void initialUsers() {
        getInstance();


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
        p2.setAddress("Nablus");
        addPerson(p2);

        Person p3 = new StoreOwner();
        p3.setEmail("Jenin_Store@gmail.com");
        p3.setPassword("store");
        p3.setFullname("store");
        p3.setUsername("Jenin Store");
        p3.setAddress("Jenin");
        addPerson(p3);

        Person p4 = new User();
        p4.setEmail("Khalid123@gmail.com");
        p4.setPassword("123");
        p4.setFullname("Khalid ahmed");
        p4.setUsername("Khalid");
        p4.setAddress("Jenin");
        addPerson(p4);

        Person p5 = new User();
        p5.setEmail("nasser123@gmail.com");
        p5.setPassword("123");
        p5.setFullname("nasser moner");
        p5.setUsername("Nasser");
        p5.setAddress("Jenin");
        addPerson(p5);

        Person p6 = new User();
        p6.setEmail("momen123@gmail.com");
        p6.setPassword("123");
        p6.setFullname("momen wasef");
        p6.setUsername("Momen");
        p6.setAddress("Nablus");
        addPerson(p6);

        Person p7 = new User();
        p7.setEmail("janna123@gmail.com");
        p7.setPassword("123");
        p7.setFullname("Janna Noor");
        p7.setUsername("Janna");
        p7.setAddress("Nablus");
        addPerson(p7);


    }
}
