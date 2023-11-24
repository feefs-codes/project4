package com.example.rupizza;

public enum Sauce {
    TOMATO("Tomato"),
    ALFREDO("Alfredo");

    private final String sauce;
    Sauce(String sauce) {
        this.sauce = sauce;
    }

    @Override
    public String toString() {
        return sauce;
    }
    
}
