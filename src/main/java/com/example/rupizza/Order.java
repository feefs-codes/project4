package com.example.rupizza;

import java.util.ArrayList;

public class Order {
    private int orderNumber;
    private ArrayList<Pizza> pizzas;

    private final double TAX_RATE = 0.06625;

    public Order(int orderNumber, ArrayList<Pizza> pizzas) {
        this.orderNumber = orderNumber;
        this.pizzas = pizzas;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void addToOrder(Pizza pizza) {
        pizzas.add(pizza);
    }

    public void removeFromOrder(Pizza pizza) {
        pizzas.remove(pizza);
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public double calculateSubTotal() {
        double subTotal = 0;
        for (Pizza pizza : pizzas) {
            subTotal += pizza.price();
        }
        return subTotal;
    }

    public double calculateTax() {
        return calculateSubTotal() * TAX_RATE;
    }

    public double calculateTotal() {
        return calculateSubTotal() + calculateTax();
    }

}
