package org.example.StateController.StoreOwner;

import org.example.Database.UserDataBase;
import org.example.Account.Person;
import org.example.StateController.Context;
import org.example.StateController.ExitState;
import org.example.StateController.State;

import java.util.Scanner;

public class MessageState implements State {
    private Context context;
    private Scanner input;

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
        System.out.println("Messaging System\n" +
                "1. Send a Message      2. Read Messages");

        if (input.hasNextLine()) {
            String command = input.nextLine();
            filterState(command);

            switch (command) {
                case "1":
                    sendMessageToUser();
                    break;
                case "2":
                    readMessageFromUser();
                    break;
                default:
                    System.out.println("Invalid command");
                    context.handleInput();
                    break;
            }
        }
    }

    private void sendMessageToUser() {
        System.out.println("Enter the username of the user:");
        String username = input.nextLine();
        Person user = UserDataBase.getPersonByUsername(username);
        if (user != null) {
            System.out.println("Enter your message:");
            String message = input.nextLine();
            UserDataBase.getPerson(user.getEmail()).recieveMessage(message);
            System.out.println("Message sent to " + user.getUsername());
        } else {
            System.out.println("User not found");
        }

        handleInput();
    }

    private void readMessageFromUser() {
        for(String message : UserDataBase.getLoggedIn().getMessages()){
            System.out.println(message);
        }
    }

    private void filterState(String input){
        if(context.isBack(input)){
            context.setCurrentState(new StoreOwnerState(context));
            context.handleInput();
        }
        else if(context.isExit(input)){
            context.setCurrentState(new ExitState(context));
            context.handleInput();
        }

    }


}