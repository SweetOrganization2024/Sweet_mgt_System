package sweetSys;

public class NotificationService {

    public void notifyOwnerOfNewSweet(String ownerEmail) {
        String subject = "New Sweet Added";
        String body = "The Added is done ";
        EmailSender.sendEmail(ownerEmail, subject, body);
    }

    public void notifyOwnerOfUpdatedSweet(String ownerEmail, String sweetId, String newSweetName) {
        String subject = "Sweet Updated";
        String body = String.format("The sweet with ID '%s' has been updated. New details: Name - '%s' ", sweetId, newSweetName);
        EmailSender.sendEmail(ownerEmail, subject, body);
    }

    public void notifyOwnerOfDeletedSweet(String ownerEmail, String sweetId) {
        String subject = "Sweet Deleted";
        String body = String.format("The sweet with ID '%s' has been removed from the system.", sweetId);
        EmailSender.sendEmail(ownerEmail, subject, body);
    }

    public void notifyUserOfOrder(String userEmail, String sweetId, String totalCost) {
        String subject = "Order Placed";
        String body = String.format("An order for sweet(s) with ID '%s' has been placed. Total cost: '%s'.", sweetId, totalCost);
        EmailSender.sendEmail(userEmail, subject, body);
    }
    public void notifyUserOfNewAccount(String email, String username) {
        String emailBody = "Hello " + username + ", your account has been successfully created. Welcome to our system! Your username is '" + username + "'.";
        EmailSender.sendEmail(email, "Account Created Successfully", emailBody);
    }





}
