package com.example.cs230assignment;

import java.util.ArrayList;
import java.lang.Math.*;

/**
 * This class defines the Player methods. this object is used to perform the actions of the
 * player in a mission as well as store the completed levels the player has already done
 * 
 * @author Charles Overton
 * @version 1.0
 */

public class Player extends Character {
    private int score;
    private ArrayList<String> levelComp;
    private String playerName;

    /**
     * constructor for player to set up basic attrubutes
     * 
     * @param name passes in the name of the player used to check what data is already stored
     * @param x spawning xcoord for the player
     * @param y spawning ycoord for the player
     */
    public Player(String name, int x, int y) {
        super("Player", x, y);
        this.playerName = name;
        this.score = 0;
        this.levelComp = new ArrayList<String>();
        loadProfile();
    }

    /**
     *activates the missionFailed method in board to restart the mission  
     */
    public void die() {
        this.score = 0;
        super.currentBoard.missionFailed();
    }

    /**
     * allows the player to perform actions on entities affected by the players next move
     * @param interactedEntity the entity being interacted with
     */
    //changed to retrive coords rather then an entity
    private void interact(int x, int y) {
        if(currentBoard.getTile(x, y).getEntity() != null){
            switch (currentBoard.getTile(x, y).getEntity().getEntityName()){
                case("Item"):
                    this.score += ((Item)(this.currentBoard.getTile(x, y).getEntity())).getItemValue();
                    break;
                case("Key"):
                    Key key = (Key) currentBoard.getTile(x, y).getEntity();
                    key.openGate();
                    break;
                case("Clock"):
                    Clock clock = (Clock) currentBoard.getTile(x, y).getEntity();
                    super.currentBoard.getTimer().addClock(clock);
                case("Door"):
                    Door door = (Door) currentBoard.getTile(x, y).getEntity();
                    door.endMission();
            }
        }else {
            // values of i to the position around tile 0
            //  7  8  1
            //  6  0  2
            //  5  4  3
            for(int i = 1; i < 9; i++){
                int tempCoordX = 0;
                int tempCoordY = 0;
                if(i%4 != 0){
                    tempCoordX = 1 + (int)-(Math.floor((double)(i/4.0))*2);
                }
                if(i > 6 || i == 1){
                    tempCoordY = 1;
                }else if(i < 6 && i > 2){
                    tempCoordY = 0;
                }
                //not off the edge and is a bomb run bomb.explodeBomb();
                if(!(tempCoordX+x > super.currentBoard.getWidth()-1 || x+tempCoordX < 0 
                    ||tempCoordY+y > super.currentBoard.getHeight()-1 || x+tempCoordX < 0 ) 
                    && currentBoard.getTile(tempCoordX, tempCoordY).getEntity().getEntityName() == "Bomb"){
                    Bomb bomb = (Bomb) currentBoard.getTile(tempCoordX, tempCoordY).getEntity();
                    bomb.explodeBomb();
                }
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
        case 4:
            directionCheckX = 1;
            break;
        case 3:
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
            Boolean foundSquare = false;
            for (int i = super.coord[1]+1; ((i < super.currentBoard
                    .getHeight()) && i > -1) && foundSquare == false; i += directionCheckY) {
                // checks every colour on the current tile with the tile being
                // checked
                for (int colourPos = 0; colourPos < 4; colourPos++) {
                    // compares a colour on the current tile and the tile being
                    // checked
                    if (super.currentBoard.getTile(super.coord[0], i)
                            .checkColour(super.currentBoard
                                    .getTile(super.coord[0], super.coord[1])
                                    .getColours()[colourPos])) {
                        super.currentBoard
                                .getTile(super.coord[0], super.coord[1])
                                .removeEntity();
                        interact(coord[0], i);
                        super.currentBoard.getTile(super.coord[0], i)
                                .setEntity(this);
                        super.coord[1] = i;
                        foundSquare = true;
                    }
                }
            }
        }
        // checks to see if the movement is on the x axis
        if (directionCheckX != 0) {
            // checks each tile from the current position to the edge of the
            // board
            Boolean foundSquare = false;
            for (int i = super.coord[1] + 1; ((i < super.currentBoard
                    .getWidth()) && i > -1) && foundSquare == false; i += directionCheckX) {

                // checks every colour on the current tile with the tile being
                // checked
                for (int colourPos = 0; colourPos < 4; colourPos++) {
                    // compares a colour on the current tile and the tile being
                    // checked
                    if (super.currentBoard.getTile(i, super.coord[1])
                            .checkColour(super.currentBoard
                                    .getTile(super.coord[0], super.coord[1])
                                    .getColours()[colourPos])) {
                        super.currentBoard
                                .getTile(super.coord[0], super.coord[1])
                                .removeEntity();
                        interact(i, coord[1]);
                        super.currentBoard.getTile(i, super.coord[1])
                                .setEntity(this);
                        super.coord[0] = i;
                        foundSquare = true;  
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
