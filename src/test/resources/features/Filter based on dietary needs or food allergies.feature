Feature: Filter recipes based on dietary needs or food allergies

  Scenario: User filters recipes based on dietary needs
    Given the user is on the recipe search page
    When the user selects "Gluten-Free" from the dietary needs filter
    And the user clicks on the "Apply Filters" button
    Then only "Gluten-Free" recipes should be displayed

  Scenario: User filters recipes based on food allergies
    Given the user is on the recipe search page
    When the user selects "Nut-Free" from the food allergies filter
    And the user clicks on the "Apply Filters" button
    Then only "Nut-Free" recipes should be displayed