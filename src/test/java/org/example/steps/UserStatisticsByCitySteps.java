package org.example.steps;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;

public class UserStatisticsByCitySteps {

    private boolean isAdminLoggedIn = false;
    private boolean userStatisticsDisplayed = false;
    private String cityName = "";

    @Given("the admin is logged in for user statistics")
    public void the_admin_is_logged_in_for_user_statistics() {
        isAdminLoggedIn = true;
    }

    @When("the admin views the user statistics for city {string}")
    public void the_admin_views_the_user_statistics_for_city(String city) {
        if (isAdminLoggedIn) {
            cityName = city;
            userStatisticsDisplayed = true;
        }
    }

    @Then("the user statistics for city {string} should be displayed")
    public void the_user_statistics_for_city_should_be_displayed(String city) {
        assertTrue(userStatisticsDisplayed);
        assertEquals(city, cityName);
    }
}