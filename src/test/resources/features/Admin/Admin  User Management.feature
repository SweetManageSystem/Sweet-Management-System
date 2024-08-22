Feature: Manage Users

  Scenario: Create a new user account
    Given I am in the Admin state
    When admin enter "1" to create a new user account
    And admin enter the email "newuser@example.com"
    And admin enter the username "newuser"
    And admin enter the password "password123"
    And admin enter the full name "New User"
    Then user "newuser" created successfully

  Scenario: Show user status
    Given I am in the Admin state
    When admin enter "2" to show user status
    And admin enter the email "momen123@gmail.com"
    Then admin should see the user status for "momen123@gmail.com"

  Scenario: Edit user details
    Given I am in the Admin state
    When admin enter "3" to edit user details
    And admin enter the email "momen123@gmail.com"
    And admin choose to edit the username
    And admin enter the new username "updateduser"
    Then the username should be updated to "updateduser"

  Scenario: Delete a user account
    Given I am in the Admin state
    When admin enter "4" to delete a user account
    And admin enter the email "momen123@gmail.com"
    Then user "momen123@gmail.com" deleted successfully
