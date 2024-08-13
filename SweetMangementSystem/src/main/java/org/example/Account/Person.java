package org.example.Account;

import org.example.Reciepes.Post;

import java.util.List;

public interface Person {
     public void setUsername(String username);
     public void setPassword(String password);
     public void setFullname(String fullname);
     public void setEmail(String email);
     public String getUsername();
     public String getPassword();
     public String getFullname();
     public String getEmail();
     public void setRole(int role);
     public int getRole();
     public List<Post> getPosts();
     public void addPost(Post post);
     public void recieveMessage(String message);
     public List<String> getMessages();
}
