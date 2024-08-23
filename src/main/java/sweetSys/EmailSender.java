package sweetSys;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmailSender {
    private static final String EMAIL_USERNAME = "SweetSystemInstitution@gmail.com";
    private static final String password = System.getenv("password");
    private static final Logger logger = Logger.getLogger(EmailSender.class.getName());

    public EmailSender(){

}
    private static Properties getProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        return props;
    }

    private static Session getSession() {
        return Session.getInstance(getProperties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
            }
        });
    }

    public static void sendEmail(String toEmail, String subject, String body) {
        if (toEmail == null || toEmail.isEmpty()) {
            logger.warning("Email address is not provided.");
            return;
        }
        if (subject == null || subject.isEmpty()) {
            logger.warning("Email subject is not provided.");
            return;
        }
        if (body == null || body.isEmpty()) {
            logger.warning("Email body is not provided.");
            return;
        }

        try {
            Message message = new MimeMessage(getSession());
            message.setFrom(new InternetAddress(EMAIL_USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
            logger.info(String.format("Email sent successfully to %s with subject '%s'", toEmail, subject));
        } catch (MessagingException e) {
            logger.log(Level.SEVERE, String.format("Failed to send email to %s with subject '%s'", toEmail, subject), e);
            throw new RuntimeException(e);
        }
    }
}
