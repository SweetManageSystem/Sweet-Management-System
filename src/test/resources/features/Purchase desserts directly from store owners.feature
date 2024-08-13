Feature: Purchase desserts directly from store owners

  Scenario: User purchases a dessert from a store owner
    Given the user is logged in for purchase
    And the user is on the store owner's page
    When the user selects a dessert to purchase
    And the user confirms the purchase
    Then the user should see a confirmation message
    And the store owner should receive the order