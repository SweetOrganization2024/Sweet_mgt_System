Feature: Update sweet

  Scenario Outline: User selects a valid sweet ID to edit
    Given The user login as owner or supplier  with   "<email>" and "<password>"
    When the user selects valid  "<id of sweet>" and "<name of sweet>" and "<type of sweet>"
    And The system will show "<message>" what the user want to update
    And The User enter what they want to update name or type
    And The User enter the new value
    Then the system update from sweet lists


   Examples:
       |id of sweet |name of sweet        |type of sweet      |message                        |
       |01          |Cake of chocolate    |zero sugar         |you want update :1-name 2-type |
       |02          |Lotus cheesecake     |gluten free        |you want update :1-name 2-type |
       |03          |Crepe                |sweet              |you want update :1-name 2-type |
       |04          |Donuts               |sweet              |you want update :1-name 2-type |
       |05          |Vegan carrot cake    |Vegetarian diet    |you want update :1-name 2-type |
       |06          |Macaron              |zero sugar         |you want update :1-name 2-type |





        Scenario Outline:User selects an invalid sweet ID and name to edit
          Given The user login as owner or supplier with   "<email>" and "<password>"
          When the user updates an sweet with not available id is "<id of sweet> " or not available  name is "<name of sweet>" or type is "<type of sweet>"
          Then The user should see a message that this sweet does not exist

         Examples:
             |id of sweet |name of sweet        |type of sweet      |
             |01          |Cake of chocolate    |zero sugar         |
             |02          |Lotus cheesecake     |gluten free        |
             |03          |Crepe                |sweet              |
             |04          |Donuts               |sweet              |
             |05          |Vegan carrot cake    |Vegetarian diet    |
             |06          |Macaron              |zero sugar         |