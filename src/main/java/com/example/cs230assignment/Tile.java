package com.example.cs230assignment;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Tile {
    private char[] squares;
    private Entity entity; // Need to implement Entity class
    private Door door;

    public Tile(char charAt, char charAt2, char charAt3, char charAt4) {
        this.squares = new char[] { charAt, charAt2, charAt3, charAt4 };
    }

    public char[] getColours() {
        return this.squares;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public Door getDoor() {
        return this.door;
    }

    public void setDoor(Door doorSet) {
        this.door = doorSet;
    }

    public boolean checkColour(char colour) {
        for (char squareColour : this.squares) {
            if (colour == squareColour) {
                return true;
            }
        }
        return false;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public void removeEntity() {
        this.entity = null;
    }

    public String getSquares() {
        return String.valueOf(this.squares);
    }
}
