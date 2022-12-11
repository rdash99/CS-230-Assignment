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
     * Returns the colours for the tile.
     * 
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
     * Sets the door of the tile to the door passed in if and only if the door
     * is supposed to be there.
     * 
     * @param doorSet the door to be set
     */
    public void setDoor(Door doorSet) {
        this.door = doorSet;
    }

    /**
     * Checks if the colour passed is in the tile.
     * 
     * @param colour the colour to be checked
     * @return boolean true if the colour is in the tile, false otherwise
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
     * Sets the entity of the tile to the entity passed.
     * 
     * @param entity the entity to be set
     */
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * Removes the entity from the tile.
     */
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
