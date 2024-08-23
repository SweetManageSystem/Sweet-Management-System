package org.example.statecontroller.login;

import org.example.account.Person;
import org.example.account.User;
import org.example.database.UserDataBase;
import org.example.statecontroller.Context;
import org.example.statecontroller.State;
import org.example.statecontroller.WelcomeState;

import java.util.Scanner;
import java.util.logging.Logger;

public class SignUpState implements State{
    private Context context;
    private Logger logger = Logger.getLogger(SignUpState.class.getName());

    public SignUpState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Person person = new User();
        Scanner scanner = new Scanner(System.in);
        logger.info("Email :");
        String input = scanner.nextLine();
        context.filterState(input);
        person.setEmail(input);
        logger.info("UserName :");
        input = scanner.nextLine();
        context.filterState(input);
        person.setUsername(input);
        logger.info("Full Name :");
        input = scanner.nextLine();
        context.filterState(input);
        person.setFullname(input);
        logger.info("Password :");
        input = scanner.nextLine();
        context.filterState(input);
        person.setPassword(input);
        logger.info("Confirm Password :");
        input = scanner.nextLine();
        context.filterState(input);
        if(!person.getPassword().equals(input))
            context.handleInput();
        UserDataBase.addPerson(person);
        logger.info("Account created successfully");
        context.setCurrentState(new WelcomeState(context));
        context.handleInput();

    }



}
