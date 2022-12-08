package com.example.cs230assignment;

public class FlyingAssassin extends NPC {

    public FlyingAssassin(double movementTimerPass, int x, int y, int xdir,
            int ydir) {
        super("Flying Assassin", movementTimerPass, x, y);
        super.coordChange = new int[] {xdir,ydir};
    }

    // was flying assassin kill changed to fit better with boardupdate
    // kills any npcs
    @Override
    protected void interact() {

    }

    private void validMove() {
        if (super.coord[0] + super.coordChange[0] < 0 
        || super.coord[0] + super.coordChange[0] > super.currentBoard.getWidth()-1 
        || super.coord[1] + super.coordChange[1] < 0 
        || super.coord[1] + super.coordChange[1] > super.currentBoard.getHeight()-1 ){
            super.coordChange[0] = - super.coordChange[0];
            super.coordChange[1] = - super.coordChange[1];
        }
    }

    public int[] getDirection() {
        return super.coordChange;
    }
}
