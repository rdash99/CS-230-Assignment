package com.example.cs230assignment;

/**
 * @author Maxwell
 */
public class Lever extends Entity {
    private char leverColour;

    /**
     * This is the constructor for the lever class
     * 
     * @param entityNamePass the name of the lever
     * @param x              the x coordinate of the lever
     * @param y              the y coordinate of the lever
     */
    public Lever(String entityNamePass, int x, int y) {
        super(entityNamePass, x, y);
    }

    /**
     * @return char
     */
    public char getleverColour() {
        return leverColour;
    }

    /**
     * @param newColour the colour of the lever to be set
     */
    public void setleverColour(char newColour) {
        this.leverColour = newColour;
    }
}