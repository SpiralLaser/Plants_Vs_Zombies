import java.util.Locale;

public abstract class Plant {
	
	    private PvZModel game;
	    protected GridCell gridCell;
	    private int plantHealth;

	    /**
	     * Plant superclass for all plant objects
	     * @param initialGridCell is the starting position of the plant on the board
	     * @param newGame is reference to the current game
	     * @author Kevin Sun
	     */
	    public Plant(GridCell initialGridCell, PvZModel newGame)
	    {

	        gridCell = initialGridCell;
	        game = newGame;
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
	     * Sets this unit's GridCell to the passed UnitGridCell
	     */    
	    public void setGridCell(GridCell newGridCell)
	    {
	        gridCell = newGridCell;
	    }
	    
	    /**
	     * Returns this unit's GridCell
	     */
	    public GridCell getGridCell()
	    {
	        return gridCell;
	    }

	    /**
	     * Returns the game that this unit is playing on
	     */
	    public PvZModel getGame()
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

