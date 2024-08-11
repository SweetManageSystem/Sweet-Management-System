package org.example.StateController;

import org.example.StateController.Login.LogInState;
import org.example.StateController.Login.SignUpState;

import java.util.Scanner;

public class WelcomeState implements State{
    private Context context;

    public WelcomeState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        System.out.println("Welcome To Our System\n" + "1.Log In    2.Sign Up");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if(context.isExit(input)){
            context.setCurrentState(new ExitState(context));
            context.handleInput();
        }
        else if(input.equals("1")) {
            context.setCurrentState(new LogInState(context));
            context.handleInput();

        }
        else if(input.equals("2")) {
            context.setCurrentState(new SignUpState(context));
            context.handleInput();
        }

    }


}
