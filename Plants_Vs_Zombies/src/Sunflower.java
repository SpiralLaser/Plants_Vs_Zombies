public class Sunflower extends Unit {
	PvZGame game;

	public Sunflower(UnitLocation initialLocation, PvZGame newGame) {

		super(initialLocation, newGame);
		super.id = 'S';
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

	//add functionality of producing sunlight every x turns

    public void endTurn()
    {
    	game.increaseSunlight();
    }
}