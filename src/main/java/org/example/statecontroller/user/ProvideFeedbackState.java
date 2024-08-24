package org.example.statecontroller.user;
import org.example.database.feedback.Feedback;
import org.example.database.feedback.FeedbackDataBase;
import org.example.database.UserDataBase;
import org.example.statecontroller.Context;
import org.example.statecontroller.ExitState;
import org.example.statecontroller.State;

import java.util.Scanner;
import java.util.logging.Logger;

public class ProvideFeedbackState implements State {
    private Logger logger = Logger.getLogger(ProvideFeedbackState.class.getName());
    private String feedbackMessage;
    private String feedbackId;
    private Context context;

    public void setFeedbackMessage(String feedbackMessage) {
        this.feedbackMessage = feedbackMessage;
    }
    public void setFeedbackId(String feedbackId) {
        this.feedbackId = feedbackId;
    }
    public ProvideFeedbackState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Scanner scanner = new Scanner(System.in);
        logger.info("Please provide your feedback on purchased products or shared recipes:");
        if(!context.isTest())
            feedbackMessage = scanner.nextLine();

        context.filterState(feedbackMessage);


        if(!context.isTest())
            feedbackId = generateFeedbackId();

        Feedback feedback = new Feedback(feedbackId, UserDataBase.getLoggedIn().getUsername(), feedbackMessage);
        FeedbackDataBase.addFeedback(feedback);

        logger.info("Thank you for your feedback!");

        context.setCurrentState(new UserState(context));
        if(context.isTest())
            context.setCurrentState(new ExitState());
        context.handleInput();
    }



    public String generateFeedbackId() {
        return "FB" + (FeedbackDataBase.getFeedbacks().size() + 1);
    }
}