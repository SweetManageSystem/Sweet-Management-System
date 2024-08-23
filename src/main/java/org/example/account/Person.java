package org.example.account;

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
     public List<String> getPosts();
     public void addPost(String post);
     public void recieveMessage(String message);
     public List<String> getMessages();
     public String getAddress();
     public void setAddress(String address);
}
