Feature: Partial Update(patch) user


  Scenario Outline: patch_request
    Given set up get endpoint
    And user sets the expected data "<name>","<email>","<status>"
    And send patch request
    Then validate updated user
    Examples:
      | name     |  email            | status |
      | Sue Lion |  s.lion@gmail.com | active |