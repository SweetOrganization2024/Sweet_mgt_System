package project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import sweetSys.EmailSender;

public class SendEmailSteps {

    private String toEmail;
    private String subject;
    private String body;
    private Exception exception;

    @Given("a valid email address {string}")
    public void aValidEmailAddress(String email) {
        this.toEmail = email;
    }

    @Given("a valid email subject {string}")
    public void aValidEmailSubject(String subject) {
        this.subject = subject;
    }

    @Given("a valid email body {string}")
    public void aValidEmailBody(String body) {
        this.body = body;
    }

    @Given("an empty email address")
    public void anEmptyEmailAddress() {
        this.toEmail = "";
    }

    @Given("an empty email subject")
    public void anEmptyEmailSubject() {
        this.subject = "";
    }

    @Given("an empty email body")
    public void anEmptyEmailBody() {
        this.body = "";
    }

    @When("the email is sent")
    public void theEmailIsSent() {
        try {
            EmailSender.sendEmail(toEmail, subject, body);
        } catch (Exception e) {
            this.exception = e;
        }
    }

    @Then("the email should be sent successfully")
    public void theEmailShouldBeSentSuccessfully() {
        assertEquals(null, exception);
    }

    @Then("an error should be thrown with message {string}")
    public void anErrorShouldBeThrownWithMessage(String errorMessage) {
        assertEquals(errorMessage, exception.getMessage());
    }
}
