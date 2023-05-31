Feature: Create agent from genetic code

  @CreateAgent
  Scenario: Successfully create agent from genetic code
    Given the user knows a genetic code and has enough amino and nucleotid
    When the user creates an agent
    Then the user gets the agent
