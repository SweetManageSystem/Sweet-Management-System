package org.example.statecontroller.storeowner;

import org.example.database.ProductDataBase;
import org.example.statecontroller.Context;
import org.example.statecontroller.State;

import java.util.Scanner;
import java.util.logging.Logger;

public class DiscountState implements State {
    private Context context;
    private Logger logger = Logger.getLogger(DiscountState.class.getName());
    private static final String ENTER_DISCOUNT_PERCENTAGE = "Enter Discount Percentage";

    public DiscountState(Context context) {
        this.context = context;
    }


    @Override
    public void handleInput() {
        try (Scanner input = new Scanner(System.in)) {
            String textBlock = """
                     <html>
                          <body>
                                <tag>
                                 Choose discount criteria:<br>
                                 1. Apply discount to all products<br>
                                 2. Apply discount to best-selling product<br>
                                 3. Apply discount to products in a price range
                                </tag>
                          </body>
                    </html>""";
            logger.info(textBlock);
            String command = input.nextLine();
            context.filterState(command);
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
                    logger.info("Invalid command");
                    handleInput();
                    return;
            }
                context.setCurrentState(new StoreOwnerState(context));
                context.handleInput();
        }
    }

    private void applyDiscountToAllProducts(Scanner input) {
        logger.info(ENTER_DISCOUNT_PERCENTAGE);
        double discount = input.nextDouble();
        ProductDataBase.applyDiscountToAllProducts(discount);
        logger.info("Discount applied to all products.");
    }

    private void applyDiscountToBestSellingProduct(Scanner input) {
        logger.info(ENTER_DISCOUNT_PERCENTAGE);
        double discount = input.nextDouble();
        ProductDataBase.applyDiscountToBestSellingProduct(discount);
        logger.info("Discount applied to best-selling product.");
    }

    private void applyDiscountToPriceRange(Scanner input) {
        logger.info("Enter minimum price:");
        double minPrice = input.nextDouble();
        logger.info("Enter maximum price:");
        double maxPrice = input.nextDouble();
        logger.info("Enter discount percentage:");
        double discount = input.nextDouble();
        ProductDataBase.applyDiscountToPriceRange(minPrice, maxPrice, discount);
        logger.info("Discount applied to products in the price range.");
    }


}