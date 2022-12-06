package com.example.cs230assignment;

public class Character extends Entity {
    protected Board currentBoard;

    public Character(String name, int x, int y) {
        super(name, x, y);
    }
    public void boardSet(Board freshBoard){
        this.currentBoard = freshBoard;
    }

    protected void interact(Timer timer) {
        int x = this.coord[0];
        int y = this.coord[1];
        for (int i = 0; i < currentBoard.getTile(x, y)
                .getEntities().length; i++) {
            if (currentBoard.getTile(x, y).getEntities()[i]
                    .getEntityName() == "item") {
                currentBoard.getTile(x, y).removeEntity(
                        currentBoard.getTile(x, y).getEntities()[i]);
            } else if (currentBoard.getTile(x, y).getEntities()[i]
                    .getEntityName() == "key") {
                Key key = (Key) currentBoard.getTile(x, y).getEntities()[i];
                key.openGate();
                currentBoard.getTile(x, y).removeEntity(
                        currentBoard.getTile(x, y).getEntities()[i]);
            } else if (currentBoard.getTile(x, y).getEntities()[i]
                    .getEntityName() == "clock") {
                Clock clock = (Clock) currentBoard.getTile(x, y)
                        .getEntities()[i];
                timer.addClock(clock);
            } else if (currentBoard.getTile(x, y).getEntities()[i]
                    .getEntityName() == "player") {
                currentBoard.getPlayer().die();
            }
        }
    }
    // protected void drawMove(){
    // }
    // not needed as this will be covered by currentBoard update
}
