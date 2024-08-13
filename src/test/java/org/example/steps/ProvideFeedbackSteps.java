package org.example.steps;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;

public class ProvideFeedbackSteps {

    private boolean isLoggedIn = false;
    private boolean hasPurchasedProduct = false;
    private boolean hasViewedRecipe = false;
    private boolean feedbackProvided = false;
    private String confirmationMessage = "";

    @Given("the user is logged in for feedback")
    public void the_user_is_logged_in_for_feedback() {
        isLoggedIn = true;
    }

    @Given("the user has purchased a product")
    public void the_user_has_purchased_a_product() {
        if (isLoggedIn) {
            hasPurchasedProduct = true;
        }
    }

    @Given("the user has viewed a shared recipe")
    public void the_user_has_viewed_a_shared_recipe() {
        if (isLoggedIn) {
            hasViewedRecipe = true;
        }
    }

    @When("the user provides feedback on the purchased product")
    public void the_user_provides_feedback_on_the_purchased_product() {
        if (hasPurchasedProduct) {
            feedbackProvided = true;
            confirmationMessage = "Feedback on purchased product submitted!";
        }
    }

    @When("the user provides feedback on the shared recipe")
    public void the_user_provides_feedback_on_the_shared_recipe() {
        if (hasViewedRecipe) {
            feedbackProvided = true;
            confirmationMessage = "Feedback on shared recipe submitted!";
        }
    }

    @Then("the feedback should be recorded")
    public void the_feedback_should_be_recorded() {
        assertTrue(feedbackProvided);
    }

    @Then("the user should see a confirmation of feedback submission")
    public void the_user_should_see_a_confirmation_of_feedback_submission() {
        System.out.println("Confirmation Message: " + confirmationMessage);
        assertTrue(confirmationMessage.equals("Feedback on purchased product submitted!") ||
                confirmationMessage.equals("Feedback on shared recipe submitted!"));
    }
}