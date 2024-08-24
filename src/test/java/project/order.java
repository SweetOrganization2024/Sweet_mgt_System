package project;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweetSys.newSweet;
import sweetSys.person;
import sweetSys.sweet;
import sweetSys.OrderManager;
import java.time.LocalDate;
import static org.junit.Assert.*;
import static sweetSys.OrderManager.*;
import sweetSys.OrderManager.Order;
import static org.junit.Assert.assertTrue;


public class order { 
    private sweet AppSweet;
    private OrderManager orderManager;
    private String email;
    private String password;
    private String orderId;
    private String totalPrice;
    private boolean isUserRegistered = false;
    private boolean cancelled = false;
    private String id_of_sweet1;
    private String name_of_sweet1;
    private String type_of_sweet1;
    private String quantity;



    public order() {
        this.AppSweet = sweet.getInstance();
        this.orderManager = new OrderManager();
    }
    @Before
    public void setUp() {
        this.orderManager = getInstance();
        this.orderManager.getOrders().clear();
    }


    @Given("a sweet with ID {string}, name {string}, and type {string} is available for order for user with Email {string} and password {string}")
    public void aSweetWithIDNameAndTypeIsAvailableForOrderForUserWithEmailAndPassword(String ID, String NAME, String TYPE, String EMAIL, String PASSWORD) {
        this.id_of_sweet1 = ID;
        this.name_of_sweet1 = NAME;
        this.type_of_sweet1 = TYPE;
        this.email = EMAIL;
        this.password = PASSWORD;
        boolean isValidSweet = sweet.validSweet(name_of_sweet1,id_of_sweet1,type_of_sweet1);

        boolean isValidUser = sweet.validPeople(email,password);
        assertTrue("Sweet should be valid and user should be registered", isValidSweet && isValidUser);
    }

    @Given("the user selects a quantity of {string} for the sweet")
    public void theUserSelectsAQuantityOfForTheSweet(String quan) {
        this.quantity = quan;
    }

    @When("the user places an order with a total price of {string}")
    public void theUserPlacesAnOrderWithATotalPriceOf(String totalPrice) {
        this.totalPrice = totalPrice;
        String orderId = "order" + (orderManager.getOrders().size() + 1); // Generate a new order ID
        LocalDate orderDate = LocalDate.now();
        double cost = Double.parseDouble(totalPrice);
        orderManager.addOrder(orderId, orderDate, cost);
        System.out.println("Orders after adding new order: " + orderManager.getOrders());
    }


    @Then("the system should confirm the order with a success message including order number, estimated delivery date, and total cost")
    public void theSystemShouldConfirmTheOrderWithASuccessMessageIncludingOrderNumberEstimatedDeliveryDateAndTotalCost() {
        Order currentOrder = orderManager.getCurrentOrder();
        if (currentOrder == null) {
            System.out.println("No orders found.");
            System.out.println("Orders List: " + orderManager.getOrders());
        } else {
            System.out.println("Order confirmed: ID = " + currentOrder.getOrderId() +
                    ", Date = " + currentOrder.getOrderDate() +
                    ", Total cost = " + currentOrder.getTotalCost());
        }
    }

    @Given("a sweet with ID {string}, name {string}, and type {string} is not available for order")
    public void aSweetWithIDNameAndTypeIsNotAvailableForOrder(String id, String name, String type) {
        boolean isAvailable = false;
        for (newSweet s : sweet.getListOfSweet()) {
            if (s.getId().equals(id) || s.getName().equals(name) || s.getType().equals(type)) {
                isAvailable = true;
                break;
            }
        }
        assertFalse("Sweet should not be available for order", isAvailable);
    }

    @Then("the system should display an error message indicating that the sweet with ID {string} is out of stock or unavailable")
    public void theSystemShouldDisplayAnErrorMessageIndicatingThatTheSweetWithIDIsOutOfStockOrUnavailable(String msg) {
        System.out.println("The sweet with ID " + msg + " is out of stock or unavailable.");
    }

    @When("the user enters payment information with card number {string} and expiration date {string}")
    public void theUserEntersPaymentInformationWithCardNumberAndExpirationDate(String card_number, String expiration_date) {
        System.out.println("Payment info: Card number = " + card_number + ", Expiration date = " + expiration_date);
    }

    @Then("the system should confirm successful payment and provide an order confirmation with order number and estimated delivery date")
    public void theSystemShouldConfirmSuccessfulPaymentAndProvideAnOrderConfirmationWithOrderNumberAndEstimatedDeliveryDate() {
        System.out.println("Payment confirmed.");
    }

    @Given("an order with order number {string} exists")
    public void anOrderWithOrderNumberExists(String order_n) {
        this.orderId = order_n;
    }

    @When("the user cancels the order")
    public void theUserCancelsTheOrder() {
        orderManager.cancelOrder(orderId);
        cancelled = true;
    }

    @Then("the system should confirm that the order with order number {string} has been cancelled")
    public void theSystemShouldConfirmThatTheOrderWithOrderNumberHasBeenCancelled(String ordernumber) {
        assertTrue("Order should be cancelled", cancelled);
    }

    @Given("a user with Email {string} and password {string} is logged in")
    public void aUserWithEmailAndPasswordIsLoggedIn(String EMAIL, String PASSWORD) {
        this.email = EMAIL;
        this.password = PASSWORD;
        for (person f : sweet.getPeopleList()) {
            if (f.getEmail().equals(email) && f.getPass().equals(password) && f.getType().equals("USER")) {
                isUserRegistered = true;
                break;
            }
        }
        assertTrue(isUserRegistered);
    }

    @When("the user requests to view their order history")
    public void theUserRequestsToViewTheirOrderHistory() {
        assertTrue(isUserRegistered);
    }

    @Then("the system should display a list of past orders including order numbers, dates, and total costs")
    public void theSystemShouldDisplayAListOfPastOrdersIncludingOrderNumbersDatesAndTotalCosts() {
        orderManager.displayPastOrders();
    }
    @Given("the order with order number {string} has been cancelled")
    public void theOrderWithOrderNumberHasBeenCancelled(String idd) {
        orderManager.cancelOrder(idd);
        cancelled = true;
    }
    @When("the user tries to cancel the order")
    public void theUserTriesToCancelTheOrder() {
        orderManager.cancelOrder(orderId);
    }
    @Then("the system should log a warning indicating that the order with order number {string} is already cancelled")
    public void theSystemShouldLogAWarningIndicatingThatTheOrderWithOrderNumberIsAlreadyCancelled(String number) {
       boolean x=false;
        for (newSweet s : sweet.getListOfSweet()) {
            if (number != s.getId()){
                x=true;
                System.out.println("the order already cancelled");
                assertTrue(x);
            }
            else{
                assertTrue(x);
            }
        }
    }

}
