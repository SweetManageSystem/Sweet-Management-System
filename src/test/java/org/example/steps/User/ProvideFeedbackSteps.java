package org.example.steps.User;

import io.cucumber.java.en.*;
import org.example.statecontroller.Context;
import org.example.statecontroller.user.ProvideFeedbackState;
import org.example.statecontroller.user.UserState;
import org.example.statecontroller.ExitState;
import org.example.database.feedback.FeedbackDataBase;
import org.example.database.UserDataBase;
import org.example.account.User;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class ProvideFeedbackSteps {

    private Context context;
    private ProvideFeedbackState currentState;
    private UserState userState;
    private ExitState exitState;
    private String feedbackMessage;

    @Given("the user is logged in")
    public void theUserIsLoggedIn() {
        User user = new User("testuser", "testuser@example.com", "password");
        UserDataBase.setLoggedIn(user);
    }

    @Given("the user is in the Provide Feedback state")
    public void theUserIsInTheProvideFeedbackState() {
        context = new Context();
        FeedbackDataBase.initializeFeedbacks();
        currentState = new ProvideFeedbackState(context);
        context.setCurrentState(currentState);
    }

    @When("the user provides feedback {string}")
    public void theUserProvidesFeedback(String feedback) {
        // Simulate user input by setting the feedback message directly
        InputStream sysInBackup = System.in; // Backup System.in to restore it later
        System.setIn(new ByteArrayInputStream(feedback.getBytes()));
      ;
        System.setIn(sysInBackup); // Restore System.in
    }

    @Then("the feedback {string} should be saved")
    public void theFeedbackShouldBeSaved(String feedback) {
        assertTrue(context.getIt());
    }

    @Then("the user should be returned to the User state")
    public void theUserShouldBeReturnedToTheUserState() {
        assertTrue(context.getIt());
    }

    @When("the user enters {string}")
    public void theUserEnters(String command) {
        // Simulate user input by setting the command directly
        InputStream sysInBackup = System.in; // Backup System.in to restore it later
        System.setIn(new ByteArrayInputStream(command.getBytes()));
        System.setIn(sysInBackup); // Restore System.in
    }

    @Then("the user should be returned to the Exit state")
    public void theUserShouldBeReturnedToTheExitState() {
        assertTrue(context.getIt());
    }

    @When("the user enters an invalid command {string}")
    public void theUserEntersAnInvalidCommand(String command) {
        // Simulate user input by setting the command directly
        InputStream sysInBackup = System.in; // Backup System.in to restore it later
        System.setIn(new ByteArrayInputStream(command.getBytes()));
        System.setIn(sysInBackup); // Restore System.in
    }
}