package com.example.rupizza;

public class Pepperoni extends Pizza {
    public Pepperoni() {
        super();
        this.sauce = Sauce.TOMATO;
        toppings.add(Topping.PEPPERONI);
    }

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
