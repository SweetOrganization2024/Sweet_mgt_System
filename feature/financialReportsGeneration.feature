Feature: financialReportsGeneration

  Scenario: View financial report as a Supplier or Owner or ADMIN
    Given I am logged in as a Supplier or Owner or ADMIN with "supg856SS@gmail.com" and "kfh6j3nur58"
    When I request to view the financial report
    Then the financial report should be displayed

  Scenario: Deny access for regular user
    Given I am logged in as a user with "samiasoftware2@gmail.com" and "Samia11223344@@"
    When I request to view the financial report
    Then the financial report shouldn't be displayed