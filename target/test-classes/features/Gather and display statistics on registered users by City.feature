Feature: Gather and display statistics on registered users by City

  Scenario: Admin gathers and displays user statistics for city "Nablus"
    Given the admin is logged in for user statistics
    When the admin views the user statistics for city "Nablus"
    Then the user statistics for city "Nablus" should be displayed

  Scenario: Admin gathers and displays user statistics for city "Jenin"
    Given the admin is logged in for user statistics
    When the admin views the user statistics for city "Jenin"
    Then the user statistics for city "Jenin" should be displayed