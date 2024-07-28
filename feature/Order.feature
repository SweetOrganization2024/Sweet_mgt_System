Feature: Order

  Scenario: Successful order
    Given that an sweet with ID "01" and with name "Cake of chocolate " is available for order for user with Email "shahd22@gmail.com" and with password "123123"
    When The user is trying to order an sweets with a total price "50"
    Then the system should confirm the order with a success message
    And the sweet status should be updated to "Ordered"

  Scenario: Unavailable sweet order
    Given that an sweet with id "01" and name "Cake of chocolate" is not available for order
    Then the system should display an error message indicating that the sweet is not available for order
