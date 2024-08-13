package org.example.Account;

import org.example.Database.UserDataBase;
import java.util.ArrayList;
import java.util.List;

public class Admin implements Person {
    private String email, password, userName, fullName;
    private int role = 3;
    private String currentState;
    private String lastMessage;
    private String emailForAction;
    private List<String> posts = new ArrayList<>();

    public Admin() {}

    public Admin(String email, String password){
        this.email = email;
        this.password = password;
    }

    public Admin(String username, String email, String password) {
        this.setUsername(username);
        this.setEmail(email);
        this.setPassword(password);
    }

    public Admin(Person a) {
        this.email = a.getEmail();
        this.password = a.getPassword();
        this.userName = a.getUsername();
        this.fullName = a.getFullname();
        UserDataBase.removePerson(a);
        UserDataBase.addPerson(this);
    }

    @Override
    public void setUsername(String username) {
        this.userName = username;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setFullname(String fullname) {
        this.fullName = fullname;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getFullname() {
        return fullName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public int getRole() {
        return role;
    }

    @Override
    public List<String> getPosts() {
        return posts;
    }

    public void addPost(String post) {
        posts.add(post);
    }

    public boolean login() {
        System.out.println("Attempting login for email: " + this.email);
        Person admin = UserDataBase.getPerson(this.email);
        if (admin != null) {
            System.out.println("Admin found: " + admin.getEmail());
            if (admin.getPassword().equals(this.password) && admin.getRole() == 3) {
                System.out.println("Login successful");
                return true;
            }
        }
        System.out.println("Login failed");
        return false;
    }

    public List<String> getAvailableOptions() {
        List<String> options = new ArrayList<>();
        if ("User Management".equals(currentState)) {
            options.add("Create a new user account");
            options.add("Show user status");
            options.add("Edit user details");
            options.add("Delete a user account");
        } else {
            options.add("User Management");
        }
        System.out.println("Available options: " + options);
        return options;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void selectOption(String option) {
        System.out.println("Selecting option: " + option);
        if ("User Management".equals(option)) {
            this.currentState = "User Management";
        }
        System.out.println("Current state: " + this.currentState);
    }

    public String createUser(String username, String role, String email, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            return "Password and confirm password do not match";
        }
        int roleInt = "StoreOwner".equalsIgnoreCase(role) ? 0 : 1;
        Person newUser = new User(username, email, password, roleInt);
        UserDataBase.addPerson(newUser);

        lastMessage = "User Created Successfully";
        return lastMessage;
    }

    public void setEmailForAction(String email) {
        this.emailForAction = email;
    }

    public String getDisplayedUserInfo() {
        Person user = UserDataBase.getPerson(emailForAction);
        if (user != null) {
            return "Username: " + user.getUsername() + ", Role: " + user.getRole() + ", Password: " + user.getPassword();
        }
        return "User not found";
    }

    public String updateUser(String username, String role, String email, String password) {
        Person user = UserDataBase.getPerson(emailForAction);
        if (user != null) {
            user.setUsername(username);
            user.setRole("StoreOwner".equalsIgnoreCase(role) ? 0 : 1);
            user.setEmail(email);
            user.setPassword(password);
            lastMessage = "User Updated Successfully";
            return lastMessage;
        }
        lastMessage = "User not found";
        return lastMessage;
    }

    public String deleteUser(String email) {
        System.out.println("Attempting to delete user with email: " + email);
        User user = UserDataBase.getUserByEmail(email);
        if (user != null) {
            UserDataBase.deleteUser(email);
            System.out.println("User deleted: " + email);
            return "User Deleted Successfully";
        } else {
            System.out.println("User not found: " + email);
            return "User not found";
        }
    }
}