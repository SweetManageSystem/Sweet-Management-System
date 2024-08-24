package org.example.steps.User;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.statecontroller.Context;
import org.example.statecontroller.ExitState;
import org.example.statecontroller.*;
import org.example.statecontroller.user.*;

import static org.junit.Assert.*;

public class SearchStateSteps {

    private Context context;
    private State currentState;

    @Given("the user is in the User State")
    public void the_user_is_in_the_User_State() {
        context = new Context();
        UserState userState = new UserState(context);
        context.setCurrentState(userState);
        context.setIsTest(true);
        userState.setCommand("3");
        userState.handleInput();
        userState.isTestCase();
        context.handleInput();
        SearchState searchState = new SearchState(context);
        context.setCurrentState(searchState);
        searchState.setSearchTerm("test1");
        searchState.setCommand("1");
        searchState.handleInput();
        searchState.setCommand("2");
        searchState.handleInput();
        searchState.setCommand("3");
        searchState.handleInput();
        searchState.setCommand("0");
        searchState.handleInput();
    }

    @When("the user selects the {string} option")
    public void the_user_selects_the_option(String option) {
        if (option.equals("Search for recipes")) {
            currentState = new SearchState(context);
            context.setCurrentState(currentState);

        }
    }

    @Then("the user should be in the Search State")
    public void the_user_should_be_in_the_Search_State() {
        assertTrue(context.getCurrentState() instanceof SearchState);
    }

    @Given("the user is in the Search State")
    public void the_user_is_in_the_Search_State() {
        context = new Context();
        currentState = new SearchState(context);
        context.setCurrentState(currentState);
    }

    @When("the user inputs {string}")
    public void the_user_inputs(String input) {
        if (input.equals("back")) {
            currentState = new UserState(context);
            context.setCurrentState(currentState);
        } else if (input.equals("exit")) {
            currentState = new ExitState();
            context.setCurrentState(currentState);
        }
    }

    @Then("the user should be in the User State")
    public void the_user_should_be_in_the_User_State() {
        assertTrue(context.getCurrentState() instanceof UserState);
    }

    @Then("the user should be in the Exit State")
    public void the_user_should_be_in_the_Exit_State() {
        assertTrue(context.getCurrentState() instanceof ExitState);
    }
}