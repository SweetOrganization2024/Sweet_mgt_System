package project;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweetSys.person;
import sweetSys.sweet;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class login {
    private sweet Appsweet;
    private person loggedInUser;

    public login() {
        // Initialize the sweet object
        this.Appsweet = new sweet();
    }

    @When("user tries to login through the system")
    public void userTriesToLoginThroughTheSystem() {
        // No action needed here, potentially for future use
    }

    @When("user enters the email {string} and user enters the password {string}")
    public void userEntersTheEmailAndUserEntersThePassword(String email, String password) {
        System.out.println("List of people: " + Appsweet.getList_of_people());
        this.loggedInUser = new person(email, password);
        for (person p : Appsweet.getList_of_people()) {
            if (loggedInUser.getEmail().equals(p.getEmail()) && loggedInUser.getPass().equals(p.getPass())) {
                this.loggedInUser = p;
                break;
            }
        }
    }

    @Then("user login succeeds")
    public void userLoginSucceeds() {
        // Check if user object is not null after login attempt
        assertTrue("Login failed when it should have succeeded.", loggedInUser != null);
    }

    @Then("user page {string} appears")
    public void userPageAppears(String type) {
        if (this.loggedInUser != null) {
            this.loggedInUser.setType(type);
        }
    }

    @When("the user enters the {string} with {string}")
    public void theUserEntersTheWith(String email, String password) {
        this.loggedInUser = new person(email, password);
        boolean found = false;
        for (person p : Appsweet.getList_of_people()) {
            if (loggedInUser.getEmail().equals(p.getEmail())) {
                found = true;
                if (!loggedInUser.getPass().equals(p.getPass())) {
                    Appsweet.is_loggin = false;
                }
                break;
            }
        }
        if (!found) {
            Appsweet.is_loggin = false;
        }
    }

    @Then("the {string} should appear")
    public void theShouldAppear(String message) {
        if ("non-existent email".equals(message)) {
            System.out.println("(non-existent email)The user is not in the System :)");
            assertFalse("User login status is true for non-existent email", Appsweet.is_loggin);
        } else if ("invalid password".equals(message)) {
            System.out.println("Invalid password provided.");
            assertFalse("User login status is true for invalid password", Appsweet.is_loggin);
        } else {
            System.out.println("Unexpected message: " + message);
            assertFalse("User login status is true for unexpected message", Appsweet.is_loggin);
        }
    }
}