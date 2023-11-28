package com.example.rupizza;

/**
 * Meatzza class inherits from Pizza class and represents a meatzza specialty pizza.
 *
 * @author Pranay Bhatt and Fiona Wang
 */
public class Meatzza extends Pizza {

    /**
     * Default Meatzza constructor;
     * constructs the meatzza pizza object and adds necessary toppings.
     */
    public Meatzza() {
        super();
        this.sauce = Sauce.TOMATO;
        toppings.add(Topping.SAUSAGE);
        toppings.add(Topping.PEPPERONI);
        toppings.add(Topping.BEEF);
        toppings.add(Topping.HAM);
    }

    /**
     * Returns the price of pizza based on user selection.
     * @return price
     */
    @Override
    public double price() {
        double price = switch (size) {
            case SMALL -> 16.99;
            case MEDIUM -> 18.99;
            case LARGE -> 20.99;
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
     * Returns a string representation of meatzza pizza.
     * @return a string representation of meatzza pizza.
     */
    @Override
    public String toString() {
        String str = "Meatzza Pizza | " + size.toString();
        if (extraSauce) {
            str += " | Extra Sauce";
        }
        if (extraCheese) {
            str += " | Extra Cheese";
        }
        return str;
    }
}
