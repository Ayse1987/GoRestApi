Feature: Create user

  @NewUser
  Scenario Outline:new_user
    Given user sets up endpoint.
    And user sets the expected data "<name>","<gender>","<email>","<status>"
    And user send the request and get the response.
    When user saves the new user's record to correspondent files
    Then user validates new user created
    Examples:
      | name | gender | email            | status |
      | Sue Lion | female   | s.lion@gmail.com | active |

  #@NewUser
  #Scenario:get_user_details
  #  Given set up get endpoint
  #  And send get request
  #  Then validate the user





