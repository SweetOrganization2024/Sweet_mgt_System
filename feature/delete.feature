Feature: Delete


  Scenario Outline:delete an sweet with Valid information
    Given The user login as owner or supplier with   "<email>" and "<password>"
    When The information is valid name is "<name of sweet>" and id is "<id of sweet>" and type is "<type of sweet>"
    Then The specified sweet  must be deleted from  sweet list
    And The User should see the sweet successfully deleted

   Examples:
       |id of sweet |name of sweet        |type of sweet      |
       |01          |Cake of chocolate    |zero sugar         |
       |02          |Lotus cheesecake     |gluten free        |
       |03          |Crepe                |sweet              |
       |04          |Donuts               |sweet              |
       |05          |Vegan carrot cake    |Vegetarian diet    |
       |06          |Macaron              |zero sugar         |

  Scenario Outline: Trying to delete an sweet that does not exist
    Given The user login as owner or supplier with   "<email>" and "<password>"
    When the user deletes an sweet with not available id is "<id of sweet> " or not available  name is "<name of sweet>" or type is "<type of sweet>"
    Then The user should see a message that this sweet does not exist

   Examples:
       |id of sweet |name of sweet        |type of sweet      |
       |01          |Cake of chocolate    |zero sugar         |
       |02          |Lotus cheesecake     |gluten free        |
       |03          |Crepe                |sweet              |
       |04          |Donuts               |sweet              |
       |05          |Vegan carrot cake    |Vegetarian diet    |
       |06          |Macaron              |zero sugar         |