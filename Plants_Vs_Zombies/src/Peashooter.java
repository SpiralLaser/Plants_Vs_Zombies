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
		game = newGame;
	}
	
	public Peashooter(Plant p) {
		this(p.getGridCell(), p.getGame());
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
    	if (cell != null)
    	{
    		game.getCell(cell).getZombie().zombieHit(1);
    		//System.out.println("Hit zombie at " + game.getCell(cell).getRow() + ", " + game.getCell(cell).getCol());

    	}
    	
    }
    
}
