Feature: login
  As a registered user
  I want to log in to the Sweet Management System
  So that I can access my account and its features

  Background:
    Given the Sweet Management System is running

  Scenario: Successful login
    Given I am on the login page
    When I enter a valid email "test@example.com"
    And I enter a valid password "password123"
    And I submit the login form
    Then I should be redirected to the dashboard
    And I should see a welcome message "Welcome, test@example.com!"

  Scenario: Login with incorrect email or password or both
    Given I am on the login page
    When I enter an invalid email "invalid@example.com"
    And I enter an invalid password "wrongpassword"
    And I submit the login form
    Then I should see an error message "Invalid email or password."

  Scenario: Login with missing email or password
    Given I am on the login page
    When I leave the email field blank
    And I enter a valid password "password123"
    And I submit the login form
    Then I should see an error message "Email is required."
    When I enter a valid email "test@example.com"
    And I leave the password field blank
    And I submit the login form
    Then I should see an error message "Password is required."