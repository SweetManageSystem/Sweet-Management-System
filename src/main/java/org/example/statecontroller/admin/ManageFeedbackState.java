package org.example.statecontroller.admin;

import org.example.database.feedback.*;
import org.example.database.feedback.FeedbackDataBase;
import org.example.statecontroller.Context;
import org.example.statecontroller.State;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class ManageFeedbackState implements State {

    private Context context;
    private Logger logger = Logger.getLogger(ManageFeedbackState.class.getName());

    public ManageFeedbackState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Scanner input = new Scanner(System.in);
        String textBlock = """
                     <html>
                          <body>
                                <tag>
                                   Manage Feedback<br>
                                   1. View Feedback       2. Respond to Feedback<br>
                                   3. Delete Feedback     4.Back
                                  </tag>
                          </body>
                    </html>""";
        logger.info(textBlock);
        String command = input.nextLine();
        context.filterState(command);

        switch (command) {
            case "1":
                viewFeedback();
                break;
            case "2":
                respondToFeedback(input);
                break;
            case "3":
                deleteFeedback(input);
                break;
            case "4":
                context.setCurrentState(new AdminState(context));
                context.handleInput();
                break;
            default:
                logger.info("Invalid command");
                context.handleInput();
                break;
        }
    }

    private void viewFeedback() {
        List<Feedback> feedbacks = FeedbackDataBase.getFeedbacks();
        if (feedbacks.isEmpty()) {
            logger.info("No feedback available.");
        } else {
            for (Feedback feedback : feedbacks) {
                String output = feedback.toString();
                logger.info(output);
            }
        }
        context.handleInput();
    }

    private void respondToFeedback(Scanner input) {
        logger.info("Enter Feedback ID to respond:");
        String id = input.nextLine();
        Feedback feedback = FeedbackDataBase.getFeedbackById(id);
        if (feedback != null) {
            logger.info("Enter your response:");
            feedback.setResponded(true);
            logger.info("Response recorded for feedback ID: " +id);
        } else {
            logger.info("Feedback ID not found.");
        }
        context.handleInput();
    }

    private void deleteFeedback(Scanner input) {
        logger.info("Enter Feedback ID to delete:");
        String id = input.nextLine();
        FeedbackDataBase.removeFeedback(id);
        logger.info("Feedback deleted with ID: "+id);
        context.handleInput();
    }


}