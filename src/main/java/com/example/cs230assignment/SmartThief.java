package com.example.cs230assignment;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class to model a smart thief. A type of thief that can plot its own path
 * towards the closest interactable entity.
 * 
 * @author Thomas McAuley
 * @version 1.0
 */
public class SmartThief extends NPC {
    private int destanceFromSmartThief;
    private int movementTimer;

    /**
     * This is the constructor for the smart thief class
     * 
     * @param x                 the x coordinate of the smart thief
     * @param y                 the y coordinate of the smart thief
     * @param movementTimerPass the time between each movement of the smart
     *                          thief
     */
    public SmartThief(int x, int y, int movementTimerPass) {
        super("Smart Thief", movementTimerPass, x, y);
        this.destanceFromSmartThief = -1;
        this.movementTimer = movementTimerPass;
    }

    /**
     * Method to update the smart thief's coordinates based on its movement
     * rules.
     *
     * @param gc a reference to the canvas to be drawn on.
     * @param board a reference to the board to find the closest
     *              interactable item
     */
    // changed to move to overwrite move in character
    public void move(GraphicsContext gc, Board board) {
        Entity closestInteractable = findClosestInteractable(board);
        int targetXCoord = closestInteractable.getXCoord();
        int targetYCoord = closestInteractable.getYCoord();
        boolean foundSquare = false;
        if (this.getYCoord() > targetYCoord) {
            for (int i = super.coord[1] - 1; i >= targetYCoord && i < board.getHeight() && i > -1 && !foundSquare; i--) {
                for (int colourPos = 0; colourPos < 4; colourPos++) {
                    if (board.getTile(i, super.coord[0])
                            .checkColour(board
                                    .getTile(super.coord[1], super.coord[0])
                                    .getColours()[colourPos])) {
                        board.getTile(super.coord[0], super.coord[1])
                                .removeEntity();
                        //Interact for Smart Thief should go here
                        board.getTile(super.coord[0], i)
                                .setEntity(this);
                        super.coord[1] = i;
                        foundSquare = true;
                    }
                }
            }
            board.getTimer().boardUpdate(gc, board);

        } else if (this.getXCoord() > targetXCoord) {
            for (int i = super.coord[0] - 1; i >= targetXCoord && i < board.getWidth() && i > -1 && !foundSquare; i--) {
                for (int colourPos = 0; colourPos < 4; colourPos++) {
                    if (board.getTile(super.coord[1], i)
                            .checkColour(board
                                    .getTile(super.coord[1], super.coord[0])
                                    .getColours()[colourPos])) {
                        board.getTile(super.coord[0], super.coord[1])
                                .removeEntity();
                        //Interact for Smart Thief should go here
                        board.getTile(i, super.coord[1])
                                .setEntity(this);
                        super.coord[0] = i;
                        foundSquare = true;
                    }
                }
            }
            board.getTimer().boardUpdate(gc, board);

        } else if (this.getYCoord() < targetYCoord) {
            for (int i = super.coord[1] + 1; i <= targetYCoord && i < board.getHeight() && i > -1 && !foundSquare; i++) {
                for (int colourPos = 0; colourPos < 4; colourPos++) {
                    if (board.getTile(i, super.coord[0])
                            .checkColour(board
                                    .getTile(super.coord[1], super.coord[0])
                                    .getColours()[colourPos])) {
                        board.getTile(super.coord[0], super.coord[1])
                                .removeEntity();
                        //Interact for Smart Thief should go here
                        board.getTile(super.coord[0], i)
                                .setEntity(this);
                        super.coord[1] = i;
                        foundSquare = true;
                    }
                }
            }
            board.getTimer().boardUpdate(gc, board);

        } else if (this.getXCoord() < targetXCoord) {
            for (int i = super.coord[0] + 1; i <= targetXCoord && i < board.getWidth() && i > -1 && !foundSquare; i++) {
                for (int colourPos = 0; colourPos < 4; colourPos++) {
                    if (board.getTile(super.coord[1], i)
                            .checkColour(board
                                    .getTile(super.coord[1], super.coord[0])
                                    .getColours()[colourPos])) {
                        board.getTile(super.coord[0], super.coord[1])
                                .removeEntity();
                        //Interact for Smart Thief should go here
                        board.getTile(i, super.coord[1])
                                .setEntity(this);
                        super.coord[0] = i;
                        foundSquare = true;
                    }
                }
            }
            board.getTimer().boardUpdate(gc, board);
        }
    }

    /**
     * Method to locate the closest interactable entity from the smart thief.
     *
     * @param board the board where the closest interactable to smart thief
     *              resides.
     * @return Entity the closest interactable entity to the smart thief.
     */
    public Entity findClosestInteractable(Board board) {
        int distanceFromClosestInteractable = -1;
        for (Entity entity : board.getEntities()) {
            if (entity instanceof Key
                    || entity instanceof Item) {
                entity.setDistanceFromSmartThief(-1);
            }
        }
        Entity nextInteractable = null;
        Queue<Entity> queue = new LinkedList<>();
        queue.add(this);

        while (!(queue.isEmpty())) {
            queue.remove();
            for (Entity entity : board.getEntities()) {
                if (entity.getDistanceFromSmartThief() == -1
                        && (entity instanceof Key || entity instanceof Item)) {
                    entity.setDistanceFromSmartThief(
                            getXDistanceFromInteractable()
                                    + getYDistanceFromInteractable());
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
     * Method to retrieve the distance of an interactable entity from a smart
     * thief along the x axis.
     *
     * @return int the distance of an interactable entity from smart thief
     * along the x axis.
     */
    private int getXDistanceFromInteractable() {
        int distanceFromX = 0;
        for (int i = 0; i < super.coord[0] + 1; i++) {
            distanceFromX = i;
        }
        return distanceFromX;
    }

    /**
     * Method to retrieve the distance of an interactable entity from a smart
     * thief along the y axis.
     *
     * @return int the distance of an interactable entity from smart thief
     *      * along the y axis.
     */
    private int getYDistanceFromInteractable() {
        int distanceFromY = 0;
        for (int i = 0; i < super.coord[1] + 1; i++) {
            distanceFromY = i;
        }
        return distanceFromY;
    }
}