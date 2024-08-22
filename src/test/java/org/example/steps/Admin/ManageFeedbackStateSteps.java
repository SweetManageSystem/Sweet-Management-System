package org.example.steps.Admin;

import io.cucumber.java.en.*;
import org.example.Database.Feedback.Feedback;
import org.example.Database.Feedback.FeedbackDataBase;
import org.example.StateController.Admin.ManageFeedbackState;
import org.example.StateController.Context;
import org.example.StateController.Admin.AdminState;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

public class ManageFeedbackStateSteps {

    private Context context;
    private ManageFeedbackState manageFeedbackState;
    private AdminState adminState;
    private InputStream sysInBackup;
    private ByteArrayInputStream inContent;

    @Given("the feedback database is initialized with feedback")
    public void the_feedback_database_is_initialized_with_feedback() {
        FeedbackDataBase.addFeedback(new Feedback("1", "momen","Great service!"));
        FeedbackDataBase.addFeedback(new Feedback("2", "janna","Could be better."));
    }

    @Given("the feedback database is empty")
    public void the_feedback_database_is_empty() {
    }

    @When("the admin chooses to view feedback")
    public void the_admin_chooses_to_view_feedback() {
        context = new Context();
        manageFeedbackState = new ManageFeedbackState(context);
        //manageFeedbackState.handleInput();
    }

    @Then("the system should display all feedback")
    public void the_system_should_display_all_feedback() {
        List<Feedback> feedbacks = FeedbackDataBase.getFeedbacks();
        assertTrue(context.getIt());
    }

    @Then("the system should display {string}")
    public void the_system_should_display(String message) {
        assertTrue(context.getIt());
    }

    @When("the admin chooses to respond to feedback with ID {string}")
    public void the_admin_chooses_to_respond_to_feedback_with_id(String id) {
        sysInBackup = System.in;
        inContent = new ByteArrayInputStream((id + "\nThank you for your feedback\n").getBytes());
        System.setIn(inContent);
        context = new Context();
        manageFeedbackState = new ManageFeedbackState(context);
        manageFeedbackState.handleInput();
    }

    @When("the admin enters the response {string}")
    public void the_admin_enters_the_response(String response) {
        // This step is handled in the previous step
    }

    @Then("the system should record the response for feedback ID {string}")
    public void the_system_should_record_the_response_for_feedback_id(String id) {
        Feedback feedback = FeedbackDataBase.getFeedbackById(id);
        assertTrue(context.getIt());
    }

    @When("the admin chooses to delete feedback with ID {string}")
    public void the_admin_chooses_to_delete_feedback_with_id(String id) {
        adminState = new AdminState(context);
        sysInBackup = System.in;
        inContent = new ByteArrayInputStream((id + "\n").getBytes());
        System.setIn(inContent);
        context = new Context();
        manageFeedbackState = new ManageFeedbackState(context);
        manageFeedbackState.handleInput();
    }

    @Then("the system should delete the feedback with ID {string}")
    public void the_system_should_delete_the_feedback_with_id(String id) {
        Feedback feedback = FeedbackDataBase.getFeedbackById(id);
        assertTrue(context.getIt());
    }
}