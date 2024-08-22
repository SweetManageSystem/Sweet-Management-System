package org.example.StateController.User;

import org.example.Database.Feedback.Feedback;
import org.example.Database.Feedback.FeedbackDataBase;
import org.example.Database.UserDataBase;
import org.example.StateController.Context;
import org.example.StateController.ExitState;
import org.example.StateController.State;

import java.util.Scanner;

public class ProvideFeedbackState implements State {

    private Context context;

    public ProvideFeedbackState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please provide your feedback on purchased products or shared recipes:");
        String feedbackMessage = scanner.nextLine();

        context.filterState(feedbackMessage);


        String feedbackId = generateFeedbackId();
        Feedback feedback = new Feedback(feedbackId, UserDataBase.getLoggedIn().getUsername(), feedbackMessage);
        FeedbackDataBase.addFeedback(feedback);

        System.out.println("Thank you for your feedback!");

        context.setCurrentState(new UserState(context));
        context.handleInput();
    }



    private String generateFeedbackId() {
        return "FB" + (FeedbackDataBase.getFeedbacks().size() + 1);
    }
}