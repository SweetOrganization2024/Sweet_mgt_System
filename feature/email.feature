Feature: Send Email Notification

  Scenario: Successfully send an email
    Given a valid email address "test@example.com"
    And a valid email subject "Test Subject"
    And a valid email body "This is a test email."
    When the email is sent
    Then the email should be sent successfully

  Scenario: Fail to send an email due to missing email address
    Given an empty email address
    And a valid email subject "Test Subject"
    And a valid email body "This is a test email."
    When the email is sent
    Then an error should be thrown with message "Email address must be provided."

  Scenario: Fail to send an email due to missing subject
    Given a valid email address "test@example.com"
    And an empty email subject
    And a valid email body "This is a test email."
    When the email is sent
    Then an error should be thrown with message "Email subject must be provided."

  Scenario: Fail to send an email due to missing body
    Given a valid email address "test@example.com"
    And a valid email subject "Test Subject"
    And an empty email body
    When the email is sent
    Then an error should be thrown with message "Email body must be provided."

  Scenario: Log error on failure
    Given a valid email address "test@example.com"
    And a valid email subject "Test Subject"
    And a valid email body "This is a test email."
    And the email sending fails due to a messaging exception
    When the email is sent
    Then an error should be logged with message "Failed to send email to test@example.com with subject 'Test Subject'."
