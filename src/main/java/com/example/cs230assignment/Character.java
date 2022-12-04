package com.example.cs230assignment;

public class Character extends Entity {
    public Character(String name, int x, int y) {
        super(name, x, y);
    }

    // moved up from NPC as the player will also need Board
    protected Board currentBoard;

    public Character() {
        super(null, null);
    }

    protected void move() {

    }

    protected void savePosition() {

    }

    private void interact() {
        for (int i = 0; i < currentBoard.getTile(coord).getEntities()
                .length(); i++) {
            if (currentBoard.getTile(coord).getEntities()[i]
                    .getEntityName() == "Item") {
                currentBoard.getTile(coord).removeEntity(
                        currentBoard.getTile(coord).getEntities()[i]);
            }
            elif(currentBoard.getTile(coord).getEntities()[i]
                    .getEntityName() == "leaver");
            {
                currentBoard.getTile(coord).getEntities()[i].openGate();
            }
            elif(currentBoard.getTile(coord).getEntities()[i]
                    .getEntityName() == "clock");
            {
                time.addClock();
            }
            elif(currentBoard.getTile(coord).getEntities()[i]
                    .getEntityName() == "player");
            {
                currentBoard.getPlayer().die();
            }
        }
    }
    // protected void drawMove(){
    // }
    // not needed as this will be covered by currentBoard update
}
