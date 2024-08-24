Feature: Sign Up

  Scenario: Sign up with valid data
    When The user enter firstName with 'shahd' and finalName with 'almasri' and email with 'shahd@example.com' and password with 'Shahd1233@7' and Confirm password with 'Shahd1233@7' and type with 'USER'
    Then creating an account successfully
    And The user should see a confirmation message

  Scenario Outline: Sign up with different validation scenarios
    When the user fill the email with "<Email>" and the user fill the password with "<Password>" and the user fill the Confirm password with "<ConfirmPassword>"
    Then a "<Message>" should appear

    Examples:
      | Email                 | Password     | ConfirmPassword | Message                               |
      |                       | Password123! | Password123!    | email is required                     |
      | incoremail11.com      | Password123! | Password123!    | invalid email syntax                  |
      | supg856SS@gmail.com   | kfh6j3nur58  | kfh6j3nur58     | email is already registered           |
      | valid@email.com       | kfh6j3nur58  | kfh6j3nur58     | weak password                         |
      | valid@email.com       | Password123! | Password456!    | password mismatch                     |
      | valid@email.com       |              | Password123!    | password is required                  |
      | valid@email.com       | Password123! |                  | confirm password is required          |
      | valid@email.com       |              |                  | password and confirm password are required |
