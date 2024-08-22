package org.example.StateController.Admin;

import org.example.Database.Feedback.*;
import org.example.Database.Feedback.FeedbackDataBase;
import org.example.StateController.Context;
import org.example.StateController.ExitState;
import org.example.StateController.State;

import java.util.List;
import java.util.Scanner;

public class ManageFeedbackState implements State {

    private Context context;

    public ManageFeedbackState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Scanner input = new Scanner(System.in);
        System.out.println("Manage Feedback\n" +
                "1. View Feedback       2. Respond to Feedback\n" +
                "3. Delete Feedback     4.Back");

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
                System.out.println("Invalid command");
                context.handleInput();
                break;
        }
    }

    private void viewFeedback() {
        List<Feedback> feedbacks = FeedbackDataBase.getFeedbacks();
        if (feedbacks.isEmpty()) {
            System.out.println("No feedback available.");
        } else {
            for (Feedback feedback : feedbacks) {
                System.out.println(feedback);
            }
        }
        context.handleInput();
    }

    private void respondToFeedback(Scanner input) {
        System.out.println("Enter Feedback ID to respond:");
        String id = input.nextLine();
        Feedback feedback = FeedbackDataBase.getFeedbackById(id);
        if (feedback != null) {
            System.out.println("Enter your response:");
            String response = input.nextLine();
            feedback.setResponded(true);
            System.out.println("Response recorded for feedback ID: " + id);
        } else {
            System.out.println("Feedback ID not found.");
        }
        context.handleInput();
    }

    private void deleteFeedback(Scanner input) {
        System.out.println("Enter Feedback ID to delete:");
        String id = input.nextLine();
        FeedbackDataBase.removeFeedback(id);
        System.out.println("Feedback deleted with ID: " + id);
        context.handleInput();
    }


}