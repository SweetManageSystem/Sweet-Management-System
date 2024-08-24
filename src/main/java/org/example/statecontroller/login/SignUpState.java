package org.example.statecontroller.login;

import org.example.account.Person;
import org.example.account.User;
import org.example.database.UserDataBase;
import org.example.statecontroller.Context;
import org.example.statecontroller.ExitState;
import org.example.statecontroller.State;
import org.example.statecontroller.WelcomeState;

import java.util.Scanner;
import java.util.logging.Logger;

public class SignUpState implements State{
    private Context context;
    private Logger logger = Logger.getLogger(SignUpState.class.getName());
    private String email;
    private String password;
    private String userName;
    private String fullName;

    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;

    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public SignUpState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Person person = new User();
        Scanner scanner = new Scanner(System.in);
        logger.info("Email :");
        if(!context.isTest())
            email = scanner.nextLine();
        context.filterState(email);
        person.setEmail(email);
        logger.info("UserName :");
        if(!context.isTest())
            userName = scanner.nextLine();
        context.filterState(userName);
        person.setUsername(userName);
        logger.info("Full Name :");
        if(!context.isTest())
            fullName = scanner.nextLine();
        context.filterState(fullName);
        person.setFullname(fullName);
        logger.info("Password :");
        if(!context.isTest())
            password = scanner.nextLine();
        context.filterState(password);
        person.setPassword(password);
        logger.info("Confirm Password :");
        if(!context.isTest()) {
            String input = scanner.nextLine();
            context.filterState(input);
            if (!person.getPassword().equals(input)) {
                logger.info("Passwords missmatch");
                context.handleInput();
            }
        }
        UserDataBase.addPerson(person);
        logger.info("Account created successfully");
        context.setCurrentState(new WelcomeState(context));
        if(context.isTest())
            context.setCurrentState(new ExitState());
        context.handleInput();

    }



}
