package project;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

import sweetSys.*;

public class persontest {
    private person p;
    private String email1;
    private String password1;
    private String type1;
    private String lastName1;
    private String firstName1;
    boolean validEmail = false, validPass = false;

    @Given("a person with email {string}, password {string}, type {string}, first name {string}, and last name {string}")
    public void createFullPerson(String email, String password, String type, String firstName, String lastName) {
        email1 = email;
        password1 = password;
        type1 = type;
        firstName1 = firstName;
        lastName1 = lastName;
        p =new person(email1,password1,type1,firstName1,lastName1);

    }

    @When("the person's email should be in right pattern")
    public void thePersonSEmailShouldBeInRightPattern() {
        assertTrue(successfull.isValidEmail(email1));
    }
    @When("the person's password should be in right pattern")
    public void thePersonSPasswordShouldBeInRightPattern() {
        assertTrue(successfull.isValidPassword(password1));
    }
    @When("the person is new")
    public void thePersonIsNew() {
        assertNull(sweet.retperson(email1,password1));
    }
    @Then("add the person")
    public void addThePerson() {
        sweet.setToList(p);
    }

    @Given("a person with email {string} and password {string}")
    public void aPersonWithEmailAndPassword(String email, String pas) {
        p = sweet.retperson(email,pas);
    }

    @Then("the person's type should be {string}")
    public void thePersonTypeShouldBe(String ty) {
        assertEquals(ty,p.getType());
    }

    @Then("the person's first name should be {string}")
    public void thePersonFirstNameShouldBe(String fn) {
        assertEquals(fn, p.getFirstName());
    }

    @Then("the person's last name should be {string}")
    public void thePersonLastNameShouldBe(String ln) {
        assertEquals(ln, p.getLastName());
    }



    @Given("a person with email {string}, password {string}, and type {string}")
    public void aPersonWithEmailPasswordAndType(String email, String password, String type) {
        email1 = email;
        password1 = password;
        type1 = type;
        p = sweet.retperson(email,password);
    }

    @Then("the person should have the type {string}")
    public void thePersonShouldHaveTheType(String expectedType) {
        assertEquals(expectedType,p.getType());
    }

}
