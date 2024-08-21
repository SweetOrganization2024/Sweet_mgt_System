Feature: discounts

  Scenario: Add a new discount
    Given I am logged in as an Spplier or Owner with "supg856SS@gmail.com" and "kfh6j3nur58"
    When I add a new discount with ID "1" and description "Summer Sale"
    And the discount percentage is "50.0"
    And the discount is valid from "2024-08-01" to "2024-08-31"
    Then the discount should be added successfully
    And I should see the discount in the list of discounts

  Scenario: Apply a discount to a sweet product
    Given a time-based discount with ID "1" is available
    And the sweet product with ID "02" has a price of "50"
    When I apply the discount with ID "1" to the sweet product with ID "02"
    Then the discounted price of the sweet product should be "25.00"