package org.example.steps.StoreOwner;

import io.cucumber.java.en.Given;
import org.example.database.UserDataBase;
import org.example.statecontroller.Context;
import org.example.statecontroller.storeowner.ManageAccountState;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.statecontroller.storeowner.StoreOwnerState;
import org.example.account.Person;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ManageAccountSteps {

    private Context context;
    private ManageAccountState manageAccountState;

    public ManageAccountSteps() {
        context = new Context(); // Initialize context in the constructor
        UserDataBase.initialUsers(); // Initialize the database with predefined users
    }

    @Given("I am logged in as a Store Owner with email {string}")
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

    @When("Store Owner select {string}")
    public void i_select(String option) {
        // Ensure context is not null before using it
        if (context == null) {
            throw new IllegalStateException("Context is not initialized.");
        }

        // Simulate selecting the "Manage Account" option
        if (option.equals("Manage Account")) {
            manageAccountState = new ManageAccountState(context);
            context.setCurrentState(manageAccountState);
        }
    }

    @When("Store Owner choose to edit the username")
    public void i_choose_to_edit_the_username() {
        // Simulate choosing to edit the username
        if (manageAccountState != null) {
            simulateUserInput("1\nNewUsername\n");
            manageAccountState.handleInput();
        } else {
            throw new IllegalStateException("ManageAccountState is not initialized.");
        }
    }

    @When("Store Owner enter the new username {string}")
    public void i_enter_the_new_username(String newUsername) {
        if (manageAccountState != null) {
            simulateUserInput(newUsername + "\n");
            manageAccountState.handleInput();
        } else {
            throw new IllegalStateException("ManageAccountState is not initialized.");
        }
    }

    @Then("username should be updated to {string}")
    public void the_username_should_be_updated_to(String expectedUsername) {
        assert UserDataBase.getLoggedIn().getUsername().equals(expectedUsername);
    }

    @When("Store Owner choose to edit the email")
    public void i_choose_to_edit_the_email() {
        // Simulate choosing to edit the email
        if (manageAccountState != null) {
            simulateUserInput("2\nnewemail@example.com\n");
            manageAccountState.handleInput();
        } else {
            throw new IllegalStateException("ManageAccountState is not initialized.");
        }
    }

    @When("Store Owner enter the new email {string}")
    public void i_enter_the_new_email(String newEmail) {
        if (manageAccountState != null) {
            simulateUserInput(newEmail + "\n");
            manageAccountState.handleInput();
        } else {
            throw new IllegalStateException("ManageAccountState is not initialized.");
        }
    }

    @Then("email should be updated to {string}")
    public void the_email_should_be_updated_to(String expectedEmail) {
        assert UserDataBase.getLoggedIn().getEmail().equals(expectedEmail);
    }

    @When("Store Owner choose to edit the password")
    public void i_choose_to_edit_the_password() {
        // Simulate choosing to edit the password
        if (manageAccountState != null) {
            simulateUserInput("3\nNewPassword123\n");
            manageAccountState.handleInput();
        } else {
            throw new IllegalStateException("ManageAccountState is not initialized.");
        }
    }

    @When("Store Owner enter the new password {string}")
    public void i_enter_the_new_password(String newPassword) {
        if (manageAccountState != null) {
            simulateUserInput(newPassword + "\n");
            manageAccountState.handleInput();
        } else {
            throw new IllegalStateException("ManageAccountState is not initialized.");
        }
    }

    @Then("password should be updated to {string}")
    public void the_password_should_be_updated_to(String expectedPassword) {
        assert UserDataBase.getLoggedIn().getPassword().equals(expectedPassword);
    }

    @When("Store Owner choose to edit the full name")
    public void i_choose_to_edit_the_full_name() {
        // Simulate choosing to edit the full name
        if (manageAccountState != null) {
            simulateUserInput("4\nNew FullName\n");
            manageAccountState.handleInput();
        } else {
            throw new IllegalStateException("ManageAccountState is not initialized.");
        }
    }

    @When("Store Owner enter the new full name {string}")
    public void i_enter_the_new_full_name(String newFullName) {
        if (manageAccountState != null) {
            simulateUserInput(newFullName + "\n");
            manageAccountState.handleInput();
        } else {
            throw new IllegalStateException("ManageAccountState is not initialized.");
        }
    }

    @Then("full name should be updated to {string}")
    public void the_full_name_should_be_updated_to(String expectedFullName) {
        assert UserDataBase.getLoggedIn().getFullname().equals(expectedFullName);
    }

    private void simulateUserInput(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }
}