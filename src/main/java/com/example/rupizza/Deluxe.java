package com.example.rupizza;

/**
 * Deluxe class inherits from Pizza class and represents a deluxe specialty pizza.
 *
 * @author Pranay Bhatt and Fiona Wang
 */
public class Deluxe extends Pizza {

    Sauce deluxeSauce = Sauce.TOMATO;

    /**
     * Default Deluxe constructor;
     * constructs the deluxe pizza object and adds necessary toppings.
     */
    public Deluxe() {
        super();
        this.sauce = Sauce.TOMATO;
        toppings.add(Topping.SAUSAGE);
        toppings.add(Topping.PEPPERONI);
        toppings.add(Topping.GREEN_PEPPER);
        toppings.add(Topping.ONION);
        toppings.add(Topping.MUSHROOM);
    }

    /**
     * Returns the price of pizza based on user selection.
     * @return price
     */
    @Override
    public double price() {
        double price = switch (size) {
            case SMALL -> 14.99;
            case MEDIUM -> 16.99;
            case LARGE -> 18.99;
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
     * Returns a string representation of deluxe pizza.
     * @return a string representation of deluxe pizza.
     */
    @Override
    public String toString() {
        String str = "Deluxe Pizza | " + size.toString();
        if (extraSauce) {
            str += " | Extra Sauce";
        }
        if (extraCheese) {
            str += " | Extra Cheese";
        }
        return str;
    }
}
