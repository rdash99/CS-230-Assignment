package com.example.cs230assignment;

public class Key extends Entity {
    private char keyColour;

    public Key(char keyColourPass, int x, int y) {
        super("Key", x, y);
        this.keyColour = keyColourPass;
    }

    public char getKeyColour() {
        return keyColour;
    }

    public void interact() {
    }

}
