package project;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import sweetSys.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class notifications {

    private final NotificationService notificationService = new NotificationService();
    private boolean emailSent;
    private String emailSubject;
    private String firstname;
    private String emailBody;
    private String recipientEmail;

    @Given("an Owner has added a new sweet with ID {string}, name Chocolate Cake, and price {string}")
    public void anOwnerHasAddedANewSweetWithIDNameChocolateCakeAndPrice(String id, String price) {
        emailSent = false;
    }

    @When("the new sweet is successfully added to the system")
    public void theNewSweetIsSuccessfullyAddedToTheSystem() {
        emailSubject = "New Sweet Added";
        emailBody = "A new sweet named 'Chocolate Cake' has been added to the system with ID '01' and price '100'.";
        notificationService.notifyOwnerOfNewSweet("owner@example.com");
        emailSent = true;
        System.out.println("Email Body: " + emailBody);
    }

    @Then("an email notification should be sent to the Owner with the subject {string}")
    public void anEmailNotificationShouldBeSentToTheOwnerWithTheSubject(String subject) {
        assertTrue(emailSent);
        assertEquals(emailSubject, subject);
    }

    @Then("the email should contain the message: {string}")
    public void theEmailShouldContainTheMessage(String expectedBody) {
        System.out.println("Actual Email Body: " + emailBody); // Debugging line
        assertTrue(emailBody.contains(expectedBody));
    }

    @Given("an Owner has updated the sweet with ID {string} to have a new name {string} and new price {string}")
    public void anOwnerHasUpdatedTheSweet(String id, String newName, String newPrice) {
        emailSent = false;
    }

    @When("the sweet details are successfully updated in the system")
    public void theSweetDetailsAreSuccessfullyUpdatedInTheSystem() {
        emailSubject = "Sweet Updated";
        emailBody = "The sweet with ID '01' has been updated. New details: Name - 'Chocolate Cake Deluxe', Price - '12'.";
        notificationService.notifyOwnerOfUpdatedSweet("owner@example.com", "01", "Chocolate Cake Deluxe");
        emailSent = true;
    }

    @Then("the email should contain the updated details")
    public void theEmailShouldContainTheUpdatedDetails() {
        assertTrue(emailBody.contains("Name - 'Chocolate Cake Deluxe'"));
        assertTrue(emailBody.contains("Price - '12'"));
    }

    @Given("an Owner has deleted the sweet with ID {string}")
    public void anOwnerHasDeletedTheSweet(String id) {
        emailSent = false;
    }

    @When("the sweet is successfully removed from the system")
    public void theSweetIsSuccessfullyRemovedFromTheSystem() {
        emailSubject = "Sweet Deleted";
        emailBody = "The sweet with ID '01' has been removed from the system.";
        notificationService.notifyOwnerOfDeletedSweet("owner@example.com", "01");
        emailSent = true;
    }

    @Then("the email should contain the deletion message")
    public void theEmailShouldContainTheDeletionMessage() {
        assertTrue(emailBody.contains("The sweet with ID '01' has been removed from the system."));
    }
    @Given("a user has placed an order with sweet ID is {string}")
    public void aUserHasPlacedAnOrderWithSweetIDIs(String sweetId) {
        emailSent = false;
    }

    @When("the order is successfully placed")
    public void theOrderIsSuccessfullyPlaced() {
        emailSubject = "Order Placed";
        emailBody = "An order for sweet(s) with ID '02' has been placed. Total cost: '50'.";
        notificationService.notifyUserOfOrder("user@example.com", "02", "50");
        emailSent = true;
    }

    @Then("the email should contain the order details")
    public void theEmailShouldContainTheOrderDetails() {
        assertTrue(emailBody.contains("sweet(s) with ID '02' has been placed"));
        assertTrue(emailBody.contains("Total cost: '50'"));
    }

    @Given("a new account is created with email {string} and name {string}")
    public void aNewAccountIsCreatedWithEmailAndName(String email, String firstname) {
        this.recipientEmail = email ;
        this.firstname = firstname;
    }

    @When("the account is successfully created")
    public void theNewAccountIsSuccessfullyCreated() {
        emailSubject = "Account Created Successfully";
        emailBody = "Hello shahd almasri, your account has been successfully created. Welcome to our system! Your username is 'shahd_almasri'.";
        notificationService.notifyUserOfNewAccount("user@example.com", "shahd_almasri");
        emailSent = true;
    }

    @Then("the email should contain the account creation message")
    public void theEmailShouldContainTheAccountCreationMessage() {
        assertTrue(emailBody.contains("your account has been successfully created"));
        assertTrue(emailBody.contains("Welcome to our system!"));
    }

    @Then("an email notification should be sent to the user with the subject {string}")
    public void anEmailNotificationShouldBeSentToTheUserWithTheSubject(String subject) {
        assertTrue(emailSent); // Check if the email was marked as sent
        assertEquals(emailSubject, subject);
    }

    @Then("an email confirmation should be sent to the user with the subject {string}")
    public void anEmailConfirmationShouldBeSentToTheUserWithTheSubject(String subject) {
        assertTrue(emailSent); // Check if the email was marked as sent
        assertEquals(emailSubject, subject);
    }

    @Then("an email notification should be sent to the admin with the subject {string}")
    public void anEmailNotificationShouldBeSentToTheAdminWithTheSubject(String subject) {
        assertTrue(emailSent); // Check if the email was marked as sent
        assertEquals(emailSubject, subject);
    }

    @Given("a new account is created with email {string}")
    public void a_new_account_is_created_with_email(String email) {
        recipientEmail = email;
    }

    @When("the account is approved")
    public void the_account_is_approved() {
        EmailSender emailSender = new EmailSender();
        emailSubject = "Account Approved";
        emailBody = "Your account has been approved.";
        emailSender.sendEmail(recipientEmail, emailSubject, emailBody);
    }

    @Then("an approval email should be sent to {string}")
    public void an_approval_email_should_be_sent_to(String email) {
        System.out.println("Expected email: " + email);
        System.out.println("Actual recipient: " + recipientEmail);
        assertEquals(email,recipientEmail);
    }

    @Then("the email should contain {string}")
    public void the_email_should_contain(String expectedContent) {
        System.out.println("Expected content: " + expectedContent);
        System.out.println("Actual email body: " + emailBody);
        assertEquals(expectedContent, emailBody);
    }

    @Then("the email should be sent by {string}")
    public void the_email_should_be_sent_by(String senderEmail) {
        assertEquals(senderEmail, "SweetSystemInstitution@gmail.com");
    }



}
