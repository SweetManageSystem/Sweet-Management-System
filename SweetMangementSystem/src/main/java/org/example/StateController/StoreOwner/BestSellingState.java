package org.example.StateController.StoreOwner;

import org.example.Database.ProductDataBase;
import org.example.Reciepes.Product;
import org.example.StateController.Context;
import org.example.StateController.State;
public class BestSellingState implements State {

    private Context context;
    private boolean isTestMode;

    public BestSellingState(Context context) {
        this(context, false);
    }

    public BestSellingState(Context context, boolean isTestMode) {
        this.context = context;
        this.isTestMode = isTestMode;
    }

    @Override
    public void handleInput() {
        Product product = ProductDataBase.getBestSelling();
        System.out.println("The best selling product in this store is : " + product.getName());
        System.out.println("Sold : " + product.getSellCounter());

        if (!isTestMode) {
            context.setCurrentState(new StoreOwnerState(context));
            context.handleInput();
        }
    }
}
