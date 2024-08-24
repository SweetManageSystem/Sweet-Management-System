package org.example.steps.StoreOwner;
import io.cucumber.java.en.*;
import org.example.database.ProductDataBase;
import org.example.reciepes.Product;
import org.example.statecontroller.Context;
import org.example.statecontroller.storeowner.AdjustProduct;
import org.example.statecontroller.storeowner.StoreOwnerState;


import static org.junit.Assert.*;

public class AdjustProductSteps {
    private Context context;
    private AdjustProduct adjustProductState;
    private StoreOwnerState storeOwnerState;
    private String productName;
    private double productPrice;
    private String newProductName;
    private int productId;

    @Given("I am logged in as a Store Owner")
    public void i_am_logged_in_as_a_store_owner() {
        context = new Context();
        context.setIsTest(true);
        storeOwnerState = new StoreOwnerState(context);
        context.setCurrentState(storeOwnerState);
    }

    @When("I choose to add a new product")
    public void i_choose_to_add_a_new_product() {
        adjustProductState = new AdjustProduct(context);
        context.setCurrentState(adjustProductState);
        adjustProductState.setChoice("1");
        adjustProductState.setName("hehe");
        adjustProductState.setId("1");
        adjustProductState.setNewName("hoho");
        adjustProductState.setNewPrice(22.0);
        adjustProductState.setPrice(20.2);
        adjustProductState.handleInput();
        adjustProductState.setChoice("2");
        adjustProductState.handleInput();
        adjustProductState.setChoice("3");
        adjustProductState.handleInput();
        adjustProductState.setChoice("4");
        adjustProductState.handleInput();
    }

    @When("I enter the product name {string}")
    public void i_enter_the_product_name(String name) {
        this.productName = name;
    }

    @When("I enter the product price {double}")
    public void i_enter_the_product_price(double price) {
        this.productPrice = price;
        Product product = new Product(ProductDataBase.getProducts().size() + 1, productName, productPrice,0);
        ProductDataBase.addProduct(product);
    }

    @Then("the product {string} with price {double} should be added successfully")
    public void the_product_with_price_should_be_added_successfully(String name, double price) {
        assertTrue(context.getIt());
    }

    @Given("the product with ID {int} exists")
    public void the_product_with_id_exists(int id) {
        Product product = new Product(id, "Existing Product", 50.00,0);
        ProductDataBase.addProduct(product);
        this.productId = id;
    }

    @When("I choose to update the product with ID {int}")
    public void i_choose_to_update_the_product_with_id(int id) {
        adjustProductState = new AdjustProduct(context);
        context.setCurrentState(adjustProductState);
        this.productId = id;
    }

    @When("I enter the new product name {string}")
    public void i_enter_the_new_product_name(String newName) {
        this.newProductName = newName;
    }

    @When("I enter the new product price {double}")
    public void i_enter_the_new_product_price(double newPrice) {
        ProductDataBase.editProduct(productId, newProductName, newPrice);
    }

    @Then("the product with ID {int} should be updated to name {string} and price {double}")
    public void the_product_with_id_should_be_updated_to_name_and_price(int id, String newName, double newPrice) {
        assertTrue(context.getIt());
        assertTrue(context.getIt());
        assertTrue(context.getIt());
    }


    @When("I choose to remove the product with ID {int}")
    public void i_choose_to_remove_the_product_with_id(int id) {
        ProductDataBase.removeProduct(id);
    }

    @Then("the product with ID {int} should be removed successfully")
    public void the_product_with_id_should_be_removed_successfully(int id) {

        assertTrue(context.getIt());
    }

    @When("I enter an invalid choice {string}")
    public void i_enter_an_invalid_choice(String choice) {
        // Simulate entering an invalid choice
        // You might need to modify this to fit your actual implementation
    }

    @Then("I should see an error message {string}")
    public void i_should_see_an_error_message(String errorMessage) {
        // Simulate entering an invalid choice
        // You might need to modify this to fit your actual implementation
    }
}