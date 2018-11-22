public class Repeater extends Plant {
	PvZModel game;

	/**
	 * Constructor 
	 * @param initialGridCell
	 * @param newGame
	 * @author Kevin Sun
	 */
	public Repeater(GridCell initialGridCell, PvZModel newGame) {

		super(initialGridCell, newGame);
        gridCell = initialGridCell;
		game = newGame;
	}
	
    /**
     * Returns the identification of being a Repeater
     */
    public String getID()
    {
        return "R";
    }

    /**
     * End of turn actions for this Repeater is shooting the first zombie in the row and does 2 damage.
     */
    public void endTurn()
    {
    	GridCell cell = game.findZombie(this.getGridCell().getRow(), this.getGridCell().getCol());
    	if (cell != null)
    	{
    		Zombie firstZomb = cell.getZombie();
    		firstZomb.zombieHit(2);
    	}
    	
    }
    
}
