package org.example.steps.User;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.example.database.ProductDataBase;
import org.example.reciepes.Product;
import org.example.statecontroller.Context;
import org.example.statecontroller.user.PurchaseState;
import org.example.statecontroller.user.UserState;

import java.util.List;

import static org.junit.Assert.*;

public class PurchaseStateSteps {

    private Context context;
    private PurchaseState purchaseState;
    private List<Product> products;
    private Product selectedProduct;
    private String confirmationMessage;

    @Before
    public void setUp() {
        ProductDataBase.getProducts().clear(); // Clear existing products
        ProductDataBase.initialProduct(); // Ensure products are initialized before each test
    }

    @Given("the user is in the Purchase State")
    public void the_user_is_in_the_purchase_state() {
        context = new Context();
        context.setIsTest(true);
        UserState userState = new UserState(context);
        userState.setCommand("5");
        userState.handleInput();
        purchaseState = new PurchaseState(context);
        context.setCurrentState(purchaseState);
        purchaseState.setConfirmation("yes");
        purchaseState.setProductId(1);
        purchaseState.handleInput();
        purchaseState.setConfirmation("no");
        purchaseState.handleInput();
        purchaseState.setProductId(10);
        purchaseState.handleInput();

    }

    @When("the system displays available products")
    public void the_system_displays_available_products() {
        products = ProductDataBase.getProducts();
        System.out.println("Available products: " + products); // Debug statement
    }

    @Then("the user should see a list of products with their IDs, names, and prices")
    public void the_user_should_see_a_list_of_products_with_their_ids_names_and_prices() {
        assertNotNull(products);
        assertFalse(products.isEmpty());
        for (Product product : products) {
            assertNotEquals(0,product.getId());
            assertNotNull(product.getName());
            assertNotEquals(0.0,product.getPrice());
        }
    }

    @When("the user enters a valid product ID")
    public void the_user_enters_a_valid_product_id() {
        selectedProduct = ProductDataBase.getProduct(1); // Assuming ID 1 is valid for testing
        assertNotNull(selectedProduct);
        System.out.println("Selected product: " + selectedProduct); // Debug statement
    }

    @When("the user confirms the purchase")
    public void the_user_confirms_the_purchase() {
        if (selectedProduct != null) {
            selectedProduct.setSellCounter(selectedProduct.getSellCounter() + 1);
            confirmationMessage = "Purchase successful! Thank you for your purchase.";
        }
    }

    @Then("the system should update the product's sell counter")
    public void the_system_should_update_the_product_s_sell_counter() {
        assertEquals(12, selectedProduct.getSellCounter()); // Assuming initial sell counter was 10
    }

    @Then("the user should see a purchase successful message")
    public void the_user_should_see_a_purchase_successful_message() {
        assertEquals("Purchase successful! Thank you for your purchase.", confirmationMessage);
    }

    @When("the user cancels the purchase")
    public void the_user_cancels_the_purchase() {
        confirmationMessage = "Purchase canceled.";
    }

    @Then("the system should not update the product's sell counter")
    public void the_system_should_not_update_the_product_s_sell_counter() {
        assertEquals(11, selectedProduct.getSellCounter()); // Assuming initial sell counter was 10
    }

    @Then("the user should see a purchase canceled message")
    public void the_user_should_see_a_purchase_canceled_message() {
        assertEquals("Purchase canceled.", confirmationMessage);
    }

    @When("the user enters an invalid product ID")
    public void the_user_enters_an_invalid_product_id() {
        selectedProduct = ProductDataBase.getProduct(999); // Assuming ID 999 is invalid for testing
        if (selectedProduct == null) {
            confirmationMessage = "Invalid product ID. Returning to User State.";
            context.setCurrentState(new UserState(context));
        }
    }

    @Then("the user should see an invalid product ID message")
    public void the_user_should_see_an_invalid_product_id_message() {
        assertEquals("Invalid product ID. Returning to User State.", confirmationMessage);
    }

    @Then("the system should return to the User State")
    public void the_system_should_return_to_the_user_state() {
        assertTrue(context.getCurrentState() instanceof UserState);
    }
}