package org.example.statecontroller.admin;

import org.example.account.Person;
import org.example.database.UserDataBase;
import org.example.statecontroller.Context;
import org.example.statecontroller.State;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class DiplayStatisticsState implements State {
    private Context context;
    private Logger logger = Logger.getLogger(DiplayStatisticsState.class.getName());

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
        logger.info("User Statistics by City:");
        for (Map.Entry<String, Integer> entry : userCountByCity.entrySet()) {
            logger.info("City: " + entry.getKey() + ", Number of Users: " + entry.getValue());
        }

        // Return to the AdminState after displaying the statistics
        context.setCurrentState(new AdminState(context));
        context.handleInput();
    }


}
