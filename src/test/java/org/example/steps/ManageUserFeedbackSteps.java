package org.example.steps;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class ManageUserFeedbackSteps {

    private boolean isAdminLoggedIn = false;
    private List<String> feedbackList = new ArrayList<>();

    @Given("the admin is logged in for feedback management")
    public void the_admin_is_logged_in_for_feedback_management() {
        isAdminLoggedIn = true;
        // Initialize some sample feedback
        feedbackList.add("Great recipe!");
        feedbackList.add("Could use more sugar.");
    }

    @When("the admin views the list of user feedback")
    public void the_admin_views_the_list_of_user_feedback() {
        assertTrue(isAdminLoggedIn);
    }

    @Then("the list of user feedback should be displayed")
    public void the_list_of_user_feedback_should_be_displayed() {
        assertFalse(feedbackList.isEmpty());
    }

    @When("the admin deletes feedback with content {string}")
    public void the_admin_deletes_feedback_with_content(String content) {
        feedbackList.remove(content);
    }

    @Then("the feedback with content {string} should be removed")
    public void the_feedback_with_content_should_be_removed(String content) {
        assertFalse(feedbackList.contains(content));
    }
}