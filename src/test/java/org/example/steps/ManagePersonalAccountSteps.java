package org.example.steps;

import org.example.Account.User;
import org.example.Database.UserDataBase;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import java.util.List;
import java.util.Arrays;
import static org.junit.Assert.*;

public class ManagePersonalAccountSteps {
    private static User user;
    private UserDataBase userDataBase;
    private String lastMessage;

    @Before
    public void setUp() {
        userDataBase = new UserDataBase();
        System.out.println("Setting up initial users...");
        UserDataBase.initialUsers();
    }

    @Given("the user has logged in with username {string} and password {string}")
    public void the_user_has_logged_in_with_username_and_password(String username, String password) {
        user = new User();
        user.setUsername(username);
        user.setPassword(password);
        boolean loginSuccessful = user.login();
        assertTrue("User login failed", loginSuccessful);
        user.setOriginalUsername(username); // Set original username after login
        user.setEmail(UserDataBase.findByUsername(username).getEmail()); // Set email after login
    }

    @Given("the user has passed the Login state")
    public void the_user_has_passed_the_login_state() {
        user = new User();
        user.setUsername("user1");
        user.setPassword("password1");
        boolean loginSuccessful = user.login();
        assertTrue("User login failed", loginSuccessful);
        user.setOriginalUsername("user1"); // Set original username after login
        user.setEmail(UserDataBase.findByUsername("user1").getEmail()); // Set email after login
    }

    @Given("the user is in the {string} state")
    public void the_user_is_in_the_state(String state) {
        assertNotNull("User object is null", user);
        user.selectOption(state);
        assertEquals("User not in expected state", state, user.getCurrentState());
    }

    @Then("the user should see a {string} option")
    public void the_user_should_see_a_option(String option) {
        List<String> options = user.getAvailableOptions();
        assertNotNull("Options list is null", options);
        assertTrue("Manage Account option not available", options.contains(option));
    }

    @When("the user selects {string}")
    public void the_user_selects(String option) {
        user.selectOption(option);
    }

    @Then("the system should transition the user to the {string} state")
    public void the_system_should_transition_the_user_to_the_state(String state) {
        assertEquals("User not in expected state", state, user.getCurrentState());
    }

    @When("the user selects option {int}")
    public void the_user_selects_option(Integer option) {
        user.selectOption(option.toString());
    }

    @Then("the following user options should appear:")
    public void the_following_user_options_should_appear(io.cucumber.datatable.DataTable dataTable) {
        List<String> expectedOptions = dataTable.asList(String.class);
        List<String> actualOptions = user.getAvailableOptions();
        assertNotNull("Actual options list is null", actualOptions);
        assertTrue("Options do not match", actualOptions.containsAll(expectedOptions) && expectedOptions.containsAll(actualOptions));
    }

    @Then("the following options should appear: {int}. View Personal Account Information {int}. Update Personal Account Information {int}. Delete Account")
    public void the_following_options_should_appear_view_personal_account_information_update_personal_account_information_delete_account(Integer int1, Integer int2, Integer int3) {
        List<String> expectedOptions = Arrays.asList(
                int1 + ". View Personal Account Information",
                int2 + ". Update Personal Account Information",
                int3 + ". Delete Account"
        );
        List<String> actualOptions = user.getAvailableOptions();
        assertNotNull("Actual options list is null", actualOptions);
        assertTrue("Options do not match", actualOptions.containsAll(expectedOptions) && expectedOptions.containsAll(expectedOptions));
    }

    @Then("the system should display the user's current account information")
    public void the_system_should_display_the_user_s_current_account_information() {
        String userInfo = user.getDisplayedUserInfo();
        assertNotNull("User information is null", userInfo);
        assertTrue("Username is missing", userInfo.contains(user.getUsername() != null ? user.getUsername() : ""));
        assertTrue("Email is missing", userInfo.contains(user.getEmail() != null ? user.getEmail() : ""));
        assertTrue("Full name is missing", userInfo.contains(user.getFullname() != null ? user.getFullname() : ""));
        assertTrue("Password is missing", userInfo.contains(user.getPassword() != null ? user.getPassword() : ""));
        assertTrue("Role is missing", userInfo.contains(String.valueOf(user.getRole())));
    }

    @Then("the displayed information should include email, username, full name, password, and role")
    public void the_displayed_information_should_include_email_username_full_name_password_and_role() {
        String userInfo = user.getDisplayedUserInfo();
        assertNotNull("User information is null", userInfo);
        assertTrue("User information is incorrect", userInfo.contains(user.getUsername() != null ? user.getUsername() : "") &&
                userInfo.contains(user.getEmail() != null ? user.getEmail() : "") &&
                userInfo.contains(user.getFullname() != null ? user.getFullname() : "") &&
                userInfo.contains(user.getPassword() != null ? user.getPassword() : "") &&
                userInfo.contains(String.valueOf(user.getRole())));
    }

    @When("the user updates the \"username\" to {string}")
    public void the_user_updates_the_username_to(String newUsername) {
        user.setUsername(newUsername);
    }

    @When("the user updates the \"full name\" to {string}")
    public void the_user_updates_the_full_name_to(String newFullName) {
        user.setFullname(newFullName);
    }

    @When("the user updates the \"password\" to {string}")
    public void the_user_updates_the_password_to(String newPassword) {
        user.setPassword(newPassword);
    }

    @When("the user confirms the \"password\" as {string}")
    public void the_user_confirms_the_password_as(String confirmPassword) {
        user.setConfirmPassword(confirmPassword);
    }

    @Then("the system should validate that the new password matches the confirmation")
    public void the_system_should_validate_that_the_new_password_matches_the_confirmation() {
        assertEquals("Password confirmation does not match", user.getPassword(), user.getConfirmPassword());
    }

    @Then("the system should save the updated account information")
    public void the_system_should_save_the_updated_account_information() {
        System.out.println("Updating account for user: " + user.getUsername());
        lastMessage = user.updateAccount();
        System.out.println("Update account result: " + lastMessage);
        assertEquals("Account updated successfully", lastMessage);
    }

    @Then("the system should display a success message {string}")
    public void the_system_should_display_a_success_message(String message) {
        assertEquals(message, lastMessage);
    }

    @Then("the system should return the user to the initial state (state after login)")
    public void the_system_should_return_the_user_to_the_initial_state_state_after_login() {
        user.setCurrentState(user.getInitialPostLoginState());
        assertEquals(user.getInitialPostLoginState(), user.getCurrentState());
    }

    @Then("the system should prompt the user to update their account information")
    public void the_system_should_prompt_the_user_to_update_their_account_information() {
        user.setUsername("newusername");
        user.setFullname("newfullname");
        user.setPassword("newpassword");
        user.setConfirmPassword("newpassword");
    }

    @Then("the system should confirm the deletion of the user's account")
    public void the_system_should_confirm_the_deletion_of_the_user_s_account() {
        lastMessage = user.confirmDeleteAccount();
        assertEquals("Account deletion confirmed", lastMessage);
    }

    @Then("if confirmed, the system should delete the user's account")
    public void if_confirmed_the_system_should_delete_the_user_s_account() {
        lastMessage = user.confirmDeleteAccount();
        assertEquals("Account deletion confirmed", lastMessage);
        lastMessage = user.deleteAccount();
        assertEquals("Account deleted successfully", lastMessage);
    }

    @Then("the user account should be deleted")
    public void the_user_account_should_be_deleted() {
        lastMessage = user.deleteAccount();
        assertEquals("Account deleted successfully", lastMessage);
    }

    @Then("the system should log the user out")
    public void the_system_should_log_the_user_out() {
        user.logout();
        assertEquals("Login state", user.getCurrentState());
    }

    @Then("the system should redirect the user to the login state")
    public void the_system_should_redirect_the_user_to_the_login_state() {
        assertEquals("Login state", user.getCurrentState());
    }

    @Then("the system should display a message {string}")
    public void the_system_should_display_a_message(String message) {
        assertEquals(message, lastMessage);
    }
}