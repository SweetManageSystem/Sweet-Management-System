package org.example.steps.StoreOwner;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.StateController.Context;
import org.example.StateController.StoreOwner.MonitorSalesState;

import static org.junit.Assert.assertEquals;

public class MonitorSalesSteps {

    private Context context;
    private int totalSales;
    private double totalProfit;

    @When("the store owner checks the sales and profits")
    public void the_store_owner_checks_the_sales_and_profits() {
        context = new Context();
        MonitorSalesState monitorSalesState = new MonitorSalesState(context);
        monitorSalesState.calculateSalesAndProfits();
        totalSales = monitorSalesState.getTotalSales();
        totalProfit = monitorSalesState.getTotalProfit();
    }

    @Then("the total sales should be {int}")
    public void the_total_sales_should_be(int expectedSales) {
        assertEquals(expectedSales, totalSales);
    }

    @Then("the total profit should be {double}")
    public void the_total_profit_should_be(double expectedProfit) {
        assertEquals(expectedProfit, totalProfit, 0.01);
    }
}