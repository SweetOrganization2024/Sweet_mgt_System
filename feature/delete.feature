Feature: delete

  Scenario Outline: delete a sweet with valid information
    Given The user login as owner or supplier with "<email>" and "<password>"
    When The information is valid name is "<name_of_sweet>" and id is "<id_of_sweet>" and type is "<type_of_sweet>" and price is "<price_of_sweet>"
    Then The specified sweet must be deleted from sweet list
    And The User should see the sweet successfully deleted

    Examples:
          | email                | password      | id_of_sweet | name_of_sweet      | type_of_sweet    | price_of_sweet|
          | supg856SS@gmail.com  |kfh6j3nur58    | 01          | Cake of chocolate  | zero sugar       |50             |
          | h2eLLo3@gmail.com    | 09SNBC6       | 03          | Crepe              | sweet            |40             |
          | supg856SS@gmail.com  |kfh6j3nur58    | 04          | Donuts             | sweet            |30             |






  Scenario Outline: Trying to delete a sweet that does not exist
    Given The user login as not owner or supplier with "<email>" and "<password>"
    When the user deletes a sweet with not available id is "<id_of_sweet>" or not available name is "<name_of_sweet>" or type is "<type_of_sweet>"
    Then The user should see a message that this sweet does not exist

    Examples:
      | email               | password    | id_of_sweet | name_of_sweet     | type_of_sweet  |
      | supg856SS@gmail.com | kfh6j3nur58 | 07          | Fake Sweet        | fake type      |
      | h2eLLo3@gmail.com   | 09SNBC6     | 08          | Imaginary Dessert | non-existent   |
      | ShAd5eyd@gmail.com  | 4746f2781   | 01          | Cake of chocolate | zero sugar     |