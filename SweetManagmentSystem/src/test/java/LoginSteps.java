import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class LoginSteps {

    @Given("the Sweet Management System is running")
    public void the_sweet_management_system_is_running() {
        // Write code here that turns the phrase above into concrete actions
        // For example, you might start the application server
        System.out.println("Sweet Management System is running");
    }

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        // Write code here that turns the phrase above into concrete actions
        // For example, navigate to the login page
        System.out.println("Navigated to the login page");
    }

    @When("I enter a valid username {string}")
    public void i_enter_a_valid_username(String username) {
        // Write code here that turns the phrase above into concrete actions
        // For example, enter the username into the username field
        System.out.println("Entered valid username: " + username);
    }

    @When("I enter a valid password {string}")
    public void i_enter_a_valid_password(String password) {
        // Write code here that turns the phrase above into concrete actions
        // For example, enter the password into the password field
        System.out.println("Entered valid password: " + password);
    }

    @When("I submit the login form")
    public void i_submit_the_login_form() {
        // Write code here that turns the phrase above into concrete actions
        // For example, click the submit button
        System.out.println("Submitted the login form");
    }

    @Then("I should be redirected to the dashboard")
    public void i_should_be_redirected_to_the_dashboard() {
        // Write code here that turns the phrase above into concrete actions
        // For example, check if the current page is the dashboard
        System.out.println("Redirected to the dashboard");
    }

    @Then("I should see a welcome message {string}")
    public void i_should_see_a_welcome_message(String message) {
        // Write code here that turns the phrase above into concrete actions
        // For example, check if the welcome message is displayed
        System.out.println("Saw welcome message: " + message);
    }

    @When("I enter an invalid username {string}")
    public void i_enter_an_invalid_username(String invalidUsername) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Entered invalid username: " + invalidUsername);
    }

    @When("I enter an invalid password {string}")
    public void i_enter_an_invalid_password(String invalidPassword) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Entered invalid password: " + invalidPassword);
    }

    @Then("I should see an error message {string}")
    public void i_should_see_an_error_message(String errorMessage) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Saw error message: " + errorMessage);
    }

    @When("I leave the username field blank")
    public void i_leave_the_username_field_blank() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Left the username field blank");
    }

    @When("I leave the password field blank")
    public void i_leave_the_password_field_blank() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Left the password field blank");
    }
}