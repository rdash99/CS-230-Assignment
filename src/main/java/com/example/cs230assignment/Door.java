package com.example.cs230assignment;

/**
 * This models the doors
 * 
 * @author Maxwell
 */
public class Door extends Entity {

    /**
     * This is the constructor for the door class
     * 
     * @param name the name of the door
     * @param x    the x coordinate of the door
     * @param y    the y coordinate of the door
     */
    public Door(int x, int y) {
        super("Door", x, y);
    }

    /**
     * @param score
     * @param timeleft
     */
    // needs to calculate points from score and time left
    public void endMission(int score, int timeleft) {

    }

    /**
     * @param score
     * @return Boolean
     */
    public Boolean validEndMission(int score) {
        return false;
    }
}