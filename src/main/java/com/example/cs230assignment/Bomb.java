package com.example.cs230assignment;

public class Bomb extends Entity {
    //private int bombTimer;

    public Bomb(int x, int y){
        super("Bomb", x, y);
        //this.bombTimer = 3;
    }

    /**
     * starts the proccess of detonating the bomb
     */
    public void explodeBomb(){
        Timer bombClock = new Timer(3);

        while (bombClock.getLevelTime() > 0) {
            bombClock.decrementTime();
        }


    }
}