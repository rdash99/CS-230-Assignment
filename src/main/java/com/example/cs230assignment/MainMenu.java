package com.example.cs230assignment;

import javafx.geometry.Insets;
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

    private BorderPane root = new BorderPane();
    private Button launchGameBtn = new Button("Start the game");
    private Text titleText = new Text ("Welcome to the game!");
    private VBox vbox = new VBox();
    private HBox hbox = new HBox();

    public MainMenu() {
        titleText.setFont(Font.font ("Arial", FontWeight.BOLD, 20));
        titleText.setFill(Color.GRAY);

        vbox.getChildren().add(launchGameBtn);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);

        hbox.getChildren().add(titleText);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(20,10,10,10));

        root.setTop(hbox);
        root.setCenter(vbox);

        this.setScene(new Scene(root, 400, 300));
        this.setTitle("The game menu");
        this.show();

        //end action
        launchGameBtn.setOnAction(e -> {
            new GameGUI();
            this.close();
        } );

    }
}
