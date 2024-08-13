package org.example.steps;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;
public class MonitoringAndReportingSteps {
    private boolean isAdminLoggedIn = false;
    private boolean profitReportDisplayed = false;
    private boolean financialReportGenerated = false;
    private String confirmationMessage = "";

    @Given("the admin is logged in")
    public void the_admin_is_logged_in() {
        isAdminLoggedIn = true;
    }

    @When("the admin views the profit report")
    public void the_admin_views_the_profit_report() {
        if (isAdminLoggedIn) {
            profitReportDisplayed = true;
        }
    }

    @When("the admin generates a financial report")
    public void the_admin_generates_a_financial_report() {
        if (isAdminLoggedIn) {
            financialReportGenerated = true;
            confirmationMessage = "Financial report generated!";
        }
    }

    @Then("the profit report should be displayed")
    public void the_profit_report_should_be_displayed() {
        assertTrue(profitReportDisplayed);
    }

    @Then("the financial report should be generated")
    public void the_financial_report_should_be_generated() {
        assertTrue(financialReportGenerated);
    }

    @Then("the admin should see a confirmation of report generation")
    public void the_admin_should_see_a_confirmation_of_report_generation() {
        assertEquals("Financial report generated!", confirmationMessage);
    }
}
