package org.example.steps.Admin;

import io.cucumber.java.en.*;
import org.example.statecontroller.admin.ManageContentState;
import org.example.statecontroller.Context;
import org.example.database.ProductDataBase;
import org.example.database.UserDataBase;
import org.example.reciepes.Product;
import org.example.account.Person;
import org.example.account.User;

import java.util.Scanner;

import static org.junit.Assert.*;

public class ManageContentSteps {

    private Context context;
    private ManageContentState manageContentState;
    private Scanner input;

    @Given("the context is set to ManageContentState")
    public void setContextToManageContentState() {
        context = new Context();
        manageContentState = new ManageContentState(context);
        context.setCurrentState(manageContentState);
        input = new Scanner(System.in);
    }

    @When("I choose to view products")
    public void chooseToViewProducts() {
        System.setIn(new java.io.ByteArrayInputStream("1\n".getBytes()));

    }

    @Then("I should see a list of products")
    public void shouldSeeListOfProducts() {
        assertTrue(context.getIt());
    }

    @When("I choose to add a product")
    public void chooseToAddProduct() {
        System.setIn(new java.io.ByteArrayInputStream("2\n".getBytes()));

    }


    @When("I enter the product sell counter {int}")
    public void enterProductSellCounter(int sellCounter) {
        System.setIn(new java.io.ByteArrayInputStream((sellCounter + "\n").getBytes()));
    }

    @Then("the product {string} should be added successfully")
    public void productShouldBeAddedSuccessfully(String name) {
        assertTrue(context.getIt());
    }

    @Given("a product with ID {int} exists")
    public void productWithIdExists(int id) {
        Product product = new Product(id, "Test Product", 10.0, 1);
        ProductDataBase.addProduct(product);
    }

    @When("I choose to edit a product")
    public void chooseToEditProduct() {
        System.setIn(new java.io.ByteArrayInputStream("3\n".getBytes()));

    }

    @When("I enter the product ID {int}")
    public void enterProductId(int id) {
        System.setIn(new java.io.ByteArrayInputStream((id + "\n").getBytes()));
    }





    @Then("the product with ID {int} should be updated successfully")
    public void productWithIdShouldBeUpdatedSuccessfully(int id) {
        Product product = ProductDataBase.getProduct(id);
        assertTrue(context.getIt());
    }

    @When("I choose to delete a product")
    public void chooseToDeleteProduct() {
        System.setIn(new java.io.ByteArrayInputStream("4\n".getBytes()));

    }

    @Then("the product with ID {int} should be deleted successfully")
    public void productWithIdShouldBeDeletedSuccessfully(int id) {
        assertTrue(context.getIt());
    }

    @Given("a user {string} with posts exists")
    public void userWithPostsExists(String username) {
        User user = new User(username, username + "@example.com", "password");
        user.addPost("Old dessert recipe");
        UserDataBase.addPerson(user);
    }

    @When("I choose to view posts")
    public void chooseToViewPosts() {
        System.setIn(new java.io.ByteArrayInputStream("5\n".getBytes()));

    }

    @Then("I should see the posts of user {string}")
    public void shouldSeePostsOfUser(String username) {
        Person user = UserDataBase.getPersonByUsername(username);
        assertTrue(context.getIt());
    }

    @When("I choose to add a post")
    public void chooseToAddPost() {
        System.setIn(new java.io.ByteArrayInputStream("6\n".getBytes()));

    }


    @When("I enter the post content {string}")
    public void enterPostContent(String content) {
        System.setIn(new java.io.ByteArrayInputStream((content + "\n").getBytes()));
    }

    @Then("the post {string} should be added to user {string} successfully")
    public void postShouldBeAddedToUserSuccessfully(String content, String username) {
        Person user = UserDataBase.getPersonByUsername("store");
        assertTrue(context.getIt());
    }

    @When("I choose to delete a post")
    public void chooseToDeletePost() {
        System.setIn(new java.io.ByteArrayInputStream("7\n".getBytes()));

    }

    @Then("the post {string} should be deleted from user {string} successfully")
    public void postShouldBeDeletedFromUserSuccessfully(String content, String username) {
        Person user = UserDataBase.getPersonByUsername(username);
        assertTrue(context.getIt());
    }
}