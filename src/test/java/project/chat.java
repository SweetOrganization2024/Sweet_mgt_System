package project;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.text.ParseException;
import java.util.Iterator;
import org.junit.Assert;
import sweetSys.DiscountManager;
import sweetSys.newSweet;
import sweetSys.sweet;

public class discount {
    private sweet AppSweet = sweet.getInstance();
    double result;
    private DiscountManager discountManage;
    private newSweet sweetProduct;
    private String id = "";
    private String description = "";
    private double percentage = 0.0;
    private String start;
    private String end;
    boolean found = false;

    public discount() {
    }

    @Given("I am logged in as an Spplier or Owner with {string} and {string}")
    public void iAmLoggedInAsAnSpplierOrOwnerWithAnd(String email, String pass) {
        sweet.idSupOrOwner(email, pass);
        Iterator var3 = sweet.getListOfSweet().iterator();

        while(var3.hasNext()) {
            newSweet s = (newSweet)var3.next();
            System.out.println(newSweet.printsweet(s));
        }

    }

    @When("I add a new discount with ID {string} and description {string}")
    public void i_add_a_new_discount_with_ID_and_description(String idd, String desc) {
        this.id = idd;
        this.description = desc;
    }

    @And("the discount percentage is {string}")
    public void the_discount_percentage_is(String percentageString) {
        this.percentage = Double.parseDouble(percentageString);
        this.discountManage = new DiscountManager(this.id, this.description, this.percentage);
        DiscountManager.addDiscount(this.discountManage);
    }

    @And("the discount is valid from {string} to {string}")
    public void the_discount_is_valid_from_to(String startDate, String endDate) throws ParseException {
        this.start = startDate;
        this.end = startDate;
        this.discountManage.setStart(this.start);
        this.discountManage.setEnd(this.end);
        System.out.println("Discount valid from " + this.start + " to " + this.end);
    }

    @Then("the discount should be added successfully")
    public void the_discount_should_be_added_successfully() {
        Iterator var1 = DiscountManager.getDiscounts().iterator();

        while(var1.hasNext()) {
            DiscountManager d = (DiscountManager)var1.next();
            if (d.getId().equals(this.id)) {
                this.found = true;
                break;
            }
        }

        Assert.assertTrue("Discount not added successfully.", this.found);
    }

    @And("I should see the discount in the list of discounts")
    public void i_should_see_the_discount_in_the_list_of_discounts() {
        Assert.assertTrue("Discount not found in the list.", this.found);
    }

    @Given("a time-based discount with ID {string} is available")
    public void a_time_based_discount_with_ID_is_available(String discountId) {
        this.discountManage = null;
        Iterator var2 = DiscountManager.getDiscounts().iterator();

        while(var2.hasNext()) {
            DiscountManager d = (DiscountManager)var2.next();
            if (d.getId().equals(discountId)) {
                this.discountManage = d;
                break;
            }
        }

        Assert.assertNotNull("Discount ID not found.", this.discountManage);
    }

    @And("the sweet product with ID {string} has a price of {string}")
    public void the_sweet_product_with_ID_has_a_price_of(String sweetId, String price) {
        boolean val = false;
        Iterator var4 = sweet.getListOfSweet().iterator();

        while(var4.hasNext()) {
            newSweet n = (newSweet)var4.next();
            if (n.getId().equals(sweetId) && n.getPrice().equals(price)) {
                val = true;
                break;
            }
        }

        Assert.assertTrue("This sweet not fount", val);
    }

    @When("I apply the discount with ID {string} to the sweet product with ID {string}")
    public void i_apply_the_discount_with_ID_to_the_sweet_product_with_ID(String discountId, String sweetId) {
        this.result = DiscountManager.applyDiscountToProduct(sweetId, this.discountManage);
    }

    @Then("the discounted price of the sweet product should be {string}")
    public void the_discounted_price_of_the_sweet_product_should_be(String expectedDiscountedPrice) {
        String actual = String.format("%.2f", this.result);
        Assert.assertEquals(actual, expectedDiscountedPrice);
    }
}
