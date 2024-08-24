package org.example.statecontroller;

import org.example.statecontroller.login.LogInState;
import org.example.statecontroller.login.SignUpState;

import java.util.Scanner;
import java.util.logging.Logger;

public class WelcomeState implements State{
    private Context context;
    private Logger logger = Logger.getLogger(WelcomeState.class.getName());
    private String input;

    public WelcomeState(Context context) {
        this.context = context;
    }
    public void setInput(String input) {
        this.input = input;
    }

    @Override
    public void handleInput() {
        logger.info("Welcome To Our System\n" + "1.Log In    2.Sign Up");
        Scanner scanner = new Scanner(System.in);
        if(!context.isTest())
          input = scanner.nextLine();
        if(context.isExit(input)){
            context.setCurrentState(new ExitState());
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
