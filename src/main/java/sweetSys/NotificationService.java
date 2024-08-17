package sweetSys;

public class NotificationService {

    private final EmailSender emailSender = new EmailSender();

    public void notifyOwnerOfNewSweet(String ownerEmail, String sweetName, String sweetId, String sweetPrice) {
        String subject = "New Sweet Added";
        String body = String.format("A new sweet named '%s' has been added to the system with ID '%s' and price '%s'.", sweetName, sweetId, sweetPrice);
        emailSender.sendEmail(ownerEmail, subject, body);
    }

    public void notifyOwnerOfUpdatedSweet(String ownerEmail, String sweetId, String newSweetName) {
        String subject = "Sweet Updated";
        String body = String.format("The sweet with ID '%s' has been updated. New details: Name - '%s' ", sweetId, newSweetName);
        emailSender.sendEmail(ownerEmail, subject, body);
    }

    public void notifyOwnerOfDeletedSweet(String ownerEmail, String sweetId) {
        String subject = "Sweet Deleted";
        String body = String.format("The sweet with ID '%s' has been removed from the system.", sweetId);
        emailSender.sendEmail(ownerEmail, subject, body);
    }

    public void notifyUserOfOrder(String userEmail, String sweetId, String totalCost) {
        String subject = "Order Placed";
        String body = String.format("An order for sweet(s) with ID '%s' has been placed. Total cost: '%s'.", sweetId, totalCost);
        emailSender.sendEmail(userEmail, subject, body);
    }
    public void notifyUserOfNewAccount(String email, String username) {
        String emailBody = "Hello " + username + ", your account has been successfully created. Welcome to our system! Your username is '" + username + "'.";
        emailSender.sendEmail(email, "Account Created Successfully", emailBody);
    }


    public void notifyAdminOfNewAccount(String adminEmail, String newAccountName, String newAccountEmail) {
        String subject = "New Account Notification";
        String body = String.format("Dear Admin, a new account has been created with the following details: Name - %s, Email - %s. Please review and approve if necessary.", newAccountName, newAccountEmail);
        emailSender.sendEmail(adminEmail, subject, body);
    }


}
