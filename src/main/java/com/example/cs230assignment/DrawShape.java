package com.example.cs230assignment;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class is used to force all classes below it to implement the draw
 * method.
 * 
 * @author Rowan
 * @version 1.0
 */

public abstract class DrawShape {

    /**
     * This method will be used to draw the thing on the canvas.
     * 
     * @param gc the graphics context of the canvas
     */
    public abstract void draw(GraphicsContext gc);
}
