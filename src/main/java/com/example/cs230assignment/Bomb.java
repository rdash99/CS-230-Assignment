package com.example.cs230assignment;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * Class that models the bomb.
 * 
 * @author Maxwell
 */
public class Bomb extends Entity {
    private int xCoord;
    private int yCoord;
    /**
     * The current board that the bomb is on
     */
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

    public void setBoard(Board currentBoard) {
        this.currentBoard = currentBoard;
    }
    /**
     * Starts the proccess of detonating the bomb Needs to access all the tiles
     * that sit on the x and y axis of the bomb. Then needs to decide whether
     * there is an entity on these tiles. If there is an entity on the tile then
     * the entity needs to be deleted
     */
    public void explodeBomb() {
        Timeline bombTimeline = new Timeline(new KeyFrame(Duration.millis(1000)));
        bombTimeline.setCycleCount(3);
        bombTimeline.playFromStart();
        Timer bombClock = new Timer(3);
        /**
         * This part of the method is going to check the x coord for items
         */
        for (int i = 0; i < currentBoard.getTiles().length; i++) {
            if (this.currentBoard.getTile(xCoord, i).getEntity() != null){
                if (this.currentBoard.getTile(xCoord, i).getEntity() instanceof Bomb && this.currentBoard.getTile(xCoord, i).getEntity() != this){
                    ((Bomb)this.currentBoard.getTile(xCoord, i).getEntity()).explodeBomb();
                }else if(this.currentBoard.getTile(xCoord, i).getEntity() instanceof Item){
                    this.currentBoard.removeEntity(currentBoard.getTile(xCoord, i).getEntity());
                    currentBoard.getTile(xCoord, i).removeEntity();
                }else if(this.currentBoard.getTile(xCoord, i).getEntity() instanceof Player){
                    this.currentBoard.getPlayer().die();
                }else if(this.currentBoard.getTile(xCoord, i).getEntity() instanceof FloorFollowingThief 
                || this.currentBoard.getTile(xCoord, i).getEntity() instanceof FlyingAssassin 
                || this.currentBoard.getTile(xCoord, i).getEntity() instanceof SmartThief){
                    this.currentBoard.removeEntity(currentBoard.getTile(xCoord, i).getEntity());
                    currentBoard.getTile(xCoord, i).removeEntity();
                }
            }
        /*
         * This part of the method is going to check the y coord for items
         */
        for (int w = 0; w < currentBoard.getTiles().length; w++) {
            if (this.currentBoard.getTile(yCoord, w).getEntity() != null){
                if (this.currentBoard.getTile(yCoord, i).getEntity() instanceof Bomb && this.currentBoard.getTile(xCoord, i).getEntity() != this){
                    ((Bomb)this.currentBoard.getTile(yCoord, w).getEntity()).explodeBomb();
                }else if(this.currentBoard.getTile(yCoord, w).getEntity() instanceof Item){
                    this.currentBoard.removeEntity(currentBoard.getTile(yCoord, w).getEntity());
                    currentBoard.getTile(yCoord, w).removeEntity();
                }else if(this.currentBoard.getTile(yCoord, w).getEntity() instanceof Player){
                    this.currentBoard.getPlayer().die();
                }else if(this.currentBoard.getTile(yCoord, w).getEntity() instanceof FloorFollowingThief 
                || this.currentBoard.getTile(yCoord, w).getEntity() instanceof FlyingAssassin 
                || this.currentBoard.getTile(yCoord, w).getEntity() instanceof SmartThief){
                    this.currentBoard.removeEntity(currentBoard.getTile(yCoord, w).getEntity());
                    currentBoard.getTile(yCoord, w).removeEntity();
                }
            }
        }
    }
    this.currentBoard.removeEntity(this);
    currentBoard.getTile(xCoord, yCoord).removeEntity();
    System.out.println("bomb");
    }
}