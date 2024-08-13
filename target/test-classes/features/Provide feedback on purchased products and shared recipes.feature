Feature: Provide feedback on purchased products and shared recipes

  Scenario: User provides feedback on a purchased product
    Given the user is logged in for feedback
    And the user has purchased a product
    When the user provides feedback on the purchased product
    Then the feedback should be recorded
    And the user should see a confirmation of feedback submission

  Scenario: User provides feedback on a shared recipe
    Given the user is logged in for feedback
    And the user has viewed a shared recipe
    When the user provides feedback on the shared recipe
    Then the feedback should be recorded
    And the user should see a confirmation of feedback submission