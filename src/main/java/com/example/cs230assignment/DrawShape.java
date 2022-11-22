package com.example.cs230assignment;

import javafx.scene.canvas.GraphicsContext;

/**
 * This class is used to force all classes below it to implement the draw
 * method.
 * 
 * @author Rowan
 * @version 1.0
 */

public abstract class DrawShape {
    public abstract void draw(GraphicsContext g);
}
