package org.example.StateController.User;

import org.example.Database.ProductDataBase;
import org.example.Reciepes.Product;
import org.example.StateController.Context;
import org.example.StateController.ExitState;
import org.example.StateController.State;

import java.util.List;
import java.util.Scanner;

public class PurchaseState implements State {

    private Context context;

    public PurchaseState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Scanner scanner = new Scanner(System.in);

        // Display available products
        List<Product> products = ProductDataBase.getProducts();
        System.out.println("Available Products:");
        for (Product product : products) {
            System.out.printf("ID: %d, Name: %s, Price: %.2f\n", product.getId(), product.getName(), product.getPrice());
        }

        // Prompt user to select a product by ID
        System.out.println("Enter the ID of the product you want to purchase:");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Find the selected product
        Product selectedProduct = ProductDataBase.getProduct(productId);
        if (selectedProduct == null) {
            System.out.println("Invalid product ID. Returning to User State.");
            context.setCurrentState(new UserState(context));
            return; // Exit the method to avoid infinite loop
        }

        // Confirm purchase
        System.out.printf("You selected: %s, Price: %.2f\n", selectedProduct.getName(), selectedProduct.getPrice());
        System.out.println("Do you want to confirm the purchase? (yes/no)");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            // Update the product's sell counter
            selectedProduct.setSellCounter(selectedProduct.getSellCounter() + 1);
            System.out.println("Purchase successful! Thank you for your purchase.");
        } else {
            System.out.println("Purchase canceled.");
        }
        context.setCurrentState(new UserState(context));
        context.handleInput();
    }

}