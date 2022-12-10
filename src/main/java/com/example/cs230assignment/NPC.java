package com.example.cs230assignment;

public class NPC extends Character {
    private double movementTimer;
    protected int[] coordChange;

    // thinking about adding move into here aswell as character for the basic
    // movement of the NPCS
    public NPC(String name, double movementTimerPass, int x, int y) {
        super(name, x, y);
        this.movementTimer = movementTimerPass;
    }

    protected int[] ShortestPath(int[] coordTo) {
        return coordTo;

    }

    protected void validMove(int[] coordCheck) {

    }

    protected double getMovementTimer() {
        return movementTimer;

    }
}
