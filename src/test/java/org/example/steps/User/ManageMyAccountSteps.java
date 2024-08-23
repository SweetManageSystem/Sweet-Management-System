package org.example.steps.User;

import io.cucumber.java.en.Given;
import org.example.database.UserDataBase;
import org.example.statecontroller.Context;
import org.example.statecontroller.user.ManageMyAccount;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.statecontroller.storeowner.StoreOwnerState;
import org.example.account.Person;
import org.junit.Assert;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ManageMyAccountSteps {

    private Context context;
    private ManageMyAccount manageAccountState;

    public ManageMyAccountSteps() {
        context = new Context();
        UserDataBase.initialUsers();
        manageAccountState = new ManageMyAccount(context);
        context.setCurrentState(manageAccountState);
    }

    @Given("I am logged in as a User with email {string}")
    public void i_am_logged_in_as_a_store_owner_with_email(String email) {
        // Retrieve the user from the database and set it as logged in
        Person storeOwner = UserDataBase.getPerson(email);
        if (storeOwner == null) {
            throw new IllegalStateException("Store owner with email " + email + " not found in the database.");
        }
        UserDataBase.setLoggedIn(storeOwner);

        // Debug statement to check if the user is set correctly
        System.out.println("Logged in user: " + UserDataBase.getLoggedIn().getUsername());

        StoreOwnerState storeOwnerState = new StoreOwnerState(context);
        context.setCurrentState(storeOwnerState);
    }

    @When("I select {string}")
    public void i_select(String option) {


        // Simulate selecting the "Manage Account" option
        if (option.equals("Manage Account")) {
            manageAccountState = new ManageMyAccount(context);
            context.setCurrentState(manageAccountState);
        }
    }

    @When("I choose to edit the username")
    public void i_choose_to_edit_the_username() {


    }

    @When("I enter the new username {string}")
    public void i_enter_the_new_username(String newUsername) {



    }

    @Then("the username should be updated to {string}")
    public void the_username_should_be_updated_to(String expectedUsername) {
        Assert.assertTrue(context.getIt());
    }

    @When("I choose to edit the email")
    public void i_choose_to_edit_the_email() {



    }

    @When("I enter the new email {string}")
    public void i_enter_the_new_email(String newEmail) {


    }

    @Then("the email should be updated to {string}")
    public void the_email_should_be_updated_to(String expectedEmail) {
        Assert.assertTrue(context.getIt());
    }

    @When("I choose to edit the password")
    public void i_choose_to_edit_the_password() {
        simulateUserInput("Enter password");
    }

    @When("I enter the new password {string}")
    public void i_enter_the_new_password(String newPassword) {
        UserDataBase.getLoggedIn().setPassword("123");
    }

    @Then("the password should be updated to {string}")
    public void the_password_should_be_updated_to(String expectedPassword) {
        Assert.assertTrue(context.getIt());
    }

    @When("I choose to edit the full name")
    public void i_choose_to_edit_the_full_name() {
        simulateUserInput("enter full name");
    }

    @When("I enter the new full name {string}")
    public void i_enter_the_new_full_name(String newFullName) {
        UserDataBase.getLoggedIn().setFullname("newFullName");
    }

    @Then("the full name should be updated to {string}")
    public void the_full_name_should_be_updated_to(String expectedFullName) {
       Assert.assertTrue(context.getIt());
    }

    private void simulateUserInput(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }
}