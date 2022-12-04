package com.example.cs230assignment;

public class Clock extends Entity {
    private int clockTime = 0;

    public Clock (String clockName, int addedTime, int xCoord, int yCoord) {
        super(entityNamePass, x, y);
        this.clockTime = addedTime;
    }

}