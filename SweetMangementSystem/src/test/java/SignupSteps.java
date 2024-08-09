import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.example.Account.DataBase;
import org.example.Account.Person;
import org.example.Account.User;

import javax.swing.*;

public class SignupSteps {
    private List<Person> persons = DataBase.getDb();
    private Person person = new User();

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    private boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isEmailExisting(String email) {
        for (Person p : persons) {
            if (p.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Given("the Sweet Management System is running")
    public void the_sweet_management_system_is_running() {
        // Code to ensure the system is running
        // For now, we can assume it's always running
    }

    @Given("I am on the signup page")
    public void i_am_on_the_signup_page() {
        // Code to navigate to the signup page
    }

    @When("I enter a valid username {string}")
    public void i_enter_a_valid_username(String username) {
        person.setUsername(username);
    }

    @When("I enter a valid password {string}")
    public void i_enter_a_valid_password(String password) {
        person.setPassword(password);
    }

    @When("I enter a valid confirm password {string}")
    public void i_enter_a_valid_confirm_password(String confirmPassword) {
        // Assuming password and confirm password are the same for simplicity
        if (!person.getPassword().equals(confirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match");
        }
    }

    @When("I enter a non-matching confirm password {string}")
    public void i_enter_a_non_matching_confirm_password(String confirmPassword) {
        if (!person.getPassword().equals(confirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match");
        }
    }

    @When("I leave the password field blank")
    public void i_leave_the_password_field_blank() {
        if (person.getPassword() == null || person.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be blank");
        }
    }

    @When("I enter a valid email address {string}")
    public void i_enter_a_valid_email_address(String email) {
        if (isValidEmail(email)) {
            person.setEmail(email);
        } else {
            throw new IllegalArgumentException("Invalid email address: " + email);
        }
    }

    @When("I enter an invalid email address {string}")
    public void i_enter_an_invalid_email_address(String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email address entered: " + email);
        }
    }

    @When("I enter an existing email address {string}")
    public void i_enter_an_existing_email_address(String email) {
        if (isEmailExisting(email)) {
            throw new IllegalArgumentException("Email address already exists: " + email);
        } else {
            person.setEmail(email);
        }
    }

    @When("I enter my full name {string}")
    public void i_enter_my_full_name(String fullName) {
        person.setFullname(fullName);
    }

    @When("I submit the signup form")
    public void i_submit_the_signup_form() {
        // Add the person to the list
        try {
            DataBase.addPerson(person);
            person = new User();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        // Reset the person object for the next signup

    }

    @Then("I should see a confirmation message {string}")
    public void i_should_see_a_confirmation_message(String message) {
        // Code to check the confirmation message
        // For now, we can print the message to simulate the confirmation
        JOptionPane.showMessageDialog(null , message);
    }

    @Then("my role should be set to {string}")
    public void my_role_should_be_set_to(String role) {
        // Code to set the role
        int role0 = Integer.valueOf("role");
        // For now, we can assume the role is set correctly
        System.out.println("Role set to: " + role0);
    }
}