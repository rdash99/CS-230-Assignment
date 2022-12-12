package com.example.cs230assignment;

import java.util.ArrayList;

/**
 * This class represents the key subclass
 * 
 * @author Rowan Dash
 * @version 1.0
 */
public class Key extends Entity {
    private char keyColour;
    private ArrayList<Gate> gates;

    /**
     * This is the constructor for the key class
     * 
     * @param keyColourPass the colour of the key
     * @param x             the x coordinate of the key
     * @param y             the y coordinate of the key
     */
    public Key(char keyColourPass, int x, int y, ArrayList<Gate> gates) {
        super("Key", x, y);
        this.gates = gates;
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
        System.out.println("opengate");
        for (Gate gate : gates) {
            gate.setGateOpen(true);
        }
    }

}
