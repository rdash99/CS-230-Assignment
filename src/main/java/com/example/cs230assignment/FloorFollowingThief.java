package com.example.cs230assignment;

import javafx.scene.canvas.GraphicsContext;

/**
 * a class to create an instance of a FloorFollowingThief.
 *
 * @author Charles Overton
 * @version 1.0
 */
class FloorFollowingThief extends NPC {
  // colour the thief is assigned to follow
  private char allocatedColour;
  private Boolean waitToMove = false;

  /**
   * constructor for floor following thief(assigns basic values)
   * 
   * @param allocatedColourPass the colour the floorfollowing thief is required
   *                            to follow
   * @param x                   the x coord the thief spawns on
   * @param y                   the y coord the thief spawns on
   */
  public FloorFollowingThief(char allocatedColourPass, int x, int y, int changeX, int changeY) {
    // Allocated a timer of 0.5 seconds for the thief
    super("Floor Following Thief", 500, x, y);
    this.allocatedColour = allocatedColourPass;
    super.coordChange = new int[] { changeX, changeY };
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
   * 
   * @param gc the graphics context
   */
  public void move(GraphicsContext gc) {
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
      Character.currentBoard.getTimer().boardUpdate(gc, Character.currentBoard);
    }else {
    }
  }

  /**
   * checks the tiles for possible movements changes the change in coords till
   * finds a valid move or has looped all the way around when it finds a
   * possible move it ends with the correct change in coords
   * 
   * @return true if a valid move is found else false
   */
  private Boolean validMove() {
    // changes the coordChange to the coord to the left
    if (waitToMove == true){
      this.waitToMove = false;
    }else if (super.coordChange[0] == 1 && super.coordChange[1] == 0) {
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
        if (super.coordChange[0] == 1
            && super.coord[0] != Character.currentBoard.getWidth() - 1) {
          if (Character.currentBoard.getTile(coord[1], coord[0] + 1)
              .checkColour(this.allocatedColour)) {
            if (Character.currentBoard.getTile(coord[0] + 1, coord[1])
                .getEntity() != null) {
              if (Character.currentBoard.getTile(coord[0] + 1, coord[1])
                  .getEntity() instanceof Gate) {
                if (((Gate) Character.currentBoard
                    .getTile(coord[0] + 1, coord[1]).getEntity())
                    .getGateOpen()) {
                  return true;
                } else {
                  coordChange[0] = 0;
                  coordChange[1] = -1;
                }
              }
              if(Character.currentBoard.getTile(coord[0] + 1, coord[1]).getEntity() instanceof FloorFollowingThief 
              ||Character.currentBoard.getTile(coord[0] + 1, coord[1]).getEntity() instanceof Player 
              ||Character.currentBoard.getTile(coord[0] + 1, coord[1]).getEntity() instanceof FlyingAssassin 
              ||Character.currentBoard.getTile(coord[0] + 1, coord[1]).getEntity() instanceof SmartThief){
                this.waitToMove = true;
                return false;
              } else {
                return true;
              }
            } else {
              return true;
            }
          } else {
            coordChange[0] = 0;
            coordChange[1] = -1;
          }
        } else if (super.coordChange[0] == 1
            && super.coord[0] == Character.currentBoard.getWidth() - 1) {
          coordChange[0] = 0;
          coordChange[1] = -1;
        } else if (super.coordChange[0] == -1 && super.coord[0] != 0) {
          if (Character.currentBoard.getTile(coord[1], coord[0] - 1)
              .checkColour(this.allocatedColour)) {
            if (Character.currentBoard.getTile(coord[0] - 1, coord[1])
                .getEntity() != null) {
              if (Character.currentBoard.getTile(coord[0] - 1, coord[1])
                  .getEntity() instanceof Gate) {
                if (((Gate) Character.currentBoard
                    .getTile(coord[0] - 1, coord[1]).getEntity())
                    .getGateOpen()) {
                  return true;
                } else {
                  coordChange[0] = 0;
                  coordChange[1] = 1;
                }
              }
              if(Character.currentBoard.getTile(coord[0] - 1, coord[1]).getEntity() instanceof FloorFollowingThief 
              ||Character.currentBoard.getTile(coord[0] - 1, coord[1]).getEntity() instanceof Player 
              ||Character.currentBoard.getTile(coord[0] - 1, coord[1]).getEntity() instanceof FlyingAssassin 
              ||Character.currentBoard.getTile(coord[0] - 1, coord[1]).getEntity() instanceof SmartThief){
                this.waitToMove = true;
                return false;
              } else {
                return true;
              }
            } else {
              return true;
            }
          } else {
            coordChange[0] = 0;
            coordChange[1] = 1;
          }
        } else if (super.coordChange[0] == -1 && super.coord[0] == 0) {
          coordChange[0] = 0;
          coordChange[1] = 1;
        } else if (super.coordChange[1] == 1
            && super.coord[1] != Character.currentBoard.getHeight() - 1) {
          if (Character.currentBoard.getTile(coord[1] + 1, coord[0])
              .checkColour(this.allocatedColour)) {
            if (Character.currentBoard.getTile(coord[0], coord[1] + 1)
                .getEntity() != null) {
              if (Character.currentBoard.getTile(coord[0], coord[1] + 1)
                  .getEntity() instanceof Gate) {
                if (((Gate) Character.currentBoard
                    .getTile(coord[0], coord[1] + 1).getEntity())
                    .getGateOpen()) {
                  return true;
                } else {
                  coordChange[0] = 1;
                  coordChange[1] = 0;
                }
              }
              if(Character.currentBoard.getTile(coord[0], coord[1] + 1).getEntity() instanceof FloorFollowingThief 
              ||Character.currentBoard.getTile(coord[0], coord[1] + 1).getEntity() instanceof Player 
              ||Character.currentBoard.getTile(coord[0], coord[1] + 1).getEntity() instanceof FlyingAssassin 
              ||Character.currentBoard.getTile(coord[0], coord[1] + 1).getEntity() instanceof SmartThief){
                this.waitToMove = true;
                return false;
              } else {
                return true;
              }
            } else {
              return true;
            }
          } else {
            coordChange[0] = 1;
            coordChange[1] = 0;
          }
        } else if (super.coordChange[1] == 1
            && super.coord[1] == Character.currentBoard.getHeight() - 1) {
          coordChange[0] = 1;
          coordChange[1] = 0;
        } else if (super.coordChange[1] == -1 && super.coord[1] != 0) {
          if (Character.currentBoard.getTile(coord[1] - 1, coord[0])
              .checkColour(this.allocatedColour)) {
            if (Character.currentBoard.getTile(coord[0], coord[1] - 1)
                .getEntity() != null) {
              if (Character.currentBoard.getTile(coord[0], coord[1] - 1)
                  .getEntity() instanceof Gate) {
                if (((Gate) Character.currentBoard
                    .getTile(coord[0], coord[1] - 1).getEntity())
                    .getGateOpen()) {
                  return true;
                } else {
                  coordChange[0] = -1;
                  coordChange[1] = 0;
                }
              }
              if(Character.currentBoard.getTile(coord[0], coord[1] - 1).getEntity() instanceof FloorFollowingThief 
              ||Character.currentBoard.getTile(coord[0], coord[1] - 1).getEntity() instanceof Player 
              ||Character.currentBoard.getTile(coord[0], coord[1] - 1).getEntity() instanceof FlyingAssassin 
              ||Character.currentBoard.getTile(coord[0], coord[1] - 1).getEntity() instanceof SmartThief){
                this.waitToMove = true;
                return false;
              } else {
                return true;
              }
            } else {
              return true;
            }
          } else {
            coordChange[0] = -1;
            coordChange[1] = 0;
          }
        } else if (super.coordChange[1] == -1 && super.coord[1] == 0) {
          coordChange[0] = -1;
          coordChange[1] = 0;
        }
      } else {
        return false;
      }
    }
    return false;
  }
}
