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
        int xCoord = (entity.getXCoord());
        int yCoord = (entity.getYCoord());
        int xDefaultOffset = 25;
        int yDefaultOffset = 25;

        for (int i = 0; i < xCoord; i++) {
            xDefaultOffset += 100;
        }
        for (int i = 0; i < yCoord; i++) {
            yDefaultOffset += 100;
        }
        if (entity.getEntityName().equals("Player")) {
            gc.drawImage(playerImg, xCoord + xDefaultOffset, yDefaultOffset);
        }
    }

    public int getLevelTime() {
        return this.levelTime;
    }

    public int getInitTime() {
        return this.initTime;
    }

}
