package project;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

import sweetSys.newSweet;
import sweetSys.sweet;

import java.util.List;

import static org.junit.Assert.*;

public class search {

    private String id_of_sweet;
    private String name_of_sweet;
    private String type_of_sweet;
    private String price_of_sweet;
    boolean id_search ;
    boolean name_search , type_search , price_search;

    public search() {
        sweet appSweet = sweet.getInstance();
    }

    @Given("I am on the search page")
    public void iAmOnTheSearchPage() {
        System.out.println("You are in the search page :) ");

    }

    @When("I select search by sweet id {string}")
    public void iSelectSearchBySweetId(String id) {
        this.id_of_sweet = id;
        id_search = false;
        for(newSweet s  : sweet.getListOfSweet()){
            if(s.getId_of_sweet().equals(id)){
                id_search = true;
                break;
            }
        }
        //System.out.println(id_search);
        assertTrue(id_search);
    }

    @When("I submit the search")
    public void iSubmitTheSearch() {
        System.out.println("Submit search");

    }

    @Then("I should see the sweet with id")
    public void iShouldSeeTheSweetWithId() {

        for (newSweet s : sweet.getListOfSweet()) {
            if (s.getId_of_sweet().equals(id_of_sweet)) {
                System.out.println("Name : " + s.getName_of_sweet() + " Id : " + s.getId_of_sweet() + " Type :" + s.getType_of_sweet() + " Price : " + s.getPrice());

                break;
            }
        }

    }

    //2nd scenario

    @When("I select search by sweet id does not exist {string}")
    public void iSelectSearchBySweetIdDoesNotExist(String id) {
        this.id_of_sweet = id;
        id_search = true;
        for(newSweet s  : sweet.getListOfSweet()){
            if(s.getId_of_sweet().equals(id)){
                id_search = false;
                break;
            }
        }
        //System.out.println(id_search);
        assertTrue(id_search);


    }

    @Then("I should not see any result")
    public void iShouldNotSeeAnyResult() {

    }

    @Then("The massage  no result appears")
    public void theMassageNoResultAppears() {
        System.out.println("There are no result");

    }

    //3rd

    @When("I select search by sweet name {string}")
    public void iSelectSearchBySweetName(String name) {
        this.name_of_sweet = name;
        name_search = false;
        for(newSweet s  : sweet.getListOfSweet()){
            if(s.getName_of_sweet().equals(name)){
                name_search = true;
                break;
            }
        }
        //System.out.println(id_search);
        assertTrue(name_search);


    }
    @Then("I should see the sweet with name")
    public void iShouldSeeTheSweetWithName() {
        for (newSweet s : sweet.getListOfSweet()) {
            if (s.getName_of_sweet().equals(name_of_sweet)) {
                System.out.println("Name : " + s.getName_of_sweet() + " Id : " + s.getId_of_sweet() + " Type :" + s.getType_of_sweet() + " Price : " + s.getPrice());

                break;
            }
        }
    }

    //4
    @When("I select search by sweet name does not exist {string}")
    public void iSelectSearchBySweetNameDoesNotExist(String name) {
        this.name_of_sweet = name;
        name_search = true ;
        for(newSweet s  : sweet.getListOfSweet()){
            if(s.getName_of_sweet().equals(name)){
                name_search = false ;
                break;
            }
        }
        //System.out.println(id_search);
        assertTrue(name_search);

    }

    // 5
    @When("I select search by sweet name {string} and sweet type {string}")
    public void iSelectSearchBySweetNameAndSweetType(String name , String type ) {
        this.name_of_sweet = name;
        this.type_of_sweet = type;
        name_search = false;
        type_search = false;
        for(newSweet s  : sweet.getListOfSweet()){
            if(s.getName_of_sweet().equals(name) && (s.getType_of_sweet().equals(type))){
                name_search = true;
                type_search = true;
                break;
            }
        }
        assertTrue(name_search && type_search);

    }
    @Then("I should see the sweet with this name and type")
    public void iShouldSeeTheSweetWithThisNameAndType() {
        for (newSweet s : sweet.getListOfSweet()) {
            if (s.getName_of_sweet().equals(name_of_sweet)) {
                System.out.println("Name : " + s.getName_of_sweet() + " Id : " + s.getId_of_sweet() + " Type :" + s.getType_of_sweet() + " Price : " + s.getPrice());

                break;
            }
        }

    }

    //6

    @When("I select search by sweet name does not exist {string} or sweet type does not exist {string}")
    public void iSelectSearchBySweetNameDoesNotExistOrSweetTypeDoesNotExist(String name , String type) {
        this.name_of_sweet = name;
        this.type_of_sweet = type;
        name_search = true;
        type_search = true;
        for(newSweet s  : sweet.getListOfSweet()){
            if(s.getName_of_sweet().equals(name) && (s.getType_of_sweet().equals(type))){
                name_search = false;
                type_search = false;
                break;
            }
        }
        assertTrue(name_search && type_search);

    }

    // 7

    @When("I select search by sweet name {string} and sweet id {string}")
    public void iSelectSearchBySweetNameAndSweetId(String name, String id) {
        this.name_of_sweet = name;
        this.id_of_sweet = id;
        name_search = false;
        id_search = false;
        for(newSweet s  : sweet.getListOfSweet()){
            if(s.getName_of_sweet().equals(name) && (s.getId_of_sweet().equals(id))){
                name_search = true;
                id_search = true;
                break;
            }
        }
        assertTrue(name_search && id_search);


    }
    @Then("I should see the sweet with this name and id")
    public void iShouldSeeTheSweetWithThisNameAndId() {
        for (newSweet s : sweet.getListOfSweet()) {
            if (s.getName_of_sweet().equals(name_of_sweet)) {
                System.out.println("Name : " + s.getName_of_sweet() + " Id : " + s.getId_of_sweet() + " Type :" + s.getType_of_sweet() + " Price : " + s.getPrice());

                break;
            }
        }

    }

    // 8
    @When("I select search by sweet name does not exist {string} or  no id {string}")
    public void iSelectSearchBySweetNameDoesNotExistOrNoId(String name, String id) {
        this.name_of_sweet = name;
        this.id_of_sweet = id;
        name_search = true ;
        id_search = true ;
        for(newSweet s  : sweet.getListOfSweet()){
            if(s.getName_of_sweet().equals(name) && (s.getId_of_sweet().equals(id))){
                name_search = false ;
                id_search = false;
                break;
            }
        }
        assertTrue(name_search && id_search);

    }

    //9

    @When("I select search by sweet name {string} ,sweet type {string} and id {string}")
    public void iSelectSearchBySweetNameSweetTypeAndId(String name, String type, String id) {
        this.name_of_sweet = name;
        this.id_of_sweet = id;
        this.type_of_sweet = type;
        name_search = false;
        id_search = false;
        type_search = false ;
        for(newSweet s  : sweet.getListOfSweet()){
            if(s.getName_of_sweet().equals(name) && (s.getId_of_sweet().equals(id))&& (s.getType_of_sweet().equals(type))){
                name_search = true;
                id_search = true;
                type_search = true;
                break;
            }
        }
        assertTrue(name_search && id_search);


    }
    @Then("I should see the sweet with this name , type and id")
    public void iShouldSeeTheSweetWithThisNameTypeAndId() {
        for (newSweet s : sweet.getListOfSweet()) {
            if (s.getName_of_sweet().equals(name_of_sweet)) {
                System.out.println("Name : " + s.getName_of_sweet() + " Id : " + s.getId_of_sweet() + " Type :" + s.getType_of_sweet() + " Price : " + s.getPrice());

                break;
            }
        }

    }

    //10
    @When("I select search by sweet name does not exist {string} or sweet type does not exist {string} or sweet id does not exist {string}")
    public void iSelectSearchBySweetNameDoesNotExistOrSweetTypeDoesNotExistOrSweetIdDoesNotExist(String name, String type, String id) {
        this.name_of_sweet = name;
        this.id_of_sweet = id;
        this.type_of_sweet = type;
        name_search = true;
        id_search = true;
        type_search = true ;
        for(newSweet s  : sweet.getListOfSweet()){
            if(s.getName_of_sweet().equals(name) && (s.getId_of_sweet().equals(id))&& (s.getType_of_sweet().equals(type))){
                name_search = false;
                id_search = false;
                type_search = false;
                break;
            }
        }
    }

    //11
    @When("I select search by sweet price range between Min Price {string} and Max Price {string}")
    public void iSelectSearchBySweetPriceRangeBetweenMinPriceAndMaxPrice(String Min, String Max) {

        for (newSweet s : sweet.getListOfSweet()) {  // Assuming 'Sweet' is the class name
            String price = s.getPrice();
            int min = Integer.parseInt(Min);
            int max = Integer.parseInt(Max);
            int Myprice = Integer.parseInt(price);
            if (Myprice > min && Myprice < max) {
                System.out.println("Name : " + s.getName_of_sweet() +
                        " Id : " + s.getId_of_sweet() +
                        " Type : " + s.getType_of_sweet() +
                        " Price : " + s.getPrice());
            }
        }

    }



    @Then("I should see the sweet with price")
    public void iShouldSeeTheSweetWithPrice() {
        for (newSweet s : sweet.getListOfSweet()) {
            if (s.getName_of_sweet().equals(name_of_sweet)) {
                System.out.println("Name : " + s.getName_of_sweet() + " Id : " + s.getId_of_sweet() + " Type :" + s.getType_of_sweet() + " Price : " + s.getPrice());

                break;

            }
        }

    }

    //12
    @When("I need to see all sweets")
    public void iNeedToSeeAllSweets() {
        System.out.println("I need to see all sweets");

    }
    @Then("I should see all sweets")
    public void iShouldSeeAllSweets() {
        for (newSweet s : sweet.getListOfSweet()) {
            System.out.println("Name : " + s.getName_of_sweet() + " Id : " + s.getId_of_sweet() + " Type :" + s.getType_of_sweet() + " Price : " + s.getPrice());

        }

    }

}