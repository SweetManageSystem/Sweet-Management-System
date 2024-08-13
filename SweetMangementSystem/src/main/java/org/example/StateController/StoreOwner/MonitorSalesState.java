package org.example.StateController.StoreOwner;

import org.example.Database.ProductDataBase;
import org.example.Reciepes.Product;
import org.example.StateController.Context;
import org.example.StateController.State;

public class MonitorSalesState implements State {
    private Context context;
    private int totalSales;
    private double totalProfit;

    public MonitorSalesState(Context context) {
        this.context = context;
    }

    @Override
    public void handleInput() {
        calculateSalesAndProfits();
        System.out.println("Total sales: " + totalSales);
        System.out.println("Total profit: " + totalProfit);
        context.setCurrentState(new StoreOwnerState(context));
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