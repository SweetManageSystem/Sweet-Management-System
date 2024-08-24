package org.example.statecontroller.user;

import org.example.statecontroller.Context;
import org.example.statecontroller.ExitState;
import org.example.statecontroller.login.LogInState;
import org.example.statecontroller.State;

import java.util.Scanner;
import java.util.logging.Logger;

public class UserState implements State {
    private Logger logger = Logger.getLogger(UserState.class.getName());
    private String command;
    private Context context;

    public void setCommand(String command) {
        this.command = command;
    }
    public UserState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Scanner scanner = new Scanner(System.in);
        String textBlock = """
                     <html>
                          <body>
                                <tag>
                                   Welcome to the user state:<br>
                                      1.Manage Account           2.Post Dessert Creations<br>
                                      3.Search for recipes       4.Filter Recipes<br>
                                      5.Purchase a dessert       6.Message Store Owners<br>
                                      7.Provide Feedback         8.Log Out
                                </tag>
                          </body>
                    </html>""";
        logger.info(textBlock);
            if(!context.isTest())
                command = scanner.nextLine();
            context.filterState(command);

            switch (command) {
                case "1":
                    context.setCurrentState(new ManageMyAccount(context));
                    isTestCase();
                    break;
                case "2":
                    context.setCurrentState(new PostState(context));
                    isTestCase();
                    break;
                case "3":
                    context.setCurrentState(new SearchState(context));
                    isTestCase();
                    break;
                case "4":
                    context.setCurrentState(new FilterRecipsState(context));
                    isTestCase();
                    break;
                case "5":
                    context.setCurrentState(new PurchaseState(context));
                    isTestCase();
                    break;
                case "6":
                    context.setCurrentState(new MessageStoresState(context));
                    isTestCase();
                    break;
                case "7":
                    context.setCurrentState(new ProvideFeedbackState(context));
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

        public void isTestCase(){
            if(context.isTest())
                context.setCurrentState(new ExitState());
        }


    }