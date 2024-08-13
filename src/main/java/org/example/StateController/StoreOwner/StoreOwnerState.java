package org.example.StateController.StoreOwner;

import org.example.StateController.Admin.*;
import org.example.StateController.Context;
import org.example.StateController.ExitState;
import org.example.StateController.Login.LogInState;
import org.example.StateController.State;

import java.util.Scanner;

public class StoreOwnerState implements State {
    private Context context;
    private Scanner input;

    public StoreOwnerState(Context context) {
        this.context = context;
        this.input = new Scanner(System.in); // Default to standard input
    }

    // Constructor for dependency injection
    public StoreOwnerState(Context context, Scanner input) {
        this.context = context;
        this.input = input;
    }

    @Override
    public void handleInput() {
        System.out.println("Welcome to the Store Owner System\n" +
                "1. Manage Account          2. Monitor Profits\n" +
                "3. Best selling products   4. Make a Discount\n" +
                "5. Adjust Products         6. Track Order\n" +
                "7. Message Users           8. Log Out");
        if (input.hasNextLine()) {
            String command = input.nextLine();
            filterState(command);

            switch (command) {
                case "1":
                    context.setCurrentState(new ManageAccountState(context));
                    context.handleInput();
                    break;
                case "2":
                    context.setCurrentState(new MonitorSalesState(context));
                    context.handleInput();
                    break;
                case "3":
                    context.setCurrentState(new BestSellingState(context));
                    context.handleInput();
                    break;
                case "4":
                    context.setCurrentState(new DiscountState(context));
                    context.handleInput();
                    break;
                case "5":
                    context.setCurrentState(new AdjustProduct(context));
                    context.handleInput();
                    break;
                case "6":
                    context.setCurrentState(new TrackOrderState(context));
                    context.handleInput();
                    break;
                case "7":
                    context.setCurrentState(new MessageState(context));
                    context.handleInput();
                    break;
                case "8":
                    context.setCurrentState(new LogInState(context));
                    context.handleInput();
                    break;
                default:
                    System.out.println("Invalid command");
                    context.handleInput();
                    break;
            }
        }
    }

    private void filterState(String input) {
        if (context.isBack(input)) {
            context.setCurrentState(new LogInState(context));
            context.handleInput();
        } else if (context.isExit(input)) {
            context.setCurrentState(new ExitState(context));
            context.handleInput();
        }
    }
}