Feature: Login functionality

  Scenario: Successful login with valid credentials
    Given the user is on the login page
    When the user enters a valid email and password
    And the user clicks the login button
    Then the user should be logged in successfully

  Scenario: Unsuccessful login with invalid credentials
    Given the user is on the login page
    When the user enters an invalid email and password
    And the user clicks the login button
    Then the user should see a login error message