package com.example.rupizza;

import java.util.stream.Collectors;

/**
 *
 */
public class BuildYourOwn extends Pizza {
    public BuildYourOwn() {
        super();
    }

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
    
    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    public void removeTopping(Topping topping) {
        this.toppings.remove(topping);
    }

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
