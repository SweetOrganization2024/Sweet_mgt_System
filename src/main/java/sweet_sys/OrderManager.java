package sweet_sys;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OrderManager {
    private static OrderManager instance;
    private final List<Order> orders;
    private static int nextOrderId = 1;
    private static final Logger logger = Logger.getLogger(OrderManager.class.getName());

    // Private constructor to prevent instantiation
    private OrderManager() {
        this.orders = new ArrayList<>();
    }

    // Singleton pattern to get the single instance of OrderManager
    public static synchronized OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    // Method to get the next available order ID
    public static int getNextOrderId() {
        return nextOrderId++;
    }

    // Method to get the previous order ID
    public static int getPrevOrderId() {
        return --nextOrderId;
    }

    // Get the list of orders
    public List<Order> getOrders() {
        return orders;
    }

    // Method to add an order
    public void addOrder(String orderId, LocalDate orderDate, double totalCost) {
        // Check if the order ID is valid and not empty
        if (orderId == null || orderId.isEmpty()) {
            logger.warning("Order ID is not provided.");
            throw new IllegalArgumentException("Order ID must be provided.");
        }

        // Check if the order already exists
        if (orders.stream().anyMatch(order -> order.getOrderId().equals(orderId))) {
            logger.warning(String.format("Order with ID %s already exists.", orderId));
            throw new IllegalArgumentException("Order with this ID already exists.");
        }

        // Check if the order date is valid
        if (orderDate == null || orderDate.isAfter(LocalDate.now())) {
            logger.warning("Order date is not valid.");
            throw new IllegalArgumentException("Order date must be today or earlier.");
        }

        // Check if the total cost is positive
        if (totalCost <= 0) {
            logger.warning("Total cost must be positive.");
            throw new IllegalArgumentException("Total cost must be greater than zero.");
        }

        // Create and add the new order if all checks pass
        Order newOrder = new Order(orderId, orderDate, totalCost);
        orders.add(newOrder);
        logger.info(String.format("Order added: %s", newOrder.getOrderId()));
    }

    // Method to get the most recent order
    public Order getCurrentOrder() {
        if (orders.isEmpty()) {
            logger.info("No orders found.");
            return null;
        }
        return orders.get(orders.size() - 1);
    }

    // Method to cancel an order
    public void cancelOrder(String orderId) {
        if (orders.isEmpty()) {
            logger.warning("No orders available to cancel.");
            return;
        }
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                if (!"cancelled".equals(order.status)) {
                    order.setStatus("cancelled");
                    logger.info(String.format("Order %s has been cancelled.", orderId));
                } else {
                    logger.warning(String.format("Order %s is already cancelled.", orderId));
                }
                return;
            }
        }
        logger.warning(String.format("Order %s not found for cancellation.", orderId));
    }

    // Method to display past orders
    public void displayPastOrders() {
        if (orders.isEmpty()) {
            logger.info("No past orders to display.");
            return;
        }
        for (Order order : orders) {
            logger.info(String.format("Order ID: %s, Date: %s, Total Cost: %.2f, Status: %s",
                    order.getOrderId(), order.getOrderDate(), order.getTotalCost(), order.status));
        }
    }

    // Inner class for Order
    public static class Order {
        private final String orderId;
        private final LocalDate orderDate;
        private final double totalCost;
        private String status;

        public Order(String orderId, LocalDate orderDate, double totalCost) {
            this.orderId = orderId;
            this.orderDate = orderDate;
            this.totalCost = totalCost;
            this.status = "active";
        }

        public String getOrderId() {
            return orderId;
        }

        public LocalDate getOrderDate() {
            return orderDate;
        }

        public double getTotalCost() {
            return totalCost;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
