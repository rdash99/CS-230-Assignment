package com.example.cs230assignment;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This class is used to create a smart thief.
 * 
 * @author <name>
 * @version 1.0
 */
public class SmartThief extends NPC {
    private int destanceFromSmartThief;

    /**
     * This is the constructor for the smart thief class
     * 
     * @param x                 the x coordinate of the smart thief
     * @param y                 the y coordinate of the smart thief
     * @param movementTimerPass the time between each movement of the smart
     *                          thief
     */
    public SmartThief(int x, int y, double movementTimerPass) {
        super("Smart Thief", movementTimerPass, x, y);
        this.destanceFromSmartThief = -1;
    }

    /**
     * @return boolean
     */
    // changed to move to overwrite move in character
    // public void move(GraphicsContext gc) {
    // if (this.getYCoord() > entity.getYCoord()) {
    // for (int i = super.coord[0] - 1; ((i != entity.getYCoord()) && i > -1)
    // && !validMoveUp(); i--) {
    // //Interact for Smart Thief should go here
    // super.currentBoard.getTile(i, super.coord[1])
    // .setEntity(this);
    // super.coord[0] = i;
    // }
    // }
    // super.currentBoard.getTimer().boardUpdate(gc, super.currentBoard);
    // }

    private boolean validMoveUp() {
        boolean foundSquare = false;
        for (int i = super.coord[0] - 1; ((i < Character.currentBoard
                .getHeight()) && i > -1) && !foundSquare; i--) {
            for (int colourPos = 0; colourPos < 4; colourPos++) {
                if (Character.currentBoard.getTile(i, super.coord[0])
                        .checkColour(Character.currentBoard
                                .getTile(super.coord[1], super.coord[0])
                                .getColours()[colourPos])) {
                    foundSquare = true;
                }
            }
        }
        return foundSquare;
    }

    /**
     * @return boolean
     */
    private boolean validMoveLeft() {
        boolean foundSquare = false;
        for (int i = super.coord[0] - 1; ((i < Character.currentBoard
                .getWidth()) && i > -1) && !foundSquare; i--) {
            for (int colourPos = 0; colourPos < 4; colourPos++) {
                if (Character.currentBoard.getTile(super.coord[1], i)
                        .checkColour(Character.currentBoard
                                .getTile(super.coord[1], super.coord[0])
                                .getColours()[colourPos])) {
                    foundSquare = true;
                }
            }
        }
        return foundSquare;
    }

    /**
     * @return boolean
     */
    private boolean validMoveDown() {
        boolean foundSquare = false;
        for (int i = super.coord[0] + 1; ((i < Character.currentBoard
                .getHeight()) && i > -1) && !foundSquare; i++) {
            for (int colourPos = 0; colourPos < 4; colourPos++) {
                if (Character.currentBoard.getTile(i, super.coord[0])
                        .checkColour(Character.currentBoard
                                .getTile(super.coord[1], super.coord[0])
                                .getColours()[colourPos])) {
                    foundSquare = true;
                }
            }
        }
        return foundSquare;
    }

    /**
     * @return boolean
     */
    private boolean validMoveRight() {
        boolean foundSquare = false;
        for (int i = super.coord[0] + 1; ((i < Character.currentBoard
                .getWidth()) && i > -1) && !foundSquare; i++) {
            for (int colourPos = 0; colourPos < 4; colourPos++) {
                if (Character.currentBoard.getTile(super.coord[1], i)
                        .checkColour(Character.currentBoard
                                .getTile(super.coord[1], super.coord[0])
                                .getColours()[colourPos])) {
                    foundSquare = true;
                }
            }
        }
        return foundSquare;
    }

    /**
     * @param board
     * @return Entity
     */
    public Entity findClosestInteractable(Board board) {
        int distanceFromClosestInteractable = -1;
        Entity nextInteractable = null;
        Queue<Entity> queue = new LinkedList<>();
        queue.add(this);

        while (!(queue.isEmpty())) {
            queue.remove();
            for (Entity entity : board.getEntities()) {
                if (entity.getDistanceFromSmartThief() == -1
                        && (entity instanceof Key || entity instanceof Item)) {
                    entity.setDistanceFromSmartThief(
                            getXDistanceFromInteractable(entity
                                    .getXCoord()) + getYDistanceFromInteractable(
                                            entity.getYCoord()));
                    queue.add(entity);

                    if (distanceFromClosestInteractable == -1 || (entity
                            .getDistanceFromSmartThief() < distanceFromClosestInteractable)) {
                        distanceFromClosestInteractable = entity
                                .getDistanceFromSmartThief();
                        nextInteractable = entity;
                    }
                }
            }
        }

        if (nextInteractable == null) {
            return null;
        }
        return nextInteractable;
    }

    /**
     * @param x
     * @return int
     */
    private int getXDistanceFromInteractable(int x) {
        int distanceFromX = 0;
        for (int i = 0; i < x + 1; i++) {
            distanceFromX = i;
        }
        return distanceFromX;
    }

    /**
     * @param y
     * @return int
     */
    private int getYDistanceFromInteractable(int y) {
        int distanceFromY = 0;
        for (int i = 0; i < y + 1; i++) {
            distanceFromY = i;
        }
        return distanceFromY;
    }

    private void setXCoords() {
    }
}