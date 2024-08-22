package org.example.StateController.User;

import org.example.Database.ProductDataBase;
import org.example.Reciepes.Product;
import org.example.StateController.Context;
import org.example.StateController.ExitState;
import org.example.StateController.State;

import java.util.List;
import java.util.Scanner;

public class FilterRecipsState implements State {

    private Context context;

    public FilterRecipsState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter dietary needs or food allergies (comma-separated):");
        String input = scanner.nextLine().toLowerCase().trim();
        context.filterState(input);
        List<String> tags = List.of(input.split(","));

        List<Product> filteredProducts = ProductDataBase.filterProductsByTags(tags);

        if (filteredProducts.isEmpty()) {
            System.out.println("No recipes found matching the specified criteria.");
        } else {
            System.out.println("Filtered recipes:");
            for (Product product : filteredProducts) {
                System.out.println(product.getName() + " - $" + product.getPrice());
            }
        }

        context.setCurrentState(new UserState(context));
        context.handleInput();
    }


}