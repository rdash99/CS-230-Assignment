package com.example.cs230assignment;

import javafx.scene.image.Image;

/**
 * This class represents the character subclasse
 * 
 * @author Charles Overton
 * @version 1.0
 */
public class Character extends Entity {
    protected static Board currentBoard;

    /**
     * This is the constructor for the character class
     * 
     * @param name the name of the character
     * @param x    the x coordinate of the character
     * @param y    the y coordinate of the character
     */
    public Character(String name, int x, int y) {
        super(name, x, y);
    }

    /**
     * @param freshBoard
     */
    public static void boardSet(Board freshBoard) {
        Character.currentBoard = freshBoard;
    }

    /**
     * @param board
     */
    public static void setBoard(Board board) {
        Character.currentBoard = board;
    }

    /**
     * @return Board
     */
    public static Board getCurrentBoard() {
        return Character.currentBoard;
    }

    /**
     * @param x
     * @param y
     * @return 
     */
    protected void interact(int x, int y) {
        if (currentBoard.getTile(x, y).getEntity() != null) {
            if (currentBoard.getTile(x, y).getEntity() instanceof Item){
                Character.currentBoard.removeEntity(currentBoard.getTile(x, y).getEntity());
                Character.currentBoard.getTile(x, y).removeEntity();
            }else if(currentBoard.getTile(x, y).getEntity() instanceof Key){
                Key key = (Key) currentBoard.getTile(x, y).getEntity();
                key.openGate();
            }else if(currentBoard.getTile(x, y).getEntity() instanceof Clock){
                Character.currentBoard.getTile(x, y).removeEntity();
            }
        }
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
                if (Character.currentBoard
                        .getTile(x + tempCoordX, y + tempCoordY).getEntity() instanceof Bomb) {
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
    // protected void drawMove(){
    // }
    // not needed as this will be covered by currentBoard update
}
