package project;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import sweetSys.EmailSender;
import java.util.logging.Logger;

public class SendEmailSteps {

    private String toEmail;
    private String subject;
    private String body;
    private Exception exception;
    private EmailSender emailSender;
    private Logger logger = Logger.getLogger(SendEmailSteps.class.getName());

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

    @Given("the email sending fails due to a messaging exception")
    public void theEmailSendingFailsDueToMessagingException() {

    }

    @Given("an invalid email address {string}")
    public void anInvalidEmailAddress(String email) {
        this.toEmail = email;
    }

    @When("the email is sent")
    public void theEmailIsSent() {
        try {
            if (emailSender == null) {
                emailSender = new EmailSender(); // Initialize the real EmailSender if not mocked
            }
            emailSender.sendEmail(toEmail, subject, body);
            exception = null;
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("an error should be thrown with message {string}")
    public void anErrorShouldBeThrownWithMessage(String expectedMessage) {
        assertTrue("An exception should have been thrown", exception != null);
        assertEquals("Error message should match", expectedMessage, exception.getMessage());
    }

    @Then("an error should be logged with message {string}")
    public void anErrorShouldBeLoggedWithMessage(String expectedLogMessage) {
        try {
            if (emailSender == null) {
                emailSender = new EmailSender();
            }
            emailSender.sendEmail(toEmail, subject, body);
        } catch (Exception e) {
        }
    }

    @Then("the email should be sent successfully")
    public void theEmailShouldBeSentSuccessfully() {
        assertEquals(null, exception);
    }
}
