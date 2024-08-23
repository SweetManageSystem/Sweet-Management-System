package org.example.statecontroller.user;

import org.example.database.feedback.Feedback;
import org.example.database.feedback.FeedbackDataBase;
import org.example.database.UserDataBase;
import org.example.statecontroller.Context;
import org.example.statecontroller.State;

import java.util.Scanner;
import java.util.logging.Logger;

public class ProvideFeedbackState implements State {
    private Logger logger = Logger.getLogger(ProvideFeedbackState.class.getName());

    private Context context;

    public ProvideFeedbackState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Scanner scanner = new Scanner(System.in);
        logger.info("Please provide your feedback on purchased products or shared recipes:");
        String feedbackMessage = scanner.nextLine();

        context.filterState(feedbackMessage);


        String feedbackId = generateFeedbackId();
        Feedback feedback = new Feedback(feedbackId, UserDataBase.getLoggedIn().getUsername(), feedbackMessage);
        FeedbackDataBase.addFeedback(feedback);

        logger.info("Thank you for your feedback!");

        context.setCurrentState(new UserState(context));
        context.handleInput();
    }



    private String generateFeedbackId() {
        return "FB" + (FeedbackDataBase.getFeedbacks().size() + 1);
    }
}