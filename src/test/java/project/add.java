package project;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import sweetSys.newSweet;
import sweetSys.person;
import sweetSys.sweet;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class add {
    private sweet AppSweet;
    private String email;
    private String password;
    private String id_of_sweet;
    private String name_of_sweet;
    private String type_of_sweet;
    private boolean isUserLoggedIn;
    boolean isDuplicate = false;

    public add(sweet appSweet) {
        AppSweet = appSweet;
    }

    @Given("The user login as Owner or Supplier with {string} and {string}")
    public void theUserLoginAsOwnerOrSupplierWithAnd(String email, String password) {
        this.email = email;
        this.password = password;
        boolean isRegistered = false;

        for (person f : AppSweet.getList_of_people()) {
            if (f.getEmail().equals(email) && f.getPass().equals(password) &&
                    (f.getType().equals("Owner") || f.getType().equals("Supplier"))) {
                isRegistered = true;
                break;
            }
        }

        isUserLoggedIn = isRegistered;
        assertTrue("User should be logged in as Owner or Supplier", isRegistered);
    }

    @When("The user add a new sweet")
    public void theUserAddANewSweet() {
        if (isUserLoggedIn) {
            // Logic to add new sweet
        } else {
            throw new AssertionError("User is not logged in");
        }
    }

    @Then("The user fill in id of sweet with {string} and The User fill in name of sweet with {string} and The User fill in type of sweet with {string}")
    public void theUserFillInIdOfSweetWithAndTheUserFillInNameOfSweetWithAndTheUserFillInTypeOfSweetWith(String id, String name, String type) {
        this.id_of_sweet = id;
        this.name_of_sweet = name;
        this.type_of_sweet = type;
    }

    @Then("the new sweet must be added to the sweet list")
    public void theNewSweetMustBeAddedToTheSweetList() {
        newSweet sweet = new newSweet(id_of_sweet, name_of_sweet, type_of_sweet);
        boolean isAdded = false;
        for (newSweet s : newSweet.getListOfSweet()) {
            if (s.getId_of_sweet().equals(id_of_sweet)) {
                isAdded = true;
                break;
            }
        }
        if (!isAdded) {
            newSweet.addsweet(sweet);
        }
        assertFalse(isAdded);
        System.out.println("Current list of sweets after addition: " + newSweet.getListOfSweet());
    }

    @Then("The user should see a {string}")
    public void theUserShouldSeeA(String message) {
        System.out.println(message);
    }

    //2rd
    @Given("The user login as admin or user with {string} and {string}")
    public void theUserLoginAsAdminOrUserWithAnd(String email, String password) {
        this.email = email;
        this.password = password;
        boolean isRegistered = false;
        for (person f : AppSweet.getList_of_people()) {
            if (f.getEmail().equals(email) && f.getPass().equals(password) &&
                    (f.getType().equals("ADMIN") || f.getType().equals("USER"))) {
                isRegistered = true;
                break;
            }
        }
        isUserLoggedIn = isRegistered;
        assertTrue(isUserLoggedIn);
    }

    @Then("the new sweet mustn't be added to the sweet list")
    public void theNewSweetMustnTBeAddedToTheSweetList() {
        boolean isAdded = false;
        for (newSweet s : newSweet.getListOfSweet()) {
            if (s.getId_of_sweet().equals(id_of_sweet)) {
                isAdded = true;
                break;
            }
        }
        assertFalse(isAdded);
    }
    //3nd
    @When("The user add an existing id of sweet with the id {string} and the name {string} and the type {string}")
    public void theUserAddAnExistingIdOfSweetWithTheIdAndTheNameAndTheType(String id, String name, String type) {
        this.id_of_sweet = id;
        this.name_of_sweet = name;
        this.type_of_sweet = type;
        newSweet sweet = new newSweet(id_of_sweet, name_of_sweet, type_of_sweet);
        for (newSweet s : newSweet.getListOfSweet()) {
            if (s.getId_of_sweet().equals(id_of_sweet)) {
                isDuplicate = true;
                break;
            }
        }
        assertTrue(isDuplicate);
    }

    @Then("the new sweet must not be added to the sweet list")
    public void theNewSweetMustNotBeAddedToTheSweetList() {
        assertTrue(isDuplicate);
    }

    @Then("The user should see a message that adding the sweet failed")
    public void theUserShouldSeeAMessageThatAddingTheSweetFailed() {
        System.out.println("Adding the sweet failed");
    }


}