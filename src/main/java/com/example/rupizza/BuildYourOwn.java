package com.example.rupizza;

import java.util.stream.Collectors;


/**
 * BuildYourOwn class inherits from Pizza class and represents a user-customized pizza.
 *
 * @author Pranay Bhatt and Fiona Wang
 */

public class BuildYourOwn extends Pizza {

    /**
     * Default BuildYourOwn constructor;
     * constructs a default Pizza object.
     */
    public BuildYourOwn() {
        super();
    }

    /**
     * Returns the customized pizza price.
     * @return pizza's price
     */
    @Override
    public double price() {
        double price = switch (size) {
            case SMALL -> 8.99;
            case MEDIUM -> 10.99;
            case LARGE -> 12.99;
        };
        if (extraSauce) {
            price += 1;
        }
        if (extraCheese) {
            price += 1;
        }
        if (toppings.size() >= 3 && toppings.size() <= 7) {
            price += (toppings.size() - 3) * 1.49;
        }
        return price;
    }

    /**
     * Adds a topping to the pizza's topping list.
     * @param topping to add
     */
    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    /**
     * Removes a topping from the pizza's topping list.
     * @param topping to remove
     */
    public void removeTopping(Topping topping) {
        this.toppings.remove(topping);
    }

    /**
     * Returns a string representation of the customized pizza.
     * @return a string representation of the customized pizza
     */
    @Override
    public String toString() {
        String toppingsString = toppings.stream()
                                    .map(Topping::toString)
                                    .collect(Collectors.joining(", "));
        String str = "Custom Pizza | " + size.toString() + 
        " | " + getSauce() + " | Toppings: {" + toppingsString + "}";
        if (extraSauce) {
            str += " | Extra Sauce";
        }
        if (extraCheese) {
            str += " | Extra Cheese";
        }
        return str;
    }
    
}
