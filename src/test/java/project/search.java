package project;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

import sweetSys.newSweet;
import sweetSys.sweet;
import static org.junit.Assert.*;

public class search {

    private String id_of_sweet;
    private String name_of_sweet;
    private String type_of_sweet;
    boolean id_search;
    boolean name_search, type_search ;

    public search() {
        sweet.getInstance();
    }

    @Given("I am on the search page")
    public void iAmOnTheSearchPage() {
        System.out.println("You are in the search page :) ");

    }

    @When("I select search by sweet id {string}")
    public void iSelectSearchBySweetId(String id) {
        this.id_of_sweet = id;
        boolean id_search = sweet.Search_ID(id);
        assertTrue(id_search);
    }

    @When("I submit the search")
    public void iSubmitTheSearch() {
        System.out.println("Submit search");

    }

    @Then("I should see the sweet with id")
    public void iShouldSeeTheSweetWithId() {
        sweet.print_SweetId(id_of_sweet);
    }


    @When("I select search by sweet id does not exist {string}")
    public void iSelectSearchBySweetIdDoesNotExist(String id) {
        this.id_of_sweet = id;
        boolean id_search = sweet.Search_ID(id_of_sweet);
        assertFalse(id_search);


    }

    @Then("I should not see any result")
    public void iShouldNotSeeAnyResult() {

    }

    @Then("The massage  no result appears")
    public void theMassageNoResultAppears() {
        System.out.println("There are no result");

    }


    @When("I select search by sweet name {string}")
    public void iSelectSearchBySweetName(String name) {
        this.name_of_sweet = name;
        boolean name_search = sweet.Search_name(name);
        assertTrue(name_search);

    }

    @Then("I should see the sweet with name")
    public void iShouldSeeTheSweetWithName() {
        sweet.print_Sweetname(name_of_sweet);
    }


    @When("I select search by sweet name does not exist {string}")
    public void iSelectSearchBySweetNameDoesNotExist(String name) {
        this.name_of_sweet = name;
        boolean name_search = sweet.Search_name(name_of_sweet);
        assertFalse(name_search);
    }


    @When("I select search by sweet name {string} and sweet type {string}")
    public void iSelectSearchBySweetNameAndSweetType(String name, String type) {
        this.name_of_sweet = name;
        this.type_of_sweet = type;
        boolean Search_name_type = sweet.Search_name_Type(name_of_sweet, type_of_sweet);
        assertTrue(Search_name_type);

    }

    @Then("I should see the sweet with this name and type")
    public void iShouldSeeTheSweetWithThisNameAndType() {
        sweet.Print_Type_name(name_of_sweet,type_of_sweet);
    }



    @When("I select search by sweet name does not exist {string} or sweet type does not exist {string}")
    public void iSelectSearchBySweetNameDoesNotExistOrSweetTypeDoesNotExist(String name , String type) {
        this.name_of_sweet = name;
        this.type_of_sweet = type;
        sweet.Search_name_Type(name_of_sweet, type_of_sweet);
        assertFalse(name_search && type_search);
    }


    @When("I select search by sweet name {string} and sweet id {string}")
    public void iSelectSearchBySweetNameAndSweetId(String name, String id) {
        this.name_of_sweet = name;
        this.id_of_sweet = id;
        boolean Search_name_Id = sweet.Search_name_id(name, id);
        assertTrue(Search_name_Id);
    }

   

    @Then("I should see the sweet with this name and id")
    public void iShouldSeeTheSweetWithThisNameAndId() {
       sweet.Print_name_id(id_of_sweet,name_of_sweet);

    }



    @When("I select search by sweet name does not exist {string} or  no id {string}")
    public void iSelectSearchBySweetNameDoesNotExistOrNoId(String name, String id) {
        this.name_of_sweet = name;
        this.id_of_sweet = id;
        name_search = true ;
        id_search = true ;
        boolean Search_nameid = sweet.Search_name_id(name_of_sweet,id_of_sweet);
       assertFalse(Search_nameid);
    }


    @When("I select search by sweet name {string} ,sweet type {string} and id {string}")
    public void iSelectSearchBySweetNameSweetTypeAndId(String name, String type, String id) {
        this.name_of_sweet = name;
        this.id_of_sweet = id;
        this.type_of_sweet = type;

        boolean all = sweet.Secrch_all(name, id, type);
        assertTrue(all);
    }
    

    @Then("I should see the sweet with this name , type and id")
    public void iShouldSeeTheSweetWithThisNameTypeAndId() {
        sweet.Print_name_id_type(id_of_sweet,name_of_sweet,type_of_sweet);
    }


    @When("I select search by sweet name does not exist {string} or sweet type does not exist {string} or sweet id does not exist {string}")
    public void iSelectSearchBySweetNameDoesNotExistOrSweetTypeDoesNotExistOrSweetIdDoesNotExist(String name, String type, String id) {
        this.name_of_sweet = name;
        this.id_of_sweet = id;
        this.type_of_sweet = type;
        boolean all1 = sweet.Secrch_all(name, id, type);
        assertFalse(all1);
    }


    @When("I select search by sweet price range between Min Price {string} and Max Price {string}")
    public void iSelectSearchBySweetPriceRangeBetweenMinPriceAndMaxPrice(String Min, String Max) {
        sweet.getThePrice(name_of_sweet,type_of_sweet);
        sweet.Pricemin_max(Min, Max);

    }

    @Then("I should see the sweet with price")
    public void iShouldSeeTheSweetWithPrice() {
    }


    @When("I need to see all sweets")
    public void iNeedToSeeAllSweets() {
        System.out.println("I need to see all sweets");

    }
    @Then("I should see all sweets")
    public void iShouldSeeAllSweets() {
        for (newSweet s : sweet.getListOfSweet()) {
            System.out.println("Name : " + s.getName() + " Id : " + s.getId() + " Type :" + s.getType() + " Price : " + s.getPrice());

        }

    }

}
