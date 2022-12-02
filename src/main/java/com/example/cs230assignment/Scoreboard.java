package com.example.cs230assignment;

/**
 * This class defines the Scoreboard object. This is used to keep track of the
 * score for display on the screen.
 * 
 * @author Rowan Dash
 * @version 1.0
 */

public class Scoreboard {
    private int userScore;

    public Scoreboard() {
        this.userScore = 0;
    }

    public Scoreboard(int userScore) {
        this.userScore = userScore;
    }

    public void addScore(int score) {
        this.userScore += score;
    }
}
