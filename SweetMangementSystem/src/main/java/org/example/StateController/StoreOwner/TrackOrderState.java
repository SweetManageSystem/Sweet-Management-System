package org.example.StateController.StoreOwner;

import org.example.Database.OrderDatabase;
import org.example.Reciepes.Order;
import org.example.StateController.Context;
import org.example.StateController.ExitState;
import org.example.StateController.State;

import java.util.List;
import java.util.Scanner;

public class TrackOrderState implements State {
    private Context context;
    private Scanner input;

    public TrackOrderState(Context context) {
        this.context = context;
        this.input = new Scanner(System.in); // Default to standard input
    }

    // Constructor for dependency injection
    public TrackOrderState(Context context, Scanner input) {
        this.context = context;
        this.input = input;
    }

    @Override
    public void handleInput() {
        System.out.println("Track Order System\n" +
                "1. View All Orders         2. View Order Details\n" +
                "3. Update Order Status     4. Filter Orders by Status\n" +
                "5. Search Orders by Customer Name");

        if (input.hasNextLine()) {
            String command = input.nextLine();
            filterState(command);
            switch (command) {
                case "1":
                    viewAllOrders();
                    break;
                case "2":
                    viewOrderDetails();
                    break;
                case "3":
                    updateOrderStatus();
                    break;
                case "4":
                    filterOrdersByStatus();
                    break;
                case "5":
                    searchOrdersByCustomerName();
                    break;
                default:
                    System.out.println("Invalid command");
                    break;
            }

            context.handleInput();
        }
    }

    private void viewAllOrders() {
        List<Order> orders = OrderDatabase.getOrders();
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            for (Order order : orders) {
                System.out.println(order.toString());
            }
        }
    }

    private void viewOrderDetails() {
        System.out.print("Enter Order ID: ");
        String orderId = input.nextLine();
        Order order = OrderDatabase.getOrderById(orderId);
        if (order == null) {
            System.out.println("Order not found.");
        } else {
            System.out.println(order.toString());
        }
    }

    private void updateOrderStatus() {
        System.out.print("Enter Order ID: ");
        String orderId = input.nextLine();
        Order order = OrderDatabase.getOrderById(orderId);
        if (order == null) {
            System.out.println("Order not found.");
            return;
        }
        System.out.print("Enter new status: ");
        String status = input.nextLine();
        order.setStatus(status);
        OrderDatabase.updateOrder(order);
        System.out.println("Order status updated.");
    }

    private void filterOrdersByStatus() {
        System.out.print("Enter status to filter by: ");
        String status = input.nextLine();
        List<Order> orders = OrderDatabase.getOrdersByStatus(status);
        if (orders.isEmpty()) {
            System.out.println("No orders found with status: " + status);
        } else {
            for (Order order : orders) {
                System.out.println(order.toString());
            }
        }
    }

    private void searchOrdersByCustomerName() {
        System.out.print("Enter customer name: ");
        String customerName = input.nextLine();
        List<Order> orders = OrderDatabase.getOrdersByCustomerName(customerName);
        if (orders.isEmpty()) {
            System.out.println("No orders found for customer: " + customerName);
        } else {
            for (Order order : orders) {
                System.out.println(order.toString());
            }
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