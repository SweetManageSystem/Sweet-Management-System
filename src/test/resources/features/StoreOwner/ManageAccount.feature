Feature: Manage Account Details and Update Business Information

  Scenario: Edit Username
    Given I am logged in as a Store Owner with email "Nablus_Store@gmail.com"
    When I select "Manage Account"
    And I choose to edit the username
    And I enter the new username "NewUsername"
    Then the username should be updated to "NewUsername"

  Scenario: Edit Email
    Given I am logged in as a Store Owner with email "Nablus_Store@gmail.com"
    When I select "Manage Account"
    And I choose to edit the email
    And I enter the new email "newemail@example.com"
    Then the email should be updated to "newemail@example.com"

  Scenario: Edit Password
    Given I am logged in as a Store Owner with email "Nablus_Store@gmail.com"
    When I select "Manage Account"
    And I choose to edit the password
    And I enter the new password "NewPassword123"
    Then the password should be updated to "NewPassword123"

  Scenario: Edit Full Name
    Given I am logged in as a Store Owner with email "Nablus_Store@gmail.com"
    When I select "Manage Account"
    And I choose to edit the full name
    And I enter the new full name "New FullName"
    Then the full name should be updated to "New FullName"