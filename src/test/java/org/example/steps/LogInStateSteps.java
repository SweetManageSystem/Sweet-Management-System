package org.example.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.account.Person;
import org.example.database.UserDataBase;
import org.example.statecontroller.Context;
import org.example.statecontroller.login.LogInState;
import org.example.statecontroller.State;
import org.example.statecontroller.admin.AdminState;
import org.example.statecontroller.storeowner.StoreOwnerState;
import org.example.statecontroller.user.UserState;

import java.util.Scanner;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class LogInStateSteps {

    private Context context;
    private LogInState logInState;
    private String currentState;
    private String message;

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        UserDataBase.initialUsers(); // Initialize users
        context = new Context();
        logInState = new LogInState(context);
        context.setIsTest(true);


    }

    @When("the user enters a valid email and password")
    public void the_user_enters_a_valid_email_and_password() {
        State state = context.getCurrentState();
        if (state instanceof AdminState) {
            currentState = "AdminState";
        } else if (state instanceof StoreOwnerState) {
            currentState = "StoreOwnerState";
        } else if (state instanceof UserState) {
            currentState = "UserState";
        }
        logInState.setEmail("admin@gmail.com");
        logInState.setPassword("admin");
        logInState.handleInput();
        Logger.getLogger(SignupSteps.class.getName()).info(currentState);
    }

    @When("the user enters an invalid email and password")
    public void the_user_enters_an_invalid_email_and_password() {
        message = "User Not Found!";
        Logger.getLogger(SignupSteps.class.getName()).info(message);

        logInState.setEmail("admin@gmail.com");
        logInState.setPassword("admin123");
        logInState.handleInput();
    }



    @Then("the user should be logged in successfully")
    public void the_user_should_be_logged_in_successfully() {
        assertTrue(context.getIt());
    }

    @Then("the user should see a login error message")
    public void the_user_should_see_a_login_error_message() {
        assertTrue(context.getIt());
    }
}