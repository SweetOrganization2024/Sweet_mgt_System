Feature:add

    Scenario Outline: add valid sweet
    Given The user login as Owner or Supplier with   "<email>" and "<password>"
    When The user add a new sweet
    Then The user fill in 'id of sweet' with "<id of sweet>"
    And The User fill in 'name of sweet' with "<name of sweet>"
    And The User fill in 'type of sweet' with "<type of sweet>"
    Then the new sweet must be added to the sweet list
    And The user should see  a "<message>"
    Examples:
      |id of sweet |name of sweet        |type of sweet      |
      |01          |Cake of chocolate    |zero sugar         |
      |02          |Lotus cheesecake     |gluten free        |
      |03          |Crepe                |sweet              |
      |04          |Donuts               |sweet              |
      |05          |Vegan carrot cake    |Vegetarian diet    |
      |06          |Macaron              |zero sugar         |



    Scenario Outline: add invalid sweet
    Given The user login as admin or user with   "<email>" and "<password>"
    When The user add a new sweet
    Then the new sweet mustn't be added to the sweet list
    And The user should see  a "<message>"
    Examples:
      |Email             |password       |message                                             |
      |user1@gmail.com   |128393         |you can't add a new item                            |
      |admin1@ex.com     |29384          |you must be Owner or Supplier to  add a new item    |



    Scenario Outline: add sweet with id already exist
    Given The user login as Owner or Supplier with   "<email>" and "<password>"
    When The user add an existing id of sweet with the id "<id of sweet>" and the name "<name of sweet>" and the type "<type of sweet>"
    Then the new sweet must not be added to the sweet list
    And The user should see a message that adding the sweet failed
    Examples:
          |id of sweet    |name of sweet          |type of sweet     |
          |01             |Cake of chocolate      |zero sugar        |
          |01             |Salt cake              |diet              |
