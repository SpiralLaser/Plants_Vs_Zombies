import java.util.*;
public abstract class Plant {
	
	    private PvZGame game;
	    private GridCell location;
	    protected String race;
	    private int plantHealth;

	    public Plant(GridCell initialLocation, PvZGame newGame)
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
	    public void setLocation(GridCell newLocation)
	    {
	        location = newLocation;
	    }
	    
	    /**
	     * Returns this unit's location
	     */
	    public GridCell getLocation()
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

