package sweetSys;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderManager {
    private static OrderManager instance;
    private final List<Order> orders;
    private static int nextOrderId = 1;
    private static final Logger logger = Logger.getLogger(OrderManager.class.getName());

    private OrderManager() {
        this.orders = new ArrayList<>();
    }

    public static synchronized OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    public static int getNextOrderId() {
        return nextOrderId++;
    }

    public static int getPrevOrderId() {
        return --nextOrderId;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(String orderId, LocalDate orderDate, double totalCost) {
        Order newOrder = new Order(orderId, orderDate, totalCost);
        orders.add(newOrder);
        logger.info(String.format("Order added: %s", newOrder.getOrderId()));
    }

    public Order getCurrentOrder() {
        if (orders.isEmpty()) {
            logger.info("No orders found.");
            logger.info("Orders List: " + orders);
            return null;
        }
        return orders.get(orders.size() - 1);
    }

    public void cancelOrder(String orderId) {
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                order.setStatus("cancelled");
                logger.info(String.format("Order %s has been cancelled.", orderId));
                return;
            }
        }
        logger.warning(String.format("Order %s not found for cancellation.", orderId));
    }

    public void displayPastOrders() {
        if (orders.isEmpty()) {
            logger.info("No past orders to display.");
            return;
        }
        for (Order order : orders) {
            logger.info(String.format("Order ID: %s, Date: %s, Total Cost: %.2f",
                    order.getOrderId(), order.getOrderDate(), order.getTotalCost()));
        }
    }

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

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
