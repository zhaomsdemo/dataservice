Feature: BDD test for User

  Scenario Outline: Create a new user
    Given Create a request for new user with userName <userName> and age <age>
    When Call the service for post user
    Then Service should respond status of <status>
    And Service should respond user Id

    Examples:
    |userName   |age  |status|
    |zjh        |37   |200   |