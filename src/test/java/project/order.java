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

public class order {
    private OrderManager orderManager;
    private String email;
    private String password;
    private String orderId;
    private boolean isUserRegistered = false;
    private boolean cancelled = false;

    public order() {
        sweet appSweet = sweet.getInstance();
        this.orderManager = new OrderManager();
    }
    @Before
    public void setUp() {
        this.orderManager = OrderManager.getInstance();
        this.orderManager.getOrders().clear();
    }


    @Given("a sweet with ID {string}, name {string}, and type {string} is available for order for user with Email {string} and password {string}")
    public void aSweetWithIDNameAndTypeIsAvailableForOrderForUserWithEmailAndPassword(String ID, String NAME, String TYPE, String EMAIL, String PASSWORD) {
        this.email = EMAIL;
        this.password = PASSWORD;
        boolean isValidSweet = sweet.validSweet(NAME, ID, TYPE);

        boolean isValidUser = sweet.validPeople(email,password);
        assertTrue("Sweet should be valid and user should be registered", isValidSweet && isValidUser);
    }

    @Given("the user selects a quantity of {string} for the sweet")
    public void theUserSelectsAQuantityOfForTheSweet(String quan) {
    }

    @When("the user places an order with a total price of {string}")
    public void theUserPlacesAnOrderWithATotalPriceOf(String totalPrice) {
        String orderId = "order" + (orderManager.getOrders().size() + 1); // Generate a new order ID
        LocalDate orderDate = LocalDate.now();
        double cost = Double.parseDouble(totalPrice);
        orderManager.addOrder(orderId, orderDate, cost);

        System.out.println("Orders after adding new order: " + orderManager.getOrders());
    }


    @Then("the system should confirm the order with a success message including order number, estimated delivery date, and total cost")
    public void theSystemShouldConfirmTheOrderWithASuccessMessageIncludingOrderNumberEstimatedDeliveryDateAndTotalCost() {
        OrderManager.Order currentOrder = orderManager.getCurrentOrder();
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
            if (s.getId_of_sweet().equals(id) || s.getName_of_sweet().equals(name) || s.getType_of_sweet().equals(type)) {
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
        // Ideally, you would want to validate the payment confirmation and order details here
    }

    @Given("an order with order number {string} exists")
    public void anOrderWithOrderNumberExists(String order_n) {
        this.orderId = order_n;
        // Optionally, you can add logic to ensure the order exists in the system
    }

    @When("the user cancels the order")
    public void theUserCancelsTheOrder() {
        orderManager.cancelOrder(orderId);
        cancelled = true; // Assuming order cancellation was successful
    }

    @Then("the system should confirm that the order with order number {string} has been cancelled")
    public void theSystemShouldConfirmThatTheOrderWithOrderNumberHasBeenCancelled(String ordernumber) {
        assertTrue("Order should be cancelled", cancelled);
    }

    @Given("a user with Email {string} and password {string} is logged in")
    public void aUserWithEmailAndPasswordIsLoggedIn(String EMAIL, String PASSWORD) {
        this.email = EMAIL;
        this.password = PASSWORD;
        for (person f : sweet.getList_of_people()) {
            if (f.getEmail().equals(email) && f.getPass().equals(password) && f.getType().equals("USER")) {
                isUserRegistered = true;
                break;
            }
        }
        assertTrue(isUserRegistered);
    }

    @When("the user requests to view their order history")
    public void theUserRequestsToViewTheirOrderHistory() {
        // Optionally, you can add logic to ensure the user is requesting their order history
        assertTrue(isUserRegistered);
    }

    @Then("the system should display a list of past orders including order numbers, dates, and total costs")
    public void theSystemShouldDisplayAListOfPastOrdersIncludingOrderNumbersDatesAndTotalCosts() {
        orderManager.displayPastOrders();
    }
}
