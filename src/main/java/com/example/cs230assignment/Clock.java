package com.example.cs230assignment;

public class Clock extends Entity {
    private int clockTime = 0;

    public Clock (String entityNamePass, int addedTime, int x, int y) {
        super(entityNamePass, x, y);
        this.clockTime = addedTime;
    }

    public void setClockTime (int additionalTime) {
        this.clockTime = additionalTime;
    }

    public void getClockTime () {
        return clockTime;
    }
    
    public void addTime (int additionalTime) {

    }
}