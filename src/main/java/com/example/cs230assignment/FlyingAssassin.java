package com.example.cs230assignment;

import javafx.scene.canvas.GraphicsContext;

/**
 * a class to create an instance of a FlyingAssassin.
 * 
 * @author Charles Overton
 * @version 1.0
 */
public class FlyingAssassin extends NPC {
    /**
     * constructor to assign basic values upon creation of a flyingassassin
     * 
     * @param movementTimerPass the speed the npc moves
     * @param x                 the starting x coord of the flying assassin
     * @param y                 the starting y coord of the flying assassin
     * @param xdir              the x direction the flying assassin will move in
     *                          after spawning
     * @param ydir              the y direction of the flying assassin will move
     *                          after spawning
     */
    public FlyingAssassin(int movementTimerPass, int x, int y, int xdir,
                          int ydir) {
        // constructor for NPC
        super("Flying Assassin", movementTimerPass, x, y);
        super.coordChange = new int[] { xdir, ydir };
    }

    /**
     * moves the npc from one tile to another calls validmove to check for the
     * next move
     */
    public void move(GraphicsContext gc, Board board) {
        Boolean x = validMove(board);
        // move if a valid move is found
        if (x) {
            super.interact(this.coord[0] + this.coordChange[0],
                    this.coord[1] + this.coordChange[1]);
            board.getTile(this.coord[0], this.coord[1])
                    .removeEntity();
            board.getTile(this.coord[0] + this.coordChange[0],
                    this.coord[1] + this.coordChange[1]);
            this.coord[0] = this.coord[0] + this.coordChange[0];
            this.coord[1] = this.coord[1] + this.coordChange[1];
            board.getTimer().boardUpdate(gc, board);
        }
    }

    /**
     * interact for flying assassin allowing the assassin to kill as well as
     * interact with other entities
     */
    @Override
    protected void interact(int x, int y) {
        if (currentBoard.getTile(x, y).getEntity() != null) {
            // select the entities by type and perform the correct interactions
            switch (currentBoard.getTile(x, y).getEntity().getEntityName()) {
            case ("item"):
                Character.currentBoard.getTile(x, y).removeEntity();
                break;
            case ("key"):
                Key key = (Key) Character.currentBoard.getTile(x, y)
                        .getEntity();
                key.openGate();
                break;
            case ("clock"):
                Clock clock = (Clock) Character.currentBoard.getTile(x, y)
                        .getEntity();
                Character.currentBoard.getTimer().addClock(clock);
                break;
            case ("player"):
                Character.currentBoard.getPlayer().die();
                break;
            case ("fla"):
            case ("fft"):
            case ("smt"):
                Character.currentBoard.getTile(x, y).removeEntity();
            }
        }
        for (int i = 1; i < 9; i++) {
            // the xcoord of an adjacent tile
            int tempCoordX = 0;
            // the ycoord of an adjacent tile
            int tempCoordY = 0;
            // checks to see if i isnt 8 or 4 if it isnt anything above 4
            // becomes -1 anything below 1
            if (i % 4 != 0) {
                tempCoordX = 1 + (int) -(Math.floor((double) (i / 4.0)) * 2);
            }
            // if i coordinates to the numbers for postive y set y to 1
            if (i > 6 || i == 1) {
                tempCoordY = 1;
                // if i coordinates to the numbers for negative y set y to -1
            } else if (i < 6 && i > 2) {
                tempCoordY = -1;
            }
            // is off the edge of the board does nothing
            if ((tempCoordX + x > Character.currentBoard.getWidth() - 1
                    || x + tempCoordX < 0
                    || tempCoordY + y > Character.currentBoard.getHeight() - 1
                    || y + tempCoordY < 0)) {
                // not off the edge and is a contains an entity;
            } else if (Character.currentBoard
                    .getTile(x + tempCoordX, y + tempCoordY)
                    .getEntity() != null) {
                // if the entity is a bomb execute bomb.explodeBomb();
                if (Character.currentBoard
                        .getTile(x + tempCoordX, y + tempCoordY).getEntity()
                        .getEntityName() == "Bomb") {
                    // the bomb on an adjacent tile
                    Bomb bomb = (Bomb) Character.currentBoard
                            .getTile(x + tempCoordX, y + tempCoordY)
                            .getEntity();
                    // starts the bomb detination
                    bomb.explodeBomb();
                }
            }
        }
    }

    /**
     * @return Boolean
     */
    private Boolean validMove(Board board) {
        if (super.coord[0] + super.coordChange[0] < 0
                || super.coord[0] + super.coordChange[0] > board
                        .getWidth() - 1
                || super.coord[1] + super.coordChange[1] < 0
                || super.coord[1] + super.coordChange[1] > board
                        .getHeight() - 1) {
            super.coordChange[0] = -super.coordChange[0];
            super.coordChange[1] = -super.coordChange[1];
        }
        return true;
    }

    /**
     * @return int[]
     */
    public int[] getDirection() {
        return super.coordChange;
    }
}
