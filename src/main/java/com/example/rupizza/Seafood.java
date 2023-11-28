package com.example.rupizza;

/**
 * Seafood class inherits from Pizza class and represents a seafood specialty pizza.
 *
 * @author Pranay Bhatt and Fiona Wang
 */
public class Seafood extends Pizza {

    /**
     * Default Seafood constructor;
     * constructs the seafood pizza object and adds necessary toppings.
     */
    public Seafood() {
        super();
        this.sauce = Sauce.ALFREDO;
        toppings.add(Topping.SHRIMP);
        toppings.add(Topping.SQUID);
        toppings.add(Topping.CRAB_MEATS);
    }

    /**
     * Returns the price of pizza based on user selection.
     * @return price
     */
    @Override
    public double price() {
        double price = switch (size) {
            case SMALL -> 17.99;
            case MEDIUM -> 19.99;
            case LARGE -> 21.99;
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
     * Returns a string representation of seafood pizza.
     * @return a string representation of seafood pizza.
     */
    @Override
    public String toString() {
        String str = "Seafood Pizza | " + size.toString();
        if (extraSauce) {
            str += " | Extra Sauce";
        }
        if (extraCheese) {
            str += " | Extra Cheese";
        }
        return str;
    }
}
