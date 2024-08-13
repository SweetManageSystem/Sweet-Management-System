package org.example.steps;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;

public class CommunicationsAndFeedbackSteps {

    private boolean isLoggedIn = false;
    private boolean isOnStoreOwnerPage = false;
    private boolean isOnSupplierPage = false;
    private boolean messageSent = false;
    private boolean messageReceived = false;
    private String confirmationMessage = "";

    @Given("the user is logged in for communication")
    public void the_user_is_logged_in_for_communication() {
        isLoggedIn = true;
    }

    @Given("the user is on the store owner's page for communication")
    public void the_user_is_on_the_store_owners_page_for_communication() {
        if (isLoggedIn) {
            isOnStoreOwnerPage = true;
        }
    }

    @Given("the user is on the supplier's page")
    public void the_user_is_on_the_suppliers_page() {
        if (isLoggedIn) {
            isOnSupplierPage = true;
        }
    }

    @When("the user sends a message to the store owner")
    public void the_user_sends_a_message_to_the_store_owner() {
        if (isOnStoreOwnerPage) {
            messageSent = true;
            messageReceived = true;
            confirmationMessage = "Message sent to store owner!";
        }
    }

    @When("the user sends a message to the supplier")
    public void the_user_sends_a_message_to_the_supplier() {
        if (isOnSupplierPage) {
            messageSent = true;
            messageReceived = true;
            confirmationMessage = "Message sent to supplier!";
        }
    }

    @Then("the store owner should receive the message")
    public void the_store_owner_should_receive_the_message() {
        assertTrue(messageReceived);
    }

    @Then("the supplier should receive the message")
    public void the_supplier_should_receive_the_message() {
        assertTrue(messageReceived);
    }

    @Then("the user should see a confirmation of message sent to store owner")
    public void the_user_should_see_a_confirmation_of_message_sent_to_store_owner() {
        assertEquals("Message sent to store owner!", confirmationMessage);
    }

    @Then("the user should see a confirmation of message sent to supplier")
    public void the_user_should_see_a_confirmation_of_message_sent_to_supplier() {
        assertEquals("Message sent to supplier!", confirmationMessage);
    }
}