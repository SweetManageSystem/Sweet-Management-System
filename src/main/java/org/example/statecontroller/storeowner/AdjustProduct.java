package org.example.statecontroller.storeowner;

import org.example.database.ProductDataBase;
import org.example.reciepes.Product;
import org.example.statecontroller.Context;
import org.example.statecontroller.State;

import java.util.Scanner;
import java.util.logging.Logger;

public class AdjustProduct implements State {
    private Context context;
    private Logger logger = Logger.getLogger(AdjustProduct.class.getName());


    public AdjustProduct(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Scanner input = new Scanner(System.in);
        logger.info("1.Add   2.Adjust    3.Remove");
        String choice = input.nextLine();
        context.filterState(choice);
        switch (choice){
            case "1":
                logger.info("Enter product Name:");
                String name = input.nextLine();
                context.filterState(name);
                logger.info("Enter product Price:");
                double price = input.nextDouble();
                context.filterState(String.valueOf(price));
                Product product = new Product(ProductDataBase.getProducts().size() + 1, name,price,0);
                ProductDataBase.addProduct(product);
                logger.info("Product Added Successfully");
                context.setCurrentState(new StoreOwnerState(context));
                context.handleInput();
                break;
            case "2":
                logger.info("Enter Product ID :");
                String id = input.nextLine();
                context.filterState(id);
                logger.info("Product Name : " + ProductDataBase.getProduct(Integer.valueOf(id)).getName() + "Product Price: " + ProductDataBase.getProduct(Integer.valueOf(id)).getPrice());
                logger.info("Enter Product Name :");
                String newName = input.nextLine();
                context.filterState(newName);
                logger.info("Enter Product Price :");
                double newPrice = input.nextDouble();
                context.filterState(String.valueOf(newPrice));
                ProductDataBase.editProduct(Integer.valueOf(id), newName, newPrice);
                logger.info("Product Updated Successfully");
                context.setCurrentState(new StoreOwnerState(context));
                context.handleInput();
                break;
            case "3":
                logger.info("Enter Product ID :");
                String id1 = input.nextLine();
                context.filterState(id1);
                ProductDataBase.removeProduct(Integer.valueOf(id1));
                logger.info("Product Removed Successfully");
                context.setCurrentState(new StoreOwnerState(context));
                context.handleInput();
                break;
            default:
                logger.info("Invalid choice");
                context.handleInput();
                break;
        }

    }


}
