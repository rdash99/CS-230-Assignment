public class NPC extends Character{
    private double movementTimer;
    protected Int[] coordChange;
    protected board currentBoard;
    // thinking about adding move into here aswell as character for the basic movement of the NPCS
    public void NPC(Board boardPass, double movementTimerPass){
        this.movementTimer = movementTimerPass;
        this.currentBoard = boardPass;
    }
    protected Int[] ShortestPath(Int[] coordTo){

    }
    protected void validMove(int[] coordCheck){

    }
    protected double getMovementTimer(){

    }
}
