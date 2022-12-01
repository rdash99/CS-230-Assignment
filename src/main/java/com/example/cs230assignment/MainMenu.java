package com.example.cs230assignment;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainMenu extends Stage {
    Button openOther = new Button("Start the game");
    Label z = new Label("Main Menu placeholder");
    HBox x = new HBox();

    public MainMenu() {
        x.getChildren().add(openOther);
        x.getChildren().add(z);
        this.setScene(new Scene(x, 300, 300));
        this.show();

        openOther.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                new GameGUI();
            }//end action
        });
    }
}
