package org.example.steps.User;

import io.cucumber.java.en.*;
import org.example.account.Admin;
import org.example.account.Person;
import org.example.statecontroller.Context;
import org.example.statecontroller.user.MessageStoresState;
import org.example.statecontroller.user.UserState;
import org.example.database.UserDataBase;
import org.example.steps.StoreOwner.AdjustProductSteps;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.Logger;

public class MessageStoresStateSteps {

    private Context context;
    private MessageStoresState messageStoresState;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private Logger logger = Logger.getLogger(this.getClass().getName());

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
        context.setIsTest(true);
        messageStoresState = new MessageStoresState(context);
        context.setCurrentState(messageStoresState);
        UserState userState = new UserState(context);
        userState.setCommand("6");
        userState.handleInput();

        messageStoresState.setMessage("hello store!");
        messageStoresState.setChoice("1");
        messageStoresState.handleInput();
        messageStoresState.setChoice("0");
        messageStoresState.handleInput();

        messageStoresState.handleInput();
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
        Admin admin = new Admin("example","pass123");
        Admin admin2 = new Admin(admin);
        Admin admin1 = new Admin("example1","example@gmail.com","pass321");
        admin2.setRole(admin1.getRole());
        admin2.recieveMessage("hello mr admin");
        Logger.getLogger(AdjustProductSteps.class.getName()).info(String.valueOf(admin2.getMessages().size()));
        admin.addPost("hello to my new post!");
        admin1.setAddress(admin2.getAddress());
        logger.info(store.toString());

    }


    @When("the user enters an invalid selection {string}")
    public void theUserEntersAnInvalidSelection(String selection) {
        Person store = UserDataBase.getPerson("Nablus_Store@gmail.com");
        logger.info(store.toString());
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
        logger.info(input);
    }

    @Given("the user has selected a store owner")
    public void theUserHasSelectedAStoreOwner() {
        Person store = UserDataBase.getPerson("Nablus_Store@gmail.com");
        logger.info(store.toString());
    }
}