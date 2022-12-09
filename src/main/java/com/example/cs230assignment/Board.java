package com.example.cs230assignment;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

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
        this.player.draw(gc);
        for (Entity elem : this.getEntities()) {
            elem.draw(gc);
            if (elem instanceof SmartThief) {
                ((SmartThief) elem).findClosestInteractable(this).getEntityName();
            }
        }
            //Call entity NPC movement from here perhaps?
//            if (elem instanceof FlyingAssassin) {
//                System.out.println("FA: " + elem.getXCoord() + " " + elem.getYCoord());
//                ((FlyingAssassin) elem).move();
//            }
//            } else if (elem instanceof FloorFollowingThief) {
//                System.out.println("FFT: " + elem.getXCoord() + " " + elem.getYCoord());
//                ((FloorFollowingThief) elem).move();
//            }
        }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Entity> getEntities() {
        return this.entities;
    }

    public Tile[][] getTiles() {
        return this.tiles;
    }

    public void missionFailed() {

    }
}
