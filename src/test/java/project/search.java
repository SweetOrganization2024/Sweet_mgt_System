package project;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

import sweet_sys.NewSweet;
import sweet_sys.Sweet;
import static org.junit.Assert.*;

public class search {

    private String idOfSweet;
    private String nameOfSweet;
    private String typeOfSweet;
    boolean idSearch;
    boolean nameSearch, typeSearch;

    public search() {
        Sweet.getInstance();
    }

    @Given("I am on the search page")
    public void iAmOnTheSearchPage() {
        System.out.println("You are in the search page :) ");

    }

    @When("I select search by sweet id {string}")
    public void iSelectSearchBySweetId(String id) {
        this.idOfSweet = id;
        idSearch = Sweet.searchID(id);
        assertTrue(idSearch);
    }

    @When("I submit the search")
    public void iSubmitTheSearch() {
        System.out.println("Submit search");

    }

    @Then("I should see the sweet with id")
    public void iShouldSeeTheSweetWithId() {
        Sweet.printSweetId(idOfSweet);
    }


    @When("I select search by sweet id does not exist {string}")
    public void iSelectSearchBySweetIdDoesNotExist(String id) {
        this.idOfSweet = id;
        idSearch = Sweet.searchID(idOfSweet);
        assertFalse(idSearch);


    }

    @Then("I should not see any result")
    public void iShouldNotSeeAnyResult() {
        //
    }

    @Then("The massage  no result appears")
    public void theMassageNoResultAppears() {
        System.out.println("There are no result");

    }


    @When("I select search by sweet name {string}")
    public void iSelectSearchBySweetName(String name) {
        this.nameOfSweet = name;
         nameSearch = Sweet.searchName(name);
        assertTrue(nameSearch);

    }

    @Then("I should see the sweet with name")
    public void iShouldSeeTheSweetWithName() {
        Sweet.printSweetname(nameOfSweet);
    }


    @When("I select search by sweet name does not exist {string}")
    public void iSelectSearchBySweetNameDoesNotExist(String name) {
        this.nameOfSweet = name;
        nameSearch = Sweet.searchName(nameOfSweet);
assertFalse(localNameSearch);
    }


    @When("I select search by sweet name {string} and sweet type {string}")
    public void iSelectSearchBySweetNameAndSweetType(String name, String type) {
        this.nameOfSweet = name;
        this.typeOfSweet = type;
        boolean searchNameType = Sweet.searchNameType(nameOfSweet, typeOfSweet);
        assertTrue(searchNameType);

    }

    @Then("I should see the sweet with this name and type")
    public void iShouldSeeTheSweetWithThisNameAndType() {
        Sweet.printTypeName(nameOfSweet, typeOfSweet);
    }



    @When("I select search by sweet name does not exist {string} or sweet type does not exist {string}")
    public void iSelectSearchBySweetNameDoesNotExistOrSweetTypeDoesNotExist(String name , String type) {
        this.nameOfSweet = name;
        this.typeOfSweet = type;
        Sweet.searchNameType(nameOfSweet, typeOfSweet);
        assertFalse(nameSearch && typeSearch);
    }


    @When("I select search by sweet name {string} and sweet id {string}")
    public void iSelectSearchBySweetNameAndSweetId(String name, String id) {
        this.nameOfSweet = name;
        this.idOfSweet = id;
        boolean searchNameId = Sweet.searchNameId(name, id);
        assertTrue(searchNameId);
    }

   

    @Then("I should see the sweet with this name and id")
    public void iShouldSeeTheSweetWithThisNameAndId() {
       Sweet.printNameId(idOfSweet, nameOfSweet);

    }



    @When("I select search by sweet name does not exist {string} or  no id {string}")
    public void iSelectSearchBySweetNameDoesNotExistOrNoId(String name, String id) {
        this.nameOfSweet = name;
        this.idOfSweet = id;
        nameSearch = true ;
        idSearch = true ;
        boolean searchNameId = Sweet.searchNameId(nameOfSweet, idOfSweet);
       assertFalse(searchNameId);
    }


    @When("I select search by sweet name {string} ,sweet type {string} and id {string}")
    public void iSelectSearchBySweetNameSweetTypeAndId(String name, String type, String id) {
        this.nameOfSweet = name;
        this.idOfSweet = id;
        this.typeOfSweet = type;

        boolean all = Sweet.secrchAll(name, id, type);
        assertTrue(all);
    }
    

    @Then("I should see the sweet with this name , type and id")
    public void iShouldSeeTheSweetWithThisNameTypeAndId() {
        Sweet.printNameIdType(idOfSweet, nameOfSweet, typeOfSweet);
    }


    @When("I select search by sweet name does not exist {string} or sweet type does not exist {string} or sweet id does not exist {string}")
    public void iSelectSearchBySweetNameDoesNotExistOrSweetTypeDoesNotExistOrSweetIdDoesNotExist(String name, String type, String id) {
        this.nameOfSweet = name;
        this.idOfSweet = id;
        this.typeOfSweet = type;
        boolean all1 = Sweet.secrchAll(name, id, type);
        assertFalse(all1);
    }


    @When("I select search by sweet price range between Min Price {string} and Max Price {string}")
    public void iSelectSearchBySweetPriceRangeBetweenMinPriceAndMaxPrice(String min, String max) {
        Sweet.getThePrice(nameOfSweet, typeOfSweet);
        Sweet.priceminMax(min, max);

    }

    @Then("I should see the sweet with price")
    public void iShouldSeeTheSweetWithPrice() {
        //
    }



    @When("I need to see all sweets")
    public void iNeedToSeeAllSweets() {
        System.out.println("I need to see all sweets");

    }
    @Then("I should see all sweets")
    public void iShouldSeeAllSweets() {
        for (NewSweet s : Sweet.getListOfSweet()) {
            System.out.println("Name : " + s.getName() + " Id : " + s.getId() + " Type :" + s.getType() + " Price : " + s.getPrice());

        }

    }

}
