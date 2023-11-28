package com.example.rupizza;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit test class for BuildYourOwn class's price(), addTopping(), removeTopping(), toString() methods.
 *
 * @author Pranay Bhatt and Fiona Wang
 */
public class BuildYourOwnTest {

    /**
     * JUnit test toppings valid case 1
     * Asserts equals when a default (small) build-your-own pizza with 3 toppings is $8.99.
     */
    @Test
    public void test3Toppings() {
        BuildYourOwn pizza = new BuildYourOwn();
        pizza.setSauce(Sauce.TOMATO);
        pizza.addTopping(Topping.HAM);
        pizza.addTopping(Topping.SAUSAGE);
        pizza.addTopping(Topping.GREEN_PEPPER);
        assertEquals(8.99, pizza.price(), 0.0);
        assertEquals(
                "Custom Pizza | SMALL | Tomato | Toppings: {Ham, Sausage, Green Pepper}",
                pizza.toString());
    }

    /**
     * JUnit test toppings valid case 2
     * Asserts equals when a default (small) build-your-own pizza with 7 toppings is $8.99 + $1.49 * 4 additional toppings.
     */
    @Test
    public void test7Toppings() {
        BuildYourOwn pizza = new BuildYourOwn();
        pizza.setSauce(Sauce.TOMATO);
        pizza.addTopping(Topping.CRAB_MEATS);
        pizza.addTopping(Topping.MUSHROOM);
        pizza.addTopping(Topping.SHRIMP);
        pizza.addTopping(Topping.SQUID);
        pizza.addTopping(Topping.BLACK_OLIVE);
        pizza.addTopping(Topping.BEEF);
        pizza.addTopping(Topping.GREEN_PEPPER);
        assertEquals(8.99 + 4 * 1.49, pizza.price(), 0.0);
        assertEquals(
                "Custom Pizza | SMALL | Tomato | Toppings: {Crab Meats, Mushroom, Shrimp, Squid, Black Olive, Beef, Green Pepper}",
                pizza.toString());
    }

    /**
     * JUnit test size valid case 1
     * Asserts equals when a build-your-own pizza set at medium size with 3 toppings is $10.99.
     */
    @Test
    public void testMediumCustomPizza() {
        BuildYourOwn pizza = new BuildYourOwn();
        pizza.setSize(Size.MEDIUM);
        pizza.setSauce(Sauce.TOMATO);
        pizza.addTopping(Topping.HAM);
        pizza.addTopping(Topping.SAUSAGE);
        pizza.addTopping(Topping.GREEN_PEPPER);
        assertEquals(10.99, pizza.price(), 0.0);
        assertEquals(
                "Custom Pizza | MEDIUM | Tomato | Toppings: {Ham, Sausage, Green Pepper}",
                pizza.toString());
    }

    /**
     * JUnit test size valid case 2
     * Asserts equals when a build-your-own pizza set at large size with 3 toppings is $12.99.
     */
    @Test
    public void testLargeCustomPizza() {
        BuildYourOwn pizza = new BuildYourOwn();
        pizza.setSize(Size.LARGE);
        pizza.setSauce(Sauce.TOMATO);
        pizza.addTopping(Topping.HAM);
        pizza.addTopping(Topping.SAUSAGE);
        pizza.addTopping(Topping.GREEN_PEPPER);
        assertEquals(12.99, pizza.price(), 0.0);
        assertEquals(
                "Custom Pizza | LARGE | Tomato | Toppings: {Ham, Sausage, Green Pepper}",
                pizza.toString());
    }

    /**
     * JUnit test extra valid case 1
     * Asserts equals when a default (small) build-your-own pizza with 3 toppings and either extra sauce or cheese is $9.99.
     */
    @Test
    public void testExtraSauceOrCheese() {
        BuildYourOwn pizza1 = new BuildYourOwn();
        pizza1.setSauce(Sauce.TOMATO);
        pizza1.addTopping(Topping.HAM);
        pizza1.addTopping(Topping.SAUSAGE);
        pizza1.addTopping(Topping.GREEN_PEPPER);
        pizza1.addExtraSauce();
        assertEquals(8.99 + 1, pizza1.price(), 0.0);
        assertEquals(
                "Custom Pizza | SMALL | Tomato | Toppings: {Ham, Sausage, Green Pepper} | Extra Sauce",
                pizza1.toString());

        BuildYourOwn pizza2 = new BuildYourOwn();
        pizza2.setSauce(Sauce.TOMATO);
        pizza2.addTopping(Topping.HAM);
        pizza2.addTopping(Topping.SAUSAGE);
        pizza2.addTopping(Topping.GREEN_PEPPER);
        pizza2.addExtraCheese();
        assertEquals(8.99 + 1, pizza2.price(), 0.0);
        assertEquals(
                "Custom Pizza | SMALL | Tomato | Toppings: {Ham, Sausage, Green Pepper} | Extra Cheese",
                pizza2.toString());
    }

    /**
     * JUnit test extra valid case 2
     * Asserts equals when a default (small) build-your-own pizza with 3 toppings and both extra sauce and cheese is $10.99.
     */
    @Test
    public void testExtraSauceAndCheese() {
        BuildYourOwn pizza = new BuildYourOwn();
        pizza.setSauce(Sauce.TOMATO);
        pizza.addTopping(Topping.HAM);
        pizza.addTopping(Topping.SAUSAGE);
        pizza.addTopping(Topping.GREEN_PEPPER);
        pizza.addExtraSauce();
        pizza.addExtraCheese();
        assertEquals(8.99 + 1 + 1, pizza.price(), 0.0);
        assertEquals(
                "Custom Pizza | SMALL | Tomato | Toppings: {Ham, Sausage, Green Pepper} | Extra Sauce | Extra Cheese",
                pizza.toString());
    }

    /**
     * JUnit test remove topping valid case
     * Tests removeTopping() directly after adding that topping, and after adding additional toppings in between.
     * Asserts equals when a default (small) build-your-own pizza with 4 toppings is $8.99 + $1.49 * 1 topping.
     */
    @Test
    public void testRemoveTopping() {
        BuildYourOwn pizza = new BuildYourOwn();
        pizza.setSauce(Sauce.TOMATO);
        pizza.addTopping(Topping.HAM);
        pizza.removeTopping(Topping.HAM);

        pizza.addTopping(Topping.SAUSAGE);
        pizza.addTopping(Topping.GREEN_PEPPER);
        pizza.addTopping(Topping.PEPPERONI);
        pizza.addTopping(Topping.SQUID);
        pizza.addTopping(Topping.BLACK_OLIVE);
        pizza.removeTopping(Topping.GREEN_PEPPER);

        assertEquals(8.99 + 1 * 1.49, pizza.price(), 0.0);
        assertEquals(
                "Custom Pizza | SMALL | Tomato | Toppings: {Sausage, Pepperoni, Squid, Black Olive}",
                pizza.toString());
    }

}
