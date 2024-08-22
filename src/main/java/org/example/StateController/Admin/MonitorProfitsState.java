package org.example.StateController.Admin;

import org.example.Database.ProductDataBase;
import org.example.Reciepes.Product;
import org.example.StateController.Context;
import org.example.StateController.ExitState;
import org.example.StateController.State;

import java.util.List;

public class MonitorProfitsState implements State {
    private Context context;
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
        double totalProfits = calculateTotalProfits();
        double monthlyProfits = calculateMonthlyProfits();
        double expenses = calculateExpenses();
        double netProfit = calculateNetProfit();

        System.out.println("Generating Financial Report...");
        System.out.println("Financial Report:");
        System.out.println("Total Profits: $" + totalProfits);
        System.out.println("Monthly Profits: $" + monthlyProfits);
        System.out.println("Expenses: $" + expenses);
        System.out.println("Net Profit: $" + netProfit);
    }

    public double calculateTotalProfits() {
        List<Product> products = ProductDataBase.getProducts();
        double totalProfits = 0;
        for (Product product : products) {
            totalProfits += product.getPrice() * product.getSellCounter();
        }
        return totalProfits;
    }

    public double calculateMonthlyProfits() {
        return calculateTotalProfits() / 12;
    }

    public double calculateExpenses() {
        return 1500;
    }




    public double calculateNetProfit() {
        return  calculateTotalProfits() - calculateExpenses();
    }
}
