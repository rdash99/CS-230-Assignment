package com.example.cs230assignment;

/**
 * Defines the Entity class. This class is the parent class for several other
 * classes.
 * 
 * @author Rowan Dash
 * @version 1.0
 */
public class Entity extends DrawShape {
    private String entityName;
    protected int[] coord;

    public Entity(String entityNamePass, int[] coordPass) {
        this.coord = coordPass;
        this.entityName = entityNamePass;
    }

    public String getEntityName() {
        return this.entityName;
    }

    public int[] getCoord() {
        return this.coord;
    }

    @Override
    public void draw() {
        // TODO Auto-generated method stub

    }

}
