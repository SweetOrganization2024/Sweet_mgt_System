package project;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweetSys.*;
import org.junit.BeforeClass;
import org.junit.AfterClass;

public class signup {
    static Userfile userfile = new Userfile();
    String email = "";
    String password = "";
    String firstName = "";
    String finalName = "";
    String confirmPassword = "";
    String type = "";

    @BeforeClass
    public static void setUp() {
        sweet.getInstance();
    }

    @AfterClass
    public static void tearDown() {
        sweet.getPeopleList().clear();
        System.out.println("Cleanup done.");
    }

    @When("The user enter firstName with {string} and finalName with {string} and email with {string} and password with {string} and Confirm password with {string} and type with {string}")
    public void theUserEntersDetails(String fname, String finame, String em, String pass1, String pass2, String typ) {
        firstName = fname;
        finalName = finame;
        email = em;
        password = pass1;
        confirmPassword = pass2;
        type = typ;

        Userfile.readUsers(Userfile.FILE_NAME);
    }

    @Then("creating an account successfully")
    public void creatingAnAccountSuccessfully() {
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            throw new AssertionError("All fields are required");
        }

        successfull s = new successfull(password, email);
        boolean isEmailValid = successfull.isValidEmail(email);
        boolean isPasswordValid = password.equals(confirmPassword) && successfull.isValidPassword(password);

        if (isEmailValid && isPasswordValid) {
            if (isEmailRegistered(email)) {
                throw new AssertionError("Email is already registered");
            } else {
                s.addtoarray(password, email, type, firstName, finalName);
            }
        } else {
            throw new AssertionError("Account creation failed due to invalid email or password");
        }
    }

    @Then("The user should see a confirmation message")
    public void theUserShouldSeeAConfirmationMessage() {
        System.out.println("Successful account creation");
    }

    @When("the user fill the email with {string} and the user fill the password with {string} and the user fill the Confirm password with {string}")
    public void theUserFillsDetails(String em, String pass1, String pass2) {
        email = em;
        password = pass1;
        confirmPassword = pass2;
    }

    @Then("a {string} should appear")
    public void aMessageShouldAppear(String message) {
        if (email.isEmpty()) {
            if (message.equals("email is required")) {
                System.out.println("Email is required.");
            } else {
                throw new AssertionError("Expected email is required error, but it was not");
            }
        } else if (password.isEmpty() && confirmPassword.isEmpty()) {
            if (message.equals("password and confirm password are required")) {
                System.out.println("Password and confirm password are required.");
            } else {
                throw new AssertionError("Expected password and confirm password are required error, but it was not");
            }
        } else if (password.isEmpty()) {
            if (message.equals("password is required")) {
                System.out.println("Password is required.");
            } else {
                throw new AssertionError("Expected password is required error, but it was not");
            }
        } else if (confirmPassword.isEmpty()) {
            if (message.equals("confirm password is required")) {
                System.out.println("Confirm password is required.");
            } else {
                throw new AssertionError("Expected confirm password is required error, but it was not");
            }
        } else {
            successfull s = new successfull(password, email);

            switch (message) {
                case "invalid email syntax":
                    if (!successfull.isValidEmail(email)) {
                        System.out.println("Invalid email syntax.");
                    } else {
                        throw new AssertionError("Expected invalid email syntax error, but validation passed");
                    }
                    break;
                case "email is already registered":
                    if (isEmailRegistered(email)) {
                        System.out.println("Email is already registered.");
                    } else {
                        throw new AssertionError("Expected email is already registered error, but it was not");
                    }
                    break;
                case "weak password":
                    if (!successfull.isValidPassword(password)) {
                        System.out.println("Weak password provided.");
                    } else {
                        throw new AssertionError("Expected weak password error, but validation passed");
                    }
                    break;
                case "password mismatch":
                    if (!password.equals(confirmPassword)) {
                        System.out.println("Password mismatch.");
                    } else {
                        throw new AssertionError("Expected password mismatch error, but passwords matched");
                    }
                    break;
                default:
                    throw new AssertionError("Unexpected message: " + message);
            }
        }
    }

    private boolean isEmailRegistered(String email) {
        return Userfile.emailIsRegisted(email);
    }
}
