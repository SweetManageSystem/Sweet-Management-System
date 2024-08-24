package org.example.steps;

import io.cucumber.java.en.*;
import org.example.statecontroller.Context;
import org.example.statecontroller.login.SignUpState;
import org.example.statecontroller.WelcomeState;
import org.junit.Assert;

import java.io.ByteArrayInputStream;
import java.util.logging.Logger;

public class SignupSteps {
    private Context context;
    private WelcomeState welcomeState;
    private Logger logger = Logger.getLogger(SignupSteps.class.getName());

    @Given("the user is on the signup page")
    public void theUserIsOnTheSignupPage() {
        context = new Context();
        welcomeState = new WelcomeState(context);
        context.setIsTest(true);
        welcomeState.setInput("2");
        welcomeState.handleInput();


    }

    @When("the user enters valid details")
    public void theUserEntersValidDetails() {
        String input = "john.doe@example.com\njohndoe\nJohn Doe\npassword123\npassword123\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        SignUpState signUpState = new SignUpState(context);
        context.setCurrentState(signUpState);
    }

    @When("the user enters invalid details")
    public void theUserEntersInvalidDetails() {
        String input = "\n\n\n\n\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        SignUpState signUpState = new SignUpState(context);
        context.setIsTest(true);
        context.setCurrentState(signUpState);
        signUpState.handleInput();

    }

    @When("the user enters an existing email")
    public void theUserEntersAnExistingEmail() {
        String input = "admin@gmail.com\nnewuser\nJohn Doe\npassword123\npassword123\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        SignUpState signUpState = new SignUpState(context);
        context.setCurrentState(signUpState);

    }

    @When("the user enters an existing username")
    public void theUserEntersAnExistingUsername() {
        String input = "newuser@example.com\nadmin\nJohn Doe\npassword123\npassword123\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        SignUpState signUpState = new SignUpState(context);
        context.setCurrentState(signUpState);
    }

    @When("the user enters mismatched passwords")
    public void theUserEntersMismatchedPasswords() {
        String input = "john.doe@example.com\njohndoe\nJohn Doe\npassword123\npassword456\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        SignUpState signUpState = new SignUpState(context);
        context.setCurrentState(signUpState);

    }

    @Then("the user should see a success message")
    public void theUserShouldSeeASuccessMessage() {
        String output = "Success";
        logger.info(output);
        Assert.assertTrue(context.getIt());
    }

    @Then("the user should see an error message")
    public void theUserShouldSeeAnErrorMessage() {
        String output = "Error";
        logger.info(output);
        Assert.assertTrue(context.getIt());
    }

    @Then("the user should see an email exists message")
    public void theUserShouldSeeAnEmailExistsMessage() {
        String output = "Email Exist";
        logger.info(output);
        Assert.assertTrue(context.getIt());
    }

    @Then("the user should see a username exists message")
    public void theUserShouldSeeAUsernameExistsMessage() {
        String output = "UserName Exist";
        logger.info(output);
        Assert.assertTrue(context.getIt());
    }

    @Then("the user should see a passwords mismatch message")
    public void theUserShouldSeeAPasswordsMismatchMessage() {
        String output = "Missmatch";
        logger.info(output);
        Assert.assertTrue(context.getIt());
    }
}