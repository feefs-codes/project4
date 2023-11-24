package com.example.rupizza;

public class Meatzza extends Pizza {
    public Meatzza() {
        super();
        this.sauce = Sauce.TOMATO;
        toppings.add(Topping.SAUSAGE);
        toppings.add(Topping.PEPPERONI);
        toppings.add(Topping.BEEF);
        toppings.add(Topping.HAM);
    }

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
