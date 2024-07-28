package project;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweetSys.Userfile;
import sweetSys.person;
import sweetSys.successfull;
import sweetSys.sweet;

import static org.junit.Assert.*;

public class signup {
    private sweet Appsweet;
    private person per;
    Userfile o = new Userfile();
    String email = "";
    String password = "";
    String firstName = "";
    String finalName = "";
    String Confirm_password = "";
    String type = "";

    public signup(sweet Appsweet) {
        this.Appsweet = Appsweet;
    }

    @When("The user enter firstName with {string} and finalName with {string} and email with {string} and password with {string} and Confirm password with {string} and type with {string}")
    public void theUserEnterFirstNameWithAndFinalNameWithAndEmailWithAndPasswordWithAndConfirmPasswordWithAndTypeWith(String fname, String finame, String em, String pass1, String pass2, String typ) {
        firstName = fname;
        finalName = finame;
        password = pass1;
        Confirm_password = pass2;
        email = em;
        type = typ;
        o.readUsers(Userfile.FILE_NAME);
    }

    @Then("creating an account successfully")
    public void creatingAnAccountSuccessfully() {
        successfull s = new successfull(password, email);
        sweet ff = new sweet();
        boolean isRegistered = false;
        for (person f : ff.getList_of_people()) {
            if (s.getEmail().equals(f.getEmail())) {
                isRegistered = true;
                break;
            }
        }
        if (isRegistered) {
            throw new AssertionError("Email is already registered");
        } else {
            boolean isEmailValid = s.isValidEmail(email);
            boolean isPasswordValid = password.equals(Confirm_password) && s.isValidPassword(password);

            if (isEmailValid && isPasswordValid) {
                s.addtoarray(password, email, firstName, finalName, type);
                // Print the list of users after adding the new user
                System.out.println("Current list of people after addition: " + sweet.getList_of_people());
            } else {
                throw new AssertionError("Account creation failed due to invalid email or password");
            }
        }
    }

    @Then("The user should see a confirmation message")
    public void theUserShouldSeeAConfirmationMessage() {
        System.out.println("Successful account creation");
    }

    @When("the user fill the email with {string} and the user fill the password with {string} and the user fill the Confirm password with {string}")
    public void theUserFillTheEmailWithAndTheUserFillThePasswordWithAndTheUserFillTheConfirmPasswordWith(String em, String pass1, String pass2) {
        email = em;
        password = pass1;
        Confirm_password = pass2;
    }

    @Then("a {string} should appear")
    public void aShouldAppear(String message) {
        successfull s = new successfull(password, email);

        switch (message) {
            case "invalid email syntax":
                if (!s.isValidEmail(email)) {
                    System.out.println("Invalid email syntax.");
                } else {
                    throw new AssertionError("Expected invalid email syntax error, but validation passed");
                }
                break;
            case "email is already registered":
                if (isEmailRegistered(email)) {
                    System.out.println("Email is already registered.");
                }
                break;
            case "weak password":
                if (!s.isValidPassword(password)) {
                    System.out.println("Weak password provided.");
                } else {
                    throw new AssertionError("Expected weak password error, but validation passed");
                }
                break;
            case "password mismatch":
                if (!password.equals(Confirm_password)) {
                    System.out.println("Password mismatch.");
                } else {
                    throw new AssertionError("Expected password mismatch error, but passwords matched");
                }
                break;
            default:
                throw new AssertionError("Unexpected message: " + message);
        }
    }

    private boolean isEmailRegistered(String email) {
        for (person p : Appsweet.getList_of_people()) {
            if (p.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}
