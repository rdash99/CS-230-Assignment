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

	public Pane buildCanvas() {
		//Hard coded test values
		Tile tile1 = new Tile('r', 'g', 'b', 'y');
		Tile tile2 = new Tile('r', 'g', 'b', 'y');
		Tile tile3 = new Tile('r', 'g', 'b', 'y');
		Tile tile4 = new Tile('r', 'g', 'b', 'y');
		Tile tile5 = new Tile('r', 'g', 'b', 'y');
		Tile tile6 = new Tile('r', 'g', 'b', 'y');
		Tile tile7 = new Tile('r', 'g', 'b', 'y');
		Tile tile8 = new Tile('r', 'g', 'b', 'y');
		Tile tile9 = new Tile('r', 'g', 'b', 'y');
		Tile tile10 = new Tile('r', 'g', 'b', 'y');
		Tile[] tiles = new Tile[] {tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9, tile10};
		Board board = new Board(5, 2, tiles);
		BorderPane root = new BorderPane();

		canvas = new Canvas(DEFAULT_CANVAS_WIDTH, DEFAULT_CANVAS_HEIGHT);
		gc = canvas.getGraphicsContext2D();

		root.setCenter(canvas);
		board.draw(gc);

		return root;
	}
	        	
	public static void main(String[] args) {
		launch(args);
	}
}