public class Sunflower extends Plant {
	PvZModel game;

	/**
	 * Constructor for Sunflower plant class
	 * @author Kevin Sun
	 * @param initialGridCell
	 * @param newGame
	 */
	public Sunflower(GridCell initialGridCell, PvZModel newGame) {

		super(initialGridCell, newGame);
		game = newGame;
	}
	
	public Sunflower(Plant p) {
		this(p.getGridCell(), p.getGame());
	}
	
    /**
     * Returns the identification of being a sunflower
     */
    public String getID()
    {
        return "S";
    }


    public void endTurn()
    {
    	game.increaseSunlight(25);
    }
}
