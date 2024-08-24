package org.example.statecontroller.storeowner;

import org.example.database.ProductDataBase;
import org.example.reciepes.Product;
import org.example.statecontroller.Context;
import org.example.statecontroller.ExitState;
import org.example.statecontroller.State;

import java.util.logging.Logger;

public class MonitorSalesState implements State {
    private Context context;
    private int totalSales;
    private double totalProfit;
    private Logger logger = Logger.getLogger(MonitorSalesState.class.getName());

    public MonitorSalesState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        calculateSalesAndProfits();
        logger.info("Total sales: " + getTotalSales());
        logger.info("Total profit: " + getTotalProfit());
        context.setCurrentState(new StoreOwnerState(context));
        if (context.isTest())
            context.setCurrentState(new ExitState());
        context.handleInput();
    }

    public void calculateSalesAndProfits() {
        totalSales = 0;
        totalProfit = 0.0;
        for (Product product : ProductDataBase.getProducts()) {
            totalSales += product.getSellCounter();
            totalProfit += product.getSellCounter() * product.getPrice();
        }
    }

    public int getTotalSales() {
        return totalSales;
    }

    public double getTotalProfit() {
        return totalProfit;
    }
}