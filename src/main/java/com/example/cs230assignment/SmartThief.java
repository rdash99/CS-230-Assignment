package com.example.cs230assignment;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SmartThief extends NPC {
    private int destanceFromSmartThief;
    private int movementTimer;

    public SmartThief(int x, int y, int movementTimerPass) {
        super("Smart Thief", movementTimerPass, x, y);
        this.destanceFromSmartThief = -1;
        this.movementTimer = movementTimerPass;
    }

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
                    entity.setDistanceFromSmartThief
                            (getXDistanceFromInteractable(entity.getXCoord())
                                    + getYDistanceFromInteractable(entity.getYCoord()));
                    queue.add(entity);

                    if (distanceFromClosestInteractable == -1
                            || (entity.getDistanceFromSmartThief() < distanceFromClosestInteractable)) {
                        distanceFromClosestInteractable = entity.getDistanceFromSmartThief();
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

    private int getXDistanceFromInteractable(int x) {
        int distanceFromX = 0;
        for (int i = 0; i < x + 1; i++) {
            distanceFromX = i;
        }
        return distanceFromX;
    }

    private int getYDistanceFromInteractable(int y) {
        int distanceFromY = 0;
        for (int i = 0; i < y + 1; i++) {
            distanceFromY = i;
        }
        return distanceFromY;
    }
}