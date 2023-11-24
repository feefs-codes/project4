package com.example.rupizza;

public class Deluxe extends Pizza {

    Sauce deluxeSauce = Sauce.TOMATO;
    public Deluxe() {
        super();
        this.sauce = Sauce.TOMATO;
        toppings.add(Topping.SAUSAGE);
        toppings.add(Topping.PEPPERONI);
        toppings.add(Topping.GREEN_PEPPER);
        toppings.add(Topping.ONION);
        toppings.add(Topping.MUSHROOM);
    }

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
