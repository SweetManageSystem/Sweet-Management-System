package org.example.steps;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;

public class PurchaseDessertsDirectlyFromStoreOwnersSteps {

    private boolean isLoggedIn = false;
    private boolean isOnStoreOwnerPage = false;
    private boolean dessertSelected = false;
    private boolean purchaseConfirmed = false;
    private boolean orderReceived = false;
    private String confirmationMessage = "";

    @Given("the user is logged in for purchase")
    public void the_user_is_logged_in_for_purchase() {
        isLoggedIn = true;
    }

    @Given("the user is on the store owner's page")
    public void the_user_is_on_the_store_owners_page() {
        if (isLoggedIn) {
            isOnStoreOwnerPage = true;
        }
    }

    @When("the user selects a dessert to purchase")
    public void the_user_selects_a_dessert_to_purchase() {
        if (isOnStoreOwnerPage) {
            dessertSelected = true;
        }
    }

    @When("the user confirms the purchase")
    public void the_user_confirms_the_purchase() {
        if (dessertSelected) {
            purchaseConfirmed = true;
            confirmationMessage = "Purchase confirmed!";
            orderReceived = true;
        }
    }

    @Then("the user should see a confirmation message")
    public void the_user_should_see_a_confirmation_message() {
        assertEquals("Purchase confirmed!", confirmationMessage);
    }

    @Then("the store owner should receive the order")
    public void the_store_owner_should_receive_the_order() {
        assertTrue(orderReceived);
    }
}