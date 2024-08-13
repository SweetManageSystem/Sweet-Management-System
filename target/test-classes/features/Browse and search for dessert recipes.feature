Feature: Browse and search

  Scenario: Browse dessert recipes
    Given the user is logged in
    When the user navigates to the dessert recipes section
    Then the user should see a list of dessert recipes

  Scenario: Search for a specific dessert recipe
    Given the user is logged in
    When the user searches for "Chocolate Cake"
    Then the user should see search results containing "Chocolate Cake"