Feature: Monitor Profits State

  Scenario: Generate financial report
    Given the context is set to MonitorProfitsState
    When the handleInput method is called
    Then the financial report should be generated
    And the total profits should be calculated correctly
    And the monthly profits should be calculated correctly
    And the expenses should be calculated correctly
    And the net profit should be calculated correctly
