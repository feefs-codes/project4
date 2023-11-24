package com.example.rupizza;

import java.util.ArrayList;

public abstract class Pizza {
    protected ArrayList<Topping> toppings; //Topping is an enum class
    protected Size size; //Size is an enum class
    protected Sauce sauce; //Sauce is an enum class
    protected boolean extraSauce;
    protected boolean extraCheese;

    public Pizza() {
        this.toppings = new ArrayList<>();
        this.size = Size.SMALL;
        this.sauce = null;
        this.extraSauce = false;
        this.extraCheese = false;
    }

    public abstract double price(); //polymorphism

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }
    public void setSize(Size size) {
        this.size = size;
    }
    public void addExtraSauce() {
        this.extraSauce = true;
    }
    public void addExtraCheese() {
        this.extraCheese = true;
    }
    public void setSauce(Sauce sauce) {
        this.sauce = sauce;
    }

    public String getSauce() {
        return sauce.toString();
    }
    public String getToppings() {
        String result = "";
        for (Topping topping : toppings) {
            result += topping.toString() + "\n";
        }
        return result;
    }

}
