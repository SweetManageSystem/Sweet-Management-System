package org.example.statecontroller.storeowner;

import org.example.database.OrderDatabase;
import org.example.reciepes.Order;
import org.example.statecontroller.Context;
import org.example.statecontroller.ExitState;
import org.example.statecontroller.State;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class TrackOrderState implements State {
    private Context context;
    private Scanner input;
    private Logger logger = Logger.getLogger(TrackOrderState.class.getName());
    private String command;
    private String orderId;
    private String customerName;
    private String status;

    public void setCommand(String command) {
        this.command = command;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;

    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public void setStatus(String status) {
        this.status = status;
    }



    public TrackOrderState(Context context) {
        this.context = context;
        this.input = new Scanner(System.in); // Default to standard input
    }

    // Constructor for dependency injection


    @Override
    public void handleInput() {
        String textBlock = """
                     <html>
                          <body>
                                <tag>
                                  Track Order System<br>
                                   1. View All Orders         2. View Order Details<br>
                                   3. Update Order Status     4. Filter Orders by Status<br>
                                   5. Search Orders           6.Back
                             
                                </tag>
                          </body>
                    </html>""";
        logger.info(textBlock);
        if(!context.isTest())
           command = input.nextLine();
        context.filterState(command);
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
                case "6":
                    context.setCurrentState(new StoreOwnerState(context));
                    if(context.isTest())
                        context.setCurrentState(new ExitState());
                    break;
                default:
                    logger.info("Invalid command");
                    if(context.isTest())
                        context.setCurrentState(new ExitState());
                    break;
            }

            context.handleInput();

    }

    private void viewAllOrders() {
        List<Order> orders = OrderDatabase.getOrders();
        if (orders.isEmpty()) {
            logger.info("No orders found.");
        } else {
            for (Order order : orders) {
                String output = order.toString();
                logger.info(output);
            }
        }
    }

    private void viewOrderDetails() {
        logger.info("Enter Order ID: ");
        if(!context.isTest())
            orderId = input.nextLine();
        Order order = OrderDatabase.getOrderById(orderId);
        if (order == null) {
            logger.info("Order not found.");
        } else {
            String output = order.toString();
            logger.info(output);
        }
    }

    private void updateOrderStatus() {
        logger.info("Enter Order ID: ");
        if(!context.isTest())
            orderId = input.nextLine();
        Order order = OrderDatabase.getOrderById(orderId);
        if (order == null) {
            logger.info("Order not found.");
            return;
        }
        logger.info("Enter new status: ");
        if(!context.isTest())
            status = input.nextLine();
        order.setStatus(status);
        OrderDatabase.updateOrder(order);
        logger.info("Order status updated.");
    }

    private void filterOrdersByStatus() {
        logger.info("Enter status to filter by: ");
        if(!context.isTest())
            status = input.nextLine();
        List<Order> orders = OrderDatabase.getOrdersByStatus(status);
        if (orders.isEmpty()) {
            logger.info("No orders found with this status");
        } else {
            for (Order order : orders) {
                String output = order.toString();
                logger.info(output);
            }
        }
    }

    private void searchOrdersByCustomerName() {
        logger.info("Enter customer name: ");
        if(!context.isTest())
            customerName = input.nextLine();
        List<Order> orders = OrderDatabase.getOrdersByCustomerName(customerName);
        if (orders.isEmpty()) {
            logger.info("No orders found for this customer");
        } else {
            for (Order order : orders) {
                String output = order.toString();
                logger.info(output);
            }
        }
    }


}