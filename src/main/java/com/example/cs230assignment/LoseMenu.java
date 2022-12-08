package com.example.cs230assignment;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoseMenu extends Stage {
    private BorderPane root = new BorderPane();
    private Button retryBtn = new Button("Retry");
    private Button quitBtn = new Button("Quit");
    private Text titleText = new Text("Game Over");
    private VBox vbox = new VBox();
    private HBox hbox = new HBox();
    private String playerName;
    private GameGUI originalWindow;

    public LoseMenu(String playerName, GameGUI originalWindow) {
        this.playerName = playerName;
        this.originalWindow = originalWindow;
        root.setBackground(new Background(new BackgroundFill(Color.GRAY,
                CornerRadii.EMPTY, Insets.EMPTY)));

        titleText.setFont(Font.font("Arial", FontPosture.ITALIC, 18));
        titleText.setFill(Color.BLACK);

        vbox.getChildren().add(retryBtn);
        vbox.getChildren().add(quitBtn);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);

        hbox.getChildren().add(titleText);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(20, 10, 10, 10));

        root.setTop(hbox);
        root.setCenter(vbox);

        this.setScene(new Scene(root, 300, 200));
        this.setTitle("Game Over");
        this.show();

        retryBtn.setOnAction(e -> {
            // add code to resume ticks
            this.close();
            this.originalWindow.createGame(this.playerName);
        });
    }
}
