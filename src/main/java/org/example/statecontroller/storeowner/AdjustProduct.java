package org.example.statecontroller.storeowner;

import org.example.database.ProductDataBase;
import org.example.reciepes.Product;
import org.example.statecontroller.Context;
import org.example.statecontroller.ExitState;
import org.example.statecontroller.State;

import java.util.Scanner;
import java.util.logging.Logger;

public class AdjustProduct implements State {
    private Context context;
    private Logger logger = Logger.getLogger(AdjustProduct.class.getName());
    private String choice;
    private String name;
    private double price;
    private String id;
    private String newName;
    private double newPrice;

    public void setChoice(String choice) {
        this.choice = choice;
    }
    public void setName(String newName) {
        this.newName = newName;

    }
    public void setPrice(double newPrice) {
        this.newPrice = newPrice;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setNewName(String newName) {
        this.newName = newName;
    }
    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public AdjustProduct(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Scanner input = new Scanner(System.in);
        logger.info("1.Add   2.Adjust    3.Remove");
        if(!context.isTest())
            choice = input.nextLine();
        context.filterState(choice);
        switch (choice){
            case "1":
                logger.info("Enter product Name:");
                if(!context.isTest())
                    name = input.nextLine();
                logger.info("Enter product Price:");
                if(!context.isTest())
                    price = input.nextDouble();
                context.filterState(String.valueOf(price));
                Product product = new Product(ProductDataBase.getProducts().size() + 1, name,price,0);
                ProductDataBase.addProduct(product);
                logger.info("Product Added Successfully");
                context.setCurrentState(new StoreOwnerState(context));
                if(context.isTest())
                    context.setCurrentState(new ExitState());
                break;
            case "2":
                logger.info("Enter Product ID :");
                if(!context.isTest())
                    id = input.nextLine();
                context.filterState(id);
                logger.info("Product Name : " + ProductDataBase.getProduct(Integer.valueOf(id)).getName() + "Product Price: " + ProductDataBase.getProduct(Integer.valueOf(id)).getPrice());
                logger.info("Enter Product Name :");
                if(!context.isTest())
                    newName = input.nextLine();
                context.filterState(newName);
                logger.info("Enter Product Price :");
                if(!context.isTest())
                    newPrice = input.nextDouble();
                context.filterState(String.valueOf(newPrice));
                ProductDataBase.editProduct(Integer.valueOf(id), newName, newPrice);
                logger.info("Product Updated Successfully");
                context.setCurrentState(new StoreOwnerState(context));
                if(context.isTest())
                    context.setCurrentState(new ExitState());
                break;
            case "3":
                logger.info("Enter Product ID :");
                if(!context.isTest())
                    id = input.nextLine();
                context.filterState(id);
                ProductDataBase.removeProduct(Integer.valueOf(id));
                logger.info("Product Removed Successfully");
                context.setCurrentState(new StoreOwnerState(context));
                if(context.isTest())
                    context.setCurrentState(new ExitState());
                break;
            default:
                logger.info("Invalid choice");
                if(context.isTest())
                    context.setCurrentState(new ExitState());
                break;
        }
        context.handleInput();
    }
}
