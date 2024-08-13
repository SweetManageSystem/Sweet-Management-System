package org.example.steps;

import io.cucumber.java.en.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class ContentManagementSteps {

    private boolean isAdminLoggedIn = false;
    private List<String> recipes = new ArrayList<>();
    private List<String> posts = new ArrayList<>();

    @Given("the admin is logged in for content management")
    public void the_admin_is_logged_in_for_content_management() {
        isAdminLoggedIn = true;
        // Initialize some sample data
        recipes.add("Chocolate Cake");
        recipes.add("Vanilla Ice Cream");
        posts.add("My Dessert Creation");
        posts.add("Summer Treats");
    }

    @When("the admin views the list of recipes")
    public void the_admin_views_the_list_of_recipes() {
        assertTrue(isAdminLoggedIn);
    }

    @Then("the list of recipes should be displayed")
    public void the_list_of_recipes_should_be_displayed() {
        assertFalse(recipes.isEmpty());
    }

    @When("the admin deletes a recipe with title {string}")
    public void the_admin_deletes_a_recipe_with_title(String title) {
        recipes.remove(title);
    }

    @Then("the recipe with title {string} should be removed")
    public void the_recipe_with_title_should_be_removed(String title) {
        assertFalse(recipes.contains(title));
    }

    @When("the admin views the list of posts")
    public void the_admin_views_the_list_of_posts() {
        assertTrue(isAdminLoggedIn);
    }

    @Then("the list of posts should be displayed")
    public void the_list_of_posts_should_be_displayed() {
        assertFalse(posts.isEmpty());
    }

    @When("the admin deletes a post with title {string}")
    public void the_admin_deletes_a_post_with_title(String title) {
        posts.remove(title);
    }

    @Then("the post with title {string} should be removed")
    public void the_post_with_title_should_be_removed(String title) {
        assertFalse(posts.contains(title));
    }
}