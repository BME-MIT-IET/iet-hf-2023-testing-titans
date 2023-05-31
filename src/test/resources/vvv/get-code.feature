Feature: Get genetic code

  @GetCode
  Scenario: Successfuly get genetic code on laboratory
    Given the game has started4
    When the user steps to a laboratory
    Then the user receieves a genetic code
