package project;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweetSys.*;
import org.junit.BeforeClass;
import org.junit.AfterClass;

public class signup {
    static Userfile o = new Userfile();
    String email = "";
    String password = "";
    String firstName = "";
    String finalName = "";
    String Confirm_password = "";
    String type = "";

   @BeforeClass
    public static void setUp() {
       sweet.getInstance();

    }

    @AfterClass
    public static void tearDown() {
        sweet.getList_of_people().clear();
        System.out.println("Cleanup done.");
    }

    public signup() {
    }

    @When("The user enter firstName with {string} and finalName with {string} and email with {string} and password with {string} and Confirm password with {string} and type with {string}")
    public void theUserEnterFirstNameWithAndFinalNameWithAndEmailWithAndPasswordWithAndConfirmPasswordWithAndTypeWith(String fname, String finame, String em, String pass1, String pass2, String typ) {
        firstName = fname;
        finalName = finame;
        password = pass1;
        Confirm_password = pass2;
        email = em;
        type = typ;
        Userfile.readUsers(Userfile.FILE_NAME);


    }

    @Then("creating an account successfully")
    public void creatingAnAccountSuccessfully() {
        successfull s = new successfull(password, email);
        new sweet();
        person ss=new person(email,password);

        boolean isRegistered = person.findemail(ss);

        if (isRegistered) {
            throw new AssertionError("Email is already registered");
        }
        else {
            boolean isEmailValid = successfull.isValidEmail(email);
            boolean isPasswordValid = password.equals(Confirm_password) && successfull.isValidPassword(password);

            if (isEmailValid && isPasswordValid) {
                s.addtoarray(password,email,type,firstName,finalName);
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
        new successfull(password, email);

        switch (message) {
            case "invalid email syntax" -> {
                if (!successfull.isValidEmail(email)) {
                    System.out.println("Invalid email syntax.");
                } else {
                    throw new AssertionError("Expected invalid email syntax error, but validation passed");
                }
            }
            case "email is already registered" -> {
                if (isEmailRegistered(email)) {
                    System.out.println("Email is already registered.");
                } else {
                    throw new AssertionError("Expected email is already registered error, but it was not");
                }
            }
            case "weak password" -> {
                if (!successfull.isValidPassword(password)) {
                    System.out.println("Weak password provided.");
                } else {
                    throw new AssertionError("Expected weak password error, but validation passed");
                }
            }
            case "password mismatch" -> {
                if (!password.equals(Confirm_password)) {
                    System.out.println("Password mismatch.");
                } else {
                    throw new AssertionError("Expected password mismatch error, but passwords matched");
                }
            }
            default -> throw new AssertionError("Unexpected message: " + message);
        }
        for (person pp: sweet.getList_of_people()){
            System.out.println(pp.getEmail() + " " + pp.getType());
        }
        System.out.println(sweet.getList_of_people().size());
    }

    private boolean isEmailRegistered(String email) {
        return Userfile.emailIsRegisted(email);
    }

}
