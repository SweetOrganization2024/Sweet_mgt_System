Feature:order
  Scenario Outline: Successful order
    Given a sweet with ID "<id_of_sweet>", name "<name_of_sweet>", and type "<type_of_sweet>" is available for order for user with Email "<email>" and password "<password>"
    And the user selects a quantity of "<quantity>" for the sweet
    When the user places an order with a total price of "<total_price>"
    Then the system should confirm the order with a success message including order number, estimated delivery date, and total cost

    Examples:
      | id_of_sweet | name_of_sweet         | type_of_sweet      | email               | password | quantity | total_price |
      | 02          | Lotus cheesecake      | gluten free        | incoremail.com      | 4746f2781 | 2        | 100         |
      | 05          | Vegan carrot cake     | Vegetarian diet    | incoremail.com      | 4746f2781 | 3        | 150         |
      | 06          | Macaron                | zero sugar        | incoremail.com      | 4746f2781 | 1        | 50          |

  Scenario Outline: Unavailable sweet order
    Given a sweet with ID "<id_of_sweet>", name "<name_of_sweet>", and type "<type_of_sweet>" is not available for order
    Then the system should display an error message indicating that the sweet with ID "<id_of_sweet>" is out of stock or unavailable

    Examples:
      | id_of_sweet | name_of_sweet       | type_of_sweet      |
      | 09          | Nutella cheesecake  | diet               |
      | 11          | Biscuits            | sweet              |
      | 12          | Chocolate cake      | Vegetarian         |

  Scenario Outline: Order payment
    Given a sweet with ID "<id_of_sweet>", name "<name_of_sweet>", and type "<type_of_sweet>" is available for order for user with Email "<email>" and password "<password>"
    And the user selects a quantity of "<quantity>" for the sweet
    When the user enters payment information with card number "<card_number>" and expiration date "<expiration_date>"
    And the user places an order with a total price of "<total_price>"
    Then the system should confirm successful payment and provide an order confirmation with order number and estimated delivery date

    Examples:
      | id_of_sweet | name_of_sweet         | type_of_sweet      | email               | password      | quantity     | total_price | card_number       | expiration_date |
      | 02          | Lotus cheesecake      | gluten free        | incoremail.com      | 4746f2781     | 2            | 100         | 1234567812345678  | 12/24           |
      | 05          | Vegan carrot cake     | Vegetarian diet    | incoremail.com      | 4746f2781     | 3            | 150         | 8765432187654321  | 01/25           |
      | 06          | Macaron               | zero sugar         | incoremail.com      | 4746f2781     | 1            | 50          | 1122334455667788  | 11/23           |

  Scenario Outline: View order history
    Given a user with Email "<email>" and password "<password>" is logged in
    When the user requests to view their order history
    Then the system should display a list of past orders including order numbers, dates, and total costs

    Examples:
      | email                     | password          |
      | incoremail.com            | 4746f2781         |
      | samiasoftware2@gmail.com  | Samia11223344@@   |

  Scenario: Cancel an existing order
    Given an order with order number "02" exists
    When the user cancels the order
    Then the system should confirm that the order with order number "02" has been cancelled

  Scenario: Cancel an already cancelled order
    Given an order with order number "02" exists
    And the order with order number "02" has been cancelled
    When the user tries to cancel the order
    Then the system should log a warning indicating that the order with order number "02" is already cancelled

