package com.example.cs230assignment;

import javafx.scene.canvas.GraphicsContext;

public class Board extends DrawShape{
    private Tile[][] tiles;
    private int[][] dimensions;
    private Player player;

    public Tile getTile(int x, int y) {
        return this.tiles[x][y];
    }

    public int[][] getDimensions() {
        return this.dimensions;
    }

    public Player getPlayer() {
        return this.player;
    }

    @Override
    public void draw() {
    }
}
