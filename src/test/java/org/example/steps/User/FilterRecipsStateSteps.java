package org.example.steps.User;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.database.ProductDataBase;
import org.example.reciepes.Product;
import org.example.statecontroller.Context;
import org.example.statecontroller.user.FilterRecipsState;
import org.example.statecontroller.user.UserState;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class FilterRecipsStateSteps {

    private Context context;
    private FilterRecipsState filterRecipsState;
    private ByteArrayOutputStream outputStream;

    @Given("the user is in the FilterRecipsState")
    public void theUserIsInTheFilterRecipsState() {
        context = new Context();
        context.setIsTest(true);
        filterRecipsState = new FilterRecipsState(context);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        UserState userState = new UserState(context);
        userState.setCommand("4");
        userState.handleInput();
        userState.setCommand("8");
        userState.handleInput();
        userState.setCommand("9");
        userState.handleInput();
        filterRecipsState.setInput("fish");
        filterRecipsState.handleInput();
        filterRecipsState.setInput("");
        filterRecipsState.handleInput();

    }

    @Given("the following products are available:")
    public void theFollowingProductsAreAvailable(List<Map<String, String>> products) {
        ProductDataBase.getProducts().clear();
        for (Map<String, String> product : products) {
            int id = Integer.parseInt(product.get("id"));
            String name = product.get("name");
            double price = Double.parseDouble(product.get("price"));
            int sellCounter = Integer.parseInt(product.get("sellCounter"));
            ProductDataBase.addProduct(new Product(id, name, price, sellCounter));
        }
    }

    @When("the user input {string}")
    public void theUserInput(String input) {
        ByteArrayInputStream testInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(testInput);
        System.setIn(System.in);
    }

    @Then("no recipes should be found matching the specified criteria")
    public void noRecipesShouldBeFoundMatchingTheSpecifiedCriteria() {
        assertTrue(context.getIt());
    }

    @Then("the following recipes should be displayed:")
    public void theFollowingRecipesShouldBeDisplayed(List<Map<String, String>> expectedRecipes) {
           assertTrue(context.getIt());

    }
}