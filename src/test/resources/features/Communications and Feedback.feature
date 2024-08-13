Feature: Communication and Feedback

  Scenario: User communicates with a store owner for inquiries
    Given the user is logged in for communication
    And the user is on the store owner's page for communication
    When the user sends a message to the store owner
    Then the store owner should receive the message
    And the user should see a confirmation of message sent to store owner

  Scenario: User communicates with a supplier for assistance
    Given the user is logged in for communication
    And the user is on the supplier's page
    When the user sends a message to the supplier
    Then the supplier should receive the message
    And the user should see a confirmation of message sent to supplier