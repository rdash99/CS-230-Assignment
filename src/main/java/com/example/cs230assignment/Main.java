package com.example.cs230assignment;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

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
	
	/**
	 * Setup the new application.
	 * @param primaryStage The stage that is to be used for the application.
	 */
	public void start(Stage primaryStage) {
		new MainMenu();

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
	}
	        	
	public static void main(String[] args) {
		launch(args);
	}
}