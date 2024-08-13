Feature: Adjust Product
  As a Store Owner
  I want to be able to add, update, and remove products
  So that I can manage the product inventory effectively

  Background:
    Given I am logged in as a Store Owner

  Scenario: Add a new product
    When I choose to add a new product
    And I enter the product name "New Product"
    And I enter the product price 99.99
    Then the product "New Product" with price 99.99 should be added successfully

  Scenario: Update an existing product
    Given the product with ID 1 exists
    When I choose to update the product with ID 1
    And I enter the new product name "Updated Product"
    And I enter the new product price 79.99
    Then the product with ID 1 should be updated to name "Updated Product" and price 79.99

  Scenario: Remove an existing product
    Given the product with ID 1 exists
    When I choose to remove the product with ID 1
    Then the product with ID 1 should be removed successfully

  Scenario: Invalid choice
    When I enter an invalid choice "4"
    Then I should see an error message "Invalid choice"