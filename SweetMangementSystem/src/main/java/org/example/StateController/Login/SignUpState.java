package org.example.StateController.Login;

import org.example.Account.Person;
import org.example.Account.User;
import org.example.Database.UserDataBase;
import org.example.StateController.Context;
import org.example.StateController.ExitState;
import org.example.StateController.State;
import org.example.StateController.WelcomeState;

import java.util.Scanner;

public class SignUpState implements State{
    private Context context;

    public SignUpState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Person person = new User();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Email :");
        String input = scanner.nextLine();
        filterState(input);
        person.setEmail(input);
        System.out.println("UserName :");
        input = scanner.nextLine();
        filterState(input);
        person.setUsername(input);
        System.out.println("Full Name :");
        input = scanner.nextLine();
        filterState(input);
        person.setFullname(input);
        System.out.println("Password :");
        input = scanner.nextLine();
        filterState(input);
        person.setPassword(input);
        System.out.println("Confirm Password :");
        input = scanner.nextLine();
        filterState(input);
        if(!person.getPassword().equals(input))
            context.handleInput();
        UserDataBase.addPerson(person);
        System.out.println("Account created successfully");
        context.setCurrentState(new WelcomeState(context));
        context.handleInput();

    }


    private void filterState(String input){
        if(context.isBack(input)){
            context.setCurrentState(new WelcomeState(context));
            context.handleInput();
        }
        else if(context.isExit(input)){
            context.setCurrentState(new ExitState(context));
            context.handleInput();
        }
    }
}
