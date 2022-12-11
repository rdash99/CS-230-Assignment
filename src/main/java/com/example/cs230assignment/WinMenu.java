package com.example.cs230assignment;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * The Win Menu class. It creates the window that appears when the player wins
 * the game
 * which contains a button to retry the game and a button to quit the game.
 * 
 * @author Fraser Clough
 * @version 1.0
 */

public class WinMenu extends Stage {
    private BorderPane root = new BorderPane();
    private Label scoreLabel = new Label();
    private Button retryBtn = new Button("Retry");
    private Button quitBtn = new Button("Quit");
    private Text titleText = new Text("You Win!");
    private Label scoreLabel = new Label();
    private VBox vbox = new VBox();
    private HBox hbox = new HBox();
    private Board board;
    private String playerName;
    private GameGUI originalWindow;
    private int score;

    /**
     * Creates a new Win Menu when this class is instantiated.
     * 
     * @param playerName     The name of the player.
     * @param originalWindow The original window of the game.
     */
    public WinMenu(String playerName, GameGUI originalWindow, int score) {
        this.playerName = playerName;
        this.originalWindow = originalWindow;
        this.score = score;
        vbox.getChildren().add(scoreLabel);
        this.initStyle(StageStyle.UNDECORATED);
        root.setBackground(new Background(new BackgroundFill(Color.DARKGREEN,
                CornerRadii.EMPTY, Insets.EMPTY)));

        titleText.setFont(Font.font("Arial", FontPosture.ITALIC, 18));
        titleText.setFill(Color.YELLOW);
        scoreLabel.setText("" + this.score);

        vbox.getChildren().add(retryBtn);
        vbox.getChildren().add(scoreLabel);
        vbox.getChildren().add(quitBtn);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);

        hbox.getChildren().add(titleText);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(20, 10, 10, 10));

        root.setTop(hbox);
        root.setCenter(vbox);

        this.setScene(new Scene(root, 300, 200));
        this.setTitle("You Win");
        this.show();

        retryBtn.setOnAction(e -> {
            this.close();
            // Load next level
        });

        quitBtn.setOnAction(e -> {
            this.close();
            this.originalWindow.close();
            try {
                new MainMenu();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
    }
}
