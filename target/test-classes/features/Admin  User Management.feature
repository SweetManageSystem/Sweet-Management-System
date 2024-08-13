Feature: Admin Login and Transition to User Management State

  Scenario: Admin logs in with correct credentials
    Given the admin has logged in with email "admin@gmail.com" and password "admin"
    Then the "User Management" option should appear

  Scenario: Admin enters the User Management state
    Given the admin is now in the "User Management" state
    Then the following options should appear:1. Create a new user account 2. Show user status (based on user email, display username and role) 3. Edit user details (username, role, or email) 4. Delete a user account

  Scenario: Admin creates a new user account
    Given the admin is in the "User Management" state
    When the admin selects "Create a new user account"
    And enters the user's details: username "storeowner1", role "StoreOwner", email "storeowner1@example.com", password "123", confirmPassword "123"
    Then a new user account should be created with the provided details
    And the system should prints "User Created Successfully"

  Scenario: Admin views user status
    Given the admin is in the "User Management" state
    When the admin selects "Show user status"
    And enters the user's email "storeowner1@example.com"
    Then the system should display the user's username "storeowner1", role "0" and password "123"

  Scenario: Admin edits a user account
    Given the admin is in the "User Management" state
    When the admin selects "Edit user details"
    And enters the user's email "storeowner1@example.com"
    And updates the details to: username "newstoreowner", role "1", email "newstoreowner@example.com", password "321"
    Then the user account should be updated with the new details
    And the system should prints "User Updated Successfully"

  Scenario: Admin deletes a user account
    Given the admin is in the "User Management" state
    When the admin selects "Delete a user account"
    And enters the user's email "storeowner1@example.com"
    Then the user account associated with "storeowner1@example.com" should be deleted
    And the system should prints "User Deleted Successfully"
