Feature: Manage Feedback State

  Scenario: View feedback when feedback is available
    Given the feedback database is initialized with feedback
    When the admin chooses to view feedback
    Then the system should display all feedback

  Scenario: View feedback when no feedback is available
    Given the feedback database is empty
    When the admin chooses to view feedback
    Then the system should display "No feedback available."

  Scenario: Respond to feedback
    Given the feedback database is initialized with feedback
    When the admin chooses to respond to feedback with ID "1"
    And the admin enters the response "Thank you for your feedback"
    Then the system should record the response for feedback ID "1"

  Scenario: Delete feedback
    Given the feedback database is initialized with feedback
    When the admin chooses to delete feedback with ID "1"
    Then the system should delete the feedback with ID "1"