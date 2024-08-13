Feature: Content Management

  Scenario: Admin manages recipes
    Given the admin is logged in for content management
    When the admin views the list of recipes
    Then the list of recipes should be displayed
    When the admin deletes a recipe with title "Chocolate Cake"
    Then the recipe with title "Chocolate Cake" should be removed

  Scenario: Admin manages posts
    Given the admin is logged in for content management
    When the admin views the list of posts
    Then the list of posts should be displayed
    When the admin deletes a post with title "My Dessert Creation"
    Then the post with title "My Dessert Creation" should be removed