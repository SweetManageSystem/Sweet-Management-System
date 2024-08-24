Feature: Message Stores State

  Scenario: User selects a store owner to message
    Given the user is in the MessageStoresState
    When the user selects a store owner by entering "1"
    Then the user should be prompted to enter a message
    And the message should be sent to the selected store owner

  Scenario: User sends a message to a store owner
    Given the user is in the MessageStoresState
    And the user has selected a store owner
    When the user enters a message "Hello, I have a question about your products."
    Then the message should be sent to the selected store owner

  Scenario: User enters an invalid store owner selection
    Given the user is in the MessageStoresState
    When the user enters an invalid selection "5"
    Then the user should see an error message "Invalid choice."


