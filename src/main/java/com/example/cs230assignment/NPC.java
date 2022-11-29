package com.example.cs230assignment;

public class NPC extends Character {
    private double movementTimer;
    protected Board currentBoard;

    // thinking about adding move into here aswell as character for the basic
    // movement of the NPCS
    public NPC(Board boardPass, double movementTimerPass, int x, int y) {
        super("NPC", x, y);
        this.movementTimer = movementTimerPass;
        this.currentBoard = boardPass;
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
