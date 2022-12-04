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
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MainMenu extends Stage {


    private BorderPane root = new BorderPane();
    private Button launchGameBtn = new Button("Start the game");
    private Text titleText = new Text("Welcome to the game!");
    private Text scoresTitle = new Text("High scores: ");
    private Text motdTitle = new Text("Message of the day: ");
    private VBox middleButtons = new VBox();
    private VBox highScoreList = new VBox();
    private VBox motd = new VBox();
    private HBox hbox = new HBox();



    public MainMenu() throws IOException, InterruptedException {
        titleText.setFont(Font.font ("Arial", FontWeight.BOLD, 20));
        titleText.setFill(Color.GRAY);

        scoresTitle.setFont(Font.font ("Arial",FontWeight.BOLD, 15));
        scoresTitle.setFill(Color.GRAY);

        motdTitle.setFont(Font.font ("Arial",FontWeight.BOLD, 15));
        motdTitle.setFill(Color.GRAY);

        middleButtons.getChildren().add(launchGameBtn);
        middleButtons.setAlignment(Pos.CENTER);
        middleButtons.setSpacing(20);

        highScoreList.getChildren().add(scoresTitle);

        motd.getChildren().add(motdTitle);
        motd.getChildren().add(new Text(getsolvedMOTD(solvePuzzle())));

        hbox.getChildren().add(titleText);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(20,10,10,10));



        root.setTop(hbox);
        root.setCenter(middleButtons);
        root.setLeft(highScoreList);
        root.setRight(motd);

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

    private String getMOTDPuzzle() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://cswebcat.swansea.ac.uk/puzzle"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private String solvePuzzle() throws IOException, InterruptedException {
        String puzzle = getMOTDPuzzle();
        String answer = "";

        //TODO: implement this method
        return answer;
    }

    private String getsolvedMOTD(String puzzle) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://cswebcat.swansea.ac.uk/message?solution=" + puzzle))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }


}


