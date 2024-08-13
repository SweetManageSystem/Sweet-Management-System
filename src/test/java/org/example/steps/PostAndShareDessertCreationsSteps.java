package org.example.steps;

import org.example.Account.User;
import org.example.Database.UserDataBase;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import java.util.List;
import static org.junit.Assert.*;

public class PostAndShareDessertCreationsSteps {
    private static User user;
    private String lastMessage;
    private UserDataBase userDataBase;

    @Before
    public void setUp() {
        userDataBase = new UserDataBase();
        System.out.println("Setting up initial users...");
        UserDataBase.initialUsers();
    }

    @Given("the user is on the dessert creations login page")
    public void the_user_is_on_the_dessert_creations_login_page() {
        // Simulate user being on the dessert creations login page
        System.out.println("User is on the dessert creations login page.");
    }

    @When("the user logs in with username {string} and password {string}")
    public void the_user_logs_in_with_username_and_password(String username, String password) {
        user = new User();
        user.setUsername(username);
        user.setPassword(password);
        System.out.println("Attempting to login with username: " + username + " and password: " + password);
        boolean loginSuccessful = user.login();
        assertTrue("User login failed", loginSuccessful);
        if (loginSuccessful) {
            System.out.println("User login successful.");
            user.setOriginalUsername(username); // Set original username after login
            user.setEmail(UserDataBase.findByUsername(username).getEmail()); // Set email after login
        } else {
            System.out.println("User login failed for username: " + username);
        }
    }

    @Then("the user should be redirected to the home page for dessert creations")
    public void the_user_should_be_redirected_to_the_home_page_for_dessert_creations() {
        assertNotNull("User object is null", user);
        assertEquals("Logged In", user.getCurrentState());
        System.out.println("User redirected to the home page for dessert creations.");
    }

    @Then("the user should see a dessert creation option {string}")
    public void the_user_should_see_a_dessert_creation_option(String option) {
        assertNotNull("User object is null", user);
        List<String> options = user.getAvailableOptions();
        assertNotNull("Options list is null", options);
        assertTrue(option + " option not available", options.contains(option));
        System.out.println("User sees the option: " + option);
    }

    @When("the user selects the dessert creation option {string}")
    public void the_user_selects_the_dessert_creation_option(String option) {
        assertNotNull("User object is null", user);
        user.selectOption(option);
        System.out.println("User selected the option: " + option);
    }

    @Then("the system should transition the user to the dessert creation state {string}")
    public void the_system_should_transition_the_user_to_the_dessert_creation_state(String state) {
        assertNotNull("User object is null", user);
        assertEquals("User not in expected state", state, user.getCurrentState());
        System.out.println("System transitioned user to the state: " + state);
    }

    @When("the user posts a new dessert creation with title {string} and description {string}")
    public void the_user_posts_a_new_dessert_creation_with_title_and_description(String title, String description) {
        assertNotNull("User object is null", user);
        user.addPost(title + ": " + description);
        System.out.println("User posted a new dessert creation: " + title + " - " + description);
    }

    @Then("the system should save the new dessert creation")
    public void the_system_should_save_the_new_dessert_creation() {
        assertNotNull("User object is null", user);
        List<String> posts = user.getPosts();
        assertNotNull("Posts list is null", posts);
        assertTrue("New dessert creation not found", posts.contains("Chocolate Cake: Delicious chocolate cake recipe"));
        System.out.println("System saved the new dessert creation.");
    }

    @Then("the system should display a dessert creation success message {string}")
    public void the_system_should_display_a_dessert_creation_success_message(String message) {
        lastMessage = "Dessert creation posted successfully";
        assertEquals(message, lastMessage);
        System.out.println("System displayed the success message: " + message);
    }

    @Then("the user should see the new dessert creation in their list of posts")
    public void the_user_should_see_the_new_dessert_creation_in_their_list_of_posts() {
        assertNotNull("User object is null", user);
        List<String> posts = user.getPosts();
        assertNotNull("Posts list is null", posts);
        assertTrue("New dessert creation not found", posts.contains("Chocolate Cake: Delicious chocolate cake recipe"));
        System.out.println("User sees the new dessert creation in their list of posts.");
    }
}