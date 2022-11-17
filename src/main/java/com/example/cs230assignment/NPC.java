package com.example.cs230assignment;

public class NPC extends Character {
    private double movementTimer;
    protected int[] coordChange;
    protected Board currentBoard;

    // thinking about adding move into here aswell as character for the basic
    // movement of the NPCS
    public void NPC(Board boardPass, double movementTimerPass) {
        this.movementTimer = movementTimerPass;
        this.currentBoard = boardPass;
    }

    protected int[] ShortestPath(int[] coordTo) {

    }

    protected void validMove(int[] coordCheck) {

    }

    protected double getMovementTimer() {

    }
}
