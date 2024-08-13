package org.example.StateController.StoreOwner;

import org.example.Database.ProductDataBase;
import org.example.StateController.Context;
import org.example.StateController.ExitState;
import org.example.StateController.State;

import java.util.Scanner;

public class DiscountState implements State {
    private Context context;

    public DiscountState(Context context) {
        this.context = context;
    }


    @Override
    public void handleInput() {
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Choose discount criteria:\n" +
                    "1. Apply discount to all products\n" +
                    "2. Apply discount to best-selling product\n" +
                    "3. Apply discount to products in a price range\n");

            String command = input.nextLine();
            filterState(command);
            switch (command) {
                case "1":
                    applyDiscountToAllProducts(input);
                    break;
                case "2":
                    applyDiscountToBestSellingProduct(input);
                    break;
                case "3":
                    applyDiscountToPriceRange(input);
                    break;
                default:
                    System.out.println("Invalid command");
                    handleInput();
                    return;
            }
                context.setCurrentState(new StoreOwnerState(context));
                context.handleInput();
        }
    }

    private void applyDiscountToAllProducts(Scanner input) {
        System.out.println("Enter discount percentage:");
        double discount = input.nextDouble();
        ProductDataBase.applyDiscountToAllProducts(discount);
        System.out.println("Discount applied to all products.");
    }

    private void applyDiscountToBestSellingProduct(Scanner input) {
        System.out.println("Enter discount percentage:");
        double discount = input.nextDouble();
        ProductDataBase.applyDiscountToBestSellingProduct(discount);
        System.out.println("Discount applied to best-selling product.");
    }

    private void applyDiscountToPriceRange(Scanner input) {
        System.out.println("Enter minimum price:");
        double minPrice = input.nextDouble();
        System.out.println("Enter maximum price:");
        double maxPrice = input.nextDouble();
        System.out.println("Enter discount percentage:");
        double discount = input.nextDouble();
        ProductDataBase.applyDiscountToPriceRange(minPrice, maxPrice, discount);
        System.out.println("Discount applied to products in the price range.");
    }

    private void filterState(String input) {
        if (context.isBack(input)) {
            context.setCurrentState(new StoreOwnerState(context));
        } else if (context.isExit(input)) {
            context.setCurrentState(new ExitState(context));
        }
    }
}