Feature: Manage Content

  Background:
    Given the context is set to ManageContentState

  Scenario: View Products
    When I choose to view products
    Then I should see a list of products

  Scenario: Add Product
    When I choose to add a product
    And I enter the product name "Chocolate Cake"
    And I enter the product price 15.99
    And I enter the product sell counter 5
    Then the product "Chocolate Cake" should be added successfully

  Scenario: Edit Product
    Given a product with ID 1 exists
    When I choose to edit a product
    And I enter the product ID 1
    And I enter the new product name "Vanilla Cake"
    And I enter the new product price 12.99
    Then the product with ID 1 should be updated successfully

  Scenario: Delete Product
    Given a product with ID 1 exists
    When I choose to delete a product
    And I enter the product ID 1
    Then the product with ID 1 should be deleted successfully

  Scenario: View Posts
    Given a user "JohnDoe" with posts exists
    When I choose to view posts
    Then I should see the posts of user "JohnDoe"

  Scenario: Add Post
    Given a user "JohnDoe" with posts exists
    When I choose to add a post
    And I enter the username "JohnDoe"
    And I enter the post content "New dessert recipe"
    Then the post "New dessert recipe" should be added to user "JohnDoe" successfully

  Scenario: Delete Post
    Given a user "JohnDoe" with posts exists
    When I choose to delete a post
    And I enter the username "JohnDoe"
    And I enter the post content "Old dessert recipe"
    Then the post "Old dessert recipe" should be deleted from user "JohnDoe" successfully