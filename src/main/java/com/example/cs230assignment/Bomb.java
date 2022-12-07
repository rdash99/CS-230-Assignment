package com.example.cs230assignment;

public class Bomb extends Entity {
    private boolean bombTrigger = false;
    private int bombTimer = 0;
    private int bombDiameterX = 0;
    private int bombDiameterY = 0;

    public Bomb(String entityNamePass, int x, int y, int xDirection, int yDirection) {
        super(entityNamePass, x, y);
        this.bombDiameterX = xDirection;
        this.bombDiameterY = yDirection;
    }

    public boolean getBombTrigger() {return this.bombTrigger; }

    public void setBombTrigger(boolean bombStatus) {this.bombTrigger = bombStatus; }

    public int getbombDiameterX() {return this.bombDiameterX; }

    public void setbombDiameterX(int xDiameter) {this.bombDiameterX = xDiameter; }

    public int getbombDiameterY() {return this.bombDiameterY; }

    public void setbombDiameterY(int yDiameter) {this.bombDiameterY = yDiameter; }


}