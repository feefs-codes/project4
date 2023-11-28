package com.example.rupizza;

/**
 * Supreme class inherits from Pizza class and represents a supreme specialty pizza.
 *
 * @author Pranay Bhatt and Fiona Wang
 */
public class Supreme extends Pizza {

    /**
     * Default Supreme constructor;
     * constructs the supreme pizza object and adds necessary toppings.
     */
    public Supreme() {
        super();
        this.sauce = Sauce.TOMATO;
        toppings.add(Topping.SAUSAGE);
        toppings.add(Topping.PEPPERONI);
        toppings.add(Topping.HAM);
        toppings.add(Topping.GREEN_PEPPER);
        toppings.add(Topping.ONION);
        toppings.add(Topping.BLACK_OLIVE);
        toppings.add(Topping.MUSHROOM);
    }

    /**
     * Returns the price of pizza based on user selection.
     * @return price
     */
    @Override
    public double price() {
        double price = switch (size) {
            case SMALL -> 15.99;
            case MEDIUM -> 17.99;
            case LARGE -> 19.99;
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
     * Returns a string representation of supreme pizza.
     * @return a string representation of supreme pizza.
     */
    @Override
    public String toString() {
        String str = "Supreme Pizza | " + size.toString();
        if (extraSauce) {
            str += " | Extra Sauce";
        }
        if (extraCheese) {
            str += " | Extra Cheese";
        }
        return str;
    }
}
