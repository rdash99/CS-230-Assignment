package com.example.cs230assignment;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Defines the Entity class. This class is the parent class for several other
 * classes.
 * 
 * @author Rowan Dash
 * @version 1.0
 */

public class Entity extends DrawShape {
    protected String entityName;
    protected int[] coord;

    public Entity(String entityNamePass, int x, int y) {
        this.coord = new int[] { x, y };
        this.entityName = entityNamePass;
    }

    public String getEntityName() {
        return this.entityName;
    }

    public int[] getCoord() {
        return this.coord;
    }

    public int getXCoord() {
        return this.coord[0];
    }

    public int getYCoord() {
        return this.coord[1];
    }

    @Override
    public void draw(GraphicsContext gc) {
        //Stub method
    }

}
