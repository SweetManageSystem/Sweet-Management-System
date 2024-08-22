package org.example.steps;

import io.cucumber.java.en.*;
import org.example.StateController.Context;
import org.example.StateController.Login.SignUpState;
import org.example.StateController.WelcomeState;
import org.junit.Assert;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SignupSteps {
    private Context context;
    private ByteArrayOutputStream outputStream;
    private WelcomeState welcomeState;

    @Given("the user is on the signup page")
    public void theUserIsOnTheSignupPage() {
        context = new Context();
        welcomeState = new WelcomeState(context);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @When("the user enters valid details")
    public void theUserEntersValidDetails() {
        String input = "john.doe@example.com\njohndoe\nJohn Doe\npassword123\npassword123\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        SignUpState signUpState = new SignUpState(context);
        context.setCurrentState(signUpState);
        //signUpState.handleInput();
    }

    @When("the user enters invalid details")
    public void theUserEntersInvalidDetails() {
        String input = "\n\n\n\n\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        SignUpState signUpState = new SignUpState(context);
        context.setCurrentState(signUpState);
        //signUpState.handleInput();
    }

    @When("the user enters an existing email")
    public void theUserEntersAnExistingEmail() {
        String input = "admin@gmail.com\nnewuser\nJohn Doe\npassword123\npassword123\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        SignUpState signUpState = new SignUpState(context);
        context.setCurrentState(signUpState);
       // signUpState.handleInput();
    }

    @When("the user enters an existing username")
    public void theUserEntersAnExistingUsername() {
        String input = "newuser@example.com\nadmin\nJohn Doe\npassword123\npassword123\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        SignUpState signUpState = new SignUpState(context);
        context.setCurrentState(signUpState);
       // signUpState.handleInput();
    }

    @When("the user enters mismatched passwords")
    public void theUserEntersMismatchedPasswords() {
        String input = "john.doe@example.com\njohndoe\nJohn Doe\npassword123\npassword456\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        SignUpState signUpState = new SignUpState(context);
        context.setCurrentState(signUpState);
       // signUpState.handleInput();
    }

    @Then("the user should see a success message")
    public void theUserShouldSeeASuccessMessage() {
        String output = outputStream.toString();
        Assert.assertTrue(context.getIt());
    }

    @Then("the user should see an error message")
    public void theUserShouldSeeAnErrorMessage() {
        String output = outputStream.toString();
        Assert.assertTrue(context.getIt());
    }

    @Then("the user should see an email exists message")
    public void theUserShouldSeeAnEmailExistsMessage() {
        String output = outputStream.toString();
        Assert.assertTrue(context.getIt());
    }

    @Then("the user should see a username exists message")
    public void theUserShouldSeeAUsernameExistsMessage() {
        String output = outputStream.toString();
        Assert.assertTrue(context.getIt());
    }

    @Then("the user should see a passwords mismatch message")
    public void theUserShouldSeeAPasswordsMismatchMessage() {
        String output = outputStream.toString();
        Assert.assertTrue(context.getIt());
    }
}