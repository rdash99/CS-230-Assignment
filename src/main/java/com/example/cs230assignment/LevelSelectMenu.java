package com.example.cs230assignment;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

    /**
     * The LevelSelectMenu class. It is used to display the level select which
     * contains all unlocked levels in the game so far
     *
     * @author Thomas McAuley
     * @version 1.0
     */

    public class LevelSelectMenu extends Stage {

        private BorderPane root = new BorderPane();
        private Text titleText = new Text("Level Select");
        private Text errorText = new Text();
        private VBox levelButtons = new VBox();
        private HBox hbox = new HBox();
        private String playerName;

        /**
         * Creates a new Level Selector when this class is instantiated.
         *
         * @param playerName the name of the player
         */
        public LevelSelectMenu(String playerName) {
            this.playerName = playerName;

            titleText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
            titleText.setFill(Color.GRAY);

            hbox.getChildren().add(titleText);
            hbox.setAlignment(Pos.CENTER);
            hbox.setPadding(new Insets(20, 10, 10, 10));

            for (String level : FileHandler.readLevelFiles()) {
                levelButtons.getChildren().add(new Button(level));
            }

            int i = 0;
            for (Node elem : levelButtons.getChildren()) {
                Button button = (Button) elem;
                int finalI = i;
                button.setOnAction(e -> {
                    new GameGUI(this.playerName, FileHandler.readLevelFiles().get(finalI));
                    this.close();
                });
                i++;
            }

            root.setTop(hbox);
            root.setCenter(levelButtons);
            levelButtons.setAlignment(Pos.CENTER);
            levelButtons.setSpacing(10);

            this.setScene(new Scene(root, 600, 500));
            this.setTitle("The Level Select");
            this.show();



        }
}
