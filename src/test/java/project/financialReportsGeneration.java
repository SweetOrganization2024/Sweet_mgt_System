package project;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import sweet_sys.FinancialReportGenerator;
import sweet_sys.sweet;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class financialReportsGeneration {
    private String email;
    private String password;
    private boolean valid=false;
   
    @Given("I am logged in as a Supplier or Owner or ADMIN with {string} and {string}")
    public void iAmLoggedInAsASupplierOrOwnerOrADMINWithAnd(String em, String pas) {
        email=em;
        password=pas;
        if(sweet.idSupOrOwnerorAD(email,password)){
            valid=true;
        }
        assertTrue(valid);
    }
    @When("I request to view the financial report")
    public void iRequestToViewTheFinancialReport() {
        if(valid){
            FinancialReportGenerator.generateFinancialReports();
        }
    }
    @Then("the financial report should be displayed")
    public void theFinancialReportShouldBeDisplayed() {
        System.out.println("Display done");
    }

    @Given("I am logged in as a user with {string} and {string}")
    public void iAmLoggedInAsAUserWithAnd(String em, String pas) {
        email=em;
        password=pas;
        if(sweet.idSupOrOwnerorAD(email,password)){
            valid=true;
        }
        assertFalse(valid);
    }

    @Then("the financial report shouldn't be displayed")
    public void iShouldBeDeniedAccess() {
        System.out.println("Can't display the report");
    }



}
