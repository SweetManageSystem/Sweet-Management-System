package org.example.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.Account.User;
import org.example.Database.UserDataBase;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import java.util.List;
import java.util.Arrays;
import static org.junit.Assert.*;

public class BrowseAndSearch {
    private boolean userLoggedIn;
    private boolean navigatedToDessertRecipes;
    private String searchQuery;
    private String[] searchResults;

    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        // Simulate user login
        userLoggedIn = true;
    }

    @When("the user navigates to the dessert recipes section")
    public void the_user_navigates_to_the_dessert_recipes_section() {
        // Simulate navigation to dessert recipes section
        if (userLoggedIn) {
            navigatedToDessertRecipes = true;
        }
    }

    @Then("the user should see a list of dessert recipes")
    public void the_user_should_see_a_list_of_dessert_recipes() {
        // Simulate checking for dessert recipes
        assertTrue("User should be able to see dessert recipes", navigatedToDessertRecipes);
    }

    @When("the user searches for {string}")
    public void the_user_searches_for(String query) {
        // Simulate search functionality
        if (userLoggedIn) {
            searchQuery = query;
            searchResults = new String[]{"Chocolate Cake", "Vanilla Cake", "Strawberry Cake"};
        }
    }

    @Then("the user should see search results containing {string}")
    public void the_user_should_see_search_results_containing(String expectedResult) {
        // Simulate checking search results
        boolean found = false;
        for (String result : searchResults) {
            if (result.contains(expectedResult)) {
                found = true;
                break;
            }
        }
        assertTrue("Search results should contain " + expectedResult, found);
    }
}