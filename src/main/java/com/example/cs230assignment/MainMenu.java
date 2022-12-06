package com.example.cs230assignment;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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
import java.util.ArrayList;

public class MainMenu extends Stage {

    private BorderPane root = new BorderPane();
    private Button launchGameBtn = new Button("Start the game");
    private Text titleText = new Text("Welcome to the game!");
    private Text scoresTitle = new Text("High scores: ");
    private Text motdTitle = new Text("Message of the day: ");
    private Text motdText;
    private VBox middleButtons = new VBox();
    private VBox highScoreList = new VBox();
    private VBox motd = new VBox();
    private HBox hbox = new HBox();

    public MainMenu() throws IOException, InterruptedException {
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleText.setFill(Color.GRAY);

        scoresTitle.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        scoresTitle.setFill(Color.GRAY);
        scoresTitle.setWrappingWidth(100);

        motdTitle.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        motdTitle.setFill(Color.GRAY);
        motdTitle.setWrappingWidth(100);

        motdText = new Text(getsolvedMOTD(solvePuzzle()));
        motdText.setFont(Font.font("Arial", 12));
        motdText.setFill(Color.GRAY);
        motdText.setWrappingWidth(100);

        middleButtons.getChildren().add(launchGameBtn);
        middleButtons.setAlignment(Pos.CENTER);
        middleButtons.setSpacing(20);

        highScoreList.getChildren().add(scoresTitle);

        motd.getChildren().add(motdTitle);
        motd.getChildren().add(motdText);
        motd.setMaxWidth(100);
        motd.setMinWidth(100);

        hbox.getChildren().add(titleText);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(20, 10, 10, 10));

        root.setTop(hbox);
        root.setCenter(middleButtons);
        root.setLeft(scoresTitle);
        root.setRight(motd);

        this.setScene(new Scene(root, 600, 500));
        this.setTitle("The game menu");
        this.show();

        // end action
        launchGameBtn.setOnAction(e -> {
            new GameGUI(playerName);
            this.close();
        });

    }

    public void addHighScore(String name, int score) {
        Text scoreText = new Text(name + ": " + score);
        scoreText.setFont(Font.font("Arial", 12));
        scoreText.setFill(Color.GRAY);
        scoreText.setWrappingWidth(100);
        highScoreList.getChildren().add(scoreText);
    }

    private String getMOTDPuzzle() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://cswebcat.swansea.ac.uk/puzzle"))
                .build();
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private String solvePuzzle() throws IOException, InterruptedException {
        String puzzle = getMOTDPuzzle();
        ArrayList<Integer> charNums = new ArrayList<Integer>();

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < puzzle.length(); i++) {
            charNums.add((alphabet.indexOf(puzzle.charAt(i))));
        }

        for (int i = 0; i < charNums.size(); i++) {
            if (i % 2 != 0) {
                charNums.set(i, charNums.get(i) + (i + 1));
                if (charNums.get(i) > 26) {
                    charNums.set(i, (Math.abs(charNums.get(i) - 26)) % 26);
                } else if (charNums.get(i) < 0) {
                    charNums.set(i, (Math.abs(charNums.get(i) + 26)) % 26);
                } else if (charNums.get(i) == 26) {
                    charNums.set(i, 0);
                }

            } else if (i % 2 == 0) {
                charNums.set(i, charNums.get(i) - (i + 1));
                if (charNums.get(i) > 26) {
                    charNums.set(i, (Math.abs(charNums.get(i) - 26)) % 26);
                } else if (charNums.get(i) < 0) {
                    charNums.set(i, (Math.abs(charNums.get(i) + 26)) % 26);
                } else if (charNums.get(i) == 26) {
                    charNums.set(i, 0);
                }
            }
        }

        ArrayList<java.lang.Character> charRes = new ArrayList<java.lang.Character>();
        for (int i = 0; i < charNums.size(); i++) {
            charRes.add(alphabet.charAt(charNums.get(i)));
        }

        StringBuilder builder = new StringBuilder(charRes.size());
        for (java.lang.Character cha : charRes) {
            builder.append(cha);
        }
        String result = builder.toString().concat("CS-230");
        result = result.chars().count() + result;

        return result;
    }

    private String getsolvedMOTD(String puzzle)
            throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(
                "http://cswebcat.swansea.ac.uk/message?solution=" + puzzle))
                .build();
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

}
