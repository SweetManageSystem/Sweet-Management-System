Feature: Process and Track Orders

  As a store owner
  I want to process and track orders
  So that I can manage my store's orders efficiently

  Background:
    Given the store owner is logged into the system

  Scenario: View all orders
    When the store owner navigates to the Track Order page
    Then the store owner should see a list of all orders

  Scenario: View order details
    Given the store owner is on the Track Order page
    When the store owner selects an order with ID "123"
    Then the store owner should see the details of the order with ID "123"

  Scenario: Update order status
    Given the store owner is on the Track Order page
    And the store owner selects an order with ID "123"
    When the store owner updates the status of the order to "Shipped"
    Then the status of the order with ID "123" should be updated to "Shipped"

  Scenario: Filter orders by status
    Given the store owner is on the Track Order page
    When the store owner filters orders by status "Pending"
    Then the store owner should see a list of orders with status "Pending"

  Scenario: Search orders by customer name
    Given the store owner is on the Track Order page
    When the store owner searches for orders by customer name "John Doe"
    Then the store owner should see a list of orders placed by "John Doe"