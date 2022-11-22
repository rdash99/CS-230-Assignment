package com.example.cs230assignment;

public class Entity extends DrawShape{
    private String entityName;
    private int[][] coords;

    public Entity() {

    }

    public void spawn() {
        //Entity's start position
    }

    @Override public void draw() {
        //Draw item on the board
    }

    public String getEntityName() {
        return this.entityName;
    }
}
