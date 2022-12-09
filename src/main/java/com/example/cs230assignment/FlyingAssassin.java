package com.example.cs230assignment;

public class FlyingAssassin extends NPC {

    public FlyingAssassin(double movementTimerPass, int x, int y, int xdir,
            int ydir) {
        super("Flying Assassin", movementTimerPass, x, y);
        super.coordChange = new int[] {xdir,ydir};
    }

    public void move() {
    Boolean x = validMove();
        if (x) {
        super.interact(this.coord[0] + this.coordChange[0],this.coord[1] + this.coordChange[1]);
        super.currentBoard.getTile(this.coord[0], this.coord[1]).removeEntity();
        super.currentBoard.getTile(this.coord[0] + this.coordChange[0],this.coord[1] + this.coordChange[1]);
        this.coord[0] = this.coord[0] + this.coordChange[0];
        this.coord[1] = this.coord[1] + this.coordChange[1];
        }
  }
    // was flying assassin kill changed to fit better with boardupdate
    // kills any npcs
    @Override
    protected void interact(int x, int y) {
        if(currentBoard.getTile(x, y).getEntity() != null){
            switch (currentBoard.getTile(x, y).getEntity().getEntityName()){
                case("item"):
                    super.currentBoard.getTile(x, y).removeEntity();
                    break;
                case("key"):
                    Key key = (Key) super.currentBoard.getTile(x, y).getEntity();
                    key.openGate();
                    break;
                case("clock"):
                    Clock clock = (Clock) super.currentBoard.getTile(x, y).getEntity();
                    this.currentBoard.getTimer().addClock(clock);
                    break;
                case("player"):
                    super.currentBoard.getPlayer().die();
                    break;
                case("FlyingAssassin"):
                case("FloorFollowingThief"):
                case("SmartThief"):
                    super.currentBoard.getTile(x, y).removeEntity();
            }
        }
        for(int i = 1; i < 9; i++){
            //the xcoord of an adjacent tile
            int tempCoordX = 0;
            //the ycoord of an adjacent tile
            int tempCoordY = 0;
            //checks to see if i isnt 8 or 4 if it isnt anything above 4 becomes -1 anything below 1
            if(i%4 != 0){
                tempCoordX = 1 + (int)-(Math.floor((double)(i/4.0))*2);
            }
            //if i coordinates to the numbers for postive y set y to 1
            if(i > 6 || i == 1){
                tempCoordY = 1;
            //if i coordinates to the numbers for negative y set y to -1
            }else if(i < 6 && i > 2){
                tempCoordY = -1;
            }
            //is off the edge of the board does nothing
            if((tempCoordX+x > this.currentBoard.getWidth()-1 || x+tempCoordX < 0 
                ||tempCoordY+y > this.currentBoard.getHeight()-1 || y+tempCoordY < 0 )){
            //not off the edge and is a contains an entity;
            }else if(this.currentBoard.getTile(x+tempCoordX, y+tempCoordY).getEntity() != null){
                // if the entity is a bomb execute bomb.explodeBomb();
                if(this.currentBoard.getTile(x+tempCoordX, y+tempCoordY).getEntity().getEntityName() == "Bomb"){
                    //the bomb on an adjacent tile
                    Bomb bomb = (Bomb) this.currentBoard.getTile(x+tempCoordX, y+tempCoordY).getEntity();
                    //starts the bomb detination
                    bomb.explodeBomb();
                }
            }
        }
    }

    private Boolean validMove() {
        if (super.coord[0] + super.coordChange[0] < 0 
        || super.coord[0] + super.coordChange[0] > super.getCurrentBoard().getWidth()-1
        || super.coord[1] + super.coordChange[1] < 0 
        || super.coord[1] + super.coordChange[1] > super.getCurrentBoard().getHeight()-1 ){
            super.coordChange[0] = - super.coordChange[0];
            super.coordChange[1] = - super.coordChange[1];
        }
        return true;
    }

    public int[] getDirection() {
        return super.coordChange;
    }
}
