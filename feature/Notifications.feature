Feature: notifications

  Scenario: Admin Approval for Business Account Creation
    Given the admin is on the notification page in the admin account with email "samia10@gmail.com"
    When a user submits a request to create a business account from the email "asmarsamia2003@gmail.com" and write the "message" of his service
    And the admin receives a notification regarding the request for a business account
    And the admin approves the request
    Then a confirmation message "Your request has been approved "sent to the email address "asmarsamia2003@gmail.com"

  Scenario: Admin Rejection for Business Account Creation
    Given the admin is on the notification page in the admin account with email "samia10@gmail.com"
    When a user submits a request to create a business account from the email "asmarsamia2003@gmail.com"  and write the "message" of his service
    And the admin receives a notification regarding the request for a business account
    And the admin rejects the request
    Then a rejection message "Your request has been rejected" is sent to the email address "asmarsamia2003@gmail.com"


    Scenario: User Approval for Business Account Creation
      Given the user is on the notification page in the user account with email "shahd22@gmail.com"
      When a user submits a request to create a business account from the email "shahd.227.almasri@gmail.com" and write the "message" of his service
      And the user receives a notification regarding the request for a business account
      And the user approves the request
      Then a confirmation message "Your request has been approved "sent to the email address "shahd.227.almasri@gmail.com"

    Scenario: User Rejection for Business Account Creation
      Given the user is on the notification page in the user account with email "shahd22@gmail.com"
      When a user submits a request to create a business account from the email "shahd.227.almasri@gmail.com"  and write the "message" of his service
      And the user receives a notification regarding the request for a business account
      And the user rejects the request
      Then a rejection message "Your request has been rejected" is sent to the email address "shahd.227.almasri@gmail.com"


     Scenario: Supplier Approval for Business Account Creation
       Given the supplier is on the notification page in the supplier account with email "ahmad6@gmail.com"
       When a supplier submits a request to create a business account from the email "ahmad6@gmail.com" and write the "message" of his service
       And the supplier receives a notification regarding the request for a business account
       And the supplier approves the request
       Then a confirmation message "Your request has been approved "sent to the email address "ahmad6@gmail.com"

     Scenario: Supplier Rejection for Business Account Creation
       Given the supplier is on the notification page in the supplier account with email "ahmad6@gmail.com"
       When a supplier submits a request to create a business account from the email "ahmad6@gmail.com" and write the "message" of his service
       And the supplier receives a notification regarding the request for a business account
       And the supplier rejects the request
       Then a rejection message "Your request has been rejected" is sent to the email address "ahmad6@gmail.com"


     Scenario: Owner Approval for Business Account Creation
       Given the owner is on the notification page in the owner account with email "eman22@gmail.com"
       When a owner submits a request to create a business account from the email "abdalazizeman9224@gmail.com" and write the "message" of his service
       And the owner receives a notification regarding the request for a business account
       And the owner approves the request
       Then a confirmation message "Your request has been approved "sent to the email address "abdalazizeman9224@gmail.com"

     Scenario: Owner Rejection for Business Account Creation
       Given the owner is on the notification page in the owner account with email "eman22@gmail.com"
       When a owner submits a request to create a business account from the email "abdalazizeman9224@gmail.com" and write the "message" of his service
       And the owner receives a notification regarding the request for a business account
       And the owner rejects the request
       Then a rejection message "Your request has been rejected" is sent to the email address "abdalazizeman9224@gmail.com"
