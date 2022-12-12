package com.example.cs230assignment;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * Class that models the board which is drawn on the canvas for the user to play
 * on.
 *
 * @author Thomas McAuley
 */

public class Board extends DrawShape {
    private static final int DEFAULT_TILE_WIDTH = 100;
    private static final int DEFAULT_TILE_HEIGHT = 100;
    private static final int DEFAULT_X_OFFSET = 25;
    private static final int DEFAULT_Y_OFFSET = 25;
    private static final int DEFAULT_PLAYER_WIDTH = 50;
    private static final int DEFAULT_PLAYER_HEIGHT = 50;
    private Tile[][] tiles;
    private ArrayList<Entity> entities;
    private Player player;
    private int width;
    private int height;
    private Timer timer;
    private Timeline smartThiefTimeline;
    private Timeline flyingAssassinTimeline;
    private Timeline floorFollowingThiefTimeline;
    private GraphicsContext gc;

    /**
     * Construct the board with to be played on.
     *
     * @param width    the width of the board
     * @param height   the height of the board
     * @param tiles    the arraylist of tiles to be drawn on the board
     * @param player   the player to be drawn on the board
     * @param timer    the timer to be drawn on the board
     * @param entities the entities to be drawn on the board
     */
    public Board(int width, int height, Tile[][] tiles,
            ArrayList<Entity> entities, Player player, Timer timer) {
        this.width = width;
        this.height = height;
        this.tiles = tiles;
        this.player = player;
        this.entities = entities;
        this.timer = timer;
        for (Entity elem : this.entities) {
            if (elem instanceof SmartThief) {
                this.smartThiefTimeline = new Timeline(new KeyFrame(
                        Duration.millis(((SmartThief) elem).getMovementTimer()),
                        event -> ((SmartThief) elem).move(gc, this)));
                this.smartThiefTimeline.setCycleCount(Animation.INDEFINITE);
                this.smartThiefTimeline.playFromStart();
            } else if (elem instanceof FlyingAssassin) {
                this.flyingAssassinTimeline = new Timeline(new KeyFrame(
                        Duration.millis(
                                ((FlyingAssassin) elem).getMovementTimer()),
                        event -> ((FlyingAssassin) elem).move(gc, this)));
                this.flyingAssassinTimeline.setCycleCount(Animation.INDEFINITE);
                this.flyingAssassinTimeline.playFromStart();
            } else if (elem instanceof FloorFollowingThief) {
                this.floorFollowingThiefTimeline = new Timeline(new KeyFrame(
                        Duration.millis(((FloorFollowingThief) elem)
                                .getMovementTimer()),
                        event -> ((FloorFollowingThief) elem).move(gc)));
                this.floorFollowingThiefTimeline
                        .setCycleCount(Animation.INDEFINITE);
                this.floorFollowingThiefTimeline.playFromStart();
            }
        }
    }

    /**
     * Retrieve the tile with the inputted coordinates
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the tile with matching coordinates
     */
    public Tile getTile(int x, int y) {
        return this.tiles[x][y];
    }

    /**
     * Retrieve the player
     *
     * @return the player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Retrieve the player
     *
     * @return the player
     */
    public Timer getTimer() {
        return this.timer;
    }

    /**
     * Retrieve the height of the board
     *
     * @return the height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Retrieve the width of the board
     *
     * @return the width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Draw the tiles and player on the board
     *
     * @param gc the reference to the canvas to be drawn on
     */
    @Override
    public void draw(GraphicsContext gc) {
        Image redSquareImg = new Image("redSquare.png");
        Image greenSquareImg = new Image("greenSquare.png");
        Image blueSquareImg = new Image("blueSquare.png");
        Image yellowSquareImg = new Image("yellowSquare.png");
        Image doorImg = new Image("door.png");

        // Draw all tiles on screen to make up the board
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles[0].length; j++) {
                double xPos = j * 100;
                double yPos = i * 100;
                char[] colourArray = this.tiles[i][j].getColours();

                // Colour for top left square in a tile
                if (colourArray[0] == 'r') {
                    gc.drawImage(redSquareImg, xPos, yPos);
                } else if (colourArray[0] == 'g') {
                    gc.drawImage(greenSquareImg, xPos, yPos);
                } else if (colourArray[0] == 'b') {
                    gc.drawImage(blueSquareImg, xPos, yPos);
                } else if (colourArray[0] == 'y') {
                    gc.drawImage(yellowSquareImg, xPos, yPos);
                }

                // Colour for top right square in a tile
                if (colourArray[1] == 'r') {
                    gc.drawImage(redSquareImg, xPos + 50, yPos);
                } else if (colourArray[1] == 'g') {
                    gc.drawImage(greenSquareImg, xPos + 50, yPos);
                } else if (colourArray[1] == 'b') {
                    gc.drawImage(blueSquareImg, xPos + 50, yPos);
                } else if (colourArray[1] == 'y') {
                    gc.drawImage(yellowSquareImg, xPos + 50, yPos);
                }

                // Colour for bottom right square in a tile
                if (colourArray[2] == 'r') {
                    gc.drawImage(redSquareImg, xPos + 50, yPos + 50);
                } else if (colourArray[2] == 'g') {
                    gc.drawImage(greenSquareImg, xPos + 50, yPos + 50);
                } else if (colourArray[2] == 'b') {
                    gc.drawImage(blueSquareImg, xPos + 50, yPos + 50);
                } else if (colourArray[2] == 'y') {
                    gc.drawImage(yellowSquareImg, xPos + 50, yPos + 50);
                }

                // Colour for bottom left square in a tile
                if (colourArray[3] == 'r') {
                    gc.drawImage(redSquareImg, xPos, yPos + 50);
                } else if (colourArray[3] == 'g') {
                    gc.drawImage(greenSquareImg, xPos, yPos + 50);
                } else if (colourArray[3] == 'b') {
                    gc.drawImage(blueSquareImg, xPos, yPos + 50);
                } else if (colourArray[3] == 'y') {
                    gc.drawImage(yellowSquareImg, xPos, yPos + 50);
                }
            }
        }

        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles[0].length; j++) {
                if (tiles[i][j].getDoor() != null) {
                    int doorXDefaultOffset = 25;
                    int doorYDefaultOffset = 25;
                    int xCoord = tiles[i][j].getDoor().getXCoord();
                    int yCoord = tiles[i][j].getDoor().getYCoord();
                    for (int k = 0; k < xCoord; k++) {
                        doorXDefaultOffset += 100;
                    }
                    for (int k = 0; k < yCoord; k++) {
                        doorYDefaultOffset += 100;
                    }
                    gc.drawImage(doorImg, xCoord + doorXDefaultOffset,
                            yCoord + doorYDefaultOffset);
                }
            }
        }
        this.player.draw(gc);
        for (Entity elem : this.getEntities()) {
            elem.draw(gc);
        }
    }

    /**
     * Set the entities on the board
     * 
     * @param entities the entities to set
     */
    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    /**
     * Set the player on the board
     * 
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Retrieve the entities list from the board
     * 
     * @return ArrayList
     */
    public ArrayList<Entity> getEntities() {
        return this.entities;
    }

    /**
     * @param x The entity to be removed from the board
     */
    public void removeEntity(Entity x) {
        this.entities.remove(x);
    }

    /**
     * @param x The entity to be added to the board
     */
    public void addEntity(Entity x) {
        this.entities.add(x);
    }

    /**
     * @return Tile[][]
     */
    public Tile[][] getTiles() {
        return this.tiles;
    }

    /**
     * @return Door
     */
    public Door getDoor() {
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles[0].length; j++) {
                if (tiles[i][j].getDoor() != null) {
                    return tiles[i][j].getDoor();
                }
            }
        }
        return null;
    }

    /**
     * Should the player have died
     */
    public void missionFailed() {

    }

    /**
     * Pause the smart thief
     */
    public void pauseSmartThief() {
        this.smartThiefTimeline.stop();
    }

    /**
     * Pause the flying assassin
     */
    public void pauseFlyingAssassin() {
        this.flyingAssassinTimeline.stop();
    }

    /**
     * Pause the floor following thief
     */
    public void pauseFloorFollowingThief() {
        this.floorFollowingThiefTimeline.stop();
    }

    /**
     * Resume the smart thief
     */
    public void resumeSmartThief() {
        this.smartThiefTimeline.play();
    }

    /**
     * Resume the flying assassin
     */
    public void resumeFlyingAssassin() {
        this.flyingAssassinTimeline.play();
    }

    /**
     * Resume the floor following thief
     */
    public void resumeFloorFollowingThief() {
        this.floorFollowingThiefTimeline.play();
    }

    /**
     * Set the graphics context
     * 
     * @param gc GraphicsContext
     */
    public void setGraphicsContext(GraphicsContext gc) {
        this.gc = gc;
    }
}
