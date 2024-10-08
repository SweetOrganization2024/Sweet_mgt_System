package project;

import sweet_sys.Feedback;
import sweet_sys.Sweet;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import sweet_sys.*;
import static org.junit.Assert.assertTrue;

public class feedback {
    private String email,sweetid,discription;
    private int rating;
    private Feedback f;
    private String password;
    private boolean valid=false;
   
    @Given("I am logged in with {string} and {string}")
    public void iAmLoggedInAsAUserWithAnd(String em, String pa) {
        email=em;
        password=pa;
    }
    @When("I submit feedback for the sweet with ID {string} with the text {string} and a rating of {string}")
    public void iSubmitFeedbackForTheSweetWithIDWithTheTextAndARatingOf(String id, String text, String rat) {
        sweetid=id;
        discription=text;
        rating=Integer.parseInt(rat);
        f=new Feedback(email,sweetid,discription,rating);
    }
    @Then("the feedback should be saved successfully")
    public void theFeedbackShouldBeSavedSuccessfully() {
        f.saveFeedbackToFile();
    }

    @Given("I am logged in as an Admin with {string} and {string}")
    public void iAmLoggedInAsAnAdminWithAnd(String em, String pass) {
        email=em;
        password=pass;
        Person p= Sweet.retperson(email,password);
        if(p.getType().equals("ADMIN")){
            valid=true;
        }
    }
    @When("I request to read the feedback")
    public void iRequestToReadTheFeedback() {
        assertTrue(valid);
    }
    @Then("I should see all feedback submitted by users")
    public void iShouldSeeAllFeedbackSubmittedByUsers() {
        Feedback.readFeedbackFromFile();
    }


}
