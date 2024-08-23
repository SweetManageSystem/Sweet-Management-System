package org.example.statecontroller.storeowner;

import org.example.statecontroller.Context;
import org.example.statecontroller.login.LogInState;
import org.example.statecontroller.State;

import java.util.Scanner;
import java.util.logging.Logger;

public class StoreOwnerState implements State {
    private Context context;
    private Scanner input;
    private Logger logger = Logger.getLogger(StoreOwnerState.class.getName());

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
        if (input.hasNextLine()) {
            String command = input.nextLine();
            context.filterState(command);

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
                    logger.info("Invalid command");
                    context.handleInput();
                    break;
            }
        }
    }


}