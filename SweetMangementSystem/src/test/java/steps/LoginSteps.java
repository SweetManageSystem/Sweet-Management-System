
package steps;
import static org.junit.Assert.assertEquals;

import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.GUI.LogIn.LogInForm;

public class LoginSteps {

    private LogInForm loginFrame;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;
    private String message;

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        loginFrame = new LogInForm();
        passwordField = loginFrame.getPasswordField();
        emailField = loginFrame.getEmailField();
    }



    @When("the user enters email {string} and password {string}")
    public void the_user_enters_username_and_password(String email, String password) {
        emailField.setText(email);
        passwordField.setText(password);
    }

    @And("clicks on the login button")
    public void clicks_on_the_login_button() {
        loginFrame.loginButtonActionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
    }

    @Then("the user will see a message {string}")
    public void the_user_will_see_a_message(String expectedMessage) {
        // Simulate showing a dialog and capture the message
        message = loginFrame.getDisplayedMessage();
        assertEquals(expectedMessage, message);
    }
}