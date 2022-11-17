package com.example.cs230assignment;

import java.util.ArrayList;

public class Player extends Character {
    private String name;
    private int score;
    private ArrayList<String> levelComp;

    public void Player() {

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

}
