package org.example.statecontroller.user;

import org.example.database.ProductDataBase;
import org.example.reciepes.Product;
import org.example.statecontroller.Context;
import org.example.statecontroller.State;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class FilterRecipsState implements State {
    private Logger logger = Logger.getLogger(FilterRecipsState.class.getName());
    private Context context;

    public FilterRecipsState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Scanner scanner = new Scanner(System.in);
        logger.info("Enter dietary needs or food allergies (comma-separated):");
        String input = scanner.nextLine().toLowerCase().trim();
        context.filterState(input);
        List<String> tags = List.of(input.split(","));

        List<Product> filteredProducts = ProductDataBase.filterProductsByTags(tags);

        if (filteredProducts.isEmpty()) {
            logger.info("No recipes found matching the specified criteria.");
        } else {
            logger.info("Filtered recipes:");
            for (Product product : filteredProducts) {
                logger.info(product.getName() + " - $" + product.getPrice());
            }
        }

        context.setCurrentState(new UserState(context));
        context.handleInput();
    }


}