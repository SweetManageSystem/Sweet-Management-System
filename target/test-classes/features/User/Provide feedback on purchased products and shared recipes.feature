Feature: Provide Feedback

  Scenario: User provides feedback successfully
    Given the user is logged in
    And the user is in the Provide Feedback state
    When the user provides feedback "Great product!"
    Then the feedback "Great product!" should be saved
    And the user should be returned to the User state

  Scenario: User enters 'back' command
    Given the user is logged in
    And the user is in the Provide Feedback state
    When the user enters "back"
    Then the user should be returned to the User state

  Scenario: User enters 'exit' command
    Given the user is logged in
    And the user is in the Provide Feedback state
    When the user enters "exit"
    Then the user should be returned to the Exit state

  Scenario: User enters an invalid command
    Given the user is logged in
    And the user is in the Provide Feedback state
    When the user enters an invalid command "invalid"
    Then the feedback "invalid" should be saved
    And the user should be returned to the User state