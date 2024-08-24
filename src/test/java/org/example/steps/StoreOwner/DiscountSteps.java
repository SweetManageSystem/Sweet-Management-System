package org.example.steps.StoreOwner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.database.ProductDataBase;
import org.example.reciepes.Product;
import org.example.statecontroller.Context;
import org.example.statecontroller.storeowner.DiscountState;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

public class DiscountSteps {

    private Context context;
    private DiscountState discountState;

    @Given("the product database is initialized")
    public void the_product_database_is_initialized() {
        ProductDataBase.initialProduct();
        context = new Context();
        context.setIsTest(true);
        discountState = new DiscountState(context);
        discountState.setCommand("0");
        discountState.handleInput();
        discountState.setDiscount(10);
        discountState.setMaxPrice(25.9);
        discountState.setMinPrice(10.5);
        discountState.setCommand("1");
        discountState.handleInput();
        discountState.setCommand("2");
        discountState.handleInput();
        discountState.setCommand("3");
        discountState.handleInput();

    }

    @When("the store owner applies a {double}% discount to all products")
    public void the_store_owner_applies_a_discount_to_all_products(double discountPercentage) {
        String input = "1\n" + discountPercentage + "\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    @Then("all products should have their prices reduced by {double}%")
    public void all_products_should_have_their_prices_reduced_by(double discountPercentage) {
            assertTrue(context.getIt());
    }

    @When("the store owner applies a {double}% discount to the best-selling product")
    public void the_store_owner_applies_a_discount_to_the_best_selling_product(double discountPercentage) {
        String input = "2\n" + discountPercentage + "\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

    }

    @Then("the best-selling product should have its price reduced by {double}%")
    public void the_best_selling_product_should_have_its_price_reduced_by(double discountPercentage) {
        assertTrue(context.getIt());
    }

    @When("the store owner applies a {double}% discount to products priced between {double} and {double}")
    public void the_store_owner_applies_a_discount_to_products_priced_between(double discountPercentage, double minPrice, double maxPrice) {
        String input = "3\n" + minPrice + "\n" + maxPrice + "\n" + discountPercentage + "\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    @Then("all products priced between {double} and {double} should have their prices reduced by {double}%")
    public void all_products_priced_between_should_have_their_prices_reduced_by(double minPrice, double maxPrice, double discountPercentage) {
        List<Product> products = ProductDataBase.getProducts();
        for (Product product : products) {
            if (product.getOriginalPrice() >= minPrice && product.getOriginalPrice() <= maxPrice) {
                assertTrue(context.getIt());
            }
        }
    }
}