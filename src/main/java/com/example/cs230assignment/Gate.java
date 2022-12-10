package com.example.cs230assignment;

/**
 * Describes a class to create a gate.
 * 
 * @author Rowan Dash
 * @version 1.0
 */

public class Gate extends Entity {
    private int gateID;
    private char gateColour;
    private boolean gateOpen;

    /**
     * This is the constructor for the gate class
     * 
     * @param gateColourPass the colour of the gate
     * @param x              the x coordinate of the gate
     * @param y              the y coordinate of the gate
     */
    public Gate(char gateColourPass, int x, int y) {
        super("Gate", x, y);
        // this.gateID = gateIDPass;
        this.gateColour = gateColourPass;
        this.gateOpen = false;
    }

    /**
     * @return int
     */
    public int getGateID() {
        return gateID;
    }

    /**
     * @return char
     */
    public char getGateColour() {
        return this.gateColour;
    }

    /**
     * @return boolean
     */
    public boolean getGateOpen() {
        return gateOpen;
    }

    /**
     * @param gateOpenPass
     */
    public void setGateOpen(boolean gateOpenPass) {
        this.gateOpen = gateOpenPass;
    }

}