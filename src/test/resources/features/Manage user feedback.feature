Feature: Manage user feedback

  Scenario: Admin views user feedback
    Given the admin is logged in for feedback management
    When the admin views the list of user feedback
    Then the list of user feedback should be displayed

  Scenario: Admin deletes user feedback
    Given the admin is logged in for feedback management
    And the admin views the list of user feedback
    When the admin deletes feedback with content "Great recipe!"
    Then the feedback with content "Great recipe!" should be removed