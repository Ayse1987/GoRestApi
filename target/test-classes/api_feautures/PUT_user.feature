Feature: Full update user (put) request

  Scenario Outline: put_request
    Given set up get endpoint
    And user sets the expected data "<name>","<gender>","<email>","<status>".
    And send put request
    Then validate fully updated user
    Examples:
      | name     | gender | email            | status |
      | Sue Lion | female | s.lion@gmail.com | active |