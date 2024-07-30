Feature: login
Scenario Outline: Login with valid credentials
    When user tries to login through the system
    And user enters the email "<email>" and user enters the password "<password>"
    Then user login succeeds
    And user page "<type>" appears

    Examples:
    | email              | password | type             |
    | shahd22@gmail.com  | 123123   | USER             |
    | samia10@gmail.com  | 123456   | ADMIN            |
    | eman22@gmail.com   | 123789   | Owner            |
    | ahmad6@gmail.com   | 14758    | Supplier         |

    Scenario Outline: Login with invalid credentials
        When user tries to login through the system
        And user enters the email "<Email>" and user enters the password "<Password>"
        And the "<Message>" should appear

        Examples:
          | Email                     | Password     | Message             |
          | mahmood3@gmail.com        | 123123       | non-existent email  |
          | mohammad2@gmail.com       | 12356        | invalid password    |