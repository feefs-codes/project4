package com.example.rupizza;

import java.util.ArrayList;

/**
 * Pizza class represents a pizza as an object with a size, sauce, and toppings list.
 * It is the general type of the other pizza types.
 *
 * @author Pranay Bhatt and Fiona Wang
 */
public abstract class Pizza {
    protected ArrayList<Topping> toppings; //Topping is an enum class
    protected Size size; //Size is an enum class
    protected Sauce sauce; //Sauce is an enum class
    protected boolean extraSauce;
    protected boolean extraCheese;

    /**
     * Default Pizza constructor.
     * Sets pizza to empty toppings list, size small, no sauce, no extra sauce or cheese.
     */
    public Pizza() {
        this.toppings = new ArrayList<>();
        this.size = Size.SMALL;
        this.sauce = null;
        this.extraSauce = false;
        this.extraCheese = false;
    }

    public abstract double price(); //polymorphism

    /**
     * Adds topping to pizza.
     * @param topping topping
     */
    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    /**
     * Sets size of pizza.
     * @param size size
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Adds extra sauce.
     */
    public void addExtraSauce() {
        this.extraSauce = true;
    }

    /**
     * Adds extra cheese.
     */
    public void addExtraCheese() {
        this.extraCheese = true;
    }

    /**
     * Sets sauce of pizza.
     * @param sauce sauce
     */
    public void setSauce(Sauce sauce) {
        this.sauce = sauce;
    }


    /**
     * Returns sauce of pizza.
     * @return sauce string
     */
    public String getSauce() {
        return sauce.toString();
    }

    /**
     * Returns toppings of pizza.
     * @return toppings string
     */
    public String getToppings() {
        String result = "";
        for (Topping topping : toppings) {
            result += topping.toString() + "\n";
        }
        return result;
    }

}
