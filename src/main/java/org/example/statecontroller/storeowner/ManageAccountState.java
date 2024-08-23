package org.example.statecontroller.storeowner;

import org.example.account.Person;
import org.example.database.UserDataBase;
import org.example.statecontroller.Context;
import org.example.statecontroller.State;

import java.util.Scanner;
import java.util.logging.Logger;

public class ManageAccountState implements State {
    private Context context;
    private Logger logger = Logger.getLogger(ManageAccountState.class.getName());
    public ManageAccountState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Scanner in = new Scanner(System.in);
        String textBlock = """
                     <html>
                          <body>
                                <tag>
                                    Manage Account<br>
                                    1.Edit UserName          2.Edit Email<br>
                                    3.Edit Password          4.Edit Full Name
                                </tag>
                          </body>
                    </html>""";
        logger.info(textBlock);
        String command = in.nextLine();
        context.filterState(command);
        switch (command) {
            case "1":
                logger.info("Enter the new UserName : ");
                command = in.nextLine();
                context.filterState(command);
                if (!alreadyExists(command)) {
                    UserDataBase.getLoggedIn().setUsername(command);
                }
                break;
            case "2":
                logger.info("Enter the new Email : ");
                command = in.nextLine();
                context.filterState(command);
                if (!alreadyExists(command)) {
                    UserDataBase.getLoggedIn().setEmail(command);
                }
                break;
            case "3":
                logger.info("Enter the new Password : ");
                command = in.nextLine();
                context.filterState(command);
                UserDataBase.getLoggedIn().setPassword(command);
                break;
            case "4":
                logger.info("Enter the new Full Name : ");
                command = in.nextLine();
                context.filterState(command);
                UserDataBase.getLoggedIn().setFullname(command);
                break;
            default:
                logger.info("Invalid choice");
                break;
        }
        context.setCurrentState(new StoreOwnerState(context));
        context.handleInput();
    }


    private boolean alreadyExists(String input) {
        boolean isEmail = input.matches("[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}");
        for (Person person : UserDataBase.getDb()) {
            if(!isEmail) {
                return (person.getUsername().equals(input));
            }
            else {
                return (person.getEmail().equals(input));
            }
        }
        return false;
    }
}