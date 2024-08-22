package org.example.Account;

import org.example.Database.UserDataBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User implements Person {
    private String email, password, confirmPassword, userName, fullName, address, currentState;
    private String originalUsername;
    private int role = 0;
    private List<String> posts = new ArrayList<>();
    private List<String> messages = new ArrayList<>();
    private List<String> availableOptions = new ArrayList<>();
    private String initialPostLoginState;


    // Constructors
    public User() {}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public User(String userName, String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.fullName = fullName;
    }

    public User(Person a) {
        this.email = a.getEmail();
        this.password = a.getPassword();
        this.userName = a.getUsername();
        this.fullName = a.getFullname();
        UserDataBase.removePerson(a);
    }

    // Getters and Setters
    @Override
    public void setUsername(String username) {
        if (this.originalUsername == null) {
            this.originalUsername = this.userName;
        }
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

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setCurrentState(String state) {
        this.currentState = state;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getCurrentState() {
        return currentState;
    }

    public List<String> getAvailableOptions() {
        if ("Logged In".equals(currentState)) {
            return Arrays.asList(
                    "Manage Account",
                    "Post and share personal dessert creations",
                    "Home" // Add Home to the list of available options
            );
        }
        return availableOptions;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInitialPostLoginState() {
        return this.initialPostLoginState;
    }

    public void setInitialPostLoginState(String state) {
        this.initialPostLoginState = state;
    }

    public void setOriginalUsername(String originalUsername) {
        this.originalUsername = originalUsername;
    }

    public String getOriginalUsername() {
        return originalUsername;
    }

    public List<String> getPosts() {
        return posts;
    }

    public void addPost(String post) {
        posts.add(post);
    }

    public void recieveMessage(String message) {
        messages.add(message);
    }

    @Override
    public List<String> getMessages() {
        return messages;
    }

    // Methods
    public boolean login() {
        User storedUser = UserDataBase.findByUsername(this.userName);
        if (storedUser != null && storedUser.getPassword().equals(this.password)) {
            this.currentState = "Logged In";
            return true;
        }
        return false;
    }

    public void selectOption(String option) {
        if (availableOptions == null) {
            availableOptions = new ArrayList<>();
        }
        switch (option) {
            case "Manage Account":
                this.currentState = "Manage Account";
                this.availableOptions.clear();
                this.availableOptions.add("1. View Personal Account Information");
                this.availableOptions.add("2. Update Personal Account Information");
                this.availableOptions.add("3. Delete Account");
                this.availableOptions.add("Post and share personal dessert creations");
                break;
            case "Post and share personal dessert creations":
                this.currentState = "Post and Share";
                break;
            case "Home":
                this.currentState = "Home"; // Handle Home option
                break;
            case "1":
                this.currentState = "1. View Personal Account Information";
                break;
            case "2":
                this.currentState = "2. Update Personal Account Information";
                break;
            case "3":
                this.currentState = "3. Delete Account";
                break;
            default:
                throw new IllegalArgumentException("Invalid option: " + option);
        }
    }

    public String getDisplayedUserInfo() {
        return String.format("Username: %s, Email: %s, Full Name: %s, Password: %s, Role: %d",
                this.userName != null ? this.userName : "",
                this.email != null ? this.email : "",
                this.fullName != null ? this.fullName : "",
                this.password != null ? this.password : "",
                this.role);
    }

    public String updateAccount() {
        if (!this.password.equals(this.confirmPassword)) {
            return "Password confirmation does not match";
        }
        System.out.println("Updating account for user: " + this.userName);

        // Ensure email is set
        if (this.email == null || this.email.isEmpty()) {
            User existingUser = UserDataBase.findByUsername(this.originalUsername);
            if (existingUser != null) {
                this.email = existingUser.getEmail();
            } else {
                return "User not found";
            }
        }

        System.out.println("Updating account for user with email: " + this.email);
        User user = UserDataBase.findByUsername(this.originalUsername);
        if (user != null) {
            user.setUsername(this.userName);
            user.setFullname(this.fullName);
            user.setPassword(this.password);
            user.setEmail(this.email); // Ensure email is updated
            UserDataBase.save(user);
            this.currentState = "Initial state";
            return "Account updated successfully";
        }
        System.out.println("User not found with email: " + this.email);
        return "User not found";
    }

    public String confirmDeleteAccount() {
        return "Account deletion confirmed";
    }

    public String deleteAccount() {
        UserDataBase.deleteByUsername(this.userName);
        this.currentState = "Login state";
        return "Account deleted successfully";
    }

    public void logout() {
        this.currentState = "Login state";
    }
}
