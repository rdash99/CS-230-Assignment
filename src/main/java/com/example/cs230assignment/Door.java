package com.example.cs230assignment;

/**
 * This models the doors
 * 
 * @author Maxwell
 */
public class Door extends Entity {
    private Board currentBoard;
    private GameGUI gameGUI;

    /**
     * This is the constructor for the door class
     *
     * @param x    the x coordinate of the door
     * @param y    the y coordinate of the door
     */
    public Door(int x, int y) {
        super("Door", x, y);
    }

    /**
     * @param score
     * @param timeLeft
     */
    // needs to calculate points from score and time left
    public void endMission(int score, int timeLeft) {
        for (int i = 0; i < timeLeft; i++) {
            score += 10;
        }
        this.currentBoard.getPlayer().setScore(score);
        FileHandler.savePlayer(this.currentBoard.getPlayer());
        new WinMenu(this.currentBoard.getPlayer().getPlayerName(), this.gameGUI,
                this.currentBoard.getPlayer().getScore());
        gameGUI.pauseLevelTime();
        for (Entity elem : currentBoard.getEntities()) {
            if (elem instanceof SmartThief) {
                currentBoard.pauseSmartThief();
            } else if (elem instanceof FlyingAssassin) {
                currentBoard.pauseFlyingAssassin();
            } else if (elem instanceof FloorFollowingThief) {
                currentBoard.pauseFloorFollowingThief();
            }
        }
    }

    /**
     * @return Boolean
     */
    public Boolean validEndMission() {

        for (Entity elem : this.currentBoard.getEntities()) {
            if (elem instanceof Item || elem instanceof Key) {
                return false;
            }
        }
        return true;
    }

    public void setBoard(Board currentBoard) {
        this.currentBoard = currentBoard;
    }

    public void setGameGUI(GameGUI gameGUI) {
        this.gameGUI = gameGUI;
    }
}