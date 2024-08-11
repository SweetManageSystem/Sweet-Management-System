package org.example.StateController.User;

import org.example.StateController.Admin.*;
import org.example.StateController.Context;
import org.example.StateController.ExitState;
import org.example.StateController.Login.LogInState;
import org.example.StateController.State;

import java.util.Scanner;

public class UserState implements State {
    private Context context;

    public UserState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the user state: \n"+
                "1.Manage Account           2.Post Dessert Creations\n"+
                "3.Search for recipes       4.Filter Recipes\n" +
                "5.Purchase a dessert       6.Message Store Owners\n" +
                "7.Provide Feedback         8.Log Out");
        String command = scanner.nextLine();
        filterState(command);
        switch (command) {
            case "1":
                context.setCurrentState(new ManageMyAccount(context));
                context.handleInput();
            case "2":
                context.setCurrentState(new PostState(context));
                context.handleInput();
            case "3":
                context.setCurrentState(new SearchState(context));
                context.handleInput();
            case "4":
                context.setCurrentState(new FilterRecipsState(context));
                context.handleInput();
            case "5":
                context.setCurrentState(new PurchaseState(context));
                context.handleInput();
            case "6":
                context.setCurrentState(new MessageStoresState(context));
                context.handleInput();
            case"7":
                context.setCurrentState(new ProvideFeedbackState(context));
                context.handleInput();
            case "8":
                context.setCurrentState(new LogInState(context));
                context.handleInput();
            default:
                System.out.println("Invalid command");
                context.handleInput();
        }

    }

    private void filterState(String input){
        if(context.isBack(input)){
            context.setCurrentState(new LogInState(context));
            context.handleInput();
        }
        else if(context.isExit(input)){
            context.setCurrentState(new ExitState(context));
            context.handleInput();
        }
    }
}
