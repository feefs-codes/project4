package com.example.rupizza;

/**
 * Sauce enum class represents tomato and alfredo sauce options.
 *
 * @author Pranay Bhatt and Fiona Wang
 */
public enum Sauce {
    TOMATO("Tomato"),
    ALFREDO("Alfredo");

    /**
     * Constructs a sauce object.
     * @param sauce the sauce
     */
    private final String sauce;
    Sauce(String sauce) {
        this.sauce = sauce;
    }

    /**
     * Returns sauce string
     * @return sauce string
     */
    @Override
    public String toString() {
        return sauce;
    }
    
}
