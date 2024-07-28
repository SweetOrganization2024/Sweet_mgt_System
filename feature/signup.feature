Feature: Sign Up

  Scenario: Sign up with valid data
    When The user enter firstName with 'shahd' and finalName with 'almasri' and email with 'shahd@example.com' and password with 'Shahd1233@7' and Confirm password with 'Shahd1233@7' and type with 'USER'
    Then  creating an account successfully
    And The user should see a confirmation message

  Scenario Outline: Sign up with different validation scenarios
    When the user fill the email with "<Email>" and the user fill the password with "<Password>" and the user fill the Confirm password with "<ConfirmPassword>"
    Then a "<Message>" should appear

    Examples:
      | Email                 | Password     | ConfirmPassword | Message                               |
      | incoremail.com        | Password123! | Password123!    | invalid email syntax                  |
      | hello@gmail.com       | Password123! | Password123!    | email is already registered           |
      | valid@email.com       | PssWord123   | pssword123      | weak password                         |
      | valid@email.com       | Password123! | Password456!    | password mismatch                     |