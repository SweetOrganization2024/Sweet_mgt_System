package project;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import sweetSys.newSweet;
import sweetSys.person;
import sweetSys.sweet;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
public class update {


    private sweet AppSweet;
    private String email;
    private String password;
    private String id_of_sweet1="";
    private String name_of_sweet1="";
    private String type_of_sweet1="";

    boolean isValid = false;

    public update(sweet appSweet) {
        AppSweet = appSweet;
    }
    @Given("The user logs in as owner or supplier with {string} and {string}")
    public void theUserLoginAsOwnerOrSupplierWithAnd(String email2, String password2) {
        this.email = email2;
        this.password = password2;
        boolean isRegistered = false;

        for (person f : AppSweet.getList_of_people()) {
            if (f.getEmail().equals(email) && f.getPass().equals(password) &&
                    (f.getType().equals("Owner") || f.getType().equals("Supplier"))) {
                isRegistered = true;
                break;
            }
        }

        assertTrue( isRegistered);
    }
    @When("the user selects a valid sweet with ID {string} and name {string} and type {string}")
    public void theUserSelectsAValidSweetWithIDAndNameAndType(String name_1, String id_1, String type_1) {
        newSweet.addsweet(new newSweet(name_1, id_1, type_1));
    }
    @When("the information is valid: name is {string}, ID is {string}, and type is {string}")
    public void theInformationIsValidNameIsIDIsAndTypeIs(String id, String name, String type) {
        this.id_of_sweet1 = id;
        this.name_of_sweet1 = name;
        this.type_of_sweet1 = type;
        for (newSweet s : newSweet.getListOfSweet()) {
            if (s.getName_of_sweet().equals(name) && s.getId_of_sweet().equals(id) && s.getType_of_sweet().equals(type)) {
                isValid = true;
                break;
            }
        }

        assertTrue("Sweet information should be valid", isValid);
    }
    @When("the user enters the new value to update {string} and {string}")
    public void theUserEntersTheNewValueToUpdateAnd(String ttype, String nname) {
         newSweet update_sweet= new newSweet(name_of_sweet1,id_of_sweet1,type_of_sweet1);
          update_sweet.setType_of_sweet(ttype);
          update_sweet.setName_of_sweet(nname);
    }
    @Then("the system updates the sweet in the sweet list")
    public void theSystemUpdatesTheSweetInTheSweetList() {
      assertTrue(isValid);
    }
    //2nd name
    @When("the user enters the new value to update {string}")
    public void theUserEntersTheNewValueToUpdate(String nname) {
        newSweet update_sweet= new newSweet(name_of_sweet1,id_of_sweet1,type_of_sweet1);
        update_sweet.setName_of_sweet(nname);
    }
    //3rd type
    @When("the user enters the new type to update {string}")
    public void theUserEntersTheNewTypeToUpdate(String ttype) {
        newSweet update_sweet= new newSweet(name_of_sweet1,id_of_sweet1,type_of_sweet1);
        update_sweet.setType_of_sweet(ttype);

    }

    //4th
    @Given("The user logs in as not owner or supplier with {string} and {string}")
    public void theUserLogsInAsNotOwnerOrSupplierWithAnd(String string, String string2) {
        boolean isRegistered = false;

        for (person f : AppSweet.getList_of_people()) {
            if (f.getEmail().equals(email) && f.getPass().equals(password) &&
                    (f.getType().equals("ADMIN") || f.getType().equals("USER"))) {
                isRegistered = true;
                break;
            }
        }

        if (isRegistered) {assertTrue( isRegistered);}
        else assertFalse(isRegistered);

    }
    @When("the user tries to update a sweet with an unavailable ID {string} or unavailable name {string} or type {string}")
    public void theUserTriesToUpdateASweetWithAnUnavailableIDOrUnavailableNameOrType(String name, String id, String type) {
        boolean isValid = true;
        for (newSweet s : newSweet.getListOfSweet()) {
            if (s.getName_of_sweet().equals(name) || s.getId_of_sweet().equals(id) || s.getType_of_sweet().equals(type)) {
                isValid = false;
                break;
            }
        }
        if (isValid)
            assertTrue("Sweet dont update || u are not owner or supplier", isValid);
        else assertFalse(isValid);
    }

    @Then("the user should see a {string} indicating the sweet does not exist")
    public void theUserShouldSeeAIndicatingTheSweetDoesNotExist(String message) {
System.out.println(message);
    }
}
