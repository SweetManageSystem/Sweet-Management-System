package org.example.statecontroller.storeowner;

import org.example.database.UserDataBase;
import org.example.account.Person;
import org.example.statecontroller.Context;
import org.example.statecontroller.ExitState;
import org.example.statecontroller.State;

import java.util.Scanner;
import java.util.logging.Logger;

public class MessageState implements State {
    private Context context;
    private Scanner input;
    private Logger logger = Logger.getLogger(MessageState.class.getName());
    private String command;
    private String username;
    private String message;



    public void setCommand(String command) {
        this.command = command;

    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageState(Context context) {
        this.context = context;
        this.input = new Scanner(System.in); // Default to standard input
    }

    // Constructor for dependency injection
    public MessageState(Context context, Scanner input) {
        this.context = context;
        this.input = input;
    }

    @Override
    public void handleInput() {
        String textBlock = """
                     <html>
                          <body>
                                <tag>
                                 Messaging System<br>
                                 1. Send a Message      2. Read Messages<br>
                                 3.Back
                                </tag>
                          </body>
                    </html>""";
        logger.info(textBlock);
            if(!context.isTest())
                command = input.nextLine();
            context.filterState(command);

            switch (command) {
                case "1":
                    sendMessageToUser();
                    break;
                case "2":
                    readMessageFromUser();
                    break;
                case "3":
                    context.setCurrentState(new StoreOwnerState(context));
                    if (context.isTest())
                        context.setCurrentState(new ExitState());
                    context.handleInput();
                    break;
                default:
                    logger.info("Invalid command");
                    if (context.isTest())
                        context.setCurrentState(new ExitState());
                    context.handleInput();
                    break;
            }


    }

    private void sendMessageToUser() {
        logger.info("Enter the username of the user:");
        if (!context.isTest())
            username = input.nextLine();
        Person user = UserDataBase.getPersonByUsername(username);
        if (user != null) {
            logger.info("Enter your message:");
            if (!context.isTest())
                message = input.nextLine();
            UserDataBase.getPerson(user.getEmail()).recieveMessage(message);
            logger.info("Message sent to " + user.getUsername());
        } else {
            logger.info("User not found");
        }
        if(!context.isTest())
            handleInput();
    }

    private void readMessageFromUser() {
        for(String message : UserDataBase.getLoggedIn().getMessages()){
            logger.info(message);
        }
    }


}