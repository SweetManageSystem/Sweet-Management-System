package org.example.statecontroller.user;

import org.example.account.Person;
import org.example.account.StoreOwner;
import org.example.database.UserDataBase;
import org.example.statecontroller.Context;
import org.example.statecontroller.ExitState;
import org.example.statecontroller.State;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class MessageStoresState implements State {
    private Context context;
    private Logger logger = Logger.getLogger(MessageStoresState.class.getName());
    private String choice;
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }
    public void setChoice(String choice) {
        this.choice = choice;
    }

    public MessageStoresState(Context context) {
        this.context = context;
    }
    @Override
    public void handleInput() {
        Scanner scanner = new Scanner(System.in);
        List<Person> storeOwners = UserDataBase.getDb().stream()
                .filter(person -> person instanceof StoreOwner)
                .toList();

        if (storeOwners.isEmpty()) {
            logger.info("No store owners available.");
            context.setCurrentState(new UserState(context));
            context.handleInput();
        }
        logger.info("Select a store owner to message:");
        for (int i = 0; i < storeOwners.size(); i++) {
            logger.info((i + 1) + ". " + storeOwners.get(i).getUsername());
        }
        if(!context.isTest())
            choice = scanner.nextLine();
        context.filterState(choice);
        if (Integer.valueOf(choice) < 1 || Integer.valueOf(choice) > storeOwners.size()) {
            logger.info("Invalid choice.");
            context.setCurrentState(new UserState(context));
            if(context.isTest())
                context.setCurrentState(new ExitState());
            context.handleInput();
            return;
        }
        Person selectedStoreOwner =  storeOwners.get(Integer.valueOf(choice) - 1);
        logger.info("Enter your message:");
        if(!context.isTest())
            message = scanner.nextLine();
        context.filterState(message);
        selectedStoreOwner.recieveMessage(message);
        logger.info("Message sent to " + selectedStoreOwner.getUsername());
        context.setCurrentState(new UserState(context));
        if(context.isTest())
            context.setCurrentState(new ExitState());
        context.handleInput();

    }
}
