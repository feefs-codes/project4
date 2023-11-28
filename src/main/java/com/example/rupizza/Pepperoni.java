package com.example.rupizza;

/**
 * Pepperoni class inherits from Pizza class and represents a pepperoni specialty pizza.
 *
 * @author Pranay Bhatt and Fiona Wang
 */
public class Pepperoni extends Pizza {

    /**
     * Default Pepperoni constructor;
     * constructs the pepperoni pizza object and adds necessary toppings.
     */
    public Pepperoni() {
        super();
        this.sauce = Sauce.TOMATO;
        toppings.add(Topping.PEPPERONI);
    }

    /**
     * Returns the price of pizza based on user selection.
     * @return price
     */
    @Override
    public double price() {
        double price = switch (size) {
            case SMALL -> 10.99;
            case MEDIUM -> 12.99;
            case LARGE -> 14.99;
        };
        if (extraSauce) {
            price += 1;
        }
        if (extraCheese) {
            price += 1;
        }
        return price;
    }

    /**
     * Returns a string representation of pepperoni pizza.
     * @return a string representation of pepperoni pizza.
     */
    @Override
    public String toString() {
        String str = "Pepperoni Pizza | " + size.toString();
        if (extraSauce) {
            str += " | Extra Sauce";
        }
        if (extraCheese) {
            str += " | Extra Cheese";
        }
        return str;
    }
}
