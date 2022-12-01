package com.example.cs230assignment;

/**
 * Describes a class to create an instance of a FloorFollowingThief.
 * 
 * @author Rowan Dash
 * @version 1.0
 */
class FloorFollowingThief extends NPC {
    private char allocatedColour;

    public FloorFollowingThief(char allocatedColourPass, int x, int y) {
        // Allocated a timer of 0.5 seconds for the thief
        super("Floor Following Thief", 0.5, x, y);
        this.allocatedColour = allocatedColourPass;
    }

    private void interact() {

    }

    private void validMove() {

    }
}
