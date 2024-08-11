package project;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import sweetSys.newSweet;
import sweetSys.person;
import sweetSys.sweet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class login {
    private static sweet AppSweet;
    private person loggedInUser;

    @BeforeClass
    public static void setUp() {
        AppSweet = sweet.getInstance();
        AppSweet.getList_of_people().clear();

    }

    @AfterClass
    public static void tearDown() {
        AppSweet.getList_of_people().clear();
        System.out.println("Cleanup done.");
    }
    public login() {
      //  this.AppSweet = sweet.getInstance();
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
        //if(loggedInUser != null) System.out.println("user login succeeds");
        assertTrue("Login failed when it should have succeeded.", loggedInUser != null);


        for (person p: AppSweet.getList_of_people()){
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
            AppSweet.is_loggin = false;
        }


    }

    @Then("the {string} should appear")
    public void theShouldAppear(String message) {
        if ("non-existent email".equals(message)) {
            System.out.println("The user is not in the System :)");
            assertFalse("User login status is true for non-existent email", AppSweet.is_loggin);
        } else if ("invalid password".equals(message)) {
            System.out.println("Invalid password provided.");
            assertFalse("User login status is true for invalid password", AppSweet.is_loggin);
        } else {
            System.out.println("Unexpected message: " + message);
            assertFalse("User login status is true for unexpected message", AppSweet.is_loggin);
        }


    }


}