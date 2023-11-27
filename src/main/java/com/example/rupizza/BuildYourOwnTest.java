package com.example.rupizza;

import org.junit.Test;
import static org.junit.Assert.*;

public class BuildYourOwnTest {

    @Test
    public void test3Toppings() {
        Pizza pizza = new BuildYourOwn();
        pizza.setSauce(Sauce.TOMATO);
        pizza.addTopping(Topping.HAM);
        pizza.addTopping(Topping.SAUSAGE);
        pizza.addTopping(Topping.GREEN_PEPPER);
        assertEquals(8.99, pizza.price(), 0.0);
        assertEquals(
                "Custom Pizza | SMALL | Tomato | Toppings: {Ham, Sausage, Green Pepper}",
                pizza.toString());
    }

    @Test
    public void test7Toppings() {
        Pizza pizza = new BuildYourOwn();
        pizza.setSauce(Sauce.ALFREDO);
        pizza.addTopping(Topping.CRAB_MEATS);
        pizza.addTopping(Topping.MUSHROOM);
        pizza.addTopping(Topping.SHRIMP);
        pizza.addTopping(Topping.SQUID);
        pizza.addTopping(Topping.BLACK_OLIVE);
        pizza.addTopping(Topping.BEEF);
        pizza.addTopping(Topping.GREEN_PEPPER);
        assertEquals(8.99 + 4 * 1.49, pizza.price(), 0.0);
        assertEquals(
                "Custom Pizza | SMALL | Alfredo | Toppings: {Crab Meats, Mushroom, Shrimp, Squid, Black Olive, Beef, Green Pepper}",
                pizza.toString());
    }

    @Test
    public void testLessThan3Toppings() {
        Pizza pizza = new BuildYourOwn();
        pizza.addTopping(Topping.HAM);
        pizza.addTopping(Topping.SAUSAGE);
        pizza.addExtraSauce();
        pizza.addExtraCheese();
        assertFalse(pizza.price() > 0);
    }

    @Test
    public void testMoreThan7Toppings() {
        Pizza pizza = new BuildYourOwn();
        pizza.addTopping(Topping.CRAB_MEATS);
        pizza.addTopping(Topping.MUSHROOM);
        pizza.addTopping(Topping.SHRIMP);
        pizza.addTopping(Topping.SQUID);
        pizza.addTopping(Topping.BLACK_OLIVE);
        pizza.addTopping(Topping.BEEF);
        pizza.addTopping(Topping.GREEN_PEPPER);
        pizza.addTopping(Topping.SAUSAGE);
        assertFalse(pizza.price() > 0);
    }

    @Test
    public void testMediumCustomPizza() {
        Pizza pizza = new BuildYourOwn();
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

    @Test
    public void testLargeCustomPizza() {
        Pizza pizza = new BuildYourOwn();
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

    @Test
    public void testExtraSauceOrCheese() {
        Pizza pizza1 = new BuildYourOwn();
        pizza1.setSauce(Sauce.TOMATO);
        pizza1.addTopping(Topping.HAM);
        pizza1.addTopping(Topping.SAUSAGE);
        pizza1.addTopping(Topping.GREEN_PEPPER);
        pizza1.addExtraSauce();
        assertEquals(8.99 + 1, pizza1.price(), 0.0);
        assertEquals(
                "Custom Pizza | SMALL | Tomato | Toppings: {Ham, Sausage, Green Pepper} | Extra Sauce",
                pizza1.toString());

        Pizza pizza2 = new BuildYourOwn();
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

    @Test
    public void testExtraSauceAndCheese() {
        Pizza pizza = new BuildYourOwn();
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
