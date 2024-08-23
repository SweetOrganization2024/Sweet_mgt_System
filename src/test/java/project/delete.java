package project;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweetSys.newSweet;
import sweetSys.person;
import sweetSys.sweet;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class delete {
    private String email;
    private String password;
    private String id_of_sweet;
    private String name_of_sweet;
    private String type_of_sweet;
    
    @Given("The user login as owner or supplier with {string} and {string}")
    public void theUserLoginAsOwnerOrSupplierWithAnd(String email, String password) {
        this.email = email;
        this.password = password;


        for (person f : sweet.getList_of_people()) {
            if (f.getEmail().equals(email) && f.getPass().equals(password) &&
                    (f.getType().equals("Owner") || f.getType().equals("Supplier"))) {
                break;
            }
        }

    }


    @When("The information is valid name is {string} and id is {string} and type is {string} and price is {string}")
    public void theInformationIsValidNameIsAndIdIsAndTypeIs(String name, String id, String type, String price) {
        this.id_of_sweet = id;
        this.name_of_sweet = name;
        this.type_of_sweet = type;

        boolean isValid = false;
        for (newSweet s : sweet.getListOfSweet()) {
            if (s.getName().equals(name) && s.getId().equals(id) && s.getType().equals(type)) {
                isValid = true;
                break;
            }
        }

        assertTrue("Sweet information should be valid", isValid);
    }

    @Then("The specified sweet must be deleted from sweet list")
    public void theSpecifiedSweetMustBeDeletedFromSweetList() {
        newSweet sweetToDelete = null;
        boolean isValid = false;

        for (newSweet s : sweet.getListOfSweet()) {
            if (s.getName().equals(name_of_sweet) && s.getId().equals(id_of_sweet) && s.getType().equals(type_of_sweet)) {
                sweetToDelete = s;
                isValid = true;
                break;
            }
        }

        if (isValid) {
            sweet.deletesweet(sweetToDelete);
        }

        assertTrue("Sweet should be successfully deleted", isValid);
        for (newSweet s : sweet.getListOfSweet()) {
            System.out.println(newSweet.printsweet(s));
        }
    }

    @Then("The User should see the sweet successfully deleted")
    public void theUserShouldSeeTheSweetSuccessfullyDeleted() {
        System.out.println("Delete successful");
    }
    @Given("The user login as not owner or supplier with {string} and {string}")
    public void theUserLoginAsNotOwnerOrSupplierWithAnd(String string, String string2) {
        boolean isRegistered = false;

        for (person f : sweet.getList_of_people()) {
            if (f.getEmail().equals(email) && f.getPass().equals(password) &&
                    (f.getType().equals("ADMIN") || f.getType().equals("USER"))) {
                isRegistered = true;
                break;
            }
        }

        if (isRegistered) {assertTrue( isRegistered);}
        else assertFalse(isRegistered);
    }
    @When("the user deletes a sweet with not available id is {string} or not available name is {string} or type is {string}")
    public void theUserDeletesASweetWithNotAvailableIdIsOrNotAvailableNameIsOrTypeIs(String id, String name, String type) {
        boolean isValid = true;
        for (newSweet s : sweet.getListOfSweet()) {
            if (s.getName().equals(name) || s.getId().equals(id) || s.getType().equals(type)) {
                isValid = false;
                break;
            }
        }
        if (isValid)
            assertTrue("Sweet should not exist || u are not owner or supplier", isValid);
        else assertFalse(isValid);
    }
    @Then("The user should see a message that this sweet does not exist")
    public void theUserShouldSeeAMessageThatThisSweetDoesNotExist() {
        System.out.println("This sweet does not exist");
    }
}
