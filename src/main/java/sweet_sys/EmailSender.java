package sweet_sys;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmailSender {
    private static final String EMAIL_USERNAME = "SweetSystemInstitution@gmail.com";
    private static final String EMAIL_PASSWORD = System.getenv("password");
    private static final Logger logger = Logger.getLogger(EmailSender.class.getName());

    public EmailSender() {
        // This constructor is intentionally left empty. It is provided for future
        // extensibility or if initialization logic is needed later. Currently, there
        // are no instance-specific fields to initialize.
    }
    public static Properties getProperties() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        return props;
    }

    public static Session getSession() {
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
            throw new IllegalArgumentException("Email address must be provided.");
        }
        if (subject == null || subject.isEmpty()) {
            logger.warning("Email subject is not provided.");
            throw new IllegalArgumentException("Email subject must be provided.");
        }
        if (body == null || body.isEmpty()) {
            logger.warning("Email body is not provided.");
            throw new IllegalArgumentException("Email body must be provided.");
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
        logger.log(Level.SEVERE, String.format("Failed to send email to %s with subject '%s'.", toEmail, subject), e);
        throw new RuntimeException(String.format("An error occurred while sending email to %s with subject '%s'.", toEmail, subject), e);
    }
    }


}
