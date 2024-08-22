Feature: Best Selling Product

  Scenario: Display the best selling product
    Given the product database is initialized with products
    When I enter the BestSellingState
    Then I should see the best selling product details