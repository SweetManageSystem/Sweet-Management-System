Feature: Post Dessert Creation

  Scenario: User posts a dessert creation successfully
    Given the user is logged in for posting
    When the user enters post details "Chocolate Cake Recipe"
    Then the dessert creation should be posted successfully
    And the user should be in the UserState
