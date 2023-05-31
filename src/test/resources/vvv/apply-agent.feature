Feature: Apply agent on itself

  @ApplyAgent
  Scenario: Successfully apply agent
    Given the user has a crafted agent
    When the user applies it to itself
    Then the user gets effected
