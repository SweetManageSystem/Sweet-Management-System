package org.example.Account;

import org.example.Database.UserDataBase;
import org.example.Reciepes.Post;
import org.example.Reciepes.Product;

import java.util.ArrayList;
import java.util.List;

public class StoreOwner implements Person {
    private String email , password , userName , fullName;
    private int role = 2;
    private List<Post> posts = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private List<String> messages = new ArrayList<>();

    public List<Post> getPosts() {
        return posts;
    }

    public List<Product> getProducts() {
        return products;
    }
    public void addProduct(Product product) {
        products.add(product);
    }
    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void removeProduct(int id) {
        for(Product product : products) {
            if(product.getId() == id) {
                products.remove(product);
                return;
            }
        }
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    @Override
    public void recieveMessage(String message) {
        messages.add(message);
    }

    @Override
    public List<String> getMessages() {
        return messages;
    }

    public StoreOwner(){}

    public StoreOwner(Person a){
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

}
