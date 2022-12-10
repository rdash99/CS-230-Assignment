package com.example.cs230assignment;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.lang.Math.*;

/**
 * This class defines the Player methods. this object is used to perform the
 * actions of the player in a mission as well as store the completed levels the
 * player has already done
 *
 * @author Charles Overton
 * @version 1.0
 */

public class Player extends Character {
    // score of the player on the current mission
    private int score;
    // all the levels the player has completed
    private ArrayList<String> levelComp;
    // player name
    private String playerName;

    /**
     * constructor for player to set up basic attrubutes
     *
     * @param name passes in the name of the player used to check what data is
     *             already stored
     * @param x    spawning xcoord for the player
     * @param y    spawning ycoord for the player
     */
    public Player(String name, int x, int y) {
        // constructor for character
        super(name, x, y);
        this.playerName = name;
        this.score = 0;
        this.levelComp = new ArrayList<String>();
        this.entityName = "Player";
        loadProfile();
    }

    /**
     * activates the missionFailed method in board to restart the mission
     */
    public void die() {
        this.score = 0;
        Character.currentBoard.missionFailed();
    }

    /**
     * allows the player to perform actions on entities affected by the players
     * next move
     *
     * @param x the xcoord of the next tile with the potential interactable item
     * @param y the ycoord of the next tile with the potential interactable item
     */
    protected void interact(int x, int y) {
        // checks to see if there is an item on the next square
        if (Character.currentBoard.getTile(x, y).getEntity() != null) {
            // switch statement to find out what entity is on the next square
            switch (Character.currentBoard.getTile(x, y).getEntity()
                    .getEntityName()) {
            // interact with an item entity
            case ("Item"):
                // adding the score to player from the item
                this.score += ((Item) (Character.currentBoard.getTile(x, y)
                        .getEntity())).getItemValue();
                break;
            // interact with a key entity
            case ("Key"):
                // key being interacted with
                Key key = (Key) Character.currentBoard.getTile(x, y).getEntity();
                key.openGate();
                break;
            // interact with a clock entity
            case ("Clock"):
                // clock being interacted with
                Clock clock = (Clock) Character.currentBoard.getTile(x, y)
                        .getEntity();
                Character.currentBoard.getTimer().addClock(clock);
                break;
                // interact with a door entity
            //case ("Door"):
                // door being interacted with
            //    Door door = (Door) Character.currentBoard.getTile(x, y).getEntity();
            //    door.endMission(this.score, Character.currentBoard.getTimer().getLevelTime());
            }
        }
        if (Character.currentBoard.getTile(x, y).getDoor() != null){
            if(Character.currentBoard.getTile(x, y).getDoor().validEndMission(this.score)){
                Character.currentBoard.getTile(x, y).getDoor().endMission(this.score, Character.currentBoard.getTimer().getLevelTime());
            }
        }
        // values of i to the position around tile 0
        // 7 8 1
        // 6 0 2
        // 5 4 3
        for (int i = 1; i < 9; i++) {
            // the xcoord of an adjacent tile
            int tempCoordX = 0;
            // the ycoord of an adjacent tile
            int tempCoordY = 0;
            // checks to see if i isnt 8 or 4 if it isnt anything above 4
            // becomes -1 anything below 1
            if (i % 4 != 0) {
                tempCoordX = 1 + (int) -(Math.floor((double) (i / 4.0)) * 2);
            }
            // if i coordinates to the numbers for postive y set y to 1
            if (i > 6 || i == 1) {
                tempCoordY = 1;
                // if i coordinates to the numbers for negative y set y to -1
            } else if (i < 6 && i > 2) {
                tempCoordY = -1;
            }
            // is off the edge of the board does nothing
            if ((tempCoordX + x > Character.currentBoard.getWidth() - 1
                    || x + tempCoordX < 0
                    || tempCoordY + y > Character.currentBoard.getHeight() - 1
                    || y + tempCoordY < 0)) {
                // not off the edge and is a contains an entity;
            } else if (Character.currentBoard
                    .getTile(x + tempCoordX, y + tempCoordY)
                    .getEntity() != null) {
                // if the entity is a bomb execute bomb.explodeBomb();
                if (Character.currentBoard.getTile(x + tempCoordX, y + tempCoordY)
                        .getEntity().getEntityName() == "Bomb") {
                    // the bomb on an adjacent tile
                    Bomb bomb = (Bomb) Character.currentBoard
                            .getTile(x + tempCoordX, y + tempCoordY)
                            .getEntity();
                    // starts the bomb detination
                    bomb.explodeBomb();
                }
            }
        }
    }

    // changed to take the item in as different items will add different scores
    private void addScore(Item interactedItem) {

    }

    private void loadProfile() throws NullPointerException {
        String profile = FileHandler.loadPlayerData(playerName);
        // checks to see if the player has existed before to load the profile
        try {
            if (profile != null) {
                profile = profile.replace("[", "").replace("]", "");
                profile = profile.replace(",", "");
                String[] profileData = profile.split(",");
                String[] scoreData = new String[profileData.length / 2];
                String[] levelData = new String[profileData.length / 2];
                int i = 0;
                do {
                    String[] data = profileData[i].trim().split(" ");
                    scoreData[i] = profileData[i].trim();
                    levelData[i] = profileData[i + 1].trim();
                    this.levelComp.add((String) levelData[i]);
                    i = i + 2;
                } while (i <= profileData.length);
                // TODO needs updating to check current level and update score
                // apropiately - only if this is a loading of a saved game
                this.score = Integer.parseInt(scoreData[scoreData.length - 1]);
                // creates a new profile if one isnt found
            } else {
                this.levelComp.add("level1");
            }
        } catch (Exception e) {
            this.score = 0;
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
                for (int i = super.coord[1] - 1; ((i < Character.currentBoard
                        .getHeight()) && i > -1) && !foundSquare; i--) {
                    // checks every colour on the current tile with the tile being
                    // checked
                    for (int colourPos = 0; colourPos < 4; colourPos++) {
                        // checks for a gate to stop any future movements
                        if (Character.currentBoard.getTile(super.coord[0], i)
                                .getEntity() != null
                                && !(Character.currentBoard.getTile(super.coord[0], i)
                                .getEntity().getEntityName()).equals("Flying Assassin")
                                && !(Character.currentBoard.getTile(super.coord[0], i)
                                .getEntity() instanceof Item)
                                && !(Character.currentBoard.getTile(super.coord[0], i)
                                .getEntity().getEntityName()).equals("Door")
                                && !(Character.currentBoard.getTile(super.coord[0], i)
                                .getEntity().getEntityName()).equals("Key")) {
                            if (Character.currentBoard.getTile(super.coord[0], i)
                                    .getEntity().getEntityName().equals("Gate")) {
                                if(!((Gate) Character.currentBoard.getTile(super.coord[0], i)
                                    .getEntity()).getGateOpen())
                                    foundSquare = true;
                            }
                            // compares a colour on the current tile and the tile being
                            // checked
                        } else if (Character.currentBoard.getTile(i, super.coord[0])
                                .checkColour(Character.currentBoard
                                        .getTile(super.coord[1], super.coord[0])
                                        .getColours()[colourPos])) {
                                Character.currentBoard
                                    .getTile(super.coord[0], super.coord[1])
                                    .removeEntity();
                            interact(super.coord[0], i);
                            Character.currentBoard.getTile(super.coord[0], i)
                                    .setEntity(this);
                            super.coord[1] = i;
                            foundSquare = true;
                        }
                    }
                }
                break;
            case 2:
                for (int i = super.coord[0] - 1; ((i < Character.currentBoard
                        .getWidth()) && i > -1) && !foundSquare; i--) {
                    // checks every colour on the current tile with the tile being
                    // checked
                    for (int colourPos = 0; colourPos < 4; colourPos++) {
                        // checks for a gate to stop any future movements
                        if (Character.currentBoard.getTile(i, super.coord[1])
                                .getEntity() != null
                                && !(Character.currentBoard.getTile(i, super.coord[1])
                                .getEntity().getEntityName()).equals("Flying Assassin")
                                && !(Character.currentBoard.getTile(i, super.coord[1])
                                .getEntity() instanceof Item)
                                && !(Character.currentBoard.getTile(i, super.coord[1])
                                .getEntity().getEntityName()).equals("Door")
                                && !(Character.currentBoard.getTile(i, super.coord[1])
                                .getEntity().getEntityName()).equals("Key")) {
                            if (Character.currentBoard.getTile(i, super.coord[1])
                                    .getEntity().getEntityName().equals("Gate")) {
                                foundSquare = true;
                            }
                            // compares a colour on the current tile and the tile being
                            // checked
                        } else if (Character.currentBoard.getTile(super.coord[1], i)
                                .checkColour(Character.currentBoard
                                        .getTile(super.coord[1], super.coord[0])
                                        .getColours()[colourPos])) {
                                            Character.currentBoard
                                    .getTile(super.coord[0], super.coord[1])
                                    .removeEntity();
                            interact(i, super.coord[1]);
                            Character.currentBoard.getTile(i, super.coord[1])
                                    .setEntity(this);
                            super.coord[0] = i;
                            foundSquare = true;
                        }
                    }
                }
                break;
            case 3:
                for (int i = super.coord[1] + 1; ((i < Character.currentBoard
                        .getHeight()) && i > -1) && !foundSquare; i++) {
                    // checks every colour on the current tile with the tile being
                    // checked
                    for (int colourPos = 0; colourPos < 4; colourPos++) {
                        // checks for a gate to stop any future movements
                        if (Character.currentBoard.getTile(super.coord[0], i)
                                .getEntity() != null
                                && !(Character.currentBoard.getTile(super.coord[0], i)
                                .getEntity().getEntityName()).equals("Flying Assassin")
                                && !(Character.currentBoard.getTile(super.coord[0], i)
                                .getEntity() instanceof Item)
                                && !(Character.currentBoard.getTile(super.coord[0], i)
                                .getEntity().getEntityName()).equals("Door")
                                && !(Character.currentBoard.getTile(super.coord[0], i)
                                .getEntity().getEntityName()).equals("Key")) {
                            if (Character.currentBoard.getTile(super.coord[0], i)
                                    .getEntity().getEntityName().equals("Gate")) {
                                foundSquare = true;
                            }
                            // compares a colour on the current tile and the tile being
                            // checked
                        } else if (Character.currentBoard.getTile(i, super.coord[0])
                                .checkColour(Character.currentBoard
                                        .getTile(super.coord[1], super.coord[0])
                                        .getColours()[colourPos])) {
                            Character.currentBoard
                                    .getTile(super.coord[0], super.coord[1])
                                    .removeEntity();
                            interact(super.coord[0], i);
                            Character.currentBoard.getTile(super.coord[0], i)
                                    .setEntity(this);
                            super.coord[1] = i;
                            foundSquare = true;
                        }
                    }
                }
                break;
            case 4:
                for (int i = super.coord[0] + 1; ((i < Character.currentBoard
                        .getWidth()) && i > -1) && !foundSquare; i++) {
                    // checks every colour on the current tile with the tile being
                    // checked
                    for (int colourPos = 0; colourPos < 4; colourPos++) {
                        // checks for a gate to stop any future movements
                        if (Character.currentBoard.getTile(i, super.coord[1])
                                .getEntity() != null
                                && !(Character.currentBoard.getTile(i, super.coord[1])
                                .getEntity().getEntityName()).equals("Flying Assassin")
                                && !(Character.currentBoard.getTile(i, super.coord[1])
                                .getEntity() instanceof Item)
                                && !(Character.currentBoard.getTile(i, super.coord[1])
                                .getEntity().getEntityName()).equals("Door")
                                && !(Character.currentBoard.getTile(i, super.coord[1])
                                .getEntity().getEntityName()).equals("Key")) {
                            if (Character.currentBoard.getTile(i, super.coord[1])
                                    .getEntity().getEntityName().equals("Gate")) {
                                foundSquare = true;
                            }
                            // compares a colour on the current tile and the tile being
                            // checked
                        } else if (Character.currentBoard.getTile(super.coord[1], i)
                                .checkColour(Character.currentBoard
                                        .getTile(super.coord[1], super.coord[0])
                                        .getColours()[colourPos])) {
                            Character.currentBoard
                                    .getTile(super.coord[0], super.coord[1])
                                    .removeEntity();
                            interact(i, super.coord[1]);
                            Character.currentBoard.getTile(i, super.coord[1])
                                    .setEntity(this);
                            super.coord[0] = i;
                            foundSquare = true;
                        }
                    }
                }
                break;
        }
        currentBoard.getTimer().

                boardUpdate(gc, Character.currentBoard);

    }

    /**
     * returns the arraylist of levels
     * @return arraylist of levels
     */
    public ArrayList<String> getLevels() {
        return this.levelComp;
    }

    /**
     * increases the score of the player
     * @param scoreIncrease the amount the score is being increased by
     */
    public void increaseScore(int scoreIncrease) {
        this.score += scoreIncrease;
    }

    /**
     * returns the score within player
     * @return returns score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * returns the player name
     * @return playerName
     */
    public String getPlayerName() {
        return this.playerName;
    }

}
