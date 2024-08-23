package org.example.statecontroller.user;

import org.example.database.ProductDataBase;
import org.example.reciepes.Product;
import org.example.statecontroller.Context;
import org.example.statecontroller.State;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class SearchState implements State {

    private Logger logger = Logger.getLogger(SearchState.class.getName());
    private Context context;

    public SearchState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Scanner scanner = new Scanner(System.in);
        String textBlock = """
                     <html>
                          <body>
                                <tag>
                                   Search for dessert recipes:<br>
                                      1. Browse all recipes<br>
                                      2. Search for a specific recipe<br>
                                      3. Back
                                </tag>
                          </body>
                    </html>""";
        logger.info(textBlock);


        String command = scanner.nextLine();
        context.filterState(command);

        switch (command) {
            case "1":
                browseAllRecipes();
                break;
            case "2":
                searchForRecipe();
                break;
            case "3":
                context.setCurrentState(new UserState(context));
                context.handleInput();
                break;
            default:
                logger.info("Invalid command");
                context.handleInput();
                break;
        }
    }
    private void browseAllRecipes() {
        List<Product> products = ProductDataBase.getProducts();
        if (products.isEmpty()) {
            logger.info("No recipes available.");
        } else {
            logger.info("Available recipes:");
            for (Product product : products) {
                logger.info(product.getName() + " - $" + product.getPrice());
            }
        }
        context.handleInput();
    }
    private void searchForRecipe() {
        Scanner scanner = new Scanner(System.in);
        logger.info("Enter the name of the recipe to search for:");
        String searchTerm = scanner.nextLine();

        List<Product> products = ProductDataBase.getProducts();
        boolean found = false;
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                logger.info(product.getName() + " - $" + product.getPrice());
                found = true;
            }
        }
        if (!found) {
            logger.info("No recipes found matching the search term.");
        }
        context.handleInput();
    }
}