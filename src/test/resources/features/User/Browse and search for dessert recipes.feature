Feature: Search State

  Scenario: User navigates to Search State and performs a search
    Given the user is in the User State
    When the user selects the "Search for recipes" option
    Then the user should be in the Search State

  Scenario: User navigates back from Search State
    Given the user is in the Search State
    When the user inputs "back"
    Then the user should be in the User State

  Scenario: User exits from Search State
    Given the user is in the Search State
    When the user inputs "exit"
    Then the user should be in the Exit State