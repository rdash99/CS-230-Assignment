package com.example.cs230assignment;

import java.util.ArrayList;

public class Player extends Character {
    private int score;
    private ArrayList<String> levelComp;

    public Player(int x, int y) {
        super("Player", x, y);
        this.score = 0;
        this.levelComp = new ArrayList<String>();
    }

    public void die() {

    }

    // changed to just interact to overwrite from character
    private void interact(Item interactedItem) {

    }

    // changed to take the item in as different items will add different scores
    private void addScore(Item interactedItem) {

    }

    // added to remove from character code that is only needed for player
    public void move() {

    }

    public ArrayList<String> getLevels() {
        return this.levelComp;
    }

    public int getScore() {
        return this.score;
    }

}
