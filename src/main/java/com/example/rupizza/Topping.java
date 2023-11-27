package com.example.rupizza;

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
    Topping(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Topping getTopping(String toppingName) {
        for (Topping topping : Topping.values()) {
            if (topping.name.equals(toppingName)) {
                return topping;
            }
        }
        return null;
    }

}
