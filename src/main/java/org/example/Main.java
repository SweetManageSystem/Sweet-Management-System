package org.example;


import org.example.database.feedback.FeedbackDataBase;
import org.example.database.ProductDataBase;
import org.example.database.UserDataBase;

import org.example.statecontroller.Context;
import org.example.statecontroller.WelcomeState;

public class Main {
    public static void main(String[] args) {
        UserDataBase.initialUsers();
        ProductDataBase.initialProduct();
        Context context =  new Context();
        FeedbackDataBase.initializeFeedbacks();
        context.setCurrentState(new WelcomeState(context));
        context.handleInput();

    }
}