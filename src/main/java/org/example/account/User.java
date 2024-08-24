package org.example.account;

import org.example.database.UserDataBase;

import java.util.ArrayList;
import java.util.List;

public class User implements Person {
    private String email;
    private String password;
    private String userName;
    private String fullName;
    private String address;
    private String originalUsername;
    private int role = 0;
    private List<String> posts = new ArrayList<>();
    private List<String> messages = new ArrayList<>();



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


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

}
