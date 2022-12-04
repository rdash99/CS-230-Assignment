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
import java.io.IOException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class MainMenu extends Stage {


    private BorderPane root = new BorderPane();
    private Button launchGameBtn = new Button("Start the game");
    private Text titleText = new Text("Welcome to the game!");
    private Text scoresTitle = new Text("High scores: ");
    private VBox middleButtons = new VBox();
    private VBox highScoreList = new VBox();
    private VBox mOTD = new VBox();
    private HBox hbox = new HBox();

    public MainMenu() {
        titleText.setFont(Font.font ("Arial", FontWeight.BOLD, 20));
        titleText.setFill(Color.GRAY);

        scoresTitle.setFont(Font.font ("Arial",FontWeight.BOLD, 15));
        scoresTitle.setFill(Color.GRAY);

        middleButtons.getChildren().add(launchGameBtn);
        middleButtons.setAlignment(Pos.CENTER);
        middleButtons.setSpacing(20);

        highScoreList.getChildren().add(scoresTitle);

        hbox.getChildren().add(titleText);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(20,10,10,10));



        root.setTop(hbox);
        root.setCenter(middleButtons);
        root.setLeft(highScoreList);
        root.setRight(mOTD);

        this.setScene(new Scene(root, 600, 500));
        this.setTitle("The game menu");
        this.show();

        //end action
        launchGameBtn.setOnAction(e -> {
            new GameGUI();
            this.close();
        } );


        }
    public void addHighScore(String name, int score) {
        Text scoreText = new Text(name + ": " + score);
        scoreText.setFont(Font.font("Arial", 12));
        scoreText.setFill(Color.GRAY);
        highScoreList.getChildren().add(scoreText);
    }

    private static void synchronousRequest() throws IOException, InterruptedException {
        //here
    }
}


