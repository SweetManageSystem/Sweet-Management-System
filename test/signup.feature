Feature: signup
  As a new user
  I want to sign up for the Sweet Management System
  So that I can access the platform as a Beneficiary User

  Background:
    Given the Sweet Management System is running

  Scenario: Successful signup
    Given I am on the signup page
    When I enter a valid username "<username>"
    And I enter a valid password "<password>"
    And I enter a valid confirm password "<confirm password>"
    And I enter a valid email address "<email address>"
    And I enter my full name "<full name>"
    And I submit the signup form
    Then I should see a confirmation message "Signup successful!"
    And my role should be set to "Beneficiary User"

  Scenario: Signup with missing required fields
    Given I am on the signup page
    When I enter a valid username "<username>"
    And I leave the password field blank
    And I enter a valid email address "<email address>"
    And I enter my full name "<full name>"
    And I submit the signup form
    Then I should see an error message "Password is required."

  Scenario: Signup with invalid email address
    Given I am on the signup page
    When I enter a valid username "<username>"
    And I enter a valid password "<password>"
    And I enter a valid confirm password "<confirm password>"
    And I enter an invalid email address "<invalid email address>"
    And I enter my full name "<full name>"
    And I submit the signup form
    Then I should see an error message "Invalid email address."

  Scenario: Signup with an existing email
    Given I am on the signup page
    When I enter a valid username "<username>"
    And I enter a valid password "<password>"
    And I enter a valid confirm password "<confirm password>"
    And I enter an existing email address "<existing email address>"
    And I enter my full name "<full name>"
    And I submit the signup form
    Then I should see an error message "Username already exists."

  Scenario: Signup with non-matching password and confirm password
    Given I am on the signup page
    When I enter a valid username "<username>"
    And I enter a valid password "<password>"
    And I enter a non-matching confirm password "<non-matching confirm password>"
    And I enter a valid email address "<email address>"
    And I enter my full name "<full name>"
    And I submit the signup form
    Then I should see an error message "Passwords do not match."