package org.example.StateController.Login;

import org.example.Account.Person;
import org.example.Database.UserDataBase;
import org.example.StateController.*;
import org.example.StateController.Admin.AdminState;
import org.example.StateController.User.UserState;

import java.util.Scanner;
public class LogInState implements State {
    private Context context;

    public LogInState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Email :");
        String email = scanner.nextLine();
        filterState(email);
        System.out.println("Password :");
        String password = scanner.nextLine();
        filterState(password);
        for (Person p : UserDataBase.getDb()) {
            if (p.getEmail().equals(email) && p.getPassword().equals(password)) {
                switch (p.getRole()) {
                    case 0:
                        context.setCurrentState(new UserState(context));
                        break;
                    case 1:
                        context.setCurrentState(new MaterialSuplierState(context));
                        break;
                    case 2:
                        context.setCurrentState(new StoreOwnerState(context));
                        break;
                    case 3:
                        context.setCurrentState(new AdminState(context));
                        break;
                }
                context.handleInput();
            }
        }
        System.out.println("User Not Found!");
        context.handleInput();
    }

    private boolean filterState(String input) {
        if (context.isBack(input)) {
            context.setCurrentState(new WelcomeState(context));
            context.handleInput();
            return true;
        } else if (context.isExit(input)) {
            context.setCurrentState(new ExitState(context));
            context.handleInput();
            return true;
        }
        return false;
    }

}