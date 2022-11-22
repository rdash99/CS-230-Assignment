package com.example.cs230assignment;

import javafx.scene.canvas.GraphicsContext;

public class Entity extends DrawShape{
    private String entityName;
    private int[][] coords;

    public Entity() {

    }

    public void spawn() {
        //Entity's start position
    }

    @Override public void draw(GraphicsContext g) {
        //Draw item on the board
    }

    public String getEntityName() {
        return this.entityName;
    }
}
