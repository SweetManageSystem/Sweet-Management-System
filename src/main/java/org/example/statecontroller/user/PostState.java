package org.example.statecontroller.user;

import org.example.database.UserDataBase;
import org.example.statecontroller.Context;
import org.example.statecontroller.State;

import java.util.Scanner;
import java.util.logging.Logger;

public class PostState implements State {
    private Logger logger = Logger.getLogger(PostState.class.getName());


    private Context context;

    public PostState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Scanner scanner = new Scanner(System.in);
        logger.info("Enter your dessert creation details:");

        String postDetails = scanner.nextLine();
        context.filterState(postDetails);


        UserDataBase.getLoggedIn().addPost(postDetails);
        logger.info("Your dessert creation has been posted successfully!");

        context.setCurrentState(new UserState(context));
        context.handleInput();
    }


}