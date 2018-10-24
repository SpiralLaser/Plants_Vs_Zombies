import java.util.*;
public abstract class Unit {
	
	    private PvZGame game;
	    private UnitLocation location;
	    protected char id;
	    protected String race;

	    public Unit(UnitLocation initialLocation, PvZGame newGame)
	    {

	        location = initialLocation;
	        game = newGame;
	        game.getBoard().placeUnitAt(this,location);

	    }

	    /**
	     * Returns the ID of this unit. Is only used so that the child can override this method.
	     */
	    public String getID()
	    {
	        return "ID";
	    }
	    
	    /**
	     * Returns the race of this unit. Is only used so that the child can override this method
	     * @return race
	     */
	    public String getRace()
	    {
	    	return race;
	    }

	    /**
	     * Sets this unit's location to the passed UnitLocation
	     */    
	    public void setLocation(UnitLocation newLocation)
	    {
	        location = newLocation;
	    }
	    
	    /**
	     * Returns this unit's location
	     */
	    public UnitLocation getLocation()
	    {
	        return location;
	    }

	    /**
	     * Returns the game that this unit is playing on
	     */
	    public PvZGame getGame()
	    {
	        return game;
	    }

	    public void endTurn()
	    {
	    	
	    }
	}

