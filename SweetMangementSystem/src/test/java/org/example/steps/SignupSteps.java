package org.example.steps;

import io.cucumber.java.en.*;
import org.example.GUI.LogIn.SignUpForm;
import org.example.Account.DataBase;
import org.junit.Assert;

public class SignupSteps {
    private SignUpForm signUpForm;
    private String displayedMessage;

    @Given("the user is on the signup page")
    public void theUserIsOnTheSignupPage() {
        DataBase.initialUsers(); // Initialize users
        signUpForm = new SignUpForm();
        signUpForm.setVisible(true);
    }

    @When("the user enters valid details")
    public void theUserEntersValidDetails() {
        signUpForm.getFullNameField().setText("John Doe");
        signUpForm.getEmailField().setText("john.doe@example.com");
        signUpForm.getUsernameField().setText("johndoe");
        signUpForm.getPasswordField().setText("password123");
        signUpForm.getConfirmPasswordField().setText("password123");
    }

    @When("the user enters invalid details")
    public void theUserEntersInvalidDetails() {
        signUpForm.getFullNameField().setText("");
        signUpForm.getEmailField().setText("");
        signUpForm.getUsernameField().setText("");
        signUpForm.getPasswordField().setText("");
        signUpForm.getConfirmPasswordField().setText("");
    }

    @When("the user enters an existing email")
    public void theUserEntersAnExistingEmail() {
        signUpForm.getFullNameField().setText("John Doe");
        signUpForm.getEmailField().setText("admin@gmail.com"); // Existing email
        signUpForm.getUsernameField().setText("newuser");
        signUpForm.getPasswordField().setText("password123");
        signUpForm.getConfirmPasswordField().setText("password123");
    }

    @When("the user enters an existing username")
    public void theUserEntersAnExistingUsername() {
        signUpForm.getFullNameField().setText("John Doe");
        signUpForm.getEmailField().setText("newuser@example.com");
        signUpForm.getUsernameField().setText("admin"); // Existing username
        signUpForm.getPasswordField().setText("password123");
        signUpForm.getConfirmPasswordField().setText("password123");
    }

    @When("the user enters mismatched passwords")
    public void theUserEntersMismatchedPasswords() {
        signUpForm.getFullNameField().setText("John Doe");
        signUpForm.getEmailField().setText("john.doe@example.com");
        signUpForm.getUsernameField().setText("johndoe");
        signUpForm.getPasswordField().setText("password123");
        signUpForm.getConfirmPasswordField().setText("password456"); // Mismatched password
    }

    @When("the user clicks the signup button")
    public void theUserClicksTheSignupButton() {
        signUpForm.getSignUpButton().doClick();
        displayedMessage = signUpForm.getDisplayedMessage();
    }

    @Then("the user should see a success message")
    public void theUserShouldSeeASuccessMessage() {
        Assert.assertEquals("Account added successfully", displayedMessage);
    }

    @Then("the user should see an error message")
    public void theUserShouldSeeAnErrorMessage() {
        Assert.assertEquals("Please fill in all fields", displayedMessage);
    }

    @Then("the user should see an email exists message")
    public void theUserShouldSeeAnEmailExistsMessage() {
        Assert.assertEquals("Email already exists", displayedMessage);
    }

    @Then("the user should see a username exists message")
    public void theUserShouldSeeAUsernameExistsMessage() {
        Assert.assertEquals("Username already exists", displayedMessage);
    }

    @Then("the user should see a passwords mismatch message")
    public void theUserShouldSeeAPasswordsMismatchMessage() {
        Assert.assertEquals("Passwords do not match", displayedMessage);
    }
}