package com.example.cs230assignment;

/**
 * This class represents the key subclass
 * 
 * @author Rowan Dash
 * @version 1.0
 */
public class Key extends Entity {
    private char keyColour;

    /**
     * This is the constructor for the key class
     * 
     * @param keyColourPass the colour of the key
     * @param x             the x coordinate of the key
     * @param y             the y coordinate of the key
     */
    public Key(char keyColourPass, int x, int y) {
        super("Key", x, y);
        this.keyColour = keyColourPass;
    }

    /**
     * @return char
     */
    public char getKeyColour() {
        return keyColour;
    }

    /**
     * Open the gates
     */
    public void openGate() {
    }

}
