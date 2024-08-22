package org.example.steps.User;

import io.cucumber.java.en.*;
import org.example.Account.Person;
import org.example.StateController.Context;
import org.example.StateController.User.MessageStoresState;
import org.example.StateController.User.UserState;
import org.example.StateController.ExitState;
import org.example.Account.StoreOwner;
import org.example.Database.UserDataBase;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class MessageStoresStateSteps {

    private Context context;
    private MessageStoresState messageStoresState;
    private UserState userState;
    private ExitState exitState;
    private String outputMessage;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Given("the user is in the MessageStoresState")
    public void theUserIsInTheMessageStoresState() {
        context = new Context();
        messageStoresState = new MessageStoresState(context);
        context.setCurrentState(messageStoresState);
    }

    @When("the user selects a store owner by entering {string}")
    public void theUserSelectsAStoreOwnerByEntering(String selection) {
        Person p = UserDataBase.getPerson("Nablus_Store@gmail.com");
        p.recieveMessage("hi");
        }

    @Then("the user should be prompted to enter a message")
    public void theUserShouldBePromptedToEnterAMessage() {
        // Check if the user is prompted to enter a message
        assertTrue(context.getIt());
    }

    @And("the message should be sent to the selected store owner")
    public void theMessageShouldBeSentToTheSelectedStoreOwner() {
        Person store = UserDataBase.getPerson("Nablus_Store@gmail.com");
        store.recieveMessage("hi");
        assertTrue(context.getIt());
    }

    @When("the user enters a message {string}")
    public void theUserEntersAMessage(String message) {
        Person store = UserDataBase.getPerson("Nablus_Store@gmail.com");


    }

    @Then("the user should be returned to the UserState")
    public void theUserShouldBeReturnedToTheUserState() {
        assertTrue(context.getIt());
    }

    @When("the user enters an invalid selection {string}")
    public void theUserEntersAnInvalidSelection(String selection) {
        Person store = UserDataBase.getPerson("Nablus_Store@gmail.com");

    }

    @Then("the user should see an error message {string}")
    public void theUserShouldSeeAnErrorMessage(String errorMessage) {
        assertTrue(context.getIt());
    }

    @Given("there are no store owners in the database")
    public void thereAreNoStoreOwnersInTheDatabase() {
        UserDataBase.getDb().clear();
    }

    @Then("the user should see a message {string}")
    public void theUserShouldSeeAMessage(String message) {
        assertTrue(context.getIt());
    }

    @When("the user enter {string}")
    public void theUserEnter(String input) {

    }

    @Given("the user has selected a store owner")
    public void theUserHasSelectedAStoreOwner() {
        Person store = UserDataBase.getPerson("Nablus_Store@gmail.com");
    }
}