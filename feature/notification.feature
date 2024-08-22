Feature: Communication and Notification

  Scenario: Notify Owner When a New Sweet is Added
    Given an Owner has added a new sweet with ID '01', name Chocolate Cake, and price '100'
    When the new sweet is successfully added to the system
    Then an email notification should be sent to the Owner with the subject "New Sweet Added"
    Then the email should contain the message: "A new sweet named 'Chocolate Cake' has been added to the system with ID '01' and price '100'."

  Scenario: Notify Owner When a Sweet is Updated
    Given an Owner has updated the sweet with ID '01' to have a new name 'Chocolate Cake Deluxe' and new price '12'
    When the sweet details are successfully updated in the system
    Then an email notification should be sent to the Owner with the subject "Sweet Updated"
    And the email should contain the message: "The sweet with ID '01' has been updated. New details: Name - 'Chocolate Cake Deluxe', Price - '12'."

  Scenario: Notify Owner When a Sweet is Deleted
    Given an Owner has deleted the sweet with ID '01'
    When the sweet is successfully removed from the system
    Then an email notification should be sent to the Owner with the subject "Sweet Deleted"
    And the email should contain the message: "The sweet with ID '01' has been removed from the system."

  Scenario: Notify User When an Order is Placed
    Given a user has placed an order with sweet ID is '02'
    When the order is successfully placed
    Then an email notification should be sent to the user with the subject "Order Placed"
    And the email should contain the message: "An order for sweet(s) with ID '02' has been placed. Total cost: '50'."

  Scenario: Sending an approval email when a new account is created
    Given a new account is created with email "newuser@example.com" and name "Samia"
    When the account is approved
    Then an approval email should be sent to "newuser@example.com"
    And the email should contain "Your account has been approved."