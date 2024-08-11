Feature: add

  Scenario Outline: Add valid sweet
    Given The user login as Owner or Supplier with "<email>" and "<password>"
    When The user add a new sweet
    Then The user fill in id of sweet with "<id_of_sweet>" and The User fill in name of sweet with "<name_of_sweet>" and The User fill in type of sweet with "<type_of_sweet>" and The User fill in price of sweet with "<price_of_sweet>"
    Then the new sweet must be added to the sweet list
    And The user should see a "Sweet added successfully"

    Examples:
      | email                | password      | id_of_sweet | name_of_sweet      | type_of_sweet    | price_of_sweet|
      | supg856SS@gmail.com  |kfh6j3nur58    | 01          | Cake of chocolate  | zero sugar       |50             |
      | h2eLLo3@gmail.com    | 09SNBC6       | 02          | Lotus cheesecake   | gluten free      |50             |
      | h2eLLo3@gmail.com    | 09SNBC6       | 03          | Crepe              | sweet            |40             |
      | supg856SS@gmail.com  |kfh6j3nur58    | 04          | Donuts             | sweet            |30             |
      | h2eLLo3@gmail.com    | 09SNBC6       | 05          | Vegan carrot cake  | Vegetarian diet  |25             |
      | supg856SS@gmail.com  |kfh6j3nur58    | 06          | Macaron            | zero sugar       |30             |

  Scenario Outline: Add invalid sweet
    Given The user login as admin or user with "<email>" and "<password>"
    When The user add a new sweet
    Then the new sweet mustn't be added to the sweet list
    And The user should see a "<message>"

    Examples:
      | email                          | password  | message                                          |
      | incoremail.com                 | 4746f2781  | you can't add a new item                         |
      | Adrdmain78@gmail.com           | CHF68DNH   | you must be Owner or Supplier to add a new item  |

  Scenario Outline: Add sweet with id already exist
    Given The user login as Owner or Supplier with "<email>" and "<password>"
    When The user add an existing id of sweet with the id "<id_of_sweet>" and the name "<name_of_sweet>" and the type "<type_of_sweet>" and the type "<price_of_sweet>"
    Then the new sweet must not be added to the sweet list
    And The user should see a message that adding the sweet failed

    Examples:
      | email                | password     | id_of_sweet | name_of_sweet     | type_of_sweet | price_of_sweet   |
      | supg856SS@gmail.com  |kfh6j3nur58   | 01          | Cake of vanila    | zero sugar    | 50                |
      | supg856SS@gmail.com  |kfh6j3nur58   | 01          | Salt cake         | diet          | 44                |
