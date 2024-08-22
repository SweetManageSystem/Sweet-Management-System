package org.example.StateController.Admin;

import org.example.StateController.Context;
import org.example.StateController.ExitState;
import org.example.StateController.Login.LogInState;
import org.example.StateController.State;

import java.util.Scanner;

public class AdminState implements State {
    private Context context;

    public AdminState(Context context) {
      this.context = context;
    }

    @Override
    public void handleInput() {
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the Admin System\n" +
                "1.Manage Users                 2.Monitor Profits\n" +
                "3.Best selling products        4.Display statistics\n" +
                "5.Manage Content               6.Manage Feedback\n" +
                "7.Log Out");
        String command = input.nextLine();
        context.filterState(command);
        switch (command) {
            case "1":
                context.setCurrentState(new AManageUsersState(context));
                context.handleInput();
            case "2":
                context.setCurrentState(new MonitorProfitsState(context));
                context.handleInput();
            case "3":
                context.setCurrentState(new BestSellingState(context));
                context.handleInput();
            case "4":
                context.setCurrentState(new DiplayStatisticsState(context));
                context.handleInput();
            case "5":
                context.setCurrentState(new ManageContentState(context));
                context.handleInput();
            case "6":
                context.setCurrentState(new ManageFeedbackState(context));
                context.handleInput();
            case "7":
                context.setCurrentState(new LogInState(context));
                context.handleInput();
            default:
                System.out.println("Invalid command");
                context.handleInput();
        }

    }


}
