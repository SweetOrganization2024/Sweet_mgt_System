Feature: Update sweet

  Scenario Outline: User updates sweet details
    Given The user logs in as owner or supplier with "<email>" and "<password>"
    When the user selects a valid sweet with ID "<id_of_sweet>" and name "<name_of_sweet>" and type "<type_of_sweet>"
    And the information is valid: name is "<name_of_sweet>", ID is "<id_of_sweet>", and type is "<type_of_sweet>"
    And the user enters the new value to update "<new name>" and "<new type>"
    Then the system updates the sweet in the sweet list

  Examples:
    | email               | password    | id_of_sweet | name_of_sweet     | type_of_sweet   |new name             |new type   |
    | supg856SS@gmail.com | kfh6j3nur58 | 01          | Cake of chocolate | zero sugar      |Cake of vanilla      |zero sugar |
    | h2eLLo3@gmail.com   | 09SNBC6     | 02          | Lotus cheesecake  | gluten free     |Lotus cheesecake     |sweet      |
    | h2eLLo3@gmail.com   | 09SNBC6     | 03          | Crepe             | sweet           |Donuts               |zero sugar |
    | supg856SS@gmail.com | kfh6j3nur58 | 04          | Donuts            | sweet           |Crepe                |gluten free|
    | h2eLLo3@gmail.com   | 09SNBC6     | 05          | Vegan carrot cake | Vegetarian diet |Macaron              |zero sugar |
    | supg856SS@gmail.com | kfh6j3nur58 | 06          | Macaron           | zero sugar      |Dark chocolateDonuts |sweet      |


  Scenario Outline: User updates name
    Given The user logs in as owner or supplier with "<email>" and "<password>"
    When the user selects a valid sweet with ID "<id_of_sweet>" and name "<name_of_sweet>" and type "<type_of_sweet>"
    And the information is valid: name is "<name_of_sweet>", ID is "<id_of_sweet>", and type is "<type_of_sweet>"
    And the user enters the new value to update "<new name>"
    Then the system updates the sweet in the sweet list

  Examples:
    | email               | password    | id_of_sweet | name_of_sweet     | type_of_sweet   |new name             |
    | supg856SS@gmail.com | kfh6j3nur58 | 01          | Cake of chocolate | zero sugar      |Cake of vanilla      |
    | h2eLLo3@gmail.com   | 09SNBC6     | 02          | Lotus cheesecake  | gluten free     |Lotus cheesecake     |
    | h2eLLo3@gmail.com   | 09SNBC6     | 03          | Crepe             | sweet           |Donuts               |
    | supg856SS@gmail.com | kfh6j3nur58 | 04          | Donuts            | sweet           |Crepe                |
    | h2eLLo3@gmail.com   | 09SNBC6     | 05          | Vegan carrot cake | Vegetarian diet |Macaron              |
    | supg856SS@gmail.com | kfh6j3nur58 | 06          | Macaron           | zero sugar      |Dark chocolateDonuts |


  Scenario Outline: User updates type
    Given The user logs in as owner or supplier with "<email>" and "<password>"
    When the user selects a valid sweet with ID "<id_of_sweet>" and name "<name_of_sweet>" and type "<type_of_sweet>"
    And the information is valid: name is "<name_of_sweet>", ID is "<id_of_sweet>", and type is "<type_of_sweet>"
    And the user enters the new type to update "<new type>"
    Then the system updates the sweet in the sweet list

  Examples:
    | email               | password    | id_of_sweet | name_of_sweet     | type_of_sweet   |new type   |
    | supg856SS@gmail.com | kfh6j3nur58 | 01          | Cake of chocolate | zero sugar      |zero sugar |
    | h2eLLo3@gmail.com   | 09SNBC6     | 02          | Lotus cheesecake  | gluten free     |sweet      |
    | h2eLLo3@gmail.com   | 09SNBC6     | 03          | Crepe             | sweet           |zero sugar |
    | supg856SS@gmail.com | kfh6j3nur58 | 04          | Donuts            | sweet           |gluten free|
    | h2eLLo3@gmail.com   | 09SNBC6     | 05          | Vegan carrot cake | Vegetarian diet |zero sugar |
    | supg856SS@gmail.com | kfh6j3nur58 | 06          | Macaron           | zero sugar      |sweet      |


  Scenario Outline: User selects an invalid sweet ID or name to edit
    Given The user logs in as not owner or supplier with "<email>" and "<password>"
    When the user tries to update a sweet with an unavailable ID "<id_of_sweet>" or unavailable name "<name_of_sweet>" or type "<type_of_sweet>"
    Then the user should see a "<message>" indicating the sweet does not exist

  Examples:
    | email               | password    | id_of_sweet | name_of_sweet     | type_of_sweet    | message        |
    | supg856SS@gmail.com | kfh6j3nur58 | 07          | Fake Sweet        | fake type        | not available  |
    | h2eLLo3@gmail.com   | 09SNBC6     | 08          | Imaginary Dessert | non-existent     | not available  |