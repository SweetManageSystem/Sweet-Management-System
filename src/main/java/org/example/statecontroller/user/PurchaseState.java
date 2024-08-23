package org.example.statecontroller.user;

import org.example.database.ProductDataBase;
import org.example.reciepes.Product;
import org.example.statecontroller.Context;
import org.example.statecontroller.State;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class PurchaseState implements State {

    private Context context;
    private Logger logger = Logger.getLogger(PurchaseState.class.getName());


    public PurchaseState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Scanner scanner = new Scanner(System.in);

        // Display available products
        List<Product> products = ProductDataBase.getProducts();
        logger.info("Available Products:");
        for (Product product : products) {
            logger.info("ID: "+ product.getId() +  "\nName: " + product.getName() +  "\nPrice: " + product.getPrice());
        }

        // Prompt user to select a product by ID
        logger.info("Enter the ID of the product you want to purchase:");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Find the selected product
        Product selectedProduct = ProductDataBase.getProduct(productId);
        if (selectedProduct == null) {
            logger.info("Invalid product ID. Returning to User State.");
            context.setCurrentState(new UserState(context));
            return; // Exit the method to avoid infinite loop
        }

        // Confirm purchase
        logger.info("You selected: " + selectedProduct.getName() + " Price: "+ selectedProduct.getPrice());
        logger.info("Do you want to confirm the purchase? (yes/no)");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            // Update the product's sell counter
            selectedProduct.setSellCounter(selectedProduct.getSellCounter() + 1);
            logger.info("Purchase successful! Thank you for your purchase.");
        } else {
            logger.info("Purchase canceled.");
        }
        context.setCurrentState(new UserState(context));
        context.handleInput();
    }

}