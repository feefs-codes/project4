package com.example.rupizza;

import java.io.PrintWriter;
import java.util.ArrayList;

public class StoreOrders {

    private static StoreOrders instance = null;

    private int orderNumber = 1;
    private ArrayList<Order> orders;

    public StoreOrders() {
        orders = new ArrayList<>();
    }

    public static StoreOrders getInstance() {
        if (instance == null) {
            instance = new StoreOrders();
        }
        return instance;
    }

    public void startNewOrder() {
        Order order = new Order(orderNumber++, new ArrayList<>());
        orders.add(order);
    }

    public Order getCurrentOrder() {
        if (orders.isEmpty()) {
            return null;
        }
        return orders.get(orders.size() - 1);
    }

    public Order getOrder(int orderNumber) {
        for (Order order : orders) {
            if (order.getOrderNumber() == orderNumber) {
                return order;
            }
        }
        return null;
    }

    public ArrayList<Order> getAllOrders() {
        return orders;
    }

    public void removeFromOrders(Order order) {
        orders.remove(order);
    }

    public void exportOrders() {
        // export store orders to txt file
        try (PrintWriter writer = new PrintWriter("store_orders.txt")) {
            for (Order order : orders) {
                writer.println("Order Number: " + order.getOrderNumber());
                writer.println("Pizzas:");
                for (Pizza pizza : order.getPizzas()) {
                    writer.println(pizza.toString());
                }
                writer.println("Subtotal: $" + order.calculateSubTotal());
                writer.println("Tax: $" + order.calculateTax());
                writer.println("Total: $" + order.calculateTotal());
                writer.println();
            }
        } catch (Exception e) {
            System.out.println("Error exporting store orders");
        }
    }
}
