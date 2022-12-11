package com.example.cs230assignment;

/**
 * This class represents the NPC subclass
 * 
 * @author Charles Overton
 * @version 1.0
 */
public class NPC extends Character {
    private int movementTimer;
    protected int[] coordChange;

    // thinking about adding move into here aswell as character for the basic
    // movement of the NPCS
    /**
     * This is the constructor for the NPC class
     * 
     * @param name              the name of the NPC
     * @param movementTimerPass the time it takes for the NPC to move
     * @param x                 the x coordinate of the NPC
     * @param y                 the y coordinate of the NPC
     */

    public NPC(String name, int movementTimerPass, int x, int y) {
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
     * @param coordCheck the coordinates to be checked
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
