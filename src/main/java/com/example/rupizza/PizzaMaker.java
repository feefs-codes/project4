package com.example.rupizza;


public class PizzaMaker {
    public static Pizza createPizza(String pizzaType) {
        if (pizzaType == null) {
            return null;
        }

        if (pizzaType.equals("Deluxe")) {
            return new Deluxe();
        }
        else if (pizzaType.equals("Supreme")) {
            return new Supreme();
        }
        else if (pizzaType.equals("Meatzza")) {
            return new Meatzza();
        }
        else if (pizzaType.equals("Seafood")) {
            return new Seafood();
        }
        else if (pizzaType.equals("Pepperoni")) {
            return new Pepperoni();
        }
        else {
            return new BuildYourOwn();
        }

    }
}
