Feature: login
    Scenario Outline: Login with valid credentials
        When user tries to login through the system
        And user enters the email "<email>" and user enters the password "<password>"
        Then user login succeeds
        And user page "<type>" appears

        Examples:
            | email                   | password       | type             |
            | Incoemail23@gmail.com   | Samia11223344@ | USER             |
            | Adrdmain78@gmail.com    | CHF68DNH       | ADMIN            |
            | h2eLLo3@gmail.com       | 09SNBC6        | Owner            |
            | Halasara22@gmail.com    | Hala34567##    | Supplier         |

    Scenario Outline: Login with invalid credentials
        When user tries to login through the system
        And user enters the email "<Email>" and user enters the password "<Password>"
        And the "<Message>" should appear

        Examples:
            | Email                     | Password     | Message             |
            | mahmood3@gmail.com        | 123123       | non-existent email  |
            | mohammad2@gmail.com       | 12356        | invalid password    |
