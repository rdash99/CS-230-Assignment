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
    /**
     * The coordinates of the entity
     */
    protected int[] coord;
    private boolean visited;
    /**
     * The distance from the smart thief
     */
    protected int distanceFromSmartThief = -1;

    /**
     * This is the constructor for the entity class
     * 
     * @param entityNamePass the name of the entity
     * @param x              the x coordinate of the entity
     * @param y              the y coordinate of the entity
     */
    public Entity(String entityNamePass, int x, int y) {
        this.coord = new int[] { x, y };
        this.entityName = entityNamePass;
    }

    /**
     * @return String
     */
    public String getEntityName() {
        return this.entityName;
    }

    /**
     * @return int[]
     */
    public int[] getCoord() {
        return this.coord;
    }

    /**
     * @return int
     */
    public int getXCoord() {
        return this.coord[0];
    }

    /**
     * @return int
     */
    public int getYCoord() {
        return this.coord[1];
    }

    /**
     * @param gc the graphics context
     */
    @Override
    public void draw(GraphicsContext gc) {
        Image playerImg = new Image("player.png");
        Image flyingAssassinImg = new Image("flyingAssassin.png");
        Image floorFollowingThiefImg = new Image("floorFollowingThief.png");
        Image smartThiefImg = new Image("smartThief.png");
        Image clockImg = new Image("clock.png");
        Image gateYImg = new Image("gateY.png");
        Image gateRImg = new Image("gateR.png");
        Image gateGImg = new Image("gateG.png");
        Image gateBImg = new Image("gateB.png");
        Image keyRImg = new Image("keyR.png");
        Image keyGImg = new Image("keyG.png");
        Image keyBImg = new Image("keyB.png");
        Image keyYImg = new Image("keyY.png");
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
            gc.drawImage(playerImg, xCoord + playerXDefaultOffset,
                    yCoord + playerYDefaultOffset);
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
            gc.drawImage(flyingAssassinImg,
                    xCoord + flyingAssassinXDefaultOffset,
                    yCoord + flyingAssassinYDefaultOffset);
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
            gc.drawImage(floorFollowingThiefImg,
                    xCoord + floorFollowingThiefXDefaultOffset,
                    yCoord + floorFollowingThiefYDefaultOffset);
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
            gc.drawImage(smartThiefImg, xCoord + smartThiefXDefaultOffset,
                    yCoord + smartThiefYDefaultOffset);
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
            gc.drawImage(clockImg, xCoord + clockfXDefaultOffset,
                    yCoord + clockYDefaultOffset);
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
            Gate gate = (Gate) this;
            switch (gate.getGateColour()) {
            case 'r':
                gc.drawImage(gateRImg, xCoord + gateXDefaultOffset,
                        yCoord + gateYDefaultOffset);
                break;

            case 'g':
                gc.drawImage(gateGImg, xCoord + gateXDefaultOffset,
                        yCoord + gateYDefaultOffset);
                break;

            case 'b':
                gc.drawImage(gateBImg, xCoord + gateXDefaultOffset,
                        yCoord + gateYDefaultOffset);
                break;

            default:
                gc.drawImage(gateYImg, xCoord + gateXDefaultOffset,
                        yCoord + gateYDefaultOffset);
                break;
            }

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
            Key key = (Key) this;
            switch (key.getKeyColour()) {
            case 'r':
                gc.drawImage(keyRImg, xCoord + keyXDefaultOffset,
                        yCoord + keyYDefaultOffset);
                break;

            case 'g':
                gc.drawImage(keyGImg, xCoord + keyXDefaultOffset,
                        yCoord + keyYDefaultOffset);
                break;

            case 'b':
                gc.drawImage(keyBImg, xCoord + keyXDefaultOffset,
                        yCoord + keyYDefaultOffset);
                break;

            default:
                gc.drawImage(keyYImg, xCoord + keyXDefaultOffset,
                        yCoord + keyYDefaultOffset);
                break;
            }
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
            gc.drawImage(bombImg, xCoord + bombXDefaultOffset,
                    yCoord + bombYDefaultOffset);
            break;
        // case "Door":
        // int doorXDefaultOffset = 25;
        // int doorYDefaultOffset = 25;
        // xCoord = (this.coord[0]);
        // yCoord = (this.coord[1]);
        // for (int i = 0; i < xCoord; i++) {
        // doorXDefaultOffset += 100;
        // }
        // for (int i = 0; i < yCoord; i++) {
        // doorYDefaultOffset += 100;
        // }
        // gc.drawImage(doorImg, xCoord + doorXDefaultOffset,
        // yCoord + doorYDefaultOffset);
        // break;
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
            gc.drawImage(itemImg, xCoord + itemXDefaultOffset,
                    yCoord + itemYDefaultOffset);
        }

    }

    /**
     * @param visited the visited state to set
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    /**
     * @return boolean
     */
    public boolean checkVisited() {
        return this.visited;
    }

    /**
     * @param distanceFromSmartThief the distance from the smart thief
     */
    public void setDistanceFromSmartThief(int distanceFromSmartThief) {
        this.distanceFromSmartThief = distanceFromSmartThief;
    }

    /**
     * @return int
     */
    public int getDistanceFromSmartThief() {
        return this.distanceFromSmartThief;
    }
}
