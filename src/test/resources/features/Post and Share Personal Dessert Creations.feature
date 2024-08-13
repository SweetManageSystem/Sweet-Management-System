Feature: Post and Share Personal Dessert Creations

  Scenario: Sign in to the platform
    Given the user is on the dessert creations login page
    When the user logs in with username "newuser" and password "newpassword"
    Then the user should be redirected to the home page for dessert creations

  Scenario: View options after login
    Given the user has logged in with username "newuser" and password "newpassword"
    Then the user should see a dessert creation option "Manage Account"
    And the user should see a dessert creation option "Post and share personal dessert creations"

  Scenario: Post and share a new dessert creation
    Given the user has logged in with username "newuser" and password "newpassword"
    And the user is in the "Home" state
    When the user selects the dessert creation option "Post and share personal dessert creations"
    Then the system should transition the user to the dessert creation state "Post and Share"
    When the user posts a new dessert creation with title "Chocolate Cake" and description "Delicious chocolate cake recipe"
    Then the system should save the new dessert creation
    And the system should display a dessert creation success message "Dessert creation posted successfully"
    And the user should see the new dessert creation in their list of posts
