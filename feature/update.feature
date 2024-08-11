Feature: Update sweet

  Scenario Outline: User updates sweet details
    Given The user logs in as owner or supplier with "<email>" and "<password>"
    When the user selects a valid sweet with ID "<id_of_sweet>" and name "<name_of_sweet>" and type "<type_of_sweet>" and price "<price_of_sweet>"
    And the user enters the new value to update "<new name>" and "<new type>"
    Then the system updates the sweet in the sweet list

   Examples:
      | email               | password    | id_of_sweet | name_of_sweet     | type_of_sweet     |price_of_sweet     |new name             |new type   |
      | h2eLLo3@gmail.com   | 09SNBC6     | 02          | Lotus cheesecake  | gluten free       |  50               |oreo cheesecake      |sweet      |
      | h2eLLo3@gmail.com   | 09SNBC6     | 05          | Vegan carrot cake | Vegetarian diet   |  25               |Macaron              |zero sugar |
      | supg856SS@gmail.com | kfh6j3nur58 | 06          | Macaron           | zero sugar        |  30               |Dark chocolate       |sweet      |


  Scenario Outline: User updates name
    Given The user logs in as owner or supplier with "<email>" and "<password>"
    When the user selects a valid sweet with ID "<id_of_sweet>" and name "<name_of_sweet>" and type "<type_of_sweet>" and price "<price_of_sweet>"
    And the user enters the new value to update "<new name>"
    Then the system updates the sweet in the sweet list

   Examples:
        | email               | password    | id_of_sweet | name_of_sweet       | type_of_sweet     |price_of_sweet     |new name             |
        | h2eLLo3@gmail.com   | 09SNBC6     | 02          | oreo cheesecake     |sweet              |  50               |nutella cheesecake   |
        | h2eLLo3@gmail.com   | 09SNBC6     | 05          | Macaron             |zero sugar         |  25               |Donuts               |
        | supg856SS@gmail.com | kfh6j3nur58 | 06          |Dark chocolate       |sweet              |  30               |cake chocolate       |


  Scenario Outline: User updates type
    Given The user logs in as owner or supplier with "<email>" and "<password>"
    When the user selects a valid sweet with ID "<id_of_sweet>" and name "<name_of_sweet>" and type "<type_of_sweet>" and price "<price_of_sweet>"
    And the user enters the new type to update "<new type>"
    Then the system updates the sweet in the sweet list

 Examples:
         | email               | password    | id_of_sweet | name_of_sweet       | type_of_sweet     |price_of_sweet     |new type              |
         | h2eLLo3@gmail.com   | 09SNBC6     | 02          | nutella cheesecake  |sweet              |  50               |diet                  |
         | h2eLLo3@gmail.com   | 09SNBC6     | 05          | Donuts              |zero sugar         |  25               |sweet                 |
         | supg856SS@gmail.com | kfh6j3nur58 | 06          |cake chocolate       |sweet              |  30               |zero sugar            |


  Scenario Outline: User updates price
    Given The user logs in as owner or supplier with "<email>" and "<password>"
    When the user selects a valid sweet with ID "<id_of_sweet>" and name "<name_of_sweet>" and type "<type_of_sweet>" and price "<price_of_sweet>"
    And the user enters the new price to update "<new price>"
    Then the system updates the sweet in the sweet list

  Examples:
           | email               | password    | id_of_sweet | name_of_sweet       | type_of_sweet     |price_of_sweet     |new price       |
           | h2eLLo3@gmail.com   | 09SNBC6     | 02          | nutella cheesecake  |diet               |  50               |40              |
           | h2eLLo3@gmail.com   | 09SNBC6     | 05          | Donuts              |sweet              |  25               |20              |
           | supg856SS@gmail.com | kfh6j3nur58 | 06          |cake chocolate       |zero sugar         |  30               |25              |



  Scenario Outline: User selects an invalid sweet ID or name to edit
    Given The user logs in as not owner or supplier with "<email>" and "<password>"
   When the user tries to update a sweet with an unavailable ID "<id_of_sweet>" or unavailable name "<name_of_sweet>" or type "<type_of_sweet>"
    Then the user should see a "<message>" indicating the sweet does not exist

  Examples:
    | email               | password    | id_of_sweet | name_of_sweet     | type_of_sweet    | message        |
    | supg856SS@gmail.com | kfh6j3nur58 | 07          | Fake Sweet        | fake type        | not available  |
    | h2eLLo3@gmail.com   | 09SNBC6     | 08          | Imaginary Dessert | non-existent     | not available  |