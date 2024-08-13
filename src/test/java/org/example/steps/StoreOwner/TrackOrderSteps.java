package org.example.steps.StoreOwner;

import io.cucumber.java.en.*;
import org.example.StateController.Context;
import org.example.StateController.StoreOwner.TrackOrderState;
import org.example.Database.OrderDatabase;
import org.example.Reciepes.Order;

import java.util.List;

import static org.junit.Assert.*;

public class TrackOrderSteps {
    private Context context;
    private TrackOrderState trackOrderState;
    private List<Order> orders;
    private Order selectedOrder;

    @Given("the store owner is logged into the system")
    public void the_store_owner_is_logged_into_the_system() {
        context = new Context();
        trackOrderState = new TrackOrderState(context);
        context.setCurrentState(trackOrderState);
    }

    @When("the store owner navigates to the Track Order page")
    public void the_store_owner_navigates_to_the_track_order_page() {
        trackOrderState.handleInput();
    }

    @Given("the store owner is on the Track Order page")
    public void the_store_owner_is_on_the_track_order_page() {
        trackOrderState.handleInput();
    }

    @Then("the store owner should see a list of all orders")
    public void the_store_owner_should_see_a_list_of_all_orders() {
        orders = OrderDatabase.getOrders();
        assertNotNull(orders);
        assertFalse(orders.isEmpty());
    }

    @When("the store owner selects an order with ID {string}")
    public void the_store_owner_selects_an_order_with_id(String orderId) {
        selectedOrder = OrderDatabase.getOrderById(orderId);
        assertNotNull(selectedOrder);
    }

    @Then("the store owner should see the details of the order with ID {string}")
    public void the_store_owner_should_see_the_details_of_the_order_with_id(String orderId) {
        assertEquals(orderId, selectedOrder.getId());
    }

    @When("the store owner updates the status of the order to {string}")
    public void the_store_owner_updates_the_status_of_the_order_to(String status) {
        selectedOrder.setStatus(status);
        OrderDatabase.updateOrder(selectedOrder);
    }

    @Then("the status of the order with ID {string} should be updated to {string}")
    public void the_status_of_the_order_with_id_should_be_updated_to(String orderId, String status) {
        Order updatedOrder = OrderDatabase.getOrderById(orderId);
        assertEquals(status, updatedOrder.getStatus());
    }

    @When("the store owner filters orders by status {string}")
    public void the_store_owner_filters_orders_by_status(String status) {
        orders = OrderDatabase.getOrdersByStatus(status);
    }

    @Then("the store owner should see a list of orders with status {string}")
    public void the_store_owner_should_see_a_list_of_orders_with_status(String status) {
        for (Order order : orders) {
            assertEquals(status, order.getStatus());
        }
    }

    @When("the store owner searches for orders by customer name {string}")
    public void the_store_owner_searches_for_orders_by_customer_name(String customerName) {
        orders = OrderDatabase.getOrdersByCustomerName(customerName);
    }

    @Then("the store owner should see a list of orders placed by {string}")
    public void the_store_owner_should_see_a_list_of_orders_placed_by(String customerName) {
        for (Order order : orders) {
            assertEquals(customerName, order.getCustomerName());
        }
    }
}