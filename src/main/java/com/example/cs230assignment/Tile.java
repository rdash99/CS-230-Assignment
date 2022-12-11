package com.example.cs230assignment;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

/**
 * This class is used to design the tiles that go on a board.
 * 
 * @author Thomas McAuley
 * @version 1.0
 */
public class Tile {
    private char[] squares;
    private Entity entity;
    private Door door;

    /**
     * This is the constructor for the tile class
     * 
     * @param charAt  the first colour of the tile
     * @param charAt2 the second colour of the tile
     * @param charAt3 the third colour of the tile
     * @param charAt4 the fourth colour of the tile
     */
    public Tile(char charAt, char charAt2, char charAt3, char charAt4) {
        this.squares = new char[] { charAt, charAt2, charAt3, charAt4 };
    }

    /**
     * @return char[]
     */
    public char[] getColours() {
        return this.squares;
    }

    /**
     * @return Entity
     */
    public Entity getEntity() {
        return this.entity;
    }

    /**
     * @return Door
     */
    public Door getDoor() {
        return this.door;
    }

    /**
     * @param doorSet
     */
    public void setDoor(Door doorSet) {
        this.door = doorSet;
    }

    /**
     * @param colour
     * @return boolean
     */
    public boolean checkColour(char colour) {
        for (char squareColour : this.squares) {
            if (colour == squareColour) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param entity
     */
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public void removeEntity() {
        this.entity = null;
    }

    /**
     * @return String
     */
    public String getSquares() {
        return String.valueOf(this.squares);
    }
}
