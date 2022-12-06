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

    public Gate(char gateColourPass, int x, int y) {
        super("Gate", x, y);
        // this.gateID = gateIDPass;
        this.gateColour = gateColourPass;
        this.gateOpen = false;
    }

    public int getGateID() {
        return gateID;
    }

    public char getGateColour() {
        return this.gateColour;
    }

    public boolean getGateOpen() {
        return gateOpen;
    }

    public void setGateOpen(boolean gateOpenPass) {
        this.gateOpen = gateOpenPass;
    }

}