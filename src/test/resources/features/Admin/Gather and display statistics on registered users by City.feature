Feature: Display User Statistics by City

  Scenario: Display statistics with multiple users from different cities
    Given the following users exist:
      | email             | password | username | fullname      | address     |
      | user1@example.com | pass1    | user1    | User One      | New York    |
      | user2@example.com | pass2    | user2    | User Two      | Los Angeles |
      | user3@example.com | pass3    | user3    | User Three    | New York    |
      | user4@example.com | pass4    | user4    | User Four     | Chicago     |
    When I display the user statistics by city
    Then I should see the following statistics:
      | city        | number_of_users |
      | New York    | 2               |
      | Los Angeles | 1               |
      | Chicago     | 1               |

  Scenario: Display statistics with no users
    Given no users exist
    When I display the user statistics by city
    Then I should see no statistics

  Scenario: Display statistics with users from the same city
    Given the following users exist:
      | email             | password | username | fullname      | address  |
      | user1@example.com | pass1    | user1    | User One      | New York |
      | user2@example.com | pass2    | user2    | User Two      | New York |
    When I display the user statistics by city
    Then I should see the following statistics:
      | city     | number_of_users |
      | New York | 2               |