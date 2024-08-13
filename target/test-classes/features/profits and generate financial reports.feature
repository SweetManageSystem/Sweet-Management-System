Feature: Monitoring and Reporting

  Scenario: Admin monitors profits
    Given the admin is logged in
    When the admin views the profit report
    Then the profit report should be displayed

  Scenario: Admin generates a financial report
    Given the admin is logged in
    When the admin generates a financial report
    Then the financial report should be generated
    And the admin should see a confirmation of report generation