package org.example.StateController.User;

import org.example.Database.ProductDataBase;
import org.example.Reciepes.Product;
import org.example.StateController.Context;
import org.example.StateController.ExitState;
import org.example.StateController.State;

import java.util.List;
import java.util.Scanner;

public class SearchState implements State {

    private Context context;

    public SearchState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Search for dessert recipes:\n" +
                "1. Browse all recipes\n" +
                "2. Search for a specific recipe\n"+
                "3. Back");

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
            default:
                System.out.println("Invalid command");
                context.handleInput();
                break;
        }
    }
    private void browseAllRecipes() {
        List<Product> products = ProductDataBase.getProducts();
        if (products.isEmpty()) {
            System.out.println("No recipes available.");
        } else {
            System.out.println("Available recipes:");
            for (Product product : products) {
                System.out.println(product.getName() + " - $" + product.getPrice());
            }
        }
        context.handleInput();
    }
    private void searchForRecipe() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the recipe to search for:");
        String searchTerm = scanner.nextLine();

        List<Product> products = ProductDataBase.getProducts();
        boolean found = false;
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                System.out.println(product.getName() + " - $" + product.getPrice());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No recipes found matching the search term.");
        }
        context.handleInput();
    }
}