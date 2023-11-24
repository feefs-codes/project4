package com.example.rupizza;

public class Seafood extends Pizza {

    public Seafood() {
        super();
        this.sauce = Sauce.ALFREDO;
        toppings.add(Topping.SHRIMP);
        toppings.add(Topping.SQUID);
        toppings.add(Topping.CRAB_MEATS);
    }

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
