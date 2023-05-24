Feature: Start Game

  @StartGame
  Scenario: Successfully create new Game
    Given the game has players
    When the user pressess the start button
    Then the game starts
