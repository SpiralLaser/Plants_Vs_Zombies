public class Sunflower extends Plant {
	PvZGame game;

	/**
	 * Constructor for Sunflower plant class
	 * @author Kevin Sun
	 * @param initialLocation
	 * @param newGame
	 */
	public Sunflower(Location initialLocation, PvZGame newGame) {

		super(initialLocation, newGame);
		game = newGame;
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
    	game.increaseSunlight();
    }
}
