Feature: Manage Account Details and Update Business Information

  Scenario: Edit Username
    Given I am logged in as a Store Owner with email "Nablus_Store@gmail.com"
    When Store Owner select "Manage Account"
    And Store Owner choose to edit the username
    And Store Owner enter the new username "NewUsername"
    Then username should be updated to "NewUsername"

  Scenario: Edit Email
    Given I am logged in as a Store Owner with email "Nablus_Store@gmail.com"
    When Store Owner select "Manage Account"
    And Store Owner choose to edit the email
    And Store Owner enter the new email "newemail@example.com"
    Then email should be updated to "newemail@example.com"

  Scenario: Edit Password
    Given I am logged in as a Store Owner with email "Nablus_Store@gmail.com"
    When Store Owner select "Manage Account"
    And Store Owner choose to edit the password
    And Store Owner enter the new password "NewPassword123"
    Then password should be updated to "NewPassword123"

  Scenario: Edit Full Name
    Given I am logged in as a Store Owner with email "Nablus_Store@gmail.com"
    When Store Owner select "Manage Account"
    And Store Owner choose to edit the full name
    And Store Owner enter the new full name "New FullName"
    Then full name should be updated to "New FullName"