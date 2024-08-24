package org.example.statecontroller.storeowner;

import org.example.statecontroller.Context;
import org.example.statecontroller.ExitState;
import org.example.statecontroller.login.LogInState;
import org.example.statecontroller.State;
import org.example.statecontroller.user.ManageMyAccount;

import java.util.Scanner;
import java.util.logging.Logger;

public class StoreOwnerState implements State {
    private Context context = new Context();
    private Scanner input;
    private Logger logger = Logger.getLogger(StoreOwnerState.class.getName());
    private String command;

    public void setCommand(String command) {
        this.command = command;
    }

    public StoreOwnerState(Context context) {
        this.context = context;
        this.input = new Scanner(System.in); // Default to standard input
    }


    @Override
    public void handleInput() {
        String textBlock = """
                     <html>
                          <body>
                                <tag>
                                  Welcome to the Store Owner System<br>
                                  1. Manage Account          2. Monitor Profits<br>
                                  3. Best selling products   4. Make a Discount<br>
                                  5. Adjust Products         6. Track Order<br>
                                  7. Message Users           8. Log Out
                                </tag>
                          </body>
                    </html>""";
        logger.info(textBlock);
            if(!context.isTest())
                command = input.nextLine();
            context.filterState(command);

            switch (command) {
                case "1":
                    context.setCurrentState(new ManageMyAccount(context));
                    isTestCase();
                    break;
                case "2":
                    context.setCurrentState(new MonitorSalesState(context));
                    isTestCase();
                    break;
                case "3":
                    context.setCurrentState(new BestSellingState(context));
                    isTestCase();
                    break;
                case "4":
                    context.setCurrentState(new DiscountState(context));
                    isTestCase();
                    break;
                case "5":
                    context.setCurrentState(new AdjustProduct(context));
                    isTestCase();
                    break;
                case "6":
                    context.setCurrentState(new TrackOrderState(context));
                    isTestCase();
                    break;
                case "7":
                    context.setCurrentState(new MessageState(context));
                    isTestCase();
                    break;
                case "8":
                    context.setCurrentState(new LogInState(context));
                    isTestCase();
                    break;
                default:
                    logger.info("Invalid command");
                    isTestCase();
                    break;
            }
            context.handleInput();
        }
    public void isTestCase() {
        if (context.isTest())
            context.setCurrentState(new ExitState());
    }
}



