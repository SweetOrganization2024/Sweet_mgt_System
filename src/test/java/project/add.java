package project;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import sweetSys.newSweet;
import sweetSys.person;
import sweetSys.sweet;

import static org.junit.Assert.assertTrue;

public class add {
    private String id_of_sweet;
    private String name_of_sweet;
    private String type_of_sweet;
    private String price_of_sweet;
    private boolean isUserLoggedIn;
    boolean isDuplicate = false;
    private static final Logger logger = Logger.getLogger(add.class.getName());


    @BeforeClass
    public static void setUp() {
        sweet.getListOfSweet().clear();
        sweet.getList_of_people().clear();

    }

    @AfterClass
    public static void tearDown() {
        sweet.getListOfSweet().clear();
        sweet.getList_of_people().clear();

        System.out.println("Cleanup done.");
    }
    public add() {
    }

    @Given("The user login as Owner or Supplier with {string} and {string}")
    public void theUserLoginAsOwnerOrSupplierWithAnd(String email, String password) {

        boolean isRegistered = newSweet.if_the_type_sp_or_owner(email,password);
        person.isRightType(email,password,type_of_sweet);

        isUserLoggedIn = isRegistered;
        assertTrue("User should be logged in as Owner or Supplier", isRegistered);
    }

    @When("The user add a new sweet")
    public void theUserAddANewSweet() {
        if (isUserLoggedIn) {
        } else {
            throw new AssertionError("User is not logged in");
        }
    }

    @Then("The user fill in id of sweet with {string} and The User fill in name of sweet with {string} and The User fill in type of sweet with {string} and The User fill in price of sweet with {string}")
    public void theUserFillInIdOfSweetWithAndTheUserFillInNameOfSweetWithAndTheUserFillInTypeOfSweetWith(String id, String name, String type, String price) {
        this.id_of_sweet = id;
        this.name_of_sweet = name;
        this.type_of_sweet = type;
        this.price_of_sweet=price;
    }

    @Then("the new sweet must be added to the sweet list")
    public void theNewSweetMustBeAddedToTheSweetList() {
        newSweet MySWEET = new newSweet(id_of_sweet, name_of_sweet, type_of_sweet, price_of_sweet);
        boolean isAdded = newSweet.isAdd(id_of_sweet);

        if (!isAdded) {
            sweet.listOfSweet.add(MySWEET);
            logger.info("Sweet added: " + MySWEET.getName_of_sweet());
        }
        for (newSweet s : sweetSys.sweet.getListOfSweet()) {
            logger.info(newSweet.printsweet(s));
        }
    }


    @Then("The user should see a {string}")
    public void theUserShouldSeeA(String message) {
        logger.info(message);
    }

    //2rd
    @Given("The user login as admin or user with {string} and {string}")
    public void theUserLoginAsAdminOrUserWithAnd(String email, String password) {

        isUserLoggedIn = newSweet.if_the_type_AD_or_USR(email,password);
        assertTrue(isUserLoggedIn);
    }

    @Then("the new sweet mustn't be added to the sweet list")
    public void theNewSweetMustnTBeAddedToTheSweetList() {
        assertTrue(isUserLoggedIn);
    }


    //3rd
    @When("The user add an existing id of sweet with the id {string} and the name {string} and the type {string} and the type {string}")
    public void theUserAddAnExistingIdOfSweetWithTheIdAndTheNameAndTheType(String id, String name, String type, String price) {
        this.id_of_sweet = id;
        this.name_of_sweet = name;
        this.type_of_sweet = type;
        this.price_of_sweet=price;
        isDuplicate=newSweet.isAdd(id_of_sweet);

        assertTrue(isDuplicate);
    }

    @Then("the new sweet must not be added to the sweet list")
    public void theNewSweetMustNotBeAddedToTheSweetList() {
        assertTrue(isDuplicate);
    }

    @Then("The user should see a message that adding the sweet failed")
    public void theUserShouldSeeAMessageThatAddingTheSweetFailed() {
       logger.info("Adding the sweet failed");
    }


}
