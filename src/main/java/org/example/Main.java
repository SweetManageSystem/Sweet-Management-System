package org.example;


import org.example.Database.Feedback.FeedbackDataBase;
import org.example.Database.ProductDataBase;
import org.example.Database.UserDataBase;

import org.example.StateController.Context;
import org.example.StateController.WelcomeState;

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