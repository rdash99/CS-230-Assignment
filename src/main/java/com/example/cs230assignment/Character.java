package com.example.cs230assignment;

import javafx.scene.image.Image;

public class Character extends Entity {
    protected static Board currentBoard;

    public Character(String name, int x, int y) {
        super(name, x, y);
    }
    public static void boardSet(Board freshBoard){
        Character.currentBoard = freshBoard;
    }

    public static void setBoard(Board board) {
        Character.currentBoard = board;
    }

    public static Board getCurrentBoard() {
        return Character.currentBoard;
    }

    protected void interact(int x, int y) {
        if(currentBoard.getTile(x, y).getEntity() != null){
            switch (currentBoard.getTile(x, y).getEntity().getEntityName()){
                case("item"):
                    Character.currentBoard.getTile(x, y).removeEntity();
                    Character.currentBoard.getPlayer().increaseScore(((Item) currentBoard.getTile(x, y).getEntity()).getItemValue());
                        //if (currentEntity instanceof Item) {
                        //    Character.currentBoard.getPlayer().increaseScore(((Item) currentEntity).getItemValue());
                        //}
                    break;
                case("key"):
                    Key key = (Key) currentBoard.getTile(x, y).getEntity();
                    key.openGate();
                    break;
                case("clock"):
                    Clock clock = (Clock) currentBoard.getTile(x, y).getEntity();
                    Character.currentBoard.getTimer().addClock(clock);
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
            if((tempCoordX+x > Character.currentBoard.getWidth()-1 || x+tempCoordX < 0 
                ||tempCoordY+y > Character.currentBoard.getHeight()-1 || y+tempCoordY < 0 )){
            //not off the edge and is a contains an entity;
            }else if(Character.currentBoard.getTile(x+tempCoordX, y+tempCoordY).getEntity() != null){
                // if the entity is a bomb execute bomb.explodeBomb();
                if(Character.currentBoard.getTile(x+tempCoordX, y+tempCoordY).getEntity().getEntityName() == "Bomb"){
                    //the bomb on an adjacent tile
                    Bomb bomb = (Bomb) Character.currentBoard.getTile(x+tempCoordX, y+tempCoordY).getEntity();
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
