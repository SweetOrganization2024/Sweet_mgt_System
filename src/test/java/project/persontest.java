package project;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

import sweet_sys.*;

public class persontest {
    private Person p;
    private String email1;
    private String password1;
    private String type1;
    private String lastName1;
    private String firstName1;

    @Given("a person with email {string}, password {string}, type {string}, first name {string}, and last name {string}")
    public void createFullPerson(String email, String password, String type, String firstName, String lastName) {
        email1 = email;
        password1 = password;
        type1 = type;
        firstName1 = firstName;
        lastName1 = lastName;
        p =new Person("","","","","");

    }

    @When("the person's email should be in right pattern")
    public void thePersonSEmailShouldBeInRightPattern() {
        assertTrue(Successfull.isValidEmail(email1));
        p.setEmail(email1);
    }
    @When("the person's password should be in right pattern")
    public void thePersonSPasswordShouldBeInRightPattern() {
        assertTrue(Successfull.isValidPassword(password1));
        p.setPass(password1);
    }
    @When("the person is new")
    public void thePersonIsNew() {
        assertNull(Sweet.retperson(email1,password1));
    }
    @Then("add the person")
    public void addThePerson() {
        p.setType(type1);
        p.setFirstName(firstName1);
        p.setLastName(lastName1);
        Sweet.setToList(p);
    }

    @Given("a person with email {string} and password {string}")
    public void aPersonWithEmailAndPassword(String email, String pas) {
        p = Sweet.retperson(email,pas);
        assertTrue(Person.findemail(p));
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
        p = Sweet.retperson(email,password);
    }

    @Then("the person should have the type {string}")
    public void thePersonShouldHaveTheType(String expectedType) {
        assertEquals(expectedType,p.getType());
    }

}
