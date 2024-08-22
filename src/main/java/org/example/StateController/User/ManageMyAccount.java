package org.example.StateController.User;

import org.example.Account.Person;
import org.example.Database.UserDataBase;
import org.example.StateController.Context;
import org.example.StateController.ExitState;
import org.example.StateController.State;
import org.example.StateController.StoreOwner.StoreOwnerState;

import java.util.Scanner;

public class ManageMyAccount implements State {
    private Context context;
    public ManageMyAccount(Context context){
        this.context = context;
    }

    @Override
    public void handleInput() {
        Scanner in = new Scanner(System.in);
        System.out.println("Manage Account \n" +
                "1.Edit UserName          2.Edit Email\n" +
                "3.Edit Password          4.Edit Full Name\n");

        String command = in.nextLine();
        context.filterState(command);

        switch (command) {
            case "1":
                System.out.println("Enter the new UserName : ");
                command = in.nextLine();
                context.filterState(command);
                if (!alreadyExists(command)) {
                    UserDataBase.getLoggedIn().setUsername(command);
                }
                break;
            case "2":
                System.out.println("Enter the new Email : ");
                command = in.nextLine();
                context.filterState(command);
                if (!alreadyExists(command)) {
                    UserDataBase.getLoggedIn().setEmail(command);
                }
                break;
            case "3":
                System.out.println("Enter the new Password : ");
                command = in.nextLine();
                context.filterState(command);
                UserDataBase.getLoggedIn().setPassword(command);
                break;
            case "4":
                System.out.println("Enter the new Full Name : ");
                command = in.nextLine();
                context.filterState(command);
                UserDataBase.getLoggedIn().setFullname(command);
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }

        context.setCurrentState(new UserState(context));
        context.handleInput();
    }


    private boolean alreadyExists(String input) {
        boolean isEmail = input.matches("[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}");
        for (Person person : UserDataBase.getDb()) {
            if (!isEmail && person.getUsername().equals(input)) {
                return true;
            } else if (isEmail && person.getEmail().equals(input)) {
                return true;
            }
        }
        return false;
    }
}