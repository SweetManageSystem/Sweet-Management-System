package org.example.statecontroller.admin;

import org.example.statecontroller.Context;
import org.example.statecontroller.login.LogInState;
import org.example.statecontroller.State;

import java.util.Scanner;
import java.util.logging.Logger;

public class AdminState implements State {
    private Context context;
    private  Logger logger = Logger.getLogger(AdminState.class.getName());


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
        String command = input.nextLine();
        context.filterState(command);
        switch (command) {
            case "1":
                context.setCurrentState(new AManageUsersState(context));
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
                context.setCurrentState(new ManageContentState(context));
                break;
            case "6":
                context.setCurrentState(new ManageFeedbackState(context));
                break;
            case "7":
                context.setCurrentState(new LogInState(context));
                break;
            default:
                logger.info("Invalid command");
                break;
        }
        context.handleInput();

    }


}
