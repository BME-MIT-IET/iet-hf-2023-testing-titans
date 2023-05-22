Feature: New Game
  Starting a new game with menu
  Scenario: Successfully create new Game
    Given the game has not started yet
    When the user pressess the new game button
    Then a new game starts
    