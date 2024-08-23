package project;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import sweetSys.person;
import sweetSys.sweet;

import static org.junit.Assert.*;

public class login {
    private person loggedInUser;

    @BeforeClass
    public static void setUp() {
        sweet.getPeopleList().clear();

    }

    @AfterClass
    public static void tearDown() {
        sweet.getPeopleList().clear();
        System.out.println("Cleanup done.");
    }


    @When("user tries to login through the system")
    public void userTriesToLoginThroughTheSystem() {

    }


    @When("user enters the email {string} and user enters the password {string}")
    public void userEntersTheEmailAndUserEntersThePassword(String email, String password) {
        this.loggedInUser = person.retperson(new person(email, password));

    }

    @Then("user login succeeds")
    public void userLoginSucceeds() {
        assertNotNull("Login failed when it should have succeeded.", loggedInUser);


        for (person p: sweet.getPeopleList()){
            System.out.println("User name: "+ p.getEmail() + " Passweord: " + p.getPass() + " Type:" + p.getType());
        }
    }

    @Then("user page {string} appears")
    public void userPageAppears(String type) {
        if (this.loggedInUser != null) {
            this.loggedInUser.setType(type);
        }
    }

    @When("the user enters the {string} with {string}")
    public void theUserEntersTheWith(String email, String password) {
       boolean found = person.ifpersonsignup(new person(email, password));

        if (!found) {
            sweet.is_loggin = false;
        }


    }

    @Then("the {string} should appear")
    public void theShouldAppear(String message) {
        if ("non-existent email".equals(message)) {
            System.out.println("The user is not in the System :)");
            assertFalse("User login status is true for non-existent email", sweet.is_loggin);
        } else if ("invalid password".equals(message)) {
            System.out.println("Invalid password provided.");
            assertFalse("User login status is true for invalid password", sweet.is_loggin);
        } else {
            System.out.println("Unexpected message: " + message);
            assertFalse("User login status is true for unexpected message", sweet.is_loggin);
        }


    }


}
