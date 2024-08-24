package org.example.database.feedback;



import java.util.ArrayList;
import java.util.List;

public class FeedbackDataBase {
    private static List<Feedback> feedbacks = new ArrayList<>();

    private FeedbackDataBase(){
        throw new IllegalStateException("FeedbackDataBase class");
    }
    public static List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public static void addFeedback(Feedback feedback) {
        feedbacks.add(feedback);
    }

    public static void removeFeedback(String id) {
        feedbacks.removeIf(feedback -> feedback.getId().equals(id));
    }

    public static Feedback getFeedbackById(String id) {
        for (Feedback feedback : feedbacks) {
            if (feedback.getId().equals(id)) {
                return feedback;
            }
        }
        return null;
    }


    public static void initializeFeedbacks() {
        addFeedback(new Feedback("1", "Janna", "Great product!"));
        addFeedback(new Feedback("2", "Khalid", "Could be improved."));
        addFeedback(new Feedback("3", "Khalid", "Excellent customer service."));
        addFeedback(new Feedback("4", "Momen", "Not satisfied with the quality."));
        addFeedback(new Feedback("5", "Janna", "Fast shipping, very happy!"));
    }
}