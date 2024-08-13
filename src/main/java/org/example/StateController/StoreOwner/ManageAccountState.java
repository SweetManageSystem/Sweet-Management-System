package org.example.StateController.StoreOwner;

import org.example.Account.Person;
import org.example.Database.UserDataBase;
import org.example.StateController.Context;
import org.example.StateController.ExitState;
import org.example.StateController.State;

import java.util.Scanner;

public class ManageAccountState implements State {
    private Context context;

    public ManageAccountState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Scanner in = new Scanner(System.in);
        System.out.println("Manage Account \n" +
                "1.Edit UserName          2.Edit Email\n" +
                "3.Edit Password          4.Edit Full Name\n");

        String command = in.nextLine();
        filterState(command);
        switch (command) {
            case "1":
                System.out.println("Enter the new UserName : ");
                command = in.nextLine();
                filterState(command);
                if (!alreadyExists(command)) {
                    UserDataBase.getLoggedIn().setUsername(command);
                }
                break;
            case "2":
                System.out.println("Enter the new Email : ");
                command = in.nextLine();
                filterState(command);
                if (!alreadyExists(command)) {
                    UserDataBase.getLoggedIn().setEmail(command);
                }
                break;
            case "3":
                System.out.println("Enter the new Password : ");
                command = in.nextLine();
                filterState(command);
                UserDataBase.getLoggedIn().setPassword(command);
                break;
            case "4":
                System.out.println("Enter the new Full Name : ");
                command = in.nextLine();
                filterState(command);
                UserDataBase.getLoggedIn().setFullname(command);
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
        context.setCurrentState(new StoreOwnerState(context));
        context.handleInput();
    }

    private void filterState(String input) {
        if (context.isBack(input)) {
            context.setCurrentState(new StoreOwnerState(context));
            context.handleInput();
        } else if (context.isExit(input)) {
            context.setCurrentState(new ExitState(context));
            context.handleInput();
        }
    }

    private boolean alreadyExists(String input) {
        boolean isEmail = input.matches("[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}");
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