import java.util.Locale;

public abstract class Plant {
	
	    private PvZGame game;
	    private Location location;
	    private int plantHealth;

	    /**
	     * Plant superclass for all plant objects
	     * @param initialLocation is the starting position of the plant on the board
	     * @param newGame is reference to the current game
	     * @author Kevin Sun
	     */
	    public Plant(Location initialLocation, PvZGame newGame)
	    {

	        location = initialLocation;
	        game = newGame;
	        game.getBoard().placePlantAt(this,location);
	        plantHealth = 1;
	    }

	    /**
	     * Returns the ID of this unit. Is only used so that the child can override this method.
	     */
	    public String getID()
	    {
	        return "ID";
	    }
	    
	    /**
	     * Sets this unit's location to the passed UnitLocation
	     */    
	    public void setLocation(Location newLocation)
	    {
	        location = newLocation;
	    }
	    
	    /**
	     * Returns this unit's location
	     */
	    public Location getLocation()
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
	    
	    /**
	     * Plant takes i damage
	     * @param i
	     */
	    public void takeDamage(int i)
	    {
	    	plantHealth -= i;
	    	System.out.println(plantHealth);
	    }
	    
	    /**
	     * Checks if plant has run out of health
	     * @return boolean if plant is alive or not
	     */
	    public boolean isAlive()
	    {
	    	if (plantHealth <= 0)
	    	{
	    		return false;
	    	}
	    	return true;
	    }
	}

