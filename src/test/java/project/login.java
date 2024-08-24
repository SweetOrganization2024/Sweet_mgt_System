package project;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import sweet_sys.Person;
import sweet_sys.Sweet;

import static org.junit.Assert.*;

public class login {
    private Person loggedInUser;

    @BeforeClass
    public static void setUp() {
        Sweet.getPeopleList().clear();

    }

    @AfterClass
    public static void tearDown() {
        Sweet.getPeopleList().clear();
        System.out.println("Cleanup done.");
    }


    @When("user tries to login through the system")
    public void userTriesToLoginThroughTheSystem() {
     ////
    }


    @When("user enters the email {string} and user enters the password {string}")
    public void userEntersTheEmailAndUserEntersThePassword(String email, String password) {
        this.loggedInUser = Person.retperson(new Person(email, password));

    }

    @Then("user login succeeds")
    public void userLoginSucceeds() {
        assertNotNull("Login failed when it should have succeeded.", loggedInUser);


        for (Person p: Sweet.getPeopleList()){
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
        boolean found=false;
        if(Sweet.retperson( email, password) !=null){
            found=true;
        }
        else {
            Sweet.isLoggin = false;
        }

    }

    @Then("the {string} should appear")
    public void theShouldAppear(String message) {
        if ("non-existent email".equals(message)) {
            System.out.println("The user is not in the System :)");
            assertFalse("User login status is true for non-existent email", Sweet.isLoggin);
        } else if ("invalid password".equals(message)) {
            System.out.println("Invalid password provided.");
            assertFalse("User login status is true for invalid password", Sweet.isLoggin);
        } else {
            System.out.println("Unexpected message: " + message);
            assertFalse("User login status is true for unexpected message", Sweet.isLoggin);
        }


    }


}
