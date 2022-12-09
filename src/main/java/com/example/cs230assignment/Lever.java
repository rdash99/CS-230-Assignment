package com.example.cs230assignment;

public class Lever extends Entity {
    private char leverColour;

    public Lever(String entityNamePass, int x, int y) {
        super(entityNamePass, x, y);
    }

    public char getleverColour() {return leverColour; }

    public void setleverColour(char newColour) {this.leverColour = newColour; }
}