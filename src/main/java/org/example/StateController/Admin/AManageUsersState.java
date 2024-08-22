package org.example.StateController.Admin;

import org.example.Account.Person;
import org.example.Account.User;
import org.example.Database.UserDataBase;
import org.example.StateController.Context;
import org.example.StateController.ExitState;
import org.example.StateController.Login.LogInState;
import org.example.StateController.State;

import java.util.Scanner;

public class AManageUsersState implements State {
    private Context context;

    public AManageUsersState(Context context) {
        this.context = context;
    }


    @Override
    public void handleInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Manage Users\n"+
                "1.Create a new user account        2.Show user status\n"+
                "3.Edit user details                4.Delete a user account");
        String command = scanner.nextLine();
        context.filterState(command);
        switch (command) {
            case "1":
                System.out.println("Enter the Email :");
                String email = scanner.nextLine();
                context.filterState(email);
                System.out.println("Enter UserName :");
                String userName = scanner.nextLine();
                context.filterState(userName);
                for(Person p : UserDataBase.getDb()){
                    if(p.getEmail().equals(email) || p.getUsername().equals(userName)){
                        System.out.println("Username or Email already exists");
                        context.handleInput();
                    }
                }
                System.out.println("Enter Password :");
                String password = scanner.nextLine();
                context.filterState(password);
                System.out.println("Enter Full Name :");
                String fullName = scanner.nextLine();
                context.filterState(fullName);
                Person p = new User(userName,email,password,fullName);
                System.out.println(p.getUsername());
                UserDataBase.addPerson(p);
                System.out.println("User created successfully");
                context.setCurrentState(new AdminState(context));
                context.handleInput();
            case "2":
                System.out.println("Enter the User Email :");
                String userEmail = scanner.nextLine();
                context.filterState(userEmail);
                Person person = UserDataBase.getPerson(userEmail);
                if (person != null) {
                    System.out.println( "Username: " + person.getUsername() + "\n Role: " + person.getRole() + "\n Full Name : " +person.getFullname() +"\n Password: " + person.getPassword());
                }
                else {
                    System.out.println("User not found");
                    context.handleInput();
                }
                context.setCurrentState(new AdminState(context));
                context.handleInput();
            case "3":
                System.out.println("Enter the Email :");
                String s = scanner.nextLine();
                context.filterState(s);
                editUser(s);
                System.out.println("User Edited successfully");
                context.setCurrentState(new AdminState(context));
                context.handleInput();
            case "4":
                System.out.println("Enter the User Email :");
                command = scanner.nextLine();
                context.filterState(command);
                UserDataBase.removePersonByEmail(command);
                System.out.println("User removed successfully");
                context.setCurrentState(new AdminState(context));
                context.handleInput();
            default:
                System.out.println("Invalid command");
                context.handleInput();
        }


    }



    private void editUser(String email){
        Person p = UserDataBase.getPerson(email);
        if(p == null){
            System.out.println("User not found");
            context.handleInput();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Manage Account \n" +
                "1.Edit UserName           2.Edit Password\n" +
                "3.Edit Full Name\n");
        String command = scanner.nextLine();
        context.filterState(command);
        switch (command){
            case "1":
                System.out.println("Enter new UserName :");
                command = scanner.nextLine();
                context.filterState(command);
                for (Person n : UserDataBase.getDb()) {
                    if(n.getUsername().equals(command)){
                        System.out.println("Username already exists");
                        editUser(email);
                    }
                }
                p.setUsername(command);
                break;
            case "2":
                System.out.println("Enter new Password :");
                command = scanner.nextLine();
                context.filterState(command);
                p.setPassword(command);
                break;
            case "3":
                System.out.println("Enter new Full Name :");
                command = scanner.nextLine();
                context.filterState(command);
                p.setFullname(command);
                break;
            default:
                System.out.println("Invalid command");
                editUser(email);

        }
    }
}
