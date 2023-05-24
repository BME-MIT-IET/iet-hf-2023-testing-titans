Feature: Add player

  @AddPlayser
  Scenario: Successfully add player
    Given the add player screen is displayed
    When the user adds a player
    Then a new player is added
    