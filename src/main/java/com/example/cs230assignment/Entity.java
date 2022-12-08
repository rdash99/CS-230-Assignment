package com.example.cs230assignment;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Defines the Entity class. This class is the parent class for several other
 * classes.
 * 
 * @author Rowan Dash
 * @version 1.0
 */

public class Entity extends DrawShape {
    protected String entityName;
    protected int[] coord;

    public Entity(String entityNamePass, int x, int y) {
        this.coord = new int[] { x, y };
        this.entityName = entityNamePass;
    }

    public String getEntityName() {
        return this.entityName;
    }

    public int[] getCoord() {
        return this.coord;
    }

    public int getXCoord() {
        return this.coord[0];
    }

    public int getYCoord() {
        return this.coord[1];
    }

    @Override
    public void draw(GraphicsContext gc) {
        Image playerImg = new Image("player.png");
        Image flyingAssassinImg = new Image("flyingAssassin.png");
        Image floorFollowingThiefImg = new Image("floorFollowingThief.png");

        int xCoord = 0;
        int yCoord = 0;
        switch (this.entityName) {
            case "Player":
                int playerXDefaultOffset = 25;
                int playerYDefaultOffset = 25;
                xCoord = (this.coord[0]);
                yCoord = (this.coord[1]);
                for (int i = 0; i < xCoord; i++) {
                    playerXDefaultOffset += 100;
                }
                for (int i = 0; i < yCoord; i++) {
                    playerYDefaultOffset += 100;
                }
                gc.drawImage(playerImg, xCoord + playerXDefaultOffset, yCoord + playerYDefaultOffset);
                break;
            case "Flying Assassin":
                int flyingAssassinXDefaultOffset = 25;
                int flyingAssassinYDefaultOffset = 25;
                xCoord = (this.coord[0]);
                yCoord = (this.coord[1]);
                for (int i = 0; i < xCoord; i++) {
                    flyingAssassinXDefaultOffset += 100;
                }
                for (int i = 0; i < yCoord; i++) {
                    flyingAssassinYDefaultOffset += 100;
                }
                gc.drawImage(flyingAssassinImg, xCoord + flyingAssassinXDefaultOffset, yCoord + flyingAssassinYDefaultOffset);
                break;
            case "Floor Following Thief":
                int floorFollowingThiefXDefaultOffset = 25;
                int floorFollowingThiefYDefaultOffset = 25;
                xCoord = (this.coord[0]);
                yCoord = (this.coord[1]);
                for (int i = 0; i < xCoord; i++) {
                    floorFollowingThiefXDefaultOffset += 100;
                }
                for (int i = 0; i < yCoord; i++) {
                    floorFollowingThiefYDefaultOffset += 100;
                }
                gc.drawImage(floorFollowingThiefImg, xCoord + floorFollowingThiefXDefaultOffset, yCoord + floorFollowingThiefYDefaultOffset);
                break;
            case "Smart Thief":
                break;
            case "Clock":
                break;
            case "Gate":
                break;
            case "Key":
                break;
            case "Bomb":
                break;
            case "Door":
                break;
        }
    }

}
