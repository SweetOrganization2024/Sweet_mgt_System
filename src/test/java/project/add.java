package project;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import sweet_sys.NewSweet;
import sweet_sys.Person;
import sweet_sys.Sweet;

import static org.junit.Assert.assertTrue;

public class add {
    private String idOfSweet;
    private String nameOfSweet;
    private String typeOfSweet;
    private String priceOfSweet;
    private boolean isUserLoggedIn;
    boolean isDuplicate = false;

    @BeforeClass
    public static void setUp() {
        Sweet.getListOfSweet().clear();
        Sweet.getPeopleList().clear();

    }

    @AfterClass
    public static void tearDown() {
        Sweet.getListOfSweet().clear();
        Sweet.getPeopleList().clear();

        System.out.println("Cleanup done.");
    }
    
    @Given("The user login as Owner or Supplier with {string} and {string}")
    public void theUserLoginAsOwnerOrSupplierWithAnd(String email, String password) {

        boolean isRegistered = NewSweet.isOwnerOrSupplier(email,password);
        Person.isRightType(email,password, typeOfSweet);

        isUserLoggedIn = isRegistered;
        assertTrue("User should be logged in as Owner or Supplier", isRegistered);
    }

    @When("The user add a new sweet")
    public void theUserAddANewSweet() {
        if (!isUserLoggedIn) {
            throw new AssertionError("User is not logged in");
        }
    }

    @Then("The user fill in id of sweet with {string} and The User fill in name of sweet with {string} and The User fill in type of sweet with {string} and The User fill in price of sweet with {string}")
    public void theUserFillInIdOfSweetWithAndTheUserFillInNameOfSweetWithAndTheUserFillInTypeOfSweetWith(String id, String name, String type, String price) {
        this.idOfSweet = id;
        this.nameOfSweet = name;
        this.typeOfSweet = type;
        this.priceOfSweet =price;
    }

    @Then("the new sweet must be added to the sweet list")
    public void theNewSweetMustBeAddedToTheSweetList() {
        NewSweet mySweet = new NewSweet(idOfSweet, nameOfSweet, typeOfSweet, priceOfSweet);
        boolean isAdded = NewSweet.isAdd(idOfSweet);

        if (!isAdded) {
            Sweet.listOfSweet.add(mySweet);
            System.out.println("Sweet added: " + mySweet.getName());
        }
        for (NewSweet s : Sweet.getListOfSweet()) {
            System.out.println(NewSweet.printsweet(s));
        }
    }

    @Then("The user should see a {string}")
    public void theUserShouldSeeA(String message) {
        System.out.println(message);
    }

  
    @Given("The user login as admin or user with {string} and {string}")
    public void theUserLoginAsAdminOrUserWithAnd(String email, String password) {

        isUserLoggedIn = NewSweet.isAdminOrUser(email,password);
        assertTrue(isUserLoggedIn);
    }

    @Then("the new sweet mustn't be added to the sweet list")
    public void theNewSweetMustnTBeAddedToTheSweetList() {
        assertTrue(isUserLoggedIn);
    }

    @When("The user add an existing id of sweet with the id {string} and the name {string} and the type {string} and the type {string}")
    public void theUserAddAnExistingIdOfSweetWithTheIdAndTheNameAndTheType(String id, String name, String type, String price) {
        this.idOfSweet = id;
        this.nameOfSweet = name;
        this.typeOfSweet = type;
        this.priceOfSweet =price;
        isDuplicate= NewSweet.isAdd(idOfSweet);

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
