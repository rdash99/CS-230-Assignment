package com.example.cs230assignment;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

/**
 * @author Thomas McAuley
 *
 *         Class that acts as a controller, updating the level every so often
 *         with the latest actions to occur from either entities or the player.
 *
 *         Level time remaining is also displayed on the board in this class
 */
public class Timer {
    private int levelTime;
    private int initTime;

    /**
     * Construct a timer by initialising the levelTime and initTime
     *
     * @param levelTime the level time
     */
    public Timer(int levelTime) {
        this.levelTime = levelTime;
        this.initTime = levelTime;
    }

    /**
     * Method to update the board with any new movements or events that may have
     * occurred
     *
     * @param gc    a reference to the canvas to be drawn on
     * @param board a reference to the board to be updated
     */
    public void boardUpdate(GraphicsContext gc, Board board) {
        board.draw(gc);
    }

    /**
     * Method to increment the time remaining in a level depending on the value
     * of a clock.
     *
     * @param clock a value which will be added on to the time remaining in a
     *              level.
     */
    public void addClock(Clock clock) {
        this.levelTime += clock.gettime();
    }

    /**
     * Method to decrement the time remaining in a level by one
     *
     * @return the time remaining in a level decremented by one
     */
    public int decrementTime() {
        return this.levelTime -= 1;
    }

    /**
     * Method to get the level time
     *
     * @return the time in a given level
     */
    public int getLevelTime() {
        return this.levelTime;
    }

    /**
     * Method to get the initialisation time
     *
     * @return the initialisation time
     */
    public int getInitTime() {
        return this.initTime;
    }
    public void setLevelTime(int x){
        this.levelTime = x;
    }

}
