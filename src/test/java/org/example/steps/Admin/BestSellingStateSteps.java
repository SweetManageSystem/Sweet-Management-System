package org.example.steps.Admin;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.database.ProductDataBase;
import org.example.reciepes.Product;
import org.example.statecontroller.Context;
import org.example.statecontroller.admin.BestSellingState;

import static org.junit.Assert.assertEquals;

public class BestSellingStateSteps {

    private Context context;
    private BestSellingState bestSellingState;
    private Product bestSellingProduct;
    private String output;

    @Given("the product database is initialized with products")
    public void the_product_database_is_initialized_with_products() {
        ProductDataBase.initialProduct();
    }

    @When("I enter the BestSellingState")
    public void i_enter_the_best_selling_state() {
        context = new Context();
        bestSellingState = new BestSellingState(context);
        bestSellingProduct = ProductDataBase.getBestSelling();
        output = "The best selling product in this store is : " + bestSellingProduct.getName() + "\n" +
                "Sold : " + bestSellingProduct.getSellCounter();
    }

    @Then("I should see the best selling product details")
    public void i_should_see_the_best_selling_product_details() {
        assertEquals("The best selling product in this store is : " + bestSellingProduct.getName() + "\n" +
                "Sold : " + bestSellingProduct.getSellCounter(), output);
    }
}