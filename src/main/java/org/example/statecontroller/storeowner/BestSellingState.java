package org.example.statecontroller.storeowner;

import org.example.database.ProductDataBase;
import org.example.reciepes.Product;
import org.example.statecontroller.Context;
import org.example.statecontroller.State;

import java.util.logging.Logger;

public class BestSellingState implements State {

    private Context context;
    private boolean isTestMode;
    private Logger logger = Logger.getLogger(BestSellingState.class.getName());

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
        logger.info("The best selling product in this store is : " + product.getName());
        logger.info("Sold : " + product.getSellCounter());

        if (!isTestMode) {
            context.setCurrentState(new StoreOwnerState(context));
            context.handleInput();
        }
    }
}
