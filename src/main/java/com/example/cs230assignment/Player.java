package com.example.cs230assignment;

import java.util.ArrayList;

public class Player extends Character {
    private int score;
    private ArrayList<String> levelComp;
    private String playerName;

    public Player(String name, int x, int y) {
        super("Player", x, y);
        this.playerName = name;
        this.score = 0;
        this.levelComp = new ArrayList<String>();
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
        if(currentBoard.getTile(x, y).getEntity() != null){
            switch (currentBoard.getTile(x, y).getEntity().getEntityName()){
                case("item"):
                    this.score += ((Item)(this.currentBoard.getTile(x, y).getEntity())).getItemValue();
                    break;
                case("key"):
                    Key key = (Key) currentBoard.getTile(x, y).getEntity();
                    key.openGate();
                    break;
                case("clock"):
                    Clock clock = (Clock) currentBoard.getTile(x, y).getEntity();
                    super.currentBoard.getTimer().addClock(clock);
            }
        }
    }

    // changed to take the item in as different items will add different scores
    private void addScore(Item interactedItem) {

    }

    private void loadProfile() {
        String profile = FileHandler.loadPlayerData(playerName);
        if (profile != null) {
            String[] profileData = profile.split(" ");
            this.score = Integer.parseInt(profileData[0]);
            for (int i = 1; i < profileData.length; i++) {
                this.levelComp.add(profileData[i]);
            }
        }else{
            levelComp.add("level1");
        }
    }


    /**
     * moves the character from an input from the user
     * 
     * @param direction the direction desired by the movement inputed by the
     *                  user
     */
    public void move(int direction) {
        //the x coord direction to check for a valid square
        int directionCheckX = 0;
        // the y coord direction to check for a valid square
        int directionCheckY = 0;

        // converts the directon from a character to a usable coord direction
        switch (direction) {
        case 1:
            directionCheckY = 1;
            break;
        case 3:
            directionCheckX = 1;
            break;
        case 4:
            directionCheckY = -1;
            break;
        case 2:
            directionCheckX = -1;
            break;
        }
        // checks to see if the movement is on the y axis
        if (directionCheckY != 0) {
            // checks each tile from the current position to the edge of the
            // board
            for (int i = super.coord[1]; (i <= super.currentBoard
                    .getHeight() + 1) || i >= -1; i += directionCheckY) {
                // checks every colour on the current tile with the tile being
                // checked
                for (int colourPos = 0; colourPos <= 4; i++) {
                    // compares a colour on the current tile and the tile being
                    // checked
                    if (super.currentBoard.getTile(super.coord[0], i)
                            .checkColour(super.currentBoard
                                    .getTile(super.coord[0], super.coord[1])
                                    .getColours()[colourPos])) {
                        super.currentBoard
                                .getTile(super.coord[0], super.coord[1])
                                .removeEntity();
                        interact(super.currentBoard.getTile(coord[0], i).getEntity());
                        super.currentBoard.getTile(super.coord[0], i)
                                .setEntity(this);
                        super.coord[1] = i;
                        i = super.currentBoard.getHeight() + 2;
                    }
                }
            }
        }
        // checks to see if the movement is on the x axis
        if (directionCheckX != 0) {
            // checks each tile from the current position to the edge of the
            // board
            for (int i = super.coord[1]; (i <= super.currentBoard
                    .getWidth() + 1) || i >= -1; i += directionCheckX) {
                // checks every colour on the current tile with the tile being
                // checked
                for (int colourPos = 0; colourPos <= 4; i++) {
                    // compares a colour on the current tile and the tile being
                    // checked
                    if (super.currentBoard.getTile(i, super.coord[1])
                            .checkColour(super.currentBoard
                                    .getTile(super.coord[0], super.coord[1])
                                    .getColours()[colourPos])) {
                        super.currentBoard
                                .getTile(super.coord[0], super.coord[1])
                                .removeEntity();
                        interact(super.currentBoard.getTile(i, coord[1]).getEntity());
                        super.currentBoard.getTile(i, super.coord[1])
                                .setEntity(this);
                        super.coord[0] = i;
                        i = super.currentBoard.getWidth() + 2;
                    }
                }
            }
        }
    }

    public ArrayList<String> getLevels() {
        return this.levelComp;
    }

    public int getScore() {
        return this.score;
    }

}
