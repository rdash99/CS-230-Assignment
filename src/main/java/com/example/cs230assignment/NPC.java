package com.example.cs230assignment;

public class NPC extends Character {
    private double movementTimer;
    protected int[] coordChange;
    protected Board currentBoard;

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

    public void move() {
        validMove(this.coord);
        this.coord[0] = this.coord[0] + this.coordChange[0];
        this.coord[1] = this.coord[1] + this.coordChange[1];
    }

    protected double getMovementTimer() {
        return movementTimer;

    }
}
