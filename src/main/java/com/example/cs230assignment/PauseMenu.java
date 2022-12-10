package com.example.cs230assignment;

import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.Console;

/**
 * The pause menu class. It is used to display the pause menu which contains
 * options to resume the game or quit the game and save.
 * 
 * @author Fraser Clough
 * @version 1.0
 */
public class PauseMenu extends Stage {

    private BorderPane root = new BorderPane();
    private Button resumeBtn = new Button("Resume");
    private Button exitBtn = new Button("Save & Exit");
    private Text titleText = new Text("Game is paused");
    private VBox vbox = new VBox();
    private HBox hbox = new HBox();
    private Board board;
    private String levelName;

    /**
     * Creates a new Pause Menu when this class is instantiated.
     * 
     * @param board        the board of the games.
     * @param levelName    what level the player is on.
     * @param tickTimeline to control the game's ticks.
     */
    public PauseMenu(Board board, String levelName, Timeline tickTimeline) {
        this.board = board;
        this.levelName = levelName;
        this.initStyle(StageStyle.UNDECORATED);
        root.setBackground(new Background(new BackgroundFill(Color.GRAY,
                CornerRadii.EMPTY, Insets.EMPTY)));

        titleText.setFont(Font.font("Arial", FontPosture.ITALIC, 18));
        titleText.setFill(Color.BLACK);

        vbox.getChildren().add(resumeBtn);
        vbox.getChildren().add(exitBtn);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);

        hbox.getChildren().add(titleText);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(20, 10, 10, 10));

        root.setTop(hbox);
        root.setCenter(vbox);

        this.setScene(new Scene(root, 300, 200));
        this.setTitle("Pause menu");
        this.show();

        resumeBtn.setOnAction(e -> {
            this.close();
            tickTimeline.play();
        });

        exitBtn.setOnAction(e -> {
            FileHandler.saveGame(this.board, this.levelName);
            System.exit(0);
        });

    }

}
