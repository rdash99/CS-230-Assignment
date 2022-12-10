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

    protected int distanceFromSmartThief = -1;

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
        Image smartThiefImg = new Image("smartThief.png");
        Image clockImg = new Image("clock.png");
        Image gateImg = new Image("gate.png");
        Image keyImg = new Image("key.png");
        Image bombImg = new Image("bomb.png");
        Image doorImg = new Image("door.png");
        Image itemImg = new Image("item.png");

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
                int smartThiefXDefaultOffset = 25;
                int smartThiefYDefaultOffset = 25;
                xCoord = (this.coord[0]);
                yCoord = (this.coord[1]);
                for (int i = 0; i < xCoord; i++) {
                    smartThiefXDefaultOffset += 100;
                }
                for (int i = 0; i < yCoord; i++) {
                    smartThiefYDefaultOffset += 100;
                }
                gc.drawImage(smartThiefImg, xCoord + smartThiefXDefaultOffset, yCoord + smartThiefYDefaultOffset);
                break;
            case "Clock":
                int clockfXDefaultOffset = 25;
                int clockYDefaultOffset = 25;
                xCoord = (this.coord[0]);
                yCoord = (this.coord[1]);
                for (int i = 0; i < xCoord; i++) {
                    clockfXDefaultOffset += 100;
                }
                for (int i = 0; i < yCoord; i++) {
                    clockYDefaultOffset += 100;
                }
                gc.drawImage(clockImg, xCoord + clockfXDefaultOffset, yCoord + clockYDefaultOffset);
                break;
            case "Gate":
                int gateXDefaultOffset = 25;
                int gateYDefaultOffset = 25;
                xCoord = (this.coord[0]);
                yCoord = (this.coord[1]);
                for (int i = 0; i < xCoord; i++) {
                    gateXDefaultOffset += 100;
                }
                for (int i = 0; i < yCoord; i++) {
                    gateYDefaultOffset += 100;
                }
                gc.drawImage(gateImg, xCoord + gateXDefaultOffset, yCoord + gateYDefaultOffset);
                break;
            case "Key":
                int keyXDefaultOffset = 25;
                int keyYDefaultOffset = 25;
                xCoord = (this.coord[0]);
                yCoord = (this.coord[1]);
                for (int i = 0; i < xCoord; i++) {
                    keyXDefaultOffset += 100;
                }
                for (int i = 0; i < yCoord; i++) {
                    keyYDefaultOffset += 100;
                }
                gc.drawImage(keyImg, xCoord + keyXDefaultOffset, yCoord + keyYDefaultOffset);
                break;
            case "Bomb":
                int bombXDefaultOffset = 25;
                int bombYDefaultOffset = 25;
                xCoord = (this.coord[0]);
                yCoord = (this.coord[1]);
                for (int i = 0; i < xCoord; i++) {
                    bombXDefaultOffset += 100;
                }
                for (int i = 0; i < yCoord; i++) {
                    bombYDefaultOffset += 100;
                }
                gc.drawImage(bombImg, xCoord + bombXDefaultOffset, yCoord + bombYDefaultOffset);
                break;
            case "Door":
                int doorXDefaultOffset = 25;
                int doorYDefaultOffset = 25;
                xCoord = (this.coord[0]);
                yCoord = (this.coord[1]);
                for (int i = 0; i < xCoord; i++) {
                    doorXDefaultOffset += 100;
                }
                for (int i = 0; i < yCoord; i++) {
                    doorYDefaultOffset += 100;
                }
                gc.drawImage(doorImg, xCoord + doorXDefaultOffset, yCoord + doorYDefaultOffset);
                break;
            default:
                int itemXDefaultOffset = 25;
                int itemYDefaultOffset = 25;
                xCoord = (this.coord[0]);
                yCoord = (this.coord[1]);
                for (int i = 0; i < xCoord; i++) {
                    itemXDefaultOffset += 100;
                }
                for (int i = 0; i < yCoord; i++) {
                    itemYDefaultOffset += 100;
                }
                gc.drawImage(itemImg, xCoord + itemXDefaultOffset, yCoord + itemYDefaultOffset);
        }
    }

    public void setDistanceFromSmartThief(int distanceFromSmartThief) {
        this.distanceFromSmartThief = distanceFromSmartThief;
    }

    public int getDistanceFromSmartThief() {
        return this.distanceFromSmartThief;
    }
}
