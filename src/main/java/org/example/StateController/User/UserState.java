package org.example.StateController.User;

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
        System.out.println("Welcome to the user state: \n" +
                "1.Manage Account           2.Post Dessert Creations\n" +
                "3.Search for recipes       4.Filter Recipes\n" +
                "5.Purchase a dessert       6.Message Store Owners\n" +
                "7.Provide Feedback         8.Log Out");


            String command = scanner.nextLine();
            context.filterState(command);

            switch (command) {
                case "1":
                    context.setCurrentState(new ManageMyAccount(context));
                    break;
                case "2":
                    context.setCurrentState(new PostState(context));
                    break;
                case "3":
                    context.setCurrentState(new SearchState(context));
                    break;
                case "4":
                    context.setCurrentState(new FilterRecipsState(context));
                    break;
                case "5":
                    context.setCurrentState(new PurchaseState(context));
                    break;
                case "6":
                    context.setCurrentState(new MessageStoresState(context));
                    break;
                case "7":
                    context.setCurrentState(new ProvideFeedbackState(context));
                    break;
                case "8":
                    context.setCurrentState(new LogInState(context));
                    break;
                default:
                    System.out.println("Invalid command");
                    break;
            }

            context.handleInput();
        }


    }