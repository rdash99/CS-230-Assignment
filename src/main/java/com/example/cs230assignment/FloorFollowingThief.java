package com.example.cs230assignment;

/**
 * a class to create an instance of a FloorFollowingThief.
 *
 * @author Charles Overton
 * @version 1.0
 */
class FloorFollowingThief extends NPC {
  // colour the thief is assigned to follow
  private char allocatedColour;

  /**
   * constructor for floor following thief(assigns basic values)
   * 
   * @param allocatedColourPass the colour the floorfollowing thief is required
   *                            to follow
   * @param x                   the x coord the thief spawns on
   * @param y                   the y coord the thief spawns on
   */
  public FloorFollowingThief(char allocatedColourPass, int x, int y) {
    // Allocated a timer of 0.5 seconds for the thief
    super("Floor Following Thief", 500, x, y);
    this.allocatedColour = allocatedColourPass;
    super.coordChange = new int[] { x, y };
  }

  /**
   * passes the allocated colour
   * 
   * @return allocatedColour
   */
  public char getAllocatedColour() {
    return this.allocatedColour;
  }

  /**
   * performs the movement for the npc from tile to tile as well as call valid
   * move
   */
  public void move() {
    Boolean x = validMove();
    if (x) {
      super.interact(this.coord[0] + this.coordChange[0],
          this.coord[1] + this.coordChange[1]);
      Character.currentBoard.getTile(this.coord[0], this.coord[1])
          .removeEntity();
      Character.currentBoard.getTile(this.coord[0] + this.coordChange[0],
          this.coord[1] + this.coordChange[1]);
      this.coord[0] = this.coord[0] + this.coordChange[0];
      this.coord[1] = this.coord[1] + this.coordChange[1];
    }
  }

  /**
   * checks the tiles for possible movements changes the change in coords till
   * finds a valid move or has looped all the way around when it finds a
   * possible move it ends with the correct change in coords
   */
  private Boolean validMove() {
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
    for (int i = 0; i <= 4; i++) {
      // checks to see if it has checked all directions else sets direction to
      // stay on square
      if (i != 4) {
        // checks to see if there is a tile to the right side of the board of
        // the thief
        if ((super.coordChange[0] == 1 && super.coordChange[1] == 0)
            && super.getXCoord() != super.getCurrentBoard().getWidth()) {
          // checks to see if the tile is the correct colour
          if (super.getCurrentBoard()
              .getTile(super.getXCoord(), super.getYCoord() + 1)
              .checkColour(this.allocatedColour) == true) {
            // checks to see if there is an entity on the tile
            if (!(super.getCurrentBoard()
                .getTile(super.getXCoord(), super.getYCoord() + 1)
                .getEntity() != null)) {
              // checks to see if the entity on the tile is one that will block
              // movement
              // if not the valid move has been found
              // if it is check another direction
              switch (Character.currentBoard
                  .getTile(super.getXCoord(), super.getYCoord() + 1).getEntity()
                  .getEntityName()) {
              case ("Gate"):
                if (!((Gate) Character.currentBoard
                    .getTile(super.getXCoord(), super.getYCoord() + 1)
                    .getEntity()).getGateOpen()) {
                  i = 5;
                } else {
                  super.coordChange[0] = -1;
                  super.coordChange[1] = 0;
                }
                break;
              case ("FloorFollowingThief"):
              case ("FlyingAssassin"):
              case ("SmartThief"):
                super.coordChange[0] = 0;
                super.coordChange[1] = -1;
                break;
              case ("Player"):
                return false;
              // breaks out of the loop as the correct direction has been found
              default:
                i = 5;
              }
              // breaks out of the loop as the valid move has been found
            } else {
              i = 5;
            }
            // incorrect colour check next direction
          } else {
            super.coordChange[0] = 0;
            super.coordChange[1] = -1;
          }
          // checks to see if there is a valid move up from the
        } else if ((super.coordChange[0] == 0 && super.coordChange[1] == 1)
            && super.getYCoord() != super.getCurrentBoard().getHeight()) {
          // checks to see if the tile is the correct colour
          if (super.getCurrentBoard()
              .getTile(super.getXCoord(), super.getYCoord() + 1)
              .checkColour(this.allocatedColour) == true) {
            // checks to see if there is an entity on the tile
            if (!(super.getCurrentBoard()
                .getTile(super.getXCoord(), super.getYCoord() + 1)
                .getEntity() != null)) {
              // checks to see if the entity on the tile blocks movement
              // if not valid move has been found
              // else breaks out of the loop as the correct direction has been
              // found
              switch (Character.currentBoard
                  .getTile(super.getXCoord(), super.getYCoord() + 1).getEntity()
                  .getEntityName()) {
              case ("Gate"):
                if (!((Gate) Character.currentBoard
                    .getTile(super.getXCoord(), super.getYCoord() + 1)
                    .getEntity()).getGateOpen()) {
                  i = 5;
                } else {
                  super.coordChange[0] = -1;
                  super.coordChange[1] = 0;
                }
                break;
              case ("FloorFollowingThief"):
              case ("FlyingAssassin"):
              case ("SmartThief"):
                super.coordChange[0] = 1;
                super.coordChange[1] = 0;
                break;
              case ("Player"):
                return false;
              // breaks out of the loop as the correct direction has been found
              default:
                i = 5;
              }
              // valid move breaks out of loop as correct direction has been
              // found
            } else {
              i = 5;
            }
            // incorrect colour invalid move checks another direction
          } else {
            super.coordChange[0] = 1;
            super.coordChange[1] = 0;
          }
          // checks left of the thief on the board for a valid move
        } else if ((super.coordChange[0] == -1 && super.coordChange[1] == 0)
            && super.getXCoord() != 0) {
          // checks to see if the tile is the correct colour
          if (super.getCurrentBoard()
              .getTile(super.getXCoord(), super.getYCoord() + 1)
              .checkColour(this.allocatedColour) == true) {
            // checks to see if there is an entity on the tile
            if (!(super.getCurrentBoard()
                .getTile(super.getXCoord(), super.getYCoord() + 1)
                .getEntity() != null)) {
              // checks to see if there is an entity on the tile that blocks
              // movement
              // if it is a movement blocking entity check another direction
              // else break out of the loop as it is a valid move
              switch (Character.currentBoard
                  .getTile(super.getXCoord(), super.getYCoord() + 1).getEntity()
                  .getEntityName()) {
              case ("Gate"):
                if (!((Gate) Character.currentBoard
                    .getTile(super.getXCoord(), super.getYCoord() + 1)
                    .getEntity()).getGateOpen()) {
                  i = 5;
                } else {
                  super.coordChange[0] = -1;
                  super.coordChange[1] = 0;
                }
                break;
              case ("FloorFollowingThief"):
              case ("FlyingAssassin"):
              case ("SmartThief"):
                super.coordChange[0] = 0;
                super.coordChange[1] = 1;
                break;
              case ("Player"):
                return false;
              // breaks out of the loop as the correct direction has been found
              default:
                i = 5;
              }
              // valid move break out of loop as correct direction has been
              // found
            } else {
              i = 5;
            }
            // incorrect colour invalid move check a new direction
          } else {
            super.coordChange[0] = 0;
            super.coordChange[1] = 1;
          }
          // checks below the thief on the board for a valid move
        } else if ((super.coordChange[0] == 0 && super.coordChange[1] == -1)
            && super.getYCoord() != 0) {
          // checks to see if the tile is the correct colour
          if (super.getCurrentBoard()
              .getTile(super.getXCoord(), super.getYCoord() + 1)
              .checkColour(this.allocatedColour) == true) {
            // checks to see if the tile contains an entity
            if (!(super.getCurrentBoard()
                .getTile(super.getXCoord(), super.getYCoord() + 1)
                .getEntity() != null)) {
              // checks to see if the entity on the tile blocks movement
              // if it is check a different direction
              // if it isnt then its a valid move
              switch (Character.currentBoard
                  .getTile(super.getXCoord(), super.getYCoord() + 1).getEntity()
                  .getEntityName()) {
              case ("Gate"):
                if (!((Gate) Character.currentBoard
                    .getTile(super.getXCoord(), super.getYCoord() + 1)
                    .getEntity()).getGateOpen()) {
                  i = 5;
                } else {
                  super.coordChange[0] = -1;
                  super.coordChange[1] = 0;
                }
                break;
              case ("FloorFollowingThief"):
              case ("FlyingAssassin"):
              case ("SmartThief"):
                super.coordChange[0] = -1;
                super.coordChange[1] = 0;
                break;
              case ("Player"):
                return false;
              // breaks out of the loop as the correct direction has been found
              default:
                i = 5;
              }
              // valid move breaks out of loop as the correct direction has been
              // found
            } else {
              i = 5;
            }
            // invalid move as incorrect colour
          } else {
            super.coordChange[0] = -1;
            super.coordChange[1] = 0;
          }
        }
        // sets the movement to make the thief stand still as there is no valid
        // move
      } else {
        return false;
      }
    }
    return true;
  }
}
