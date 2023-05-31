Feature: Cannot create agent from genetic code, not enough amino

  @CannotCreateAgent
  Scenario: Unsuccessfully create agent from genetic code, because not enough amino
    Given the user knows a genetic code but does not have enough amino and nucleotid
    When the user tries to create an agent
    Then the user does not get an agent
