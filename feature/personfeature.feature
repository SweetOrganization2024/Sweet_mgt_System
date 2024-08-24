Feature: personfeature

  Scenario: Creating a person with all details
    Given a person with email "Shahd374@gmail.com", password "pSH57**sdlk65", type "Supplier", first name "Sami", and last name "zaidan"
    When the person's email should be in right pattern
    And the person's password should be in right pattern
    And the person is new
    Then add the person

  Scenario: Find a person with all details
    Given a person with email "incoremail.com" and password "4746f2781"
    Then the person's type should be "USER"
    And the person's first name should be "shahd"
    And the person's last name should be "almasri"

  Scenario: Checking if a person is of the right type
    Given a person with email "incoremail.com", password "4746f2781", and type "USER"
    Then the person should have the type "USER"
