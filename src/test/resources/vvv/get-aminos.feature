Feature: Get amino and nucleotid

  @GetAmino
  Scenario: Successfuly get amino and nucleotid when stepping on storage
    Given the game has started2
    When the user steps to a storage
    Then the user receieves amino and nucleotid
    