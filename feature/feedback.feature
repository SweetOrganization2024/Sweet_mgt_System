Feature: feedback

Scenario: Submit feedback
Given I am logged in with "samiasoftware2@gmail.com" and "Samia11223344@@"
When I submit feedback for the sweet with ID "SWEET123" with the text "Great taste!" and a rating of "5"
Then the feedback should be saved successfully

Scenario: Read feedback as an Admin
Given I am logged in as an Admin with "Adrdmain78@gmail.com" and "CHF68DNH"
When I request to read the feedback
Then I should see all feedback submitted by users