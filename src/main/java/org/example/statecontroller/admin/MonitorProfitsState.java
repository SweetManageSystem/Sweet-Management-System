package org.example.statecontroller.admin;

import org.example.database.ProductDataBase;
import org.example.reciepes.Product;
import org.example.statecontroller.Context;
import org.example.statecontroller.State;

import java.util.List;
import java.util.logging.Logger;

public class MonitorProfitsState implements State {
    private Context context;
    private Logger logger = Logger.getLogger(MonitorProfitsState.class.getName());

    public MonitorProfitsState(Context context) {
            this.context = context;
    }

    @Override
    public void handleInput() {
        generateFinancialReport();
        context.setCurrentState(new AdminState(context));
        context.handleInput();
    }

    public void generateFinancialReport() {
        logger.info("Generating Financial Report...");
        logger.info("Financial Report:");
        logger.info("Total Profits: $" + getTotalProfits());
        logger.info("Monthly Profits: $" +  getMonthlyProfits());
        logger.info("Expenses: $" + getExpenses());
        logger.info("Net Profit: $" + getNetProfit());
    }

    public double getTotalProfits() {
        List<Product> products = ProductDataBase.getProducts();
        double totalProfits = 0;
        for (Product product : products) {
            totalProfits += product.getPrice() * product.getSellCounter();
        }
        return totalProfits;
    }

    public double getMonthlyProfits() {
        return getTotalProfits() / 12;
    }

    public double getExpenses() {
        return 1500;
    }




    public double getNetProfit() {
        return  getTotalProfits() - getExpenses();
    }
}
