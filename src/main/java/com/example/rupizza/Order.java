package com.example.rupizza;

import java.util.ArrayList;

/**
 * Order class represents an order as an object with an order number and list of pizzas.
 *
 * @author Pranay Bhatt and Fiona Wang
 */
public class Order {
    private int orderNumber;
    private ArrayList<Pizza> pizzas;

    private final double TAX_RATE = 0.06625;

    /**
     * Default Order constructor;
     * constructs an Order object with the given order number and list of pizzas.
     * @param orderNumber order number
     * @param pizzas list of pizzas
     */
    public Order(int orderNumber, ArrayList<Pizza> pizzas) {
        this.orderNumber = orderNumber;
        this.pizzas = pizzas;
    }

    /**
     * Returns order number
     * @return order number
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Adds pizza to order.
     * @param pizza pizza
     */
    public void addToOrder(Pizza pizza) {
        pizzas.add(pizza);
    }

    /**
     * Removes pizza from order.
     * @param pizza pizza
     */
    public void removeFromOrder(Pizza pizza) {
        pizzas.remove(pizza);
    }

    /**
     * Returns order's pizza list
     * @return pizza list
     */
    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * Calculates order sub total.
     * @return subtotal for order
     */
    public double calculateSubTotal() {
        double subTotal = 0;
        for (Pizza pizza : pizzas) {
            subTotal += pizza.price();
        }
        return subTotal;
    }

    /**
     * Calculates tax.
     * @return sub total tax.
     */
    public double calculateTax() {
        return calculateSubTotal() * TAX_RATE;
    }

    /**
     * Calculates order total.
     * @return subtotal plus tax
     */
    public double calculateTotal() {
        return calculateSubTotal() + calculateTax();
    }

}
