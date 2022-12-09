package com.example.cs230assignment;

public class Bomb extends Entity {
    private int xCoord;
    private int yCoord;

    public Bomb(int x, int y){
        super("Bomb", x, y);
        this.xCoord = x;
        this.yCoord = y;
    }

    //Method removes all loot, clocks and levers and bombs
    /**
     * starts the proccess of detonating the bomb
     */
    public void explodeBomb(){
        Timer bombClock = new Timer(3);

        while (bombClock.getLevelTime() > 0) {
            bombClock.decrementTime();
        }

        if ()

    }
}