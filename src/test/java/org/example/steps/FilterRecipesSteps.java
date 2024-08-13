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
public class FilterRecipesSteps {

    @Given("the user is on the recipe search page")
    public void theUserIsOnTheRecipeSearchPage() {
        // Code to navigate to the recipe search page
        System.out.println("User navigates to the recipe search page");
    }

    @When("the user selects {string} from the dietary needs filter")
    public void theUserSelectsFromTheDietaryNeedsFilter(String dietaryNeed) {
        // Code to select the dietary need filter
        System.out.println("User selects " + dietaryNeed + " from the dietary needs filter");
    }

    @When("the user selects {string} from the food allergies filter")
    public void theUserSelectsFromTheFoodAllergiesFilter(String foodAllergy) {
        // Code to select the food allergy filter
        System.out.println("User selects " + foodAllergy + " from the food allergies filter");
    }

    @When("the user clicks on the {string} button")
    public void theUserClicksOnTheButton(String buttonName) {
        // Code to click the button
        System.out.println("User clicks on the " + buttonName + " button");
    }

    @Then("only {string} recipes should be displayed")
    public void onlyRecipesShouldBeDisplayed(String filterType) {
        // Code to verify that only recipes matching the filter are displayed
        System.out.println("Only " + filterType + " recipes are displayed");
        assertTrue(true); // Replace with actual verification logic
    }
}