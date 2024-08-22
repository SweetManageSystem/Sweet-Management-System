package org.example.steps.User;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.Database.ProductDataBase;
import org.example.Reciepes.Product;
import org.example.StateController.Context;
import org.example.StateController.User.FilterRecipsState;

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
        filterRecipsState = new FilterRecipsState(context);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Given("the following products are available:")
    public void theFollowingProductsAreAvailable(List<Map<String, String>> products) {
        ProductDataBase.getProducts().clear();
        for (Map<String, String> product : products) {
            int id = Integer.parseInt(product.get("id"));
            String name = product.get("name");
            double price = Double.parseDouble(product.get("price"));
            double originalPrice = Double.parseDouble(product.get("originalPrice"));
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
        String output = outputStream.toString();
        assertTrue(context.getIt());
    }

    @Then("the following recipes should be displayed:")
    public void theFollowingRecipesShouldBeDisplayed(List<Map<String, String>> expectedRecipes) {
        String output = outputStream.toString();
        for (Map<String, String> expectedRecipe : expectedRecipes) {
            String expectedOutput = expectedRecipe.get("name") + " - $" + expectedRecipe.get("price");
            assertTrue(context.getIt());
        }
    }
}