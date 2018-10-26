public class Sunflower extends Plant {
	PvZGame game;

	public Sunflower(GridCell initialLocation, PvZGame newGame) {

		super(initialLocation, newGame);
		super.race = "Plant";
		game = newGame;
	}
	
    /**
     * Returns the identification of being a sunflower
     */
    public String getID()
    {
        return "S";
    }
    
    /**
     * Returns the race of this unit
     */
    public String getRace()
    {
    	return race;
    }

    public void endTurn()
    {
    	game.increaseSunlight();
    }
}
