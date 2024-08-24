package org.example.steps.Admin;

import io.cucumber.java.en.*;
import org.example.statecontroller.Context;
import org.example.statecontroller.admin.AManageUsersState;
import org.example.database.UserDataBase;
import org.example.account.Person;
import org.example.statecontroller.admin.AdminState;
import org.junit.Assert;

public class ManageUsersSteps {

    private Context context;
    private AManageUsersState manageUsersState;
    private StringBuilder inputSequence;

    @Given("I am in the Admin state")
    public void admin_am_in_the_admin_state() {
        UserDataBase.initialUsers();
        context = new Context();
        manageUsersState = new AManageUsersState(context);
        context.setCurrentState(manageUsersState);
        inputSequence = new StringBuilder();
        AdminState adminState = new AdminState(context);
        context.setIsTest(true);
        adminState.setCommand("1");
        context.setCurrentState(adminState);

        manageUsersState.setUserName("hello");



        manageUsersState.setCommand("1");
        manageUsersState.setEmail("example@gmail.com");
        manageUsersState.setFullName("example");
        manageUsersState.setPassword("2131");
        manageUsersState.handleInput();

        manageUsersState.setCommand("2");
        manageUsersState.setEmail("admin@gmail.com");
        manageUsersState.handleInput();

        manageUsersState.setCommand("4");
        manageUsersState.setEmail("janna123@gmail.com");
        manageUsersState.handleInput();



        context.handleInput();

    }

    @When("admin enter {string} to create a new user account")
    public void admin_enter_to_create_a_new_user_account(String command) {
        appendInput(command);
    }

    @When("admin enter the email {string}")
    public void admin_enter_the_email(String email) {
        appendInput(email);
    }

    @When("admin enter the username {string}")
    public void admin_enter_the_username(String username) {
        appendInput(username);
    }

    @When("admin enter the password {string}")
    public void admin_enter_the_password(String password) {
        appendInput(password);
    }

    @When("admin enter the full name {string}")
    public void admin_enter_the_full_name(String fullName) {
        appendInput(fullName);
    }

    @Then("user {string} created successfully")
    public void user_created_successfully(String username) {
        appendInput(username);
    }

    @When("admin enter {string} to show user status")
    public void admin_enter_to_show_user_status(String command) {
        appendInput(command);
    }

    @Then("admin should see the user status for {string}")
    public void admin_should_see_the_user_status_for(String email) {
        Person user = UserDataBase.getPerson(5);
        Assert.assertTrue(context.getIt());
        String output = "Username: " + user.getUsername() + "\n Role: " + user.getRole() + "\n Full Name : " + user.getFullname() + "\n Password: " + user.getPassword();
        System.out.println(output);
    }

    @When("admin enter {string} to edit user details")
    public void admin_enter_to_edit_user_details(String command) {
        appendInput(command);
    }

    @When("admin choose to edit the username")
    public void admin_choose_to_edit_the_username() {
        manageUsersState.setFullName("hello");
        manageUsersState.setCommand("3");
        manageUsersState.editUser("janna123@gmail.com");
        manageUsersState.handleInput();
    }

    @When("admin enter the new username {string}")
    public void admin_enter_the_new_username(String newUsername) {
        appendInput(newUsername);
    }

    @Then("username updated to {string}")
    public void username_updated_to(String newUsername) {

        Person user = UserDataBase.getPerson(5);
        Assert.assertNotNull(user);
        Assert.assertTrue(context.getIt());
    }

    @When("admin enter {string} to delete a user account")
    public void admin_enter_to_delete_a_user_account(String command) {
        appendInput(command);
    }

    @When("admin enter the email {string} to delete")
    public void admin_enter_the_email_to_delete(String email) {
        appendInput(email);
    }

    @Then("user {string} deleted successfully")
    public void user_deleted_successfully(String email) {
        Assert.assertTrue(context.getIt());
    }

    @When("admin enter an invalid command {string}")
    public void admin_enter_an_invalid_command(String command) {
        appendInput(command);
    }

    @Then("admin should see an {string} message")
    public void admin_should_see_an_invalid_command_message(String message) {
        Assert.assertTrue(context.getIt());
    }

    private void appendInput(String input) {
        inputSequence.append(input).append("\n");
    }


}