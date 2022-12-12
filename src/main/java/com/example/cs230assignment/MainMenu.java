package com.example.cs230assignment;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
import java.util.ArrayList;

/**
 * The main menu class. It is used to display the main menu which contains the
 * scores, the message of the day and allows the player to enter their name and
 * start the game.
 * 
 * @author Fraser Clough
 * @version 1.0
 */

public class MainMenu extends Stage {

    private BorderPane root = new BorderPane();
    private Button launchGameBtn = new Button("Start the game");
    private Text titleText = new Text("Welcome to the game!");
    private Text scoresTitle = new Text("High scores: ");
    private Text motdTitle = new Text("Message of the day: ");
    private Text motdText;
    private TextField nameField = new TextField();
    private Text errorText = new Text();
    private VBox middleButtons = new VBox();
    private VBox highScoreList = new VBox();
    private VBox motd = new VBox();
    private HBox hbox = new HBox();

    /**
     * Creates a new Main Menu when this class is instantiated.
     * 
     * @throws IOException          if the http request fails.
     * @throws InterruptedException if the http request is interrupted.
     */
    public MainMenu() throws IOException, InterruptedException {
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        titleText.setFill(Color.GRAY);

        scoresTitle.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        scoresTitle.setFill(Color.GRAY);
        scoresTitle.setWrappingWidth(100);

        motdTitle.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        motdTitle.setFill(Color.GRAY);
        motdTitle.setWrappingWidth(100);

        motdText = new Text(getSolvedMOTD(solvePuzzle()));
        motdText.setFont(Font.font("Arial", 12));
        motdText.setFill(Color.GRAY);
        motdText.setWrappingWidth(100);

        nameField.setPromptText("Please enter your name");
        nameField.setMaxWidth(150);

        middleButtons.getChildren().add(nameField);
        middleButtons.getChildren().add(launchGameBtn);
        middleButtons.setAlignment(Pos.CENTER);
        middleButtons.setSpacing(20);

        highScoreList.getChildren().add(scoresTitle);
        addHighScores();
        highScoreList.setMaxWidth(100);
        highScoreList.setMinWidth(100);

        motd.getChildren().add(motdTitle);
        motd.getChildren().add(motdText);
        motd.setMaxWidth(100);
        motd.setMinWidth(100);

        hbox.getChildren().add(titleText);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(20, 10, 10, 10));


        root.setTop(hbox);
        root.setCenter(middleButtons);
        root.setLeft(highScoreList);
        root.setRight(motd);

        this.setScene(new Scene(root, 600, 500));
        this.setTitle("The game menu");
        this.show();

        // When the button is clicked, the game is once a name has been
        // provided.
        launchGameBtn.setOnAction(e -> {
            if ((nameField.getText() != null
                    && !nameField.getText().isEmpty())) {
                try {
                    new LevelSelectMenu(nameField.getText());
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                this.close();
            } else if (errorText.getText().isEmpty()) {
                errorText.setText("Please enter your name");
                errorText.setFill(Color.RED);
                middleButtons.getChildren().add(errorText);

            }
        });

    }

    /**
     * Adds the high scores to the high score list.
     */
    public void addHighScores() {
        ArrayList<String> highScores = FileHandler.readPlayerScores();
        for (String score : highScores) {
            Text highScore = new Text(score);
            highScore.setFont(Font.font("Arial", 12));
            highScore.setFill(Color.GRAY);
            highScore.setWrappingWidth(100);
            highScoreList.getChildren().add(highScore);
        }
    }

    /**
     * Gets the message of the day puzzle from the server.
     * 
     * @return the message of the day puzzle.
     * @throws IOException          if the http request fails.
     * @throws InterruptedException if the http request is interrupted.
     */
    private String getMOTDPuzzle() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://cswebcat.swansea.ac.uk/puzzle"))
                .build();
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    /**
     * Solves the message of the day puzzle.
     * 
     * @return the solution to the message of the day puzzle.
     * @throws IOException          if the http request fails.
     * @throws InterruptedException if the http request is interrupted.
     */
    private String solvePuzzle() throws IOException, InterruptedException {
        String puzzle = getMOTDPuzzle();
        ArrayList<Integer> charNums = new ArrayList<Integer>();

        // Converts the puzzle into an array of integers.
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < puzzle.length(); i++) {
            charNums.add((alphabet.indexOf(puzzle.charAt(i))));
        }

        // Computes the solution using integer arithmetic.
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

        // Converts the array of integers back into an array of Characters.
        ArrayList<java.lang.Character> charRes = new ArrayList<java.lang.Character>();
        for (int i = 0; i < charNums.size(); i++) {
            charRes.add(alphabet.charAt(charNums.get(i)));
        }

        // Converts the array of Characters into a String.
        StringBuilder builder = new StringBuilder(charRes.size());
        for (java.lang.Character cha : charRes) {
            builder.append(cha);
        }
        // Adds the additional prefix to the solved puzzle.
        String result = builder.toString().concat("CS-230");
        result = result.chars().count() + result;

        return result;
    }

    /**
     * Gets the message of the day from the server using a computed solution.
     * 
     * @param solution the solved message of the day puzzle.
     * @return the message of the day.
     * @throws IOException          if the http request fails.
     * @throws InterruptedException if the http request is interrupted.
     */
    private String getSolvedMOTD(String solution)
            throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(
                "http://cswebcat.swansea.ac.uk/message?solution=" + solution))
                .build();
        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

}
