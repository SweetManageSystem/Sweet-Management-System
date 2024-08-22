package org.example.StateController.User;

import org.example.Account.User;
import org.example.Database.UserDataBase;
import org.example.StateController.Context;
import org.example.StateController.ExitState;
import org.example.StateController.State;

import java.util.Scanner;

public class PostState implements State {

    private Context context;

    public PostState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your dessert creation details:");

        String postDetails = scanner.nextLine();
        context.filterState(postDetails);


        UserDataBase.getLoggedIn().addPost(postDetails);
        System.out.println("Your dessert creation has been posted successfully!");

        context.setCurrentState(new UserState(context));
        context.handleInput();
    }


}