package com.example.cs230assignment;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Class that models the board which is drawn on the canvas for the user to play on.
 *
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
    private int levelTime;

    /**
     * Construct the board with to be played on.
     *
     * @param width the width of the board
     * @param height the height of the board
     * @param tiles the arraylist of tiles to be drawn on the board
     * @param player the player to be drawn on the board
     */
    public Board(int width, int height, Tile[][] tiles, ArrayList<Entity> entities, Player player, int levelTime) {
        this.width = width;
        this.height = height;
        this.tiles = tiles;
        this.player = player;
        this.entities = entities;
        this.levelTime = levelTime;
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
     * Draw the tiles and player on the board
     *
     * @param gc the reference to the canvas to be drawn on
     */
    @Override
    public void draw(GraphicsContext gc) {
        Color red = Color.RED;
        Color green = Color.GREEN;
        Color blue = Color.BLUE;
        Color yellow = Color.YELLOW;
        Color purple = Color.PURPLE;

        //Draw all tiles on screen to make up the board
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles[0].length; j++) {
                double xPos = j * 50;
                double yPos = i * 50;
                char[] colourArray = this.tiles[i][j].getColours();
                System.out.println(colourArray[0]);

                //Colour for top left square in a tile
                if (colourArray[0] == 'r') {
                    gc.setFill(red);
                    gc.fillRect(xPos, yPos, DEFAULT_TILE_WIDTH/4, DEFAULT_TILE_HEIGHT/4);
                } else if (colourArray[0] == 'g') {
                    gc.setFill(green);
                    gc.fillRect(xPos, yPos, DEFAULT_TILE_WIDTH/4, DEFAULT_TILE_HEIGHT/4);
                } else if (colourArray[0] == 'b') {
                    gc.setFill(blue);
                    gc.fillRect(xPos, yPos, DEFAULT_TILE_WIDTH/4, DEFAULT_TILE_HEIGHT/4);
                } else if (colourArray[0] == 'y') {
                    gc.setFill(yellow);
                    gc.fillRect(xPos, yPos, DEFAULT_TILE_WIDTH/4, DEFAULT_TILE_HEIGHT/4);
                }

                //Colour for top right square in a tile
                if (colourArray[1] == 'r') {
                    gc.setFill(red);
                    gc.fillRect(xPos + DEFAULT_X_OFFSET, yPos, DEFAULT_TILE_WIDTH/4, DEFAULT_TILE_HEIGHT/4);
                } else if (colourArray[1] == 'g') {
                    gc.setFill(green);
                    gc.fillRect(xPos + DEFAULT_X_OFFSET, yPos, DEFAULT_TILE_WIDTH/4, DEFAULT_TILE_HEIGHT/4);
                } else if (colourArray[1] == 'b') {
                    gc.setFill(blue);
                    gc.fillRect(xPos + DEFAULT_X_OFFSET, yPos, DEFAULT_TILE_WIDTH/4, DEFAULT_TILE_HEIGHT/4);
                } else if (colourArray[1] == 'y') {
                    gc.setFill(yellow);
                    gc.fillRect(xPos + DEFAULT_X_OFFSET, yPos, DEFAULT_TILE_WIDTH/4, DEFAULT_TILE_HEIGHT/4);
                }

                //Colour for bottom right square in a tile
                if (colourArray[2] == 'r') {
                    gc.setFill(red);
                    gc.fillRect(xPos + DEFAULT_X_OFFSET, yPos + DEFAULT_Y_OFFSET, DEFAULT_TILE_WIDTH/4, DEFAULT_TILE_HEIGHT/4);
                } else if (colourArray[2] == 'g') {
                    gc.setFill(green);
                    gc.fillRect(xPos + DEFAULT_X_OFFSET, yPos + DEFAULT_Y_OFFSET, DEFAULT_TILE_WIDTH/4, DEFAULT_TILE_HEIGHT/4);
                } else if (colourArray[2] == 'b') {
                    gc.setFill(blue);
                    gc.fillRect(xPos + DEFAULT_X_OFFSET, yPos + DEFAULT_Y_OFFSET, DEFAULT_TILE_WIDTH/4, DEFAULT_TILE_HEIGHT/4);
                } else if (colourArray[2] == 'y') {
                    gc.setFill(yellow);
                    gc.fillRect(xPos + DEFAULT_X_OFFSET, yPos + DEFAULT_Y_OFFSET, DEFAULT_TILE_WIDTH/4, DEFAULT_TILE_HEIGHT/4);
                }

                //Colour for bottom left square in a tile
                if (colourArray[3] == 'r') {
                    gc.setFill(red);
                    gc.fillRect(xPos, yPos + DEFAULT_Y_OFFSET, DEFAULT_TILE_WIDTH/4, DEFAULT_TILE_HEIGHT/4);
                } else if (colourArray[3] == 'g') {
                    gc.setFill(green);
                    gc.fillRect(xPos, yPos + DEFAULT_Y_OFFSET, DEFAULT_TILE_WIDTH/4, DEFAULT_TILE_HEIGHT/4);
                } else if (colourArray[3] == 'b') {
                    gc.setFill(blue);
                    gc.fillRect(xPos, yPos + DEFAULT_Y_OFFSET, DEFAULT_TILE_WIDTH/4, DEFAULT_TILE_HEIGHT/4);
                } else if (colourArray[3] == 'y') {
                    gc.setFill(yellow);
                    gc.fillRect(xPos, yPos + DEFAULT_Y_OFFSET, DEFAULT_TILE_WIDTH/4, DEFAULT_TILE_HEIGHT/4);
                }
            }
        }

        //Draw player on board filled in purple
        int playerXCoord = this.player.getCoord()[0];
        int playerYCoord = this.player.getCoord()[1];
        gc.setFill(purple);
        gc.fillOval(playerXCoord, playerYCoord, DEFAULT_PLAYER_WIDTH, DEFAULT_PLAYER_HEIGHT);
    }
}
