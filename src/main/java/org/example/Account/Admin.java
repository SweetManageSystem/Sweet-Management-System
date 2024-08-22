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


    public String getCurrentState() {
        return currentState;
    }

    public void setEmailForAction(String email) {
        this.emailForAction = email;
    }

    @Override
    public void recieveMessage(String message) {
        // Implementation for receiving a message
    }

    @Override
    public List<String> getMessages() {
        return List.of(); // Return an empty list or actual messages if implemented
    }

    @Override
    public String getAddress() {
        return "admin";
    }

    @Override
    public void setAddress(String address) {

    }
}
