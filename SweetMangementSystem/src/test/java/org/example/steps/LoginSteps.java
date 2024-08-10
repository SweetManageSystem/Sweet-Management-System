package org.example.steps;

import io.cucumber.java.en.*;
import org.example.GUI.LogIn.LogInForm;
import org.example.Account.DataBase;
import org.junit.Assert;

public class LoginSteps {
    private LogInForm logInForm;
    private String displayedMessage;

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        DataBase.initialUsers(); // Initialize users
        logInForm = new LogInForm();
        logInForm.setVisible(true);
    }

    @When("the user enters a valid email and password")
    public void theUserEntersAValidEmailAndPassword() {
        logInForm.getEmailField().setText("admin@gmail.com");
        logInForm.getPasswordField().setText("admin");
    }

    @When("the user enters an invalid email and password")
    public void theUserEntersAnInvalidEmailAndPassword() {
        logInForm.getEmailField().setText("invalid@gmail.com");
        logInForm.getPasswordField().setText("invalid");
    }

    @When("the user clicks the login button")
    public void theUserClicksTheLoginButton() {
        logInForm.getLoginButton().doClick();
        displayedMessage = logInForm.getDisplayedMessage();
    }

    @Then("the user should be logged in successfully")
    public void theUserShouldBeLoggedInSuccessfully() {
        Assert.assertEquals("Login successful", displayedMessage);
    }

    @Then("the user should see a login error message")
    public void theUserShouldSeeALoginErrorMessage() {
        Assert.assertEquals("Invalid username or password", displayedMessage);
    }
}