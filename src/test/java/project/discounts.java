package project;
import io.cucumber.java.en.*;
import static org.junit.Assert.*;
import sweet_sys.*;

public class discounts {
    double result;
    private DiscountManager discountManage;
    private NewSweet sweetProduct;
    private String id ="";
    private String description="";
    private double percentage=0.0;
    private String start,end;
    boolean found = false;
   
    @Given("I am logged in as an Spplier or Owner with {string} and {string}")
    public void iAmLoggedInAsAnSpplierOrOwnerWithAnd(String email, String pass) {
        Sweet.idSupOrOwner(email,pass);
        for (NewSweet s: Sweet.getListOfSweet()){
            System.out.println(NewSweet.printsweet(s));
        }
    }


    @When("I add a new discount with ID {string} and description {string}")
    public void i_add_a_new_discount_with_ID_and_description(String idd, String desc) {
        id=idd;
        description=desc;
    }

    @And("the discount percentage is {string}")
    public void the_discount_percentage_is(String percentageString) {
        percentage = Double.parseDouble(percentageString);
        discountManage= new DiscountManager(id,description,percentage);
        DiscountManager.addDiscount(discountManage);
    }

    @And("the discount is valid from {string} to {string}")
    public void the_discount_is_valid_from_to(String startDate, String endDate) {
        start =startDate;
        end =startDate;
        discountManage.setStart(start);
        discountManage.setEnd(end);
        System.out.println("Discount valid from " + start + " to " + end);

    }

    @Then("the discount should be added successfully")
    public void the_discount_should_be_added_successfully() {
        for (DiscountManager d : DiscountManager.getDiscounts()) {
            if (d.getId().equals(id)) {
                found = true;
                break;
            }
        }
        assertTrue("Discount not added successfully.", found);
    }

    @And("I should see the discount in the list of discounts")
    public void i_should_see_the_discount_in_the_list_of_discounts() {
        assertTrue("Discount not found in the list.", found);
    }

    @Given("a time-based discount with ID {string} is available")
    public void a_time_based_discount_with_ID_is_available(String discountId) {
        discountManage = null;
        for (DiscountManager d : DiscountManager.getDiscounts()) {
            if (d.getId().equals(discountId)) {
                discountManage = d;
                break;
            }
        }
        assertNotNull("Discount ID not found.", discountManage);
    }

    @And("the sweet product with ID {string} has a price of {string}")
    public void the_sweet_product_with_ID_has_a_price_of(String sweetId, String price) {
        boolean val=false;
        for (NewSweet n: Sweet.getListOfSweet()){
            if(n.getId().equals(sweetId) && n.getPrice().equals(price)){
                val=true;
                break;
            }
        }
        assertTrue("This sweet not fount",val);
    }

    @When("I apply the discount with ID {string} to the sweet product with ID {string}")
    public void i_apply_the_discount_with_ID_to_the_sweet_product_with_ID(String discountId, String sweetId) {
        result=DiscountManager.applyDiscountToProduct(sweetId,discountManage);
    }

    @Then("the discounted price of the sweet product should be {string}")
    public void the_discounted_price_of_the_sweet_product_should_be(String expectedDiscountedPrice) {
        String actual= String.format("%.2f",result);
        assertEquals(actual,expectedDiscountedPrice);
    }
}
