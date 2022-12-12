package com.example.cs230assignment;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * Class that models the GUI for the game.
 * 
 * @author Fraser Clough
 */

public class GameGUI extends Stage {
    // The dimensions of the window
    private static final int ONE_SECOND_IN_MILLISECONDS = 1000;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 800;

    // The dimensions of the canvas
    private static final int CANVAS_WIDTH = 1000;
    private static final int CANVAS_HEIGHT = 1000;

    // The width and height (in pixels) of each cell that makes up the game.
    private static final int GRID_CELL_WIDTH = 50;
    private static final int GRID_CELL_HEIGHT = 50;

    // The width of the grid in number of cells.
    private static final int GRID_WIDTH = 12;

    // The canvas in the GUI. This needs to be a global variable
    // (in this setup) as we need to access it in different methods.
    // We could use FXML to place code in the controller instead.
    private Canvas canvas;
    private GraphicsContext gc;
    private Timeline tickTimeline;
    private Board level;
    private Label levelTimeLabel;
    private String levelName;

    /**
     * Create the game.
     * 
     * @param playerName The name of the player.
     */
    public GameGUI(String playerName, Board level) {
        this.level = level;
        createGame(playerName, level);
    }

    /**
     * Draw the game on the canvas.
     */
    public void drawGame() {
        // //Hard coded test values - Thom
        // Tile tile1 = new Tile('r', 'r', 'r', 'r');
        // Tile tile2 = new Tile('r', 'g', 'b', 'y');
        // Tile tile3 = new Tile('r', 'g', 'b', 'b');
        // Tile tile4 = new Tile('r', 'g', 'b', 'y');
        // Tile tile5 = new Tile('r', 'r', 'y', 'y');
        // Tile tile6 = new Tile('g', 'g', 'g', 'y');
        // Tile tile7 = new Tile('r', 'g', 'b', 'y');
        // Tile tile8 = new Tile('r', 'g', 'b', 'y');
        // Tile tile9 = new Tile('r', 'g', 'b', 'y');
        // Tile tile10 = new Tile('r', 'g', 'b', 'y');
        // Tile[][] tiles = new Tile[2][5];
        // tiles[0][0] = tile1;
        // tiles[0][1] = tile2;
        // tiles[0][2] = tile3;
        // tiles[0][3] = tile4;
        // tiles[0][4] = tile5;
        // tiles[1][0] = tile6;
        // tiles[1][1] = tile7;
        // tiles[1][2] = tile8;
        // tiles[1][3] = tile9;
        // tiles[1][4] = tile10;
        // FlyingAssassin entity1 = new FlyingAssassin(1, 0, 1, 1, 2);
        // ArrayList<Entity> entities = new ArrayList<>();
        // entities.add(entity1);
        // Player player = new Player(0, 0);
        // Timer timer = new Timer(1);
        // Board board = new Board(5, 2, tiles, entities, player, timer);
    }

    /**
     * @param playerName The name of the player.
     */
    public void createGame(String playerName, Board level) {
        this.level.getDoor().setGameGUI(this);

        // Build the GUI
        Pane root = buildGUI(level);

        tickTimeline = new Timeline(new KeyFrame(
                Duration.millis(ONE_SECOND_IN_MILLISECONDS), event -> tick()));
        tickTimeline.setCycleCount(level.getTimer().getLevelTime());

        this.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode() == KeyCode.P || key.getCode() == KeyCode.ESCAPE) {
                new PauseMenu(level, "testLevel", this);
                for (Entity entity : level.getEntities()) {
                    if (entity instanceof SmartThief) {
                        level.pauseSmartThief();
                    } else if (entity instanceof FlyingAssassin) {
                        level.pauseFlyingAssassin();
                    } else if (entity instanceof FloorFollowingThief) {
                        level.pauseFloorFollowingThief();
                    }
                }
                pauseLevelTime();
            }
        });

        /*
         * // for testing only this.addEventFilter(KeyEvent.KEY_PRESSED, key ->
         * { if (key.getCode() == KeyCode.L) { new LoseMenu(playerName, this);
         * tickTimeline.stop(); } });
         * 
         * // for testing only this.addEventFilter(KeyEvent.KEY_PRESSED, key ->
         * { if (key.getCode() == KeyCode.M) { new WinMenu(playerName, this,
         * 500); tickTimeline.stop(); } });
         */
        // Create a scene from the GUI
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                case W:
                    level.getPlayer().move(gc, 1);
                    break;
                case A:
                    level.getPlayer().move(gc, 2);
                    break;
                case S:
                    level.getPlayer().move(gc, 3);
                    break;
                case D:
                    level.getPlayer().move(gc, 4);
                    break;
                }
            }
        });

        // Display the scene on the stage
        this.setScene(scene);
        this.setTitle("BACKSTREET THIEVES");
        this.show();
        level.draw(gc);
        tickTimeline.playFromStart();
    }

    /**
     * Create the GUI.
     * 
     * @param level The level to be displayed.
     * @return The panel that contains the created GUI.
     */
    public Pane buildGUI(Board level) {
        // Create top-level panel that will hold all GUI nodes.
        BorderPane root = new BorderPane();

        // Create the canvas that we will draw on.
        // We store this as a gloabl variable so other methods can access it.
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        root.setCenter(canvas);

        gc = canvas.getGraphicsContext2D();
        level.setGraphicsContext(gc);

        // Create a toolbar with some nice padding and spacing
        VBox levelTimeBox = new VBox();
        levelTimeBox.setPadding(new Insets(10, 10, 10, 10));
        root.setRight(levelTimeBox);

        // Display level time in levelTimeBox
        this.levelTimeLabel = new Label("" + level.getTimer().getLevelTime());
        levelTimeBox.getChildren().add(levelTimeLabel);

        // Finally, return the border pane we built up.
        return root;

    }

    /**
     * This method is called every second.
     */
    public void tick() {
        this.levelTimeLabel.setText("" + this.level.getTimer().decrementTime());
        if (this.level.getTimer().getLevelTime() == 0) {
            for (Entity elem : level.getEntities()) {
                if (elem instanceof SmartThief) {
                    level.pauseSmartThief();
                } else if (elem instanceof FlyingAssassin) {
                    level.pauseFlyingAssassin();
                } else if (elem instanceof FloorFollowingThief) {
                    level.pauseFloorFollowingThief();
                }
            }
            new LoseMenu(level.getPlayer().getPlayerName(), this,
                    this.level);
        }
    }

    /**
     * Pause the level timer.
     */
    public void pauseLevelTime() {
        this.tickTimeline.stop();
    }

    /**
     * Resume the level timer.
     */
    public void resumeLevelTime() {
        this.tickTimeline.play();
    }

    /**
     * Return the level.
     * 
     * @return the level
     */
    public Board getLevel() {
        return this.level;
    }
}
