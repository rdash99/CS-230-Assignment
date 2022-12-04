package com.example.cs230assignment;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Sample application that demonstrates the use of JavaFX Canvas for a Game.
 * This class is intentionally not structured very well. This is just a starting point to show
 * how to draw an image on a canvas, respond to arrow key presses, use a tick method that is
 * called periodically, and use drag and drop.
 * 
 * Do not build the whole application in one file. This file should probably remain very small.
 *
 * @author Liam O'Reilly
 */
public class Main extends Application {
	private static final int DEFAULT_WINDOW_WIDTH = 700;
	private static final int DEFAULT_WINDOW_HEIGHT = 700;
	private static final int DEFAULT_CANVAS_WIDTH = 600;
	private static final int DEFAULT_CANVAS_HEIGHT = 600;

	private Canvas canvas;
	private GraphicsContext gc;

	/**
	 * Setup the new application.
	 * @param primaryStage The stage that is to be used for the application.
	 */
	public void start(Stage primaryStage) {
		new MainMenu();
	}
	        	
	public static void main(String[] args) {
		launch(args);
	}
}