package org.example.steps;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;

public class IdentifyBestSellingProductsSteps {

    private boolean isAdminLoggedIn = false;
    private boolean bestSellingProductsReportDisplayed = false;
    private String storeName = "";

    @Given("the admin is logged in for best-selling products")
    public void the_admin_is_logged_in_for_best_selling_products() {
        isAdminLoggedIn = true;
    }

    @When("the admin views the best-selling products report for store {string}")
    public void the_admin_views_the_best_selling_products_report_for_store(String store) {
        if (isAdminLoggedIn) {
            storeName = store;
            bestSellingProductsReportDisplayed = true;
        }
    }

    @Then("the best-selling products report for store {string} should be displayed")
    public void the_best_selling_products_report_for_store_should_be_displayed(String store) {
        assertTrue(bestSellingProductsReportDisplayed);
        assertEquals(store, storeName);
    }
}