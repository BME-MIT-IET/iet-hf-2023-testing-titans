Feature: Can step

  @CanStep
  Scenario: Successfuly step to new field
    Given the game has started
    When the user steps to new field
    Then the user is on new field
    