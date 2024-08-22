package org.example.StateController.Admin;

import org.example.Database.ProductDataBase;
import org.example.Reciepes.Product;
import org.example.StateController.Context;
import org.example.StateController.ExitState;
import org.example.StateController.State;

public class BestSellingState implements State {
    private Context context;
    public BestSellingState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Product product = ProductDataBase.getBestSelling();
        System.out.println("The best selling product in this store is : " + product.getName());
        System.out.println("Sold : " + product.getSellCounter());
        context.setCurrentState(new AdminState(context));
        context.handleInput();
    }


}
