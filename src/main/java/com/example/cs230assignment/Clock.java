package com.example.cs230assignment;

/**
 * This class represents the clock subclass
 * 
 * @author Rowan Dash
 * @version 1.0
 */

public class Clock extends Entity {
    private int time;

    /**
     * This is the constructor for the clock class
     * 
     * @param name the name of the clock
     * @param x    the x coordinate of the clock
     * @param y    the y coordinate of the clock
     * @param time the time the clock adds
     */
    public Clock(int x, int y, int time) {
        super("Clock", x, y);
        this.time = time;
    }

    /**
     * @return int
     */
    public int gettime() {
        return time;
    }

    /**
     * @param time
     */
    public void settime(int time) {
        this.time = time;
    }

    /**
     * @deprecated
     */
    public void tick() {
        time--;
    }
}