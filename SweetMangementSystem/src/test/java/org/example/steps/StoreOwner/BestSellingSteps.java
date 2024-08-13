package org.example.steps.StoreOwner;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Database.ProductDataBase;
import org.example.Reciepes.Product;
import org.example.StateController.Context;
import org.example.StateController.StoreOwner.BestSellingState;
import org.example.StateController.StoreOwner.StoreOwnerState;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
public class BestSellingSteps {

    private Context context;
    private String bestSellingProductName;
    private int bestSellingProductSales;

    @When("the store owner checks the best selling product")
    public void the_store_owner_checks_the_best_selling_product() {
        ProductDataBase.initialProduct();
        context = new Context();
        BestSellingState bestSellingState = new BestSellingState(context, true);
        context.setCurrentState(bestSellingState);
        bestSellingState.handleInput();
        Product bestSellingProduct = ProductDataBase.getBestSelling();
        bestSellingProductName = bestSellingProduct.getName();
        bestSellingProductSales = bestSellingProduct.getSellCounter();
    }

    @Then("the best selling product should be {string} with {int} sales")
    public void the_best_selling_product_should_be_with_sales(String expectedName, int expectedSales) {
        assertEquals(expectedName, bestSellingProductName);
        assertEquals(expectedSales, bestSellingProductSales);
    }
}
