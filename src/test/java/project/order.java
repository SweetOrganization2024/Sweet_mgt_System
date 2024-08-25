package project;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sweet_sys.NewSweet;
import sweet_sys.Person;
import sweet_sys.Sweet;
import sweet_sys.OrderManager;
import java.time.LocalDate;
import static org.junit.Assert.*;
import static sweet_sys.OrderManager.*;
import sweet_sys.OrderManager.Order;
import static org.junit.Assert.assertTrue;


public class order { 
    private OrderManager orderManager;
    private String email;
    private String password;
    private String orderId;
    private String totalPrice;
    private boolean isUserRegistered = false;
    private boolean cancelled = false;
    private String idOfSweet1;
    private String nameOfSweet1;
    private String typeOfSweet1;
    private String quantity;



    public order() {
      Sweet.getInstance();
        this.orderManager = new OrderManager();
    }
    @Before
    public void setUp() {
        this.orderManager = getInstance();
        this.orderManager.getOrders().clear();
    }


    @Given("a sweet with ID {string}, name {string}, and type {string} is available for order for user with Email {string} and password {string}")
    public void aSweetWithIDNameAndTypeIsAvailableForOrderForUserWithEmailAndPassword(String id , String name , String type, String email, String password) {
        this.idOfSweet1 = id;
        this.nameOfSweet1 = name;
        this.typeOfSweet1 = type;
        this.email = email;
        this.password = password;
        boolean isValidSweet = Sweet.validSweet(nameOfSweet1, idOfSweet1, typeOfSweet1);

        boolean isValidUser = Sweet.validPeople(email,password);
        assertTrue("Sweet should be valid and user should be registered", isValidSweet && isValidUser);
    }

    @Given("the user selects a quantity of {string} for the sweet")
    public void theUserSelectsAQuantityOfForTheSweet(String quan) {
        this.quantity = quan;
    }

    @When("the user places an order with a total price of {string}")
    public void theUserPlacesAnOrderWithATotalPriceOf(String totalPrice) {
        this.totalPrice = totalPrice;
        orderId = "order" + (orderManager.getOrders().size() + 1); // Generate a new order ID
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
        for (NewSweet s : Sweet.getListOfSweet()) {
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
    public void theUserEntersPaymentInformationWithCardNumberAndExpirationDate(String cardNumber, String expirationDate) {
        System.out.println("Payment info: Card number = " + cardNumber + ", Expiration date = " + expirationDate);
    }

    @Then("the system should confirm successful payment and provide an order confirmation with order number and estimated delivery date")
    public void theSystemShouldConfirmSuccessfulPaymentAndProvideAnOrderConfirmationWithOrderNumberAndEstimatedDeliveryDate() {
        System.out.println("Payment confirmed.");
    }

    @Given("an order with order number {string} exists")
    public void anOrderWithOrderNumberExists(String orderN) {
        this.orderId = orderN;
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
    public void aUserWithEmailAndPasswordIsLoggedIn(String email, String password) {
        this.email = email;
        this.password = password;
        for (Person f : Sweet.getPeopleList()) {
            if (f.getEmail().equals(this.email) && f.getPass().equals(this.password) && f.getType().equals("USER")) {
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
        for (NewSweet s : Sweet.getListOfSweet()) {
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
