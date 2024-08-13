Feature: Signup functionality

  Scenario: Successful signup with valid details
    Given the user is on the signup page
    When the user enters valid details
    And the user clicks the signup button
    Then the user should see a success message

  Scenario: Unsuccessful signup with missing details
    Given the user is on the signup page
    When the user enters invalid details
    And the user clicks the signup button
    Then the user should see an error message

  Scenario: Unsuccessful signup with existing email
    Given the user is on the signup page
    When the user enters an existing email
    And the user clicks the signup button
    Then the user should see an email exists message

  Scenario: Unsuccessful signup with existing username
    Given the user is on the signup page
    When the user enters an existing username
    And the user clicks the signup button
    Then the user should see a username exists message

  Scenario: Unsuccessful signup with mismatched passwords
    Given the user is on the signup page
    When the user enters mismatched passwords
    And the user clicks the signup button
    Then the user should see a passwords mismatch message