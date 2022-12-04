package com.example.cs230assignment;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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
    private Player player;

    /**
     * Construct the board with to be played on.
     *
     * @param width the width of the board
     * @param height the height of the board
     * @param tiles the arraylist of tiles to be drawn on the board
     * @param player the player to be drawn on the board
     */
    public Board(int width, int height, Tile[] tiles, Player player) {
        this.player = player;
        this.tiles = new Tile[height][width];
        int index = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.tiles[i][j] = tiles[index];
                index++;
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
     * Draw the tiles and player on the board
     *
     * @param gc the reference to the canvas to be drawn on
     */
    @Override
    public void draw(GraphicsContext gc) {
        //Draw all tiles on screen to make up the board
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                double xPos = j * 50;
                double yPos = i * 50;
                gc.strokeRect(xPos, yPos, DEFAULT_TILE_WIDTH/4, DEFAULT_TILE_HEIGHT/4);
                gc.strokeRect(xPos + DEFAULT_X_OFFSET, yPos, DEFAULT_TILE_WIDTH/4, DEFAULT_TILE_HEIGHT/4);
                gc.strokeRect(xPos, yPos + DEFAULT_Y_OFFSET, DEFAULT_TILE_WIDTH/4, DEFAULT_TILE_HEIGHT/4);
                gc.strokeRect(xPos + DEFAULT_X_OFFSET, yPos + DEFAULT_Y_OFFSET, DEFAULT_TILE_WIDTH/4, DEFAULT_TILE_HEIGHT/4);
            }
        }
        //Draw player on board filled in green
        int playerXCoord = this.player.getCoord()[0];
        int playerYCoord = this.player.getCoord()[1];
        Color green = Color.GREEN;
        gc.setFill(green);
        gc.fillOval(playerXCoord, playerYCoord, DEFAULT_PLAYER_WIDTH, DEFAULT_PLAYER_HEIGHT);
    }
}
