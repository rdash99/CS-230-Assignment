package com.example.cs230assignment;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Tile {
    private char[] squares;
    private ArrayList<Entity> entityList; // Need to implement Entity class

    public Tile(char charAt, char charAt2, char charAt3, char charAt4) {
        this.squares = new char[] { charAt, charAt2, charAt3, charAt4 };
    }

    public char[] getColours() {
        return this.squares;
    }

    public Entity[] getEntities() {
        Entity[] entities = new Entity[entityList.size()];
        entities = entityList.toArray(entities);
        return entities;
    }

    public boolean checkColour(char colour) {
        for (char squareColour : this.squares) {
            if (colour == squareColour) {
                return true;
            }
        }
        return false;
    }

    public void addEntity(Entity entity) {
        this.entityList.add(entity);
    }

    public void removeEntity(Entity entity) {
        for (Entity elem : this.entityList) {
            if (entity.equals(elem)) {
                entityList.remove(elem);
            }
        }
    }
}
