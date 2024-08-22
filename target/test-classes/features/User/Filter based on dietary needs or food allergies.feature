Feature: Filter Recipes by dietary needs or food allergies

  Scenario: Filter recipes with no matching criteria
    Given the user is in the FilterRecipsState
    When the user input "fish"
    Then no recipes should be found matching the specified criteria

  Scenario: Filter recipes with matching criteria
    Given the user is in the FilterRecipsState
    When the user input "peanuts"
    Then the following recipes should be displayed:
      | name  | price |
      | test1 | 50.5  |
      | test5 | 9.9   |

  Scenario: Filter recipes with multiple matching criteria
    Given the user is in the FilterRecipsState
    When the user input "peanuts,peppers"
    Then the following recipes should be displayed:
      | name  | price |
      | test1 | 50.5  |
      | test5 | 9.9   |

