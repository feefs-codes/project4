package com.example.rupizza;

/**
 * Topping enum class represents the 13 topping options.
 *
 * @author Pranay Bhatt and Fiona Wang
 */
public enum Topping {
    SAUSAGE("Sausage"),
    PEPPERONI("Pepperoni"),
    GREEN_PEPPER("Green Pepper"),
    ONION("Onion"),
    MUSHROOM("Mushroom"),
    HAM("Ham"),
    BLACK_OLIVE("Black Olive"),
    BEEF("Beef"),
    SHRIMP("Shrimp"),
    SQUID("Squid"),
    CRAB_MEATS("Crab Meats"),
    JALAPENO("Jalapeno"),
    PINEAPPLE("Pineapple");

    private String name;

    /**
     * Constructs a topping object.
     * @param name the topping name
     */
    Topping(String name) {
        this.name = name;
    }

    /**
     * Returns topping string
     * @return topping string
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Gets the topping object.
     * @param toppingName
     * @return topping object
     */
    public static Topping getTopping(String toppingName) {
        for (Topping topping : Topping.values()) {
            if (topping.name.equals(toppingName)) {
                return topping;
            }
        }
        return null;
    }

}
