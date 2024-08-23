package org.example.statecontroller.storeowner;

import org.example.database.UserDataBase;
import org.example.account.Person;
import org.example.statecontroller.Context;
import org.example.statecontroller.State;

import java.util.Scanner;
import java.util.logging.Logger;

public class MessageState implements State {
    private Context context;
    private Scanner input;
    private Logger logger = Logger.getLogger(MessageState.class.getName());

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
        if (input.hasNextLine()) {
            String command = input.nextLine();
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
                    context.handleInput();
                    break;
                default:
                    logger.info("Invalid command");
                    context.handleInput();
                    break;
            }
        }
    }

    private void sendMessageToUser() {
        logger.info("Enter the username of the user:");
        String username = input.nextLine();
        Person user = UserDataBase.getPersonByUsername(username);
        if (user != null) {
            logger.info("Enter your message:");
            String message = input.nextLine();
            UserDataBase.getPerson(user.getEmail()).recieveMessage(message);
            logger.info("Message sent to " + user.getUsername());
        } else {
            logger.info("User not found");
        }

        handleInput();
    }

    private void readMessageFromUser() {
        for(String message : UserDataBase.getLoggedIn().getMessages()){
            logger.info(message);
        }
    }




}