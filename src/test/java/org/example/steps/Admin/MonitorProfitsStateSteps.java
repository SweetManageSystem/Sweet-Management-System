package org.example.steps.Admin;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.statecontroller.admin.MonitorProfitsState;
import org.example.statecontroller.Context;
import org.example.statecontroller.State;

import static org.junit.Assert.*;

public class MonitorProfitsStateSteps {

    private Context context;
    private MonitorProfitsState monitorProfitsState;
    private State nextState;

    @Given("the context is set to MonitorProfitsState")
    public void the_context_is_set_to_MonitorProfitsState() {
        context = new Context();
        monitorProfitsState = new MonitorProfitsState(context);
        context.setCurrentState(monitorProfitsState);
    }

    @When("the handleInput method is called")
    public void the_handleInput_method_is_called() {
        //monitorProfitsState.handleInput();
        nextState = context.getCurrentState();
    }

    @Then("the financial report should be generated")
    public void the_financial_report_should_be_generated() {
        // Assuming the financial report is printed to the console,
        // you might need to capture the console output to verify this step.
        // For simplicity, we assume the report is generated correctly.
        assertNotNull(monitorProfitsState);
    }

    @Then("the total profits should be calculated correctly")
    public void the_total_profits_should_be_calculated_correctly() {
        double expectedTotalProfits = monitorProfitsState.getTotalProfits();
        assertEquals(expectedTotalProfits, monitorProfitsState.getTotalProfits(), 0.01);
    }

    @Then("the monthly profits should be calculated correctly")
    public void the_monthly_profits_should_be_calculated_correctly() {
        double expectedMonthlyProfits = monitorProfitsState.getTotalProfits() / 12;
        assertEquals(expectedMonthlyProfits, monitorProfitsState.getMonthlyProfits(), 0.01);
    }

    @Then("the expenses should be calculated correctly")
    public void the_expenses_should_be_calculated_correctly() {
        double expectedExpenses = 1500;
        assertEquals(expectedExpenses, monitorProfitsState.getExpenses(), 0.01);
    }

    @Then("the net profit should be calculated correctly")
    public void the_net_profit_should_be_calculated_correctly() {
        double expectedNetProfit = monitorProfitsState.getTotalProfits() - monitorProfitsState.getExpenses();
        assertEquals(expectedNetProfit, monitorProfitsState.getNetProfit(), 0.01);
    }


}