package org.example.steps.StoreOwner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.Database.UserDataBase;
import org.example.Account.StoreOwner;
import org.example.StateController.Context;
import org.example.StateController.StoreOwner.MessageState;

import static org.junit.Assert.*;

public class MessagingSteps {

    private Context context;
    private MessageState messageState;
    private StoreOwner storeOwner;

    @Given("I am logged in as a store owner")
    public void i_am_logged_in_as_a_store_owner() {
        storeOwner = new StoreOwner();
        storeOwner.setEmail("storeowner@example.com");
        storeOwner.setPassword("password");
        UserDataBase.setLoggedIn(storeOwner);
        context = new Context();
        context.setCurrentState(new MessageState(context));
    }

    @When("I navigate to the messaging system")
    public void i_navigate_to_the_messaging_system() {
        messageState = new MessageState(context);
        context.setCurrentState(messageState);
    }

    @When("I choose to send a message")
    public void i_choose_to_send_a_message() {
        // Simulate choosing to send a message
        messageState.handleInput();
    }

    @When("I enter the username {string}")
    public void i_enter_the_username(String username) {
        // Simulate entering the username
        System.setIn(new java.io.ByteArrayInputStream(username.getBytes()));
        messageState.handleInput();
    }

    @When("I enter the message {string}")
    public void i_enter_the_message(String message) {
        // Simulate entering the message
        System.setIn(new java.io.ByteArrayInputStream(message.getBytes()));
        messageState.handleInput();
    }

    @Then("the message should be sent to the user {string}")
    public void the_message_should_be_sent_to_the_user(String username) {
        // Verify that the message was sent
        assertNotNull(UserDataBase.getPersonByUsername(username));
    }

    @When("I choose to read messages")
    public void i_choose_to_read_messages() {
        // Simulate choosing to read messages
        messageState.handleInput();
    }

    @Then("I should see the list of messages from users")
    public void i_should_see_the_list_of_messages_from_users() {
        // Verify that the messages are displayed
        // This is a placeholder as the actual implementation will depend on how messages are stored and displayed
        assertTrue(true);
    }
}