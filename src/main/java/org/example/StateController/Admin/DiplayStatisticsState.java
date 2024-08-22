package org.example.StateController.Admin;

import org.example.Account.Person;
import org.example.Database.UserDataBase;
import org.example.StateController.Context;
import org.example.StateController.ExitState;
import org.example.StateController.State;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiplayStatisticsState implements State {
    private Context context;
    public DiplayStatisticsState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {

        List<Person> users = UserDataBase.getDb();
        Map<String, Integer> userCountByCity = new HashMap<>();
        for (Person user : users) {
            String city = user.getAddress();
            userCountByCity.put(city, userCountByCity.getOrDefault(city, 0) + 1);
        }

        // Display the statistics
        System.out.println("User Statistics by City:");
        for (Map.Entry<String, Integer> entry : userCountByCity.entrySet()) {
            System.out.println("City: " + entry.getKey() + ", Number of Users: " + entry.getValue());
        }

        // Return to the AdminState after displaying the statistics
        context.setCurrentState(new AdminState(context));
        context.handleInput();
    }


}
