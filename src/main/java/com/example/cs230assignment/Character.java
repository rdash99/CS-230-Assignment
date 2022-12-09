package com.example.cs230assignment;

import javafx.scene.image.Image;

public class Character extends Entity {
    protected Board currentBoard;

    public Character(String name, int x, int y) {
        super(name, x, y);
    }
    public void boardSet(Board freshBoard){
        this.currentBoard = freshBoard;
    }

    public void setBoard(Board board) {
        this.currentBoard = board;
    }

    public Board getCurrentBoard() {
        return this.currentBoard;
    }

    protected void interact() {
        int x = this.coord[0];
        int y = this.coord[1];
        Entity currentEntity = currentBoard.getTile(x, y).getEntity();
        if(currentEntity != null){
            switch (currentEntity.getEntityName()){
                //Doesn't cover the case for what happens if an NPC steals an item
                case("item"):
                    this.currentBoard.getTile(x, y).removeEntity();
                        if (currentEntity instanceof Item) {
                            this.currentBoard.getPlayer().increaseScore(((Item) currentEntity).getItemValue());
                        }
                    break;
                case("key"):
                    Key key = (Key) currentBoard.getTile(x, y).getEntity();
                    key.openGate();
                    break;
                case("clock"):
                    Clock clock = (Clock) currentBoard.getTile(x, y).getEntity();
                    this.currentBoard.getTimer().addClock(clock);
                    break;
                case("player"):
                    currentBoard.getPlayer().die();
                    break;
            }
        }
        for(int i = 1; i < 9; i++){
            //the xcoord of an adjacent tile
            int tempCoordX = 0;
            //the ycoord of an adjacent tile
            int tempCoordY = 0;
            //checks to see if i isnt 8 or 4 if it isnt anything above 4 becomes -1 anything below 1
            if(i%4 != 0){
                tempCoordX = 1 + (int)-(Math.floor((double)(i/4.0))*2);
            }
            //if i coordinates to the numbers for postive y set y to 1
            if(i > 6 || i == 1){
                tempCoordY = 1;
            //if i coordinates to the numbers for negative y set y to -1
            }else if(i < 6 && i > 2){
                tempCoordY = -1;
            }
            //is off the edge of the board does nothing
            if((tempCoordX+x > this.currentBoard.getWidth()-1 || x+tempCoordX < 0 
                ||tempCoordY+y > this.currentBoard.getHeight()-1 || y+tempCoordY < 0 )){
            //not off the edge and is a contains an entity;
            }else if(this.currentBoard.getTile(x+tempCoordX, y+tempCoordY).getEntity() != null){
                // if the entity is a bomb execute bomb.explodeBomb();
                if(this.currentBoard.getTile(x+tempCoordX, y+tempCoordY).getEntity().getEntityName() == "Bomb"){
                    //the bomb on an adjacent tile
                    Bomb bomb = (Bomb) this.currentBoard.getTile(x+tempCoordX, y+tempCoordY).getEntity();
                    //starts the bomb detination
                    bomb.explodeBomb();
                }
            }
        }
    }
    // protected void drawMove(){
    // }
    // not needed as this will be covered by currentBoard update
}
