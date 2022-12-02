package com.example.cs230assignment;

import javafx.scene.canvas.GraphicsContext;

public class Board extends DrawShape {
    private static final int DEFAULT_TILE_WIDTH = 100;
    private static final int DEFAULT_TILE_HEIGHT = 100;
    private Tile[][] tiles;
    private Player player;

    public Board(int width, int height, Tile[] tiles) {
        this.tiles = new Tile[height][width];
        int index = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.tiles[i][j] = tiles[index];
                index++;
            }
        }
    }

    public Tile getTile(int x, int y) {
        return this.tiles[x][y];
    }

    public Player getPlayer() {
        return this.player;
    }

    @Override
    public void draw(GraphicsContext gc) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                double xPos = j * 50;
                double yPos = i * 50;
                gc.strokeRect(xPos, yPos, DEFAULT_TILE_WIDTH/4, DEFAULT_TILE_HEIGHT/4);
                gc.strokeRect(xPos + 25, yPos, DEFAULT_TILE_WIDTH/4, DEFAULT_TILE_HEIGHT/4);
                gc.strokeRect(xPos, yPos + 25, DEFAULT_TILE_WIDTH/4, DEFAULT_TILE_HEIGHT/4);
                gc.strokeRect(xPos + 25, yPos + 25, DEFAULT_TILE_WIDTH/4, DEFAULT_TILE_HEIGHT/4);
            }
        }

    }
}
