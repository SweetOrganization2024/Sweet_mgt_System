package project;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet_sys.*;
import org.junit.BeforeClass;
import org.junit.AfterClass;

public class signup {
    String email = "";
    String password = "";
    String firstName = "";
    String finalName = "";
    String confirmPassword = "";
    String type = "";

    @BeforeClass
    public static void setUp() {
        Sweet.getInstance();
    }

    @AfterClass
    public static void tearDown() {
        Sweet.getPeopleList().clear();
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

        Successfull s = new Successfull(password, email);
        boolean isEmailValid = Successfull.isValidEmail(email);
        boolean isPasswordValid = password.equals(confirmPassword) && Successfull.isValidPassword(password);

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
        if (email.isEmpty() && password.isEmpty() && confirmPassword.isEmpty()) {
            if (message.equals("email and password are required")) {
                System.out.println("Email and password are required.");
            } else {
                throw new AssertionError("Expected email and password are required error, but it was not");
            }
        } else if (email.isEmpty()) {
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

            switch (message) {
                case "invalid email syntax":
                    if (!Successfull.isValidEmail(email)) {
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
                    if (!Successfull.isValidPassword(password)) {
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
                case "successful signup":
                    // Add logic to verify successful signup, e.g., check if user is added
                    if (isEmailRegistered(email)) {
                        throw new AssertionError("Signup failed, email is already registered.");
                    }
                    System.out.println("Signup was successful.");
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
