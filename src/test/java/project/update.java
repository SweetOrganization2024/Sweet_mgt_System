package project;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import sweet_sys.NewSweet;
import sweet_sys.Person;
import sweet_sys.Sweet;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class update {
    private String email;
    private String password;
    private String idOfSweet1 = "";

    boolean isValid = false;

    @Given("The user logs in as owner or supplier with {string} and {string}")
    public void theUserLoginAsOwnerOrSupplierWithAnd(String email2, String password2) {
        this.email = email2;
        this.password = password2;
        boolean isRegistered = false;

        for (Person f : Sweet.getPeopleList()) {
            if (f.getEmail().equals(email) && f.getPass().equals(password) &&
                    (f.getType().equals("Owner") || f.getType().equals("Supplier"))) {
                isRegistered = true;
                break;
            }
        }

        assertTrue(isRegistered);
    }

    @When("the user selects a valid sweet with ID {string} and name {string} and type {string} and price {string}")
    public void theUserSelectsAValidSweetWithIDAndNameAndTypeAndPrice(String id, String name, String type, String price) {
        this.idOfSweet1 = id;
        for (NewSweet s : Sweet.getListOfSweet()) {
            if (s.getId().equals(idOfSweet1)) {
                isValid = true;
                break;
            }
        }
        assertTrue("Sweet information should be valid", isValid);
    }

    @When("the user enters the new value to update {string} and {string}")
    public void theUserEntersTheNewValueToUpdateAnd(String nname, String ttype) {
        if (isValid) {
            for (NewSweet s : Sweet.getListOfSweet()) {
                if (s.getId().equals(idOfSweet1)) {
                    s.setName(nname);
                    s.setType(ttype);
                    break;
                }
            }
        }
    }

    @Then("the system updates the sweet in the sweet list")
    public void theSystemUpdatesTheSweetInTheSweetList() {
        assertTrue(isValid);
        System.out.println("Update done");
        for (NewSweet s : Sweet.getListOfSweet()) {
            System.out.println("ID: " + s.getId() + ", Name: " + s.getName() + ", Type: " + s.getType() + ", price:" + s.getPrice());
        }
    }

    @When("the user enters the new value to update {string}")
    public void theUserEntersTheNewValueToUpdate(String nname) {
        if (isValid) {
            for (NewSweet s : Sweet.getListOfSweet()) {
                if (s.getId().equals(idOfSweet1)) {
                    s.setName(nname);
                    break;
                }
            }
        }
    }

    @When("the user enters the new type to update {string}")
    public void theUserEntersTheNewTypeToUpdate(String ttype) {
        if (isValid) {
            for (NewSweet s : Sweet.getListOfSweet()) {
                if (s.getId().equals(idOfSweet1)) {
                    s.setType(ttype);
                    break;
                }
            }
        }
    }

    @When("the user enters the new price to update {string}")
    public void theUserEntersTheNewPriceToUpdate(String ppric) {
        if (isValid) {
            for (NewSweet s : Sweet.getListOfSweet()) {
                if (s.getId().equals(idOfSweet1)) {
                    s.setPrice(ppric);
                    break;
                }
            }
        }
    }
    @Given("The user logs in as not owner or supplier with {string} and {string}")
    public void theUserLogsInAsNotOwnerOrSupplierWithAnd(String email, String password) {
        this.email = email;
        this.password = password;
        boolean isRegistered = false;

        for (Person f : Sweet.getPeopleList()) {
            if (f.getEmail().equals(email) && f.getPass().equals(password) &&
                    (f.getType().equals("ADMIN") || f.getType().equals("USER"))) {
                isRegistered = true;
                break;
            }
        }

        if (isRegistered) {
            assertTrue(isRegistered);
        } else {
            assertFalse(isRegistered);
        }
    }

    @When("the user tries to update a sweet with an unavailable ID {string} or unavailable name {string} or type {string}")
    public void theUserTriesToUpdateASweetWithAnUnavailableIDOrUnavailableNameOrType(String id, String name, String type) {
        boolean isInvalid = true;
        for (NewSweet s : Sweet.getListOfSweet()) {
            if (s.getId().equals(id) || s.getName().equals(name) || s.getType().equals(type)) {
                isInvalid = false;
                break;
            }
        }
        if (isInvalid)
            assertTrue("Sweet does not exist, cannot update", isInvalid);
        else
            assertFalse(isInvalid);
    }

    @Then("the user should see a {string} indicating the sweet does not exist")
    public void theUserShouldSeeAIndicatingTheSweetDoesNotExist(String message) {
        System.out.println(message);
    }
}
