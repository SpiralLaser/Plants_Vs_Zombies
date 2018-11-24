public class Wallnut extends Plant {
	PvZModel game;

	/**
	 * Constructor for TwinSunflower plant class
	 * @author Kevin Sun
	 * @param initialGridCell
	 * @param newGame
	 */
	public Wallnut(GridCell initialGridCell, PvZModel newGame) {

		super(initialGridCell, newGame);
        gridCell = initialGridCell;
		game = newGame;
		this.setHealth(3);
	}
	
    /**
     * Returns the identification of being a sunflower
     */
    public String getID()
    {
        return "W";
    }


    public void endTurn()
    {
    	//wallnut does nothing at end of turns
    }
}