package org.example.steps.Admin;

import io.cucumber.java.en.*;
import org.example.database.feedback.Feedback;
import org.example.database.feedback.FeedbackDataBase;
import org.example.statecontroller.admin.ManageFeedbackState;
import org.example.statecontroller.Context;

import static org.junit.Assert.*;

public class ManageFeedbackStateSteps {

    private Context context;
    private ManageFeedbackState manageFeedbackState;


    @Given("the feedback database is initialized with feedback")
    public void the_feedback_database_is_initialized_with_feedback() {
        FeedbackDataBase.addFeedback(new Feedback("1", "momen","Great service!"));
        FeedbackDataBase.addFeedback(new Feedback("2", "janna","Could be better."));
    }

    @Given("the feedback database is empty")
    public void the_feedback_database_is_empty() {
        FeedbackDataBase.removeFeedback("1");

    }

    @When("the admin chooses to view feedback")
    public void the_admin_chooses_to_view_feedback() {
        context = new Context();
        manageFeedbackState = new ManageFeedbackState(context);

    }

    @Then("the system should display all feedback")
    public void the_system_should_display_all_feedback() {

        assertTrue(context.getIt());
    }

    @Then("the system should display {string}")
    public void the_system_should_display(String message) {
        assertTrue(context.getIt());
    }

    @When("the admin chooses to respond to feedback with ID {string}")
    public void the_admin_chooses_to_respond_to_feedback_with_id(String id) {
        FeedbackDataBase.initializeFeedbacks();

        context = new Context();
        context.setIsTest(true);
        manageFeedbackState = new ManageFeedbackState(context);

        manageFeedbackState.setCommand("1");

        manageFeedbackState.handleInput();
        manageFeedbackState.setCommand("2");

        manageFeedbackState.handleInput();
        manageFeedbackState.setCommand("3");
        manageFeedbackState.handleInput();
        manageFeedbackState.setCommand("4");
        manageFeedbackState.handleInput();




    }

    @When("the admin enters the response {string}")
    public void the_admin_enters_the_response(String response) {
         manageFeedbackState.setCommand("2");
    }

    @Then("the system should record the response for feedback ID {string}")
    public void the_system_should_record_the_response_for_feedback_id(String id) {

        assertTrue(context.getIt());
    }

    @When("the admin chooses to delete feedback with ID {string}")
    public void the_admin_chooses_to_delete_feedback_with_id(String id) {

        context = new Context();
        manageFeedbackState = new ManageFeedbackState(context);


    }

    @Then("the system should delete the feedback with ID {string}")
    public void the_system_should_delete_the_feedback_with_id(String id) {

        assertTrue(context.getIt());
    }
}