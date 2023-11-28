package com.example.rupizza;

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * StoreOrders class stores Order objects in an array,
 * and provides methods to start new orders, remove orders, and export all orders.
 *
 * @author Pranay Bhatt and Fiona Wang
 */
public class StoreOrders {

    private static StoreOrders instance = null;

    private static int orderNumber = 1;
    private ArrayList<Order> orders;

    /**
     * Default constructor: constructs a new array list for orders.
     */
    public StoreOrders() {
        orders = new ArrayList<>();
    }

    /**
     * Returns instance of StoreOrders
     * @return StoreOrders instance
     */
    public static StoreOrders getInstance() {
        if (instance == null) {
            instance = new StoreOrders();
        }
        return instance;
    }

    /**
     * Starts new Order.
     */
    public void startNewOrder() {
        Order order = new Order(orderNumber++, new ArrayList<>());
        orders.add(order);
    }

    /**
     * Returns current order
     * @return current order object
     */
    public Order getCurrentOrder() {
        if (orders.isEmpty()) {
            return null;
        }
        return orders.get(orders.size() - 1);
    }

    /**
     * Returns order
     * @param orderNumber order number integer
     * @return order object to return
     */
    public Order getOrder(int orderNumber) {
        for (Order order : orders) {
            if (order.getOrderNumber() == orderNumber) {
                return order;
            }
        }
        return null;
    }

    /**
     * Returns all orders.
     * @return array list of all orders
     */
    public ArrayList<Order> getAllOrders() {
        return orders;
    }

    /**
     * Removes order from list of orders.
     * @param order order object to remove
     */
    public void removeFromOrders(Order order) {
        orders.remove(order);
    }

    /**
     * Exports Orders to txt file.
     */
    public void exportOrders() {
        try (PrintWriter writer = new PrintWriter("store_orders.txt")) {
            for (Order order : orders) {
                writer.println("Order Number: " + order.getOrderNumber());
                writer.println("Pizzas:");
                for (Pizza pizza : order.getPizzas()) {
                    writer.println(pizza.toString());
                }
                writer.println("Subtotal: " + String.format("$%.2f", order.calculateSubTotal()));
                writer.println("Tax: " + String.format("$%.2f", order.calculateTax()));
                writer.println("Total: " + String.format("$%.2f", order.calculateTotal()));
                writer.println();
            }
        } catch (Exception e) {
            System.out.println("Error exporting store orders");
        }
    }
}
