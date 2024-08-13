package org.example.Account;

import org.example.Database.UserDataBase;
import org.example.Reciepes.Post;

import java.util.ArrayList;
import java.util.List;

public class Admin implements Person {
    private String email , password , userName , fullName;
    private int role = 3;
    private List<Post> posts = new ArrayList<>();


    public List<Post> getPosts() {
        return posts;
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    @Override
    public void recieveMessage(String message) {

    }

    @Override
    public List<String> getMessages() {
        return List.of();
    }

    public Admin(){}

    public Admin(Person a){
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
}
