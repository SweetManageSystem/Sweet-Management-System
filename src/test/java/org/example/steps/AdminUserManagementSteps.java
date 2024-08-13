package org.example.steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.example.Account.Admin;
import org.example.Database.UserDataBase;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AdminUserManagementSteps {
    private Admin admin;
    private String lastMessage;
    private UserDataBase userDataBase;

    @Before
    public void setUp() {
        System.out.println("Setting up initial users...");
        UserDataBase.initialUsers();
        admin = new Admin();  // Initialize the admin object here
    }

    @Given("the admin has logged in with email {string} and password {string}")
    public void the_admin_has_logged_in_with_email_and_password(String email, String password) {
        admin = new Admin();
        admin.setEmail(email);
        admin.setPassword(password);
        boolean loginSuccessful = admin.login();
        assertTrue("Admin login failed", loginSuccessful);
    }

    @Given("the admin is in the \"User Management\" state")
    public void the_admin_is_in_the_user_management_state() {
        admin.selectOption("User Management");
        assertEquals("Admin not in User Management state", "User Management", admin.getCurrentState());
    }

    @Given("the admin is now in the {string} state")
    public void the_admin_is_now_in_the_state(String state) {
        admin.selectOption(state);
        assertEquals("Admin not in expected state", state, admin.getCurrentState());
    }

    @Then("the {string} option should appear")
    public void the_option_should_appear(String option) {
        assertTrue("Option not available", admin.getAvailableOptions().contains(option));
    }

    @Then("the following options should appear:")
    public void the_following_options_should_appear(io.cucumber.datatable.DataTable dataTable) {
        List<String> expectedOptions = dataTable.asList(String.class);
        List<String> actualOptions = admin.getAvailableOptions();
        assertTrue("Options do not match", actualOptions.containsAll(expectedOptions) && expectedOptions.containsAll(actualOptions));
    }

    @When("the admin selects {string}")
    public void the_admin_selects(String option) {
        admin.selectOption(option);
    }

    @Given("the admin has created a user with email {string}")
    public void the_admin_has_created_a_user_with_email(String email) {
        admin.createUser("tempUser", "User", email, "password123", "password123");
    }

    @When("enters the user's details: username {string}, role {string}, email {string}, password {string}, confirmPassword {string}")
    public void enters_the_user_s_details(String username, String role, String email, String password, String confirmPassword) {
        lastMessage = admin.createUser(username, role, email, password, confirmPassword);
    }

    @Then("a new user account should be created with the provided details")
    public void a_new_user_account_should_be_created_with_the_provided_details() {
        assertEquals("User Created Successfully", lastMessage);
    }

    @When("enters the user's email {string}")
    public void enters_the_user_s_email(String email) {
        admin.setEmailForAction(email);
    }

    @Then("the system should display the user's username {string}, role {string} and password {string}")
    public void the_system_should_display_the_user_s_username_role_and_password(String username, String role, String password) {
        String userInfo = admin.getDisplayedUserInfo();
        assertTrue("User information is incorrect", userInfo.contains(username) && userInfo.contains(role) && userInfo.contains(password));
    }

    @When("updates the details to: username {string}, role {string}, email {string}, password {string}")
    public void updates_the_details_to(String username, String role, String email, String password) {
        lastMessage = admin.updateUser(username, role, email, password);
    }
    @Then("the user account should be updated with the new details")
    public void the_user_account_should_be_updated_with_the_new_details() {
        assertEquals("User Updated Successfully", lastMessage);
    }

    @Then("the user account associated with {string} should be deleted")
    public void the_user_account_associated_with_should_be_deleted(String email) {
        lastMessage = admin.deleteUser(email);
        assertEquals("User Deleted Successfully", lastMessage);
    }

    @Then("the system should print {string}")
    public void the_system_should_print(String expectedMessage) {
        assertEquals("Expected message was not printed", expectedMessage, lastMessage);
    }
    @Then("the system should prints {string}")
    public void the_system_should_prints(String expectedMessage) {
        // Reuse the existing method to avoid code duplication
        the_system_should_print(expectedMessage);
    }

    @Then("the following options should appear:{int}. Create a new user account {int}. Show user status \\(based on user email, display username and role) {int}. Edit user details \\(username, role, or email) {int}. Delete a user account")
    public void the_following_options_should_appear_create_a_new_user_account_show_user_status_based_on_user_email_display_username_and_role_edit_user_details_username_role_or_email_delete_a_user_account(Integer int1, Integer int2, Integer int3, Integer int4) {
        // Implement logic to verify the appearance of these options.
    }


}