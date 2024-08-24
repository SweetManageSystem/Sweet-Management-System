package org.example.statecontroller.login;

import org.example.account.Person;
import org.example.database.UserDataBase;
import org.example.statecontroller.*;
import org.example.statecontroller.admin.AdminState;
import org.example.statecontroller.storeowner.StoreOwnerState;
import org.example.statecontroller.user.UserState;

import java.util.Scanner;
import java.util.logging.Logger;

public class LogInState implements State {
    private Context context;
    private Logger logger = Logger.getLogger(LogInState.class.getName());
    private String email;
    private String password;


    public LogInState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Scanner scanner = new Scanner(System.in);
        logger.info("Email :");
        logger.info("Password :");
        if(!context.isTest()) {
            email = scanner.nextLine();
            password = scanner.nextLine();
        }
        context.filterState(email);
        context.filterState(password);
        for (Person p : UserDataBase.getDb()) {
            if (p.getEmail().equals(email) && p.getPassword().equals(password)) {
                switch (p.getRole()) {
                    case 0:
                        context.setCurrentState(new UserState(context));
                        break;
                    case 2:
                        context.setCurrentState(new StoreOwnerState(context));
                        break;
                    case 3:
                        AdminState adminState = new AdminState(context);
                        if(context.isTest())
                            adminState.setCommand("exit");
                        context.setCurrentState(adminState);
                        break;
                    default:
                        context.handleInput();
                        break;
                }
                UserDataBase.setLoggedIn(UserDataBase.getPerson(email));
                context.handleInput();
            }
        }
        logger.info("User Not Found!");
        context.handleInput();
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }


}