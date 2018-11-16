public class Peashooter extends Plant {
	PvZModel game;

	/**
	 * Constructor 
	 * @param initialGridCell
	 * @param newGame
	 * @author Kevin Sun
	 */
	public Peashooter(GridCell initialGridCell, PvZModel newGame) {

		super(initialGridCell, newGame);
        gridCell = initialGridCell;
		game = newGame;
	}
	
    /**
     * Returns the identification of being a peashooter
     */
    public String getID()
    {
        return "P";
    }

    /**
     * End of turn actions for this peashooter is shooting the first zombie in the row
     */
    public void endTurn()
    {
    	GridCell cell = game.findZombie(this.getGridCell().getRow(), this.getGridCell().getCol());
    	if (cell == null)
    	{
    		//do nothing if there is no zombie on this row
    	}
    	else
    	{
    		Zombie firstZomb = cell.getZombie();
    		firstZomb.zombieHit(1);
    	}
    	
    }
    
}
