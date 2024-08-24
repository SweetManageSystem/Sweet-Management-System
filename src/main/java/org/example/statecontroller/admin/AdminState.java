package org.example.statecontroller.admin;

import org.example.statecontroller.Context;
import org.example.statecontroller.ExitState;
import org.example.statecontroller.login.LogInState;
import org.example.statecontroller.State;

import java.util.Scanner;
import java.util.logging.Logger;

public class AdminState implements State {
    private Context context;
    private  Logger logger = Logger.getLogger(AdminState.class.getName());
    private String command;

    public AdminState(Context context) {
      this.context = context;
    }

    @Override
    public void handleInput() {
        Scanner input = new Scanner(System.in);
        String textBlock = """
                     <html>
                          <body>
                                <tag>
                                    Welcome to the Admin System<br>
                                    1.Manage Users                 2.Monitor Profits<br>
                                    3.Best selling products        4.Display statistics<br>
                                    5.Manage Content               6.Manage Feedback<br>
                                    7.Log Out
                                </tag>
                          </body>
                    </html>""";
        logger.info(textBlock);
        if(!context.isTest())
         command = input.nextLine();
        context.filterState(command);
        switch (command) {
            case "1":
                AManageUsersState amanageUsersState = new AManageUsersState(context);
                if(context.isTest())
                    amanageUsersState.setCommand("exit");
                context.setCurrentState(amanageUsersState);
                break;
            case "2":
                context.setCurrentState(new MonitorProfitsState(context));
                break;
            case "3":
                context.setCurrentState(new BestSellingState(context));
                break;
            case "4":
                context.setCurrentState(new DiplayStatisticsState(context));
                break;
            case "5":
                ManageContentState manageContentState = new ManageContentState(context);
                if(context.isTest())
                    manageContentState.setCommand("exit");
                context.setCurrentState(manageContentState);
                break;
            case "6":
                ManageFeedbackState manageFeedbackState = new ManageFeedbackState(context);
                if(context.isTest())
                    context.setCurrentState(new ExitState());
                context.setCurrentState(manageFeedbackState);
                break;
            case "7":
                LogInState logInState = new LogInState(context);
                if(context.isTest())
                    context.setCurrentState(new ExitState());
                context.setCurrentState(logInState);
                break;
            default:
                logger.info("Invalid command");
                break;
        }
        context.handleInput();

    }

    public void setCommand(String command) {
        this.command = command;
    }

}
