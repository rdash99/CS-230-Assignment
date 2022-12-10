package com.example.cs230assignment;

public class Door extends Entity {
    public Door(int x, int y) {
        super("Door", x, y);
    }
    
    //needs to calculate points from score and time left
    public void endMission(int score, int timeleft){

    }
    public Boolean validEndMission(int score){
        return false;
    }
}