package com.example.cs230assignment;

public class Character extends Entity {
    protected Board currentBoard;

    public Character(String name, int x, int y) {
        super(name, x, y);
    }
    public void boardSet(Board freshBoard){
        this.currentBoard = freshBoard;
    }

    public void setBoard(Board board) {
        this.currentBoard = board;
    }

    protected void interact(Timer timer) {
        int x = this.coord[0];
        int y = this.coord[1];
        switch (currentBoard.getTile(x, y).getEntity().getEntityName()){
            case("item"):
                this.currentBoard.getTile(x, y).removeEntity();
                break;
            case("key"):
                Key key = (Key) currentBoard.getTile(x, y).getEntity();
                key.openGate();
                break;
            case("clock"):
                Clock clock = (Clock) currentBoard.getTile(x, y).getEntity();
                this.currentBoard.getTimer().addClock(clock);
                break;
            case("player"):
                currentBoard.getPlayer().die();
                break;
        }
    }
    // protected void drawMove(){
    // }
    // not needed as this will be covered by currentBoard update
}
