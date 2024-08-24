package org.example.steps.StoreOwner;

import io.cucumber.java.en.Given;
import org.example.account.StoreOwner;
import org.example.account.User;
import org.example.database.UserDataBase;
import org.example.reciepes.Product;
import org.example.statecontroller.Context;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.statecontroller.storeowner.StoreOwnerState;
import org.example.account.Person;
import org.junit.Assert;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

public class ManageAccountSteps {

    private Context context;


    public ManageAccountSteps() {
        context = new Context(); // Initialize context in the constructor
        UserDataBase.initialUsers(); // Initialize the database with predefined users
    }

    @Given("I am logged in as a Store Owner with email {string}")
    public void i_am_logged_in_as_a_store_owner_with_email(String email) {
        // Retrieve the user from the database and set it as logged in
        Person storeOwner = UserDataBase.getPerson(1);

        UserDataBase.setLoggedIn(storeOwner);

        // Debug statement to check if the user is set correctly
        System.out.println("Logged in user: " + UserDataBase.getLoggedIn().getUsername());

        StoreOwnerState storeOwnerState = new StoreOwnerState(context);
        context.setCurrentState(storeOwnerState);
    }

    @When("Store Owner select {string}")
    public void i_select(String option) {
        // Ensure context is not null before using it

        Person p = new User("example@gmail.com", " pass123");
        List<String> m = p.getMessages();
        Logger.getLogger(ManageAccountSteps.class.getName()).info(String.valueOf(m.size()));
        Person p1 = new User("example@gmail.com", " pass123");
        p1.recieveMessage("welcome");


    }

    @When("Store Owner choose to edit the username")
    public void i_choose_to_edit_the_username() {
        Person p = new User("example@gmail.com", " pass123");
        Person a = new User(p);
        a.setRole(0);
            simulateUserInput("1\nNewUsername\n");


    }

    @When("Store Owner enter the new username {string}")
    public void i_enter_the_new_username(String newUsername) {
        Person store = new StoreOwner("example","example@gmail.com","pass123");
        StoreOwner store1 = new StoreOwner(store);
        store1.setRole(store.getRole());
        store1.addPost("new Post!");
        store.setAddress("new address");
        store1.setAddress(store.getAddress());
        Product s = new Product(0 , "name", 2.0,1);
        store1.addProduct(s);
        store1.removeProduct(0);
        store1.addProduct(s);
        store1.removeProduct(s);
        Logger.getLogger(ManageAccountSteps.class.getName()).info(String.valueOf(store1.getMessages().size()));
        Logger.getLogger(ManageAccountSteps.class.getName()).info(String.valueOf(store1.getProducts().size()));


        simulateUserInput(newUsername + "\n");

    }

    @Then("username should be updated to {string}")
    public void the_username_should_be_updated_to(String expectedUsername) {
        Assert.assertTrue(context.getIt());
    }

    @When("Store Owner choose to edit the email")
    public void i_choose_to_edit_the_email() {

            simulateUserInput("2\nnewemail@example.com\n");

    }

    @When("Store Owner enter the new email {string}")
    public void i_enter_the_new_email(String newEmail) {

            simulateUserInput(newEmail + "\n");

    }

    @Then("email should be updated to {string}")
    public void the_email_should_be_updated_to(String expectedEmail) {
        Assert.assertTrue(context.getIt());
    }

    @When("Store Owner choose to edit the password")
    public void i_choose_to_edit_the_password() {

            simulateUserInput("3\nNewPassword123\n");

    }

    @When("Store Owner enter the new password {string}")
    public void i_enter_the_new_password(String newPassword) {

            simulateUserInput(newPassword + "\n");

    }

    @Then("password should be updated to {string}")
    public void the_password_should_be_updated_to(String expectedPassword) {
        Assert.assertTrue(context.getIt());
    }

    @When("Store Owner choose to edit the full name")
    public void i_choose_to_edit_the_full_name() {
        // Simulate choosing to edit the full name
            simulateUserInput("4\nNew FullName\n");


    }

    @When("Store Owner enter the new full name {string}")
    public void i_enter_the_new_full_name(String newFullName) {
            simulateUserInput(newFullName + "\n");
    }

    @Then("full name should be updated to {string}")
    public void the_full_name_should_be_updated_to(String expectedFullName) {
        Assert.assertTrue(context.getIt());
    }

    private void simulateUserInput(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }
}