package com.example.cs230assignment;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Class that acts as a controller, updating the level every so often with the
 * latest actions to occur from either entities or the player.
 *
 * Level time remaining is also displayed on the board in this class
 */
public class Timer {
    private int levelTime;
    private int initTime;

    public Timer(int levelTime) {
        this.levelTime = levelTime;
        this.initTime = levelTime;
    }

    public void boardUpdate(GraphicsContext gc, Entity entity) {
        redraw(gc, entity);
    }

    public void addClock(Clock clock) {
        this.levelTime += clock.gettime();
    }

    /**
     * Method to draw time remaining depending on level on the canvas
     *
     * @param gc the reference to the canvas to be drawn on
     */
    public void redraw(GraphicsContext gc, Entity entity) {
        Image playerImg = new Image("player.png");
        int xCoord = entity.getXCoord();
        int yCoord = entity.getYCoord();

        gc.drawImage(playerImg, xCoord + 25, yCoord + 25);
    }

    public int getLevelTime() {
        return this.levelTime;
    }

    public int getInitTime() {
        return this.initTime;
    }

}
