Feature: Get equipment

  @GetEquipment
  Scenario: Successfuly get equipment on shelter
    Given the game has started3
    When the user steps to a shelter
    Then the user receieves equipment
    