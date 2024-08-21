Feature: chatMessaging

  Scenario: Sending a message
    Given I am logged in as "samiaaa"
    And I am connected to "Hala"
    When I send a message "Hello Hala!" to "Hala"
    Then the message should be delivered to "Hala" reliably
    And "Hala" should see the message "Hello Hala!" in her chat window
    And I should receive a delivery confirmation for the message

  Scenario: Receiving a message
    Given I am logged in as "Hala"
    And I am connected to "samiaaa"
    When "samiaaa" sends me a message "Hello Hala!"
    Then I should receive the message "Hello Hala!" reliably
    And I should see the message "Hello Hala!" in my chat window

  Scenario: Viewing chat history
    Given I have previously sent messages to "Hala"
    When I view my chat history
    Then I should see all messages sent to "Hala" in the correct order
    And I should be able to search through the chat history
    And I should have the option to delete individual messages or the entire chat history
    And the chat history should be subject to any storage limits

  Scenario: Handling errors
    Given I encounter an issue while sending or receiving a message
    Then I should see an appropriate error message explaining the problem
    And I should be informed about any steps to resolve the issue

  Scenario: Managing offline messages
    Given I am offline
    When "samiaaa" sends me a message
    Then the message should be stored and delivered to me once I am back online
    And I should see the message in my chat window as soon as I reconnect