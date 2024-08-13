package sweetSys;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private static OrderManager instance; // Singleton instance
    private List<Order> orders; // Non-static list
    private static int nextOrderId = 1;

    public OrderManager() {
        this.orders = new ArrayList<>();
    }

    // Static method to provide global access to the instance
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
        return nextOrderId--;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(String orderId, LocalDate orderDate, double totalCost) {
        Order newOrder = new Order(orderId, orderDate, totalCost);
        orders.add(newOrder);
        System.out.println("Order added: " + newOrder.getOrderId());
        //System.out.println("Orders List: " + orders);
    }

    public Order getCurrentOrder() {
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            System.out.println("Orders List: " + orders);
            return null;
        }
        return orders.get(orders.size() - 1); // Return the last added order
    }

    public void cancelOrder(String orderId) {
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                order.setStatus("cancelled");

                return;
            }
        }

    }

    public void displayPastOrders() {
        for (Order order : orders) {
            System.out.println("Order ID: " + order.getOrderId() +
                    ", Date: " + order.getOrderDate() +
                    ", Total Cost: " + order.getTotalCost());
        }
    }

    public static class Order {
        private String orderId;
        private LocalDate orderDate;
        private double totalCost;
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
