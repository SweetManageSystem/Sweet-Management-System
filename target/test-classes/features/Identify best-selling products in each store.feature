Feature: Identify best-selling products in each store

  Scenario: Admin identifies best-selling products in a store
    Given the admin is logged in for best-selling products
    When the admin views the best-selling products report for store "Store A"
    Then the best-selling products report for store "Store A" should be displayed