public class NPC extends Character{
    private double movementTimer;
    protected Int[] coordChange;
    protected board currentBoard;
    // thinking about adding move into here aswell as character for the basic movement of the NPCS
    public void NPC(boardPass, movementTimerPass){
        this.movementTimer = movementTimerPass;
        this.currentBoard = boardPass;
    }
    protected Int[] ShortestPath(coordTo){

    }
    protected void validMove(coordCheck){

    }
    protected double getMovementTimer(){
        
    }
}