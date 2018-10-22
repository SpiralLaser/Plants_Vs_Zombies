
public class Sunflower extends Unit {

	public Sunflower(UnitLocation initialLocation, PvZGame newGame) {

		super(initialLocation, newGame);
		super.id = 'S';
		super.race = "Plant";
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

}
