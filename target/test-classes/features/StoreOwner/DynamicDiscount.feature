Feature: Dynamic Discount Feature

  Scenario: Apply discount to all products
    Given the product database is initialized
    When the store owner applies a 10% discount to all products
    Then all products should have their prices reduced by 10%

  Scenario: Apply discount to best-selling product
    Given the product database is initialized
    When the store owner applies a 20% discount to the best-selling product
    Then the best-selling product should have its price reduced by 20%

  Scenario: Apply discount to products in a price range
    Given the product database is initialized
    When the store owner applies a 15% discount to products priced between 10 and 50
    Then all products priced between 10 and 50 should have their prices reduced by 15%