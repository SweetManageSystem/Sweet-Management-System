Feature: Messaging System

  Scenario: Send a message to a user
    Given I am logged in as a store owner
    When I navigate to the messaging system
    And I choose to send a message
    And I enter the username "Khalid"
    And I enter the message "Hello Khalid, your order is ready for pickup."
    Then the message should be sent to the user "Khalid"

  Scenario: Read messages from users
    Given I am logged in as a store owner
    When I navigate to the messaging system
    And I choose to read messages
    Then I should see the list of messages from users