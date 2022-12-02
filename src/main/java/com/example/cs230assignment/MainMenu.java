package com.example.cs230assignment;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenu extends Stage {

    BorderPane root = new BorderPane();
    Button launchGameBtn = new Button("Start the game");
    Text titleText = new Text ("Welcome to the game!");
    VBox vbox = new VBox();
    HBox hbox = new HBox();

    public MainMenu() {
        titleText.setFont(Font.font ("Arial", FontWeight.BOLD, 20));
        titleText.setFill(Color.GRAY);

        vbox.getChildren().add(launchGameBtn);
        hbox.getChildren().add(titleText);

        hbox.setAlignment(Pos.CENTER);

        root.setTop(hbox);
        root.setCenter(launchGameBtn);

        this.setScene(new Scene(root, 300, 300));
        this.setTitle("The game menu");
        this.show();

        //end action
        launchGameBtn.setOnAction(t -> new GameGUI());
    }
}
