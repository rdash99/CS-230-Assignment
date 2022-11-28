package com.example.cs230assignment;

public class Board {
    private Tile[][] tiles;


    public getTile(int[] coordPass){
        return this.tiles[coordPass[0]][coordPass[1]];
    }
}
