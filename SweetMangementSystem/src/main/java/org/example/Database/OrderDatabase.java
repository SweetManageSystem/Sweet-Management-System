package org.example.Database;

import org.example.Reciepes.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDatabase {
    private static List<Order> orders = new ArrayList<>();

    static {
        // Add some initial orders for testing
        orders.add(new Order("123", "Janna", "Pending"));
        orders.add(new Order("124", "Momen", "Shipped"));
        orders.add(new Order("125", "Nasser", "Pending"));
    }

    public static List<Order> getOrders() {
        return orders;
    }

    public static Order getOrderById(String id) {
        for (Order order : orders) {
            if (order.getId().equals(id)) {
                return order;
            }
        }
        return null;
    }

    public static void updateOrder(Order order) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId().equals(order.getId())) {
                orders.set(i, order);
                return;
            }
        }
    }

    public static List<Order> getOrdersByStatus(String status) {
        return orders.stream()
                .filter(order -> order.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    public static List<Order> getOrdersByCustomerName(String customerName) {
        return orders.stream()
                .filter(order -> order.getCustomerName().equals(customerName))
                .collect(Collectors.toList());
    }

    public static void addOrder(Order order) {
        orders.add(order);
    }
}