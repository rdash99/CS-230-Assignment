package com.example.cs230assignment;

import java.util.ArrayList;

public class Tile {
    private char[] squares;
    private ArrayList <Entity> entityList; //Need to implement Entity class

    public char[] getColours() {
        return this.squares;
    }

    public ArrayList<Entity> getEntityList() {
        return this.entityList;
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
