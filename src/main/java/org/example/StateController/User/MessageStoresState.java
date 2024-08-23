package org.example.StateController.User;

import org.example.Account.Person;
import org.example.Account.StoreOwner;
import org.example.Database.UserDataBase;
import org.example.StateController.Context;
import org.example.StateController.ExitState;
import org.example.StateController.State;

import java.util.List;
import java.util.Scanner;

public class MessageStoresState implements State {
    private Context context;
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
            System.out.println("No store owners available.");
            context.setCurrentState(new UserState(context));
            context.handleInput();
        }
        System.out.println("Select a store owner to message:");
        for (int i = 0; i < storeOwners.size(); i++) {
            System.out.println((i + 1) + ". " + storeOwners.get(i).getUsername());
        }
        String choice = scanner.nextLine();
        context.filterState(choice);
        if (Integer.valueOf(choice) < 1 || Integer.valueOf(choice) > storeOwners.size()) {
            System.out.println("Invalid choice.");
            context.setCurrentState(new UserState(context));
            context.handleInput();
            return;
        }
        Person selectedStoreOwner =  storeOwners.get(Integer.valueOf(choice) - 1);
        System.out.println("Enter your message:");
        String message = scanner.nextLine();
        context.filterState(message);
        selectedStoreOwner.recieveMessage(message);
        System.out.println("Message sent to " + selectedStoreOwner.getUsername());
        context.setCurrentState(new UserState(context));
        context.handleInput();

    }
}
