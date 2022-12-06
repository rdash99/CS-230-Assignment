package com.example.cs230assignment;

public class FlyingAssassin extends NPC {
    private int xdir;
    private int ydir;

    public FlyingAssassin(double movementTimerPass, int x, int y, int xdir,
            int ydir) {
        super("Flying Assasin", movementTimerPass, x, y);
        this.xdir = xdir;
        this.ydir = ydir;
    }

    // was flying assassin kill changed to fit better with boardupdate
    //kills any npcs
    private void interact() {

    }

    private void validMove() {

    }

    public int[] getDirection() {
        return new int[] { this.xdir, this.ydir };
    }
}
