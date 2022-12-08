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
    super.coordChange = new int[] { x, y };
  }


  // needs to compare to the previous coordChange as it needs to stick to the
  // edge not to the left
  // needs to check to the left of the previous coord change
  private void validMove() {
    // changes the coordChange to the coord to the left

    if (super.coordChange[0] == 1 && super.coordChange[1] == 0) {
      super.coordChange[0] = 0;
      super.coordChange[1] = 1;
    } else if (super.coordChange[0] == 0 && super.coordChange[1] == 1) {
      super.coordChange[0] = -1;
      super.coordChange[1] = 0;
    } else if (super.coordChange[0] == -1 && super.coordChange[1] == 0) {
      super.coordChange[0] = 0;
      super.coordChange[1] = -1;
    } else if (super.coordChange[0] == 0 && super.coordChange[1] == -1) {
      super.coordChange[0] = 1;
      super.coordChange[1] = 0;
    }
    // loops in all directions to check for a valid move
    for (int i = 0; i < 4; i++) {
      if (i != 4) {
        if ((super.coordChange[0] == 1 && super.coordChange[1] == 0)
            && super.getXCoord() != super.getCurrentBoard().getWidth()) {
          if (super.getCurrentBoard()
              .getTile(super.getXCoord(), super.getYCoord() + 1)
              .checkColour(this.allocatedColour) == true || !(super.getCurrentBoard()
              .getTile(super.getXCoord(), super.getYCoord() + 1).getEntity().getEntityName() == "Gate")) {
            i = 5;
          } else {
            super.coordChange[0] = 0;
            super.coordChange[1] = -1;
          }
        } else if ((super.coordChange[0] == 0 && super.coordChange[1] == 1)
            && super.getYCoord() != super.getCurrentBoard().getHeight()) {
          if (super.getCurrentBoard()
              .getTile(super.getXCoord(), super.getYCoord() + 1)
              .checkColour(this.allocatedColour) == true || !(super.getCurrentBoard()
              .getTile(super.getXCoord(), super.getYCoord() + 1).getEntity().getEntityName() == "Gate")) {
            i = 5;
          } else {
            super.coordChange[0] = 1;
            super.coordChange[1] = 0;
          }
        } else if ((super.coordChange[0] == -1 && super.coordChange[1] == 0)
            && super.getXCoord() != 0) {
          if (super.getCurrentBoard()
              .getTile(super.getXCoord(), super.getYCoord() - 1)
              .checkColour(this.allocatedColour) == true || !(super.getCurrentBoard()
              .getTile(super.getXCoord(), super.getYCoord() + 1).getEntity().getEntityName() == "Gate")) {
            i = 5;
          } else {
            super.coordChange[0] = 0;
            super.coordChange[1] = 1;
          }
        } else if ((super.coordChange[0] == 0 && super.coordChange[1] == -1)
            && super.getYCoord() != 0) {
          if (super.getCurrentBoard()
              .getTile(super.getXCoord(), super.getYCoord() - 1)
              .checkColour(this.allocatedColour) == true || !(super.getCurrentBoard()
              .getTile(super.getXCoord(), super.getYCoord() + 1).getEntity().getEntityName() == "Gate")) {
            i = 5;
          } else {
            super.coordChange[0] = -1;
            super.coordChange[1] = 0;
          }
        }
      } else {
        super.coordChange[0] = 0;
        super.coordChange[1] = 0;
      }
    }
  }
}
