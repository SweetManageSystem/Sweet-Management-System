Feature: login
As a registered user
I want to log in to the Sweet Management System
So that I can access my account and its features

  Background:
    Given the Sweet Management System is running

  Scenario: Successful login
    Given I am on the login page
    When I enter a valid username "<username>"
    And I enter a valid password "<password>"
    And I submit the login form
    Then I should be redirected to the dashboard
    And I should see a welcome message "Welcome, <username>!"

  Scenario: Login with incorrect username or password or both
    Given I am on the login page
    When I enter an invalid username "<invalid username>"
    And I enter an invalid password "<invalid password>"
    And I submit the login form
    Then I should see an error message "Invalid username or password."

  Scenario: Login with missing username or password
    Given I am on the login page
    When I leave the username field blank
    And I enter a valid password "<password>"
    And I submit the login form
    Then I should see an error message "Username is required."
    When I enter a valid username "<username>"
    And I leave the password field blank
    And I submit the login form
    Then I should see an error message "Password is required."
