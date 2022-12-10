package com.example.cs230assignment;

/**
 * This class defines the Scoreboard object. This is used to keep track of the
 * score for display on the screen.
 * 
 * @author Rowan Dash
 * @version 1.0
 * @deprecated
 */

public class Scoreboard {
    private int userScore;

    /**
     * This is the constructor for the Scoreboard class
     */
    public Scoreboard() {
        this.userScore = 0;
    }

    /**
     * This is the constructor for the Scoreboard class
     * 
     * @param userScore the score of the user
     */
    public Scoreboard(int userScore) {
        this.userScore = userScore;
    }

    /**
     * @param score
     */
    public void addScore(int score) {
        this.userScore += score;
    }
}
