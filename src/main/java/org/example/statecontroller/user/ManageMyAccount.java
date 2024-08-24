package org.example.statecontroller.user;

import org.example.account.Person;
import org.example.database.UserDataBase;
import org.example.statecontroller.Context;
import org.example.statecontroller.ExitState;
import org.example.statecontroller.State;
import org.example.statecontroller.storeowner.StoreOwnerState;

import java.util.Scanner;
import java.util.logging.Logger;

public class ManageMyAccount implements State {
    private Context context;
    private String command;
    private String input;

    public void setCommand(String command) {
        this.command = command;
    }
    public void setInput(String input) {
        this.input = input;
    }

    private Logger logger = Logger.getLogger(ManageMyAccount.class.getName());
    public ManageMyAccount(Context context){
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
        if(!context.isTest())
            command = in.nextLine();
        context.filterState(command);
        switch (command) {
            case "1":
                logger.info("Enter the new UserName : ");
                if(!context.isTest())
                    input = in.nextLine();
                context.filterState(input);
                if (!alreadyExists(input)) {
                    UserDataBase.getLoggedIn().setUsername(input);
                }
                break;
            case "2":
                logger.info("Enter the new Email : ");
                if(!context.isTest())
                    input = in.nextLine();
                context.filterState(input);
                if (!alreadyExists(input)) {
                    UserDataBase.getLoggedIn().setEmail(input);
                }
                break;
            case "3":
                logger.info("Enter the new Password : ");
                if(!context.isTest())
                    input = in.nextLine();
                context.filterState(input);
                UserDataBase.getLoggedIn().setPassword(input);
                break;
            case "4":
                logger.info("Enter the new Full Name : ");
                if(!context.isTest())
                    input = in.nextLine();
                context.filterState(input);
                UserDataBase.getLoggedIn().setFullname(input);
                break;
            default:
                logger.info("Invalid choice");
                break;
        }
        context.setCurrentState(new StoreOwnerState(context));
        if(!context.isTest())
            context.setCurrentState(new ExitState());
        context.handleInput();
    }


    public boolean alreadyExists(String input) {
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