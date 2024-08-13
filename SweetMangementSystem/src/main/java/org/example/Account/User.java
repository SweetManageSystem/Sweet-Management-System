package org.example.Account;

import org.example.Database.UserDataBase;
import org.example.Reciepes.Post;

import java.util.ArrayList;
import java.util.List;

public class User implements Person {
    private String email , password , userName , fullName, address;
    private int role = 0;
    private List<Post> posts = new ArrayList<>();
    private List<String> messages = new ArrayList<>();


    public List<Post> getPosts() {
        return posts;
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public void recieveMessage(String message) {
        messages.add(message);
    }

    @Override
    public List<String> getMessages() {
        return messages;
    }


    public User(){}

    public User(String email, String password){
        this.email = email;
        this.password = password;
        userName = null;
        fullName = null;
    }
    public User(Person a){
        this.email = a.getEmail();
        this.password = a.getPassword();
        this.userName = a.getUsername();
        this.fullName = a.getFullname();
        UserDataBase.removePerson(a);
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
