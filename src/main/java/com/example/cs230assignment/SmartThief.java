package com.example.cs230assignment;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;

import java.util.*;

/**
 * Class to model a smart thief. A type of thief that can plot its own path
 * towards the closest interactable entity.
 *
 * @author Thomas McAuley
 * @version 1.0
 */
public class SmartThief extends NPC {
    private int distanceFromSmartThief;
    private int movementTimer;
    private Random randGen = new Random();
    private Door door;

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
        this.distanceFromSmartThief = -1;
        this.movementTimer = movementTimerPass;
    }

    /**
     * Method to update the smart thief's coordinates based on its movement
     * rules.
     *
     * @param gc    a reference to the canvas to be drawn on.
     * @param board a reference to the board to find the closest interactable
     *              item
     */
    // changed to move to overwrite move in character
    public void move(GraphicsContext gc, Board board) {
        Entity closestInteractable = findClosestInteractable(board);
        int targetXCoord;
        int targetYCoord;
        if (closestInteractable == null) {
            targetXCoord = getDoor().getXCoord();
            targetYCoord = getDoor().getYCoord();
        } else {
            targetXCoord = closestInteractable.getXCoord();
            targetYCoord = closestInteractable.getYCoord();
        }
        if (this.getYCoord() > targetYCoord) {
            moveUp(board);
            board.getTimer().boardUpdate(gc, board);
        } else if (this.getXCoord() > targetXCoord) {
            moveLeft(board);
            board.getTimer().boardUpdate(gc, board);
        } else if (this.getYCoord() < targetYCoord) {
            moveDown(board);
            board.getTimer().boardUpdate(gc, board);
        } else if (this.getXCoord() < targetXCoord) {
            moveRight(board);
            board.getTimer().boardUpdate(gc, board);
        }
    }

    /**
     * Move randomly.
     *
     * @param board The board to move on
     */
    public void randomMovement(Board board) {
        int directionChoice = randGen.nextInt(1, 5);
        switch (directionChoice) {
        case (1):
            moveUp(board);
            break;
        case (2):
            moveLeft(board);
            break;
        case (3):
            moveDown(board);
            break;
        case (4):
            moveRight(board);
            break;
        }
    }

    /**
     * Method to move the smart thief up.
     *
     * @param board The board to move on
     */
    public void moveUp(Board board) {
        boolean foundSquare = false;
        for (int i = super.coord[1] - 1; i < board.getHeight() && i > -1
                && !foundSquare; i--) {
            for (int colourPos = 0; colourPos < 4; colourPos++) {
                if (board.getTile(i, super.coord[0]).checkColour(
                        board.getTile(super.coord[1], super.coord[0])
                                .getColours()[colourPos])) {
                    board.getTile(super.coord[0], super.coord[1])
                            .removeEntity();
                    this.interact(super.coord[0], i);
                    board.getTile(super.coord[0], i).setEntity(this);
                    super.coord[1] = i;
                    foundSquare = true;
                }
            }
        }
    }

    /**
     * Moves the entity left if possible.
     *
     * @param board the board
     */
    public void moveLeft(Board board) {
        boolean foundSquare = false;
        for (int i = super.coord[0] - 1; i < board.getWidth() && i > -1
                && !foundSquare; i--) {
            for (int colourPos = 0; colourPos < 4; colourPos++) {
                if (board.getTile(super.coord[1], i).checkColour(
                        board.getTile(super.coord[1], super.coord[0])
                                .getColours()[colourPos])) {
                    board.getTile(super.coord[0], super.coord[1])
                            .removeEntity();
                    this.interact(i, super.coord[1]);
                    board.getTile(i, super.coord[1]).setEntity(this);
                    super.coord[0] = i;
                    foundSquare = true;
                }
            }
        }
    }

    /**
     * Moves the entity down one square if possible.
     *
     * @param gc    the graphics context
     * @param board the board
     */
    public void moveDown(Board board) {
        boolean foundSquare = false;
        for (int i = super.coord[1] + 1; i < board.getHeight() && i > -1
                && !foundSquare; i++) {
            for (int colourPos = 0; colourPos < 4; colourPos++) {
                if (board.getTile(i, super.coord[0]).checkColour(
                        board.getTile(super.coord[1], super.coord[0])
                                .getColours()[colourPos])) {
                    board.getTile(super.coord[0], super.coord[1])
                            .removeEntity();
                    this.interact(super.coord[0], i);
                    board.getTile(super.coord[0], i).setEntity(this);
                    super.coord[1] = i;
                    foundSquare = true;
                }
            }
        }
    }

    /**
     * Moves the entity right one square if possible.
     *
     * @param board the board
     */
    public void moveRight(Board board) {
        boolean foundSquare = false;
        for (int i = super.coord[0] + 1; i < board.getWidth() && i > -1
                && !foundSquare; i++) {
            for (int colourPos = 0; colourPos < 4; colourPos++) {
                if (board.getTile(super.coord[1], i).checkColour(
                        board.getTile(super.coord[1], super.coord[0])
                                .getColours()[colourPos])) {
                    board.getTile(super.coord[0], super.coord[1])
                            .removeEntity();
                    this.interact(i, super.coord[1]);
                    board.getTile(i, super.coord[1]).setEntity(this);
                    super.coord[0] = i;
                    foundSquare = true;
                }
            }
        }
    }

    /**
     * Method to find the closest interactable item to a smart thief.
     *
     * @param board the board
     * @return Entity
     */
    private Entity findClosestInteractable(Board board) {
        Vertex vertex = null;
        for (Entity elem : board.getEntities()) {
            if (elem instanceof Key || elem instanceof Item) {
                vertex = new Vertex<>(elem);
                vertex.setNeighbours(Arrays.asList(vertex));
            }
        }
        BFS bfs = new BFS(vertex);
        return bfs.traverse().getVertexData();
    }

    /**
     * Method to retrieve the distance of an interactable entity from a smart
     * thief along the x axis.qq
     *
     * @return int the distance of an interactable entity from smart thief along
     *         the x axis.
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
     * @return int the distance of an interactable entity from smart thief *
     *         along the y axis.
     */
    private int getYDistanceFromInteractable() {
        int distanceFromY = 0;
        for (int i = 0; i < super.coord[1] + 1; i++) {
            distanceFromY = i;
        }
        return distanceFromY;
    }

    /**
     * Method to set the door.
     *
     * @param door the door
     */
    public void setDoor(Door door) {
        this.door = door;
    }

    /**
     * Method to retrieve the door.
     *
     * @return Door the door
     */
    public Door getDoor() {
        return door;
    }
}