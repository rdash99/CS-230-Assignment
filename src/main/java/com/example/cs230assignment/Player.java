package com.example.cs230assignment;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Player extends Character {
    private int score;
    private ArrayList<String> levelComp;
    private String playerName;
    private Image playerImg;

    public Player(String name, int x, int y) {
        super("Player", x, y);
        this.playerName = name;
        this.score = 0;
        this.levelComp = new ArrayList<String>();
        this.playerImg = new Image("player.png");
        loadProfile();
    }

    public void die() {
        this.score = 0;
        super.currentBoard.missionFailed();
    }

    // changed to just interact to overwrite from character
    private void interact(Entity interactedEntity) {
        int x = this.coord[0];
        int y = this.coord[1];
        if (currentBoard.getTile(x, y).getEntity() != null) {
            switch (currentBoard.getTile(x, y).getEntity().getEntityName()) {
                case ("item"):
                    this.score += ((Item) (this.currentBoard.getTile(x, y)
                            .getEntity())).getItemValue();
                    break;
                case ("key"):
                    Key key = (Key) currentBoard.getTile(x, y).getEntity();
                    key.openGate();
                    break;
                case ("clock"):
                    Clock clock = (Clock) currentBoard.getTile(x, y).getEntity();
                    super.currentBoard.getTimer().addClock(clock);
            }
        }
    }

    // changed to take the item in as different items will add different scores
    private void addScore(Item interactedItem) {

    }

    private void loadProfile() throws NullPointerException {
        String profile = FileHandler.loadPlayerData(playerName);
        if (profile != null) {
            profile = profile.replace("[", "").replace("]", "");
            String[] profileData = profile.split(" ");
            this.score = Integer.parseInt(profileData[0]);
            for (int i = 1; i < profileData.length; i++) {
                this.levelComp.add(profileData[i]);
            }
        }
    }

    /**
     * moves the character from an input from the user
     *
     * @param direction the direction desired by the movement inputed by the
     *                  user
     */
    public void move(GraphicsContext gc, int direction) {
        Boolean foundSquare = false;
        switch (direction) {

            case 1:
                // checks each tile from the current position to the edge of the
                // board
                for (int i = super.coord[1] - 1; ((i < super.currentBoard
                        .getHeight()) && i > -1) && !foundSquare; i--) {
                    // checks every colour on the current tile with the tile being
                    // checked
                    for (int colourPos = 0; colourPos < 4; colourPos++) {
                        // compares a colour on the current tile and the tile being
                        // checked
                        if (super.currentBoard.getTile(i, super.coord[0])
                                .checkColour(super.currentBoard
                                        .getTile(super.coord[1], super.coord[0])
                                        .getColours()[colourPos])) {
                            super.currentBoard
                                    .getTile(super.coord[0], super.coord[1])
                                    .removeEntity();
                            interact(super.currentBoard.getTile(super.coord[0], i)
                                    .getEntity());
                            super.currentBoard.getTile(super.coord[0], i)
                                    .setEntity(this);
                            super.coord[1] = i;
                            foundSquare = true;
                        }
                    }
                }
                break;
            case 2:
                for (int i = super.coord[0] - 1; ((i < super.currentBoard
                        .getWidth()) && i > -1) && !foundSquare; i--) {
                    // checks every colour on the current tile with the tile being
                    // checked
                    for (int colourPos = 0; colourPos < 4; colourPos++) {
                        // compares a colour on the current tile and the tile being
                        // checked
                        if (super.currentBoard.getTile(super.coord[1], i)
                                .checkColour(super.currentBoard
                                        .getTile(super.coord[1], super.coord[0])
                                        .getColours()[colourPos])) {
                            super.currentBoard
                                    .getTile(super.coord[0], super.coord[1])
                                    .removeEntity();
                            interact(super.currentBoard.getTile(i, super.coord[1])
                                    .getEntity());
                            super.currentBoard.getTile(i, super.coord[1])
                                    .setEntity(this);
                            super.coord[0] = i;
                            foundSquare = true;
                        }
                    }
                }
                break;
            case 3:
                for (int i = super.coord[1] + 1; ((i < super.currentBoard
                        .getHeight()) && i > -1) && !foundSquare; i++) {
                    // checks every colour on the current tile with the tile being
                    // checked
                    for (int colourPos = 0; colourPos < 4; colourPos++) {
                        // compares a colour on the current tile and the tile being
                        // checked
                        if (super.currentBoard.getTile(i, super.coord[0])
                                .checkColour(super.currentBoard
                                        .getTile(super.coord[1], super.coord[0])
                                        .getColours()[colourPos])) {
                            super.currentBoard
                                    .getTile(super.coord[0], super.coord[1])
                                    .removeEntity();
                            interact(super.currentBoard.getTile(super.coord[0], i)
                                    .getEntity());
                            super.currentBoard.getTile(super.coord[0], i)
                                    .setEntity(this);
                            super.coord[1] = i;
                            foundSquare = true;
                        }
                    }
                }
                break;
            case 4:
                for (int i = super.coord[0] + 1; ((i < super.currentBoard
                        .getWidth()) && i > -1) && !foundSquare; i++) {
                    // checks every colour on the current tile with the tile being
                    // checked
                    for (int colourPos = 0; colourPos < 4; colourPos++) {
                        // compares a colour on the current tile and the tile being
                        // checked
                        if (super.currentBoard.getTile(super.coord[1], i)
                                .checkColour(super.currentBoard
                                        .getTile(super.coord[1], super.coord[0])
                                        .getColours()[colourPos])) {
                            super.currentBoard
                                    .getTile(super.coord[0], super.coord[1])
                                    .removeEntity();
                            interact(super.currentBoard.getTile(i, super.coord[1])
                                    .getEntity());
                            super.currentBoard.getTile(i, super.coord[1])
                                    .setEntity(this);
                            super.coord[0] = i;
                            foundSquare = true;
                        }
                    }
                }
                break;
        }
        currentBoard.getTimer().boardUpdate(gc, this, super.currentBoard);
    }

    public ArrayList<String> getLevels() {
        return this.levelComp;
    }

    public int getScore() {
        return this.score;
    }

    public Image getImage() {
        return this.playerImg;
    }

}
