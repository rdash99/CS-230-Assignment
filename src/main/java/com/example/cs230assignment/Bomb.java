package com.example.cs230assignment;

import java.util.ArrayList;

/**
 * Class that models the bomb.
 * 
 * @author Maxwell
 */
public class Bomb extends Entity {
    private int xCoord;
    private int yCoord;
    protected Board currentBoard;

    /**
     * Constructor for the bomb
     * 
     * @param x the x coordinate of the bomb
     * @param y the y coordinate of the bomb
     */

    public Bomb(int x, int y) {
        super("Bomb", x, y);
        this.xCoord = x;
        this.yCoord = y;
    }

    /**
     * Starts the proccess of detonating the bomb Needs to access all the tiles
     * that sit on the x and y axis of the bomb. Then needs to decide whether
     * there is an entity on these tiles. If there is an entity on the tile then
     * the entity needs to be deleted
     */
    public void explodeBomb() {
        Timer bombClock = new Timer(3);

        while (bombClock.getLevelTime() > 0) {
            bombClock.decrementTime();
        }
        /**
         * This part of the method is going to check the x coord for items
         */
        for (int i = 0; i < currentBoard.getTiles().length; i++) {
            if (currentBoard.getTile(xCoord, i).getEntity()
                    .getEntityName() == "bomb") {
                explodeBomb();
            } else if (currentBoard.getTile(xCoord, i).getEntity() != null) {
                if (currentBoard.getTile(xCoord, i).getEntity()
                        .getEntityName() == "Gate") {
                    i++;
                } else {
                    currentBoard.getTile(xCoord, i).removeEntity();
                }
            } else {
                i++;
            }
        }
        /*
         * This part of the method is going to check the y coord for items
         */
        for (int i = 0; i < currentBoard.getTiles().length; i++) {
            if (currentBoard.getTile(i, yCoord).getEntity()
                    .getEntityName() == "bomb") {
                explodeBomb();
            } else if (currentBoard.getTile(i, yCoord).getEntity() != null) {
                if (currentBoard.getTile(i, yCoord).getEntity()
                        .getEntityName() == "Gate") {
                    i++;
                } else {
                    currentBoard.getTile(i, yCoord).removeEntity();
                }
            } else {
                i++;
            }
        }
    }
}