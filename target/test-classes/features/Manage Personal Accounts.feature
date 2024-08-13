Feature: Manage Personal Accounts
  As a beneficiary user
  I want to manage my personal account
  So that I can view, update my personal information, or delete my account

  Scenario: User Accesses Manage Account
    Given the user has passed the Login state
    Then the user should see a "Manage Account" option
    When the user selects "Manage Account"
    Then the system should transition the user to the "Manage Account" state

  Scenario: Manage Account Options
    Given the user is in the "Manage Account" state
    Then the following options should appear: 1. View Personal Account Information 2. Update Personal Account Information 3. Delete Account

  Scenario: View Personal Account Information
    Given the user is in the "Manage Account" state
    When the user selects option 1
    Then the system should display the user's current account information
    And the displayed information should include email, username, full name, password, and role

  Scenario: Update Personal Account Information
    Given the user is in the "Manage Account" state
    When the user selects option 2
    Then the system should prompt the user to update their account information
    When the user updates the "username" to "newusername"
    And the user updates the "full name" to "newfullname"
    And the user updates the "password" to "newpassword"
    And the user confirms the "password" as "newpassword"
    Then the system should validate that the new password matches the confirmation
    And the system should save the updated account information
    And the system should display a success message "Account updated successfully"
    And the system should return the user to the initial state (state after login)

  Scenario: Delete Account
    Given the user is in the "Manage Account" state
    When the user selects option 3
    Then the system should confirm the deletion of the user's account
    And if confirmed, the system should delete the user's account
    And the system should log the user out
    And the system should redirect the user to the login state
    And the system should display a message "Account deleted successfully"