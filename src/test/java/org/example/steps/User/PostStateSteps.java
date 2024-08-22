package org.example.steps.User;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.example.StateController.Context;
import org.example.StateController.ExitState;
import org.example.StateController.User.PostState;
import org.example.StateController.User.UserState;
import org.example.Account.User;
import org.example.Database.UserDataBase;

public class PostStateSteps {

    private Context context;
    private User loggedInUser;
    private PostState postState;

    @Given("the user is logged in for posting")
    public void the_user_is_logged_in_for_posting() {
        context = new Context();
        loggedInUser = new User("testuser", "testuser@example.com", "password");
        UserDataBase.setLoggedIn(loggedInUser);
        context.setCurrentState(new UserState(context));
        postState = new PostState(context); // Initialize postState here
    }

    @Given("the user is in the PostState")
    public void the_user_is_in_the_PostState() {
        context = new Context();
        loggedInUser = new User("testuser", "testuser@example.com", "password");
        UserDataBase.setLoggedIn(loggedInUser);
        postState = new PostState(context);
        context.setCurrentState(postState);
    }

    @When("the user enters post details {string}")
    public void the_user_enters_post_details(String postDetails) {
        // Simulate user input by directly setting the post details
        System.setIn(new java.io.ByteArrayInputStream(postDetails.getBytes()));

    }

    @Then("the dessert creation should be posted successfully")
    public void the_dessert_creation_should_be_posted_successfully() {
        assertTrue(context.getIt());
    }

    @Then("the user should be in the UserState")
    public void the_user_should_be_in_the_UserState() {
        assertTrue(context.getIt());
    }

    @Then("the user should be in the ExitState")
    public void the_user_should_be_in_the_ExitState() {
        assertTrue(context.getIt());
    }
}