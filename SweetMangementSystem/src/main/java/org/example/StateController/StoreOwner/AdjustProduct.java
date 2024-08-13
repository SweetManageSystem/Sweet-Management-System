package org.example.StateController.StoreOwner;

import org.example.Database.ProductDataBase;
import org.example.Reciepes.Product;
import org.example.StateController.Context;
import org.example.StateController.ExitState;
import org.example.StateController.Login.LogInState;
import org.example.StateController.State;

import java.util.Scanner;

public class AdjustProduct implements State {
    private Context context;

    public AdjustProduct(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Scanner input = new Scanner(System.in);
        System.out.println("1.Add   2.Adjust    3.Remove");
        String choice = input.nextLine();
        filterState(choice);
        switch (choice){
            case "1":
                System.out.println("Enter product Name:");
                String name = input.nextLine();
                filterState(name);
                System.out.println("Enter product Price:");
                double price = input.nextDouble();
                filterState(String.valueOf(price));
                Product product = new Product(ProductDataBase.getProducts().size() + 1, name,price,0);
                System.out.println("Product Added Successfully");
                context.setCurrentState(new StoreOwnerState(context));
                context.handleInput();
            case "2":
                System.out.println("Enter Product ID :");
                String id = input.nextLine();
                filterState(id);
                System.out.println("Product Name : " + ProductDataBase.getProduct(Integer.valueOf(id)).getName() + "Product Price: " + ProductDataBase.getProduct(Integer.valueOf(id)).getPrice());
                System.out.println("Enter Product Name :");
                String newName = input.nextLine();
                filterState(newName);
                System.out.println("Enter Product Price :");
                double newPrice = input.nextDouble();
                filterState(String.valueOf(newPrice));
                ProductDataBase.editProduct(Integer.valueOf(id), newName, newPrice);
                System.out.println("Product Updated Successfully");
                context.setCurrentState(new StoreOwnerState(context));
                context.handleInput();
            case "3":
                System.out.println("Enter Product ID :");
                String id1 = input.nextLine();
                filterState(id1);
                ProductDataBase.removeProduct(Integer.valueOf(id1));
                System.out.println("Product Removed Successfully");
                context.setCurrentState(new StoreOwnerState(context));
                context.handleInput();
            default:
                System.out.println("Invalid choice");
                context.handleInput();
        }

    }

    private void filterState(String input){
        if(context.isBack(input)){
            context.setCurrentState(new StoreOwnerState(context));
            context.handleInput();
        }
        else if(context.isExit(input)){
            context.setCurrentState(new ExitState(context));
            context.handleInput();
        }

    }
}
