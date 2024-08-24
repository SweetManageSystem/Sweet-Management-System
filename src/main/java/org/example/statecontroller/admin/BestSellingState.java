package org.example.statecontroller.admin;

import org.example.database.ProductDataBase;
import org.example.reciepes.Product;
import org.example.statecontroller.Context;
import org.example.statecontroller.ExitState;
import org.example.statecontroller.State;

import java.util.logging.Logger;

public class BestSellingState implements State {
    private Context context;
    private Logger logger = Logger.getLogger(BestSellingState.class.getName());
    public BestSellingState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        Product product = ProductDataBase.getBestSelling();
        logger.info("The best selling product in this store is :" + product.getName());
        logger.info("Sold : "+ product.getSellCounter());
        if(context.isTest()){
            context.setCurrentState(new ExitState());
            context.handleInput();
        }
        else {
            context.setCurrentState(new AdminState(context));
            context.handleInput();
        }
    }


}
