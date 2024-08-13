Feature: Get the best selling product

  Scenario: Store owner retrieves the best selling product
    Given the following products exist:
      | id | name   | price | sellCounter |
      | 1  | test1  | 50.5  | 10          |
      | 2  | test2  | 99.9  | 6           |
      | 3  | test3  | 14.5  | 25          |
      | 4  | test4  | 19.9  | 23          |
      | 5  | test5  | 9.9   | 18          |
    When the store owner checks the best selling product
    Then the best selling product should be "test3" with 25 sales