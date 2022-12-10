package com.example.cs230assignment;

/**
 * This class represents the NPC subclass
 * 
 * @author Charles Overton
 * @version 1.0
 */
public class NPC extends Character {
    private double movementTimer;
    protected int[] coordChange;

    // thinking about adding move into here aswell as character for the basic
    // movement of the NPCS
    /**
     * This is the constructor for the NPC class
     * 
     * @param name
     * @param movementTimerPass
     * @param x
     * @param y
     */
    public NPC(String name, double movementTimerPass, int x, int y) {
        super(name, x, y);
        this.movementTimer = movementTimerPass;
    }

    /**
     * @param coordTo
     * @return int[]
     */
    protected int[] ShortestPath(int[] coordTo) {
        return coordTo;

    }

    /**
     * @param coordCheck
     */
    protected void validMove(int[] coordCheck) {

    }

    /**
     * @return double
     */
    protected double getMovementTimer() {
        return movementTimer;

    }
}
