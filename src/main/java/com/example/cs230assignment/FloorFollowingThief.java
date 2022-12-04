package com.example.cs230assignment;

/**
 * Describes a class to create an instance of a FloorFollowingThief.
 *
 * @author Rowan Dash
 * @version 1.0
 */
class FloorFollowingThief extends NPC {
    private char allocatedColour;

    public FloorFollowingThief(char allocatedColourPass, int x, int y) {
        // Allocated a timer of 0.5 seconds for the thief
        super("Floor Following Thief", 0.5, x, y);
        this.allocatedColour = allocatedColourPass;
        this.coordChange = coordChangePass;
    }

    private void interact() {

    }

    // needs to compare to the previous coordChange as it needs to stick to the
    // edge not to the left
    // needs to check to the left of the previous coord change
    private void validMove() {
      //changes the coordChange to the coord to the left
      switch(this.coordChange){
        case [1,0]:
          this.coordChange = [0,1];
          break;
        case [0,1]:
          this.coordChange = [-1,0];
          break;
        case [-1,0]:
          this.coordChange = [0,-1];
          break;
        case [0,-1]:
          this.coordChange = [1,0];
          break;
      }
      //loops in all directions to check for a valid move
      for (int i = 0; i < 4; i++){
        if (i != 4){
          switch(this.coordChange){
            case [1,0]:
              if (this.board.getTile([this.coord[0]+1,this.coord[1]]).checkColour(this.allocatedColour) == true){
                i = 5;
                break;
              }else{
                this.coordChange = [0,-1];
                break;
              }
            case [0,1]:
              if (this.board.getTile([this.coord[0],this.coord[1]+1]).checkColour(this.allocatedColour) == true){
                i = 5;
                break;
              }else{
                this.coordChange = [1,0];
                break;
              }
            case [0,-1]:
              if (this.board.getTile([this.coord[0],this.coord[1]-1]).checkColour(this.allocatedColour) == true){
                i = 5;
                break;
              }else{
                this.coordChange = [-1,0];
                break;
              }
            case [-1,0];
              if (this.board.getTile([this.coord[0],this.coord[1]-1]).checkColour(this.allocatedColour) == true){
                i = 5;
                break;
              }else{
                this.coordChange = [0,1];
                break;
              }
          }
        }else{
          this.coordChange = [0,0];
        }
      }
    }
}
