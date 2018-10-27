public class Sunflower extends Plant {
	PvZGame game;

	public Sunflower(GridCell initialLocation, PvZGame newGame) {

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
