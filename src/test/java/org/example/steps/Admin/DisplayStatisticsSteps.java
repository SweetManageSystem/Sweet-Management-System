package org.example.steps.Admin;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Account.Person;
import org.example.Account.User;
import org.example.Database.UserDataBase;
import org.example.StateController.Context;
import org.example.StateController.Admin.DiplayStatisticsState;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class DisplayStatisticsSteps {

    private Context context;
    private Map<String, Integer> actualStatistics;

    @Given("the following users exist:")
    public void the_following_users_exist(List<Map<String, String>> users) {
        UserDataBase.getDb().clear();
        for (Map<String, String> user : users) {
            User newUser = new User(user.get("username"), user.get("email"), user.get("password"));
            newUser.setFullname(user.get("fullname"));
            newUser.setAddress(user.get("address"));
            UserDataBase.addPerson(newUser);
        }
    }

    @Given("no users exist")
    public void no_users_exist() {
        UserDataBase.getDb().clear();
    }

    @When("I display the user statistics by city")
    public void i_display_the_user_statistics_by_city() {
        context = new Context();
        DiplayStatisticsState displayStatisticsState = new DiplayStatisticsState(context);
        //displayStatisticsState.handleInput();
        actualStatistics = new HashMap<>();
        List<Person> users = UserDataBase.getDb();
        for (Person user : users) {
            String city = user.getAddress();
            actualStatistics.put(city, actualStatistics.getOrDefault(city, 0) + 1);
        }
    }

    @Then("I should see the following statistics:")
    public void i_should_see_the_following_statistics(List<Map<String, String>> expectedStatistics) {
        for (Map<String, String> expected : expectedStatistics) {
            String city = expected.get("city");
            int expectedCount = Integer.parseInt(expected.get("number_of_users"));
            assertEquals(expectedCount, actualStatistics.getOrDefault(city, 0).intValue());
        }
    }

    @Then("I should see no statistics")
    public void i_should_see_no_statistics() {
        assertEquals(0, actualStatistics.size());
    }
}