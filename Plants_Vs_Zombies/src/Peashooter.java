public class Peashooter extends Plant {
	PvZGame game;

	/**
	 * Constructor 
	 * @param initialLocation
	 * @param newGame
	 * @author Kevin Sun
	 */
	public Peashooter(Location initialLocation, PvZGame newGame) {

		super(initialLocation, newGame);
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
    	GridCell cell = game.getBoard().findZombie(this.getLocation().getRow(), this.getLocation().getCol());
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
