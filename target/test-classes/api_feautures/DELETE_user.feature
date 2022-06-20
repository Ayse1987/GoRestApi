Feature: delete user

  Scenario:delete_user
    Given set up get endpoint
    And send delete request
    Then validate status Code is 204