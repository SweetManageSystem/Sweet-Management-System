Feature: Monitor Sales

  Scenario: Store owner monitors sales and profits
    Given the following products exist:
      | id | name  | price | sellCounter |
      | 1  | test1 | 50.5  | 10          |
      | 2  | test2 | 99.9  | 6           |
      | 3  | test3 | 14.5  | 25          |
      | 4  | test4 | 19.9  | 23          |
      | 5  | test5 | 9.9   | 18          |
    When the store owner checks the sales and profits
    Then the total sales should be 82
    And the total profit should be 2102.8