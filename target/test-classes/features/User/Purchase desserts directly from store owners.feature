Feature: Purchase State

  Scenario: Display available products
    Given the user is in the Purchase State
    When the system displays available products
    Then the user should see a list of products with their IDs, names, and prices

  Scenario: Select a product by ID and confirm purchase
    Given the user is in the Purchase State
    And the system displays available products
    When the user enters a valid product ID
    And the user confirms the purchase
    Then the system should update the product's sell counter
    And the user should see a purchase successful message

  Scenario: Select a product by ID and cancel purchase
    Given the user is in the Purchase State
    And the system displays available products
    When the user enters a valid product ID
    And the user cancels the purchase
    Then the system should not update the product's sell counter
    And the user should see a purchase canceled message

  Scenario: Enter an invalid product ID
    Given the user is in the Purchase State
    And the system displays available products
    When the user enters an invalid product ID
    Then the user should see an invalid product ID message
    And the system should return to the User State