package com.example.cs230assignment;

public class Character extends Entity {
    <<<<<<<
    Updated upstream

    public Character(String name, int x, int y) {
        super(name, x, y);
=======
    //moved up from NPC as the player will also need board
    protected Board currentBoard;

    public Character() {
        super(null, null);
>>>>>>> Stashed changes
    }

    protected void move() {

    }

    protected void savePosition() {

    }

    private void interact() {
        for( int i = 0; i <board.getTile(coord).getEntities().length(); i++){
            if (board.getTile(coord).getEntities()[i].getEntityName() == "Item"){
                board.getTile(coord).removeEntity(board.getTile(coord).getEntities()[i]);
            }elif (board.getTile(coord).getEntities()[i].getEntityName() == "leaver"){
                board.getTile(coord).getEntities()[i].openGate();
            }elif (board.getTile(coord).getEntities()[i].getEntityName() == "clock"){
                time.addClock();
            }elif (board.getTile(coord).getEntities()[i].getEntityName() == "player"){
                board.getPlayer().die()
            }
        }
    }
    // protected void drawMove(){
    // }
    // not needed as this will be covered by board update
}
