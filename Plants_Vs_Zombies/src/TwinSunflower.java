public class TwinSunflower extends Plant {
	PvZModel game;

	/**
	 * Constructor for TwinSunflower plant class
	 * @author Kevin Sun
	 * @param initialGridCell
	 * @param newGame
	 */
	public TwinSunflower(GridCell initialGridCell, PvZModel newGame) {

		super(initialGridCell, newGame);
		game = newGame;
	}
	
	public TwinSunflower(Plant p) {
		this(p.getGridCell(), p.getGame());
	}
	
    /**
     * Returns the identification of being a twin sunflower
     */
    public String getID()
    {
        return "T";
    }


    public void endTurn()
    {
    	game.increaseSunlight(50);
    }
}
