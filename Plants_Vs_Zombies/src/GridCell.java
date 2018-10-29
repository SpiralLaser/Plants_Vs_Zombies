import java.util.*;

public class GridCell {

	private LinkedList<Zombie> zombies;
	private int row, col;
	private Plant plant;

	/** 
	 * Constructor for the grid class that takes a
	 * zombie or plant and placed it on the grid space 
	 * @author Leo Paz
	 **/
	public GridCell (int newRow, int newCol) {
		zombies = new LinkedList<Zombie>();
		plant = null;
		row = newRow;
		col = newCol;
	}

	/* 
	 * Getter method to see the visitors on the current grid cell
	 * @return ArrayList of visitors on the grid 
	 **/
	public LinkedList<Zombie> getZombies(){
		return zombies;
	}
	/**
	 * Getter method to get the first zombie on that grid cell
	 * @return Single zombie object
	 */
	public Zombie getZombie()
	{
		return zombies.getFirst();
	}

	/*
	 * Retrieves the current plant on the grid cell
	 * @return the Plant on the grid if there is a plant, null otherwise
	 */
	public Plant getPlant() {
		return plant;
	}

	/*
	 * Adds a zombie to the linked list of zombies for that grid cell
	 */
	public void addZombie(Zombie zombie) {

		zombies.add(zombie);
	}

	/* 
	 * Adds a new plant if the grid doesn't have a current plant
	 **/
	public void addPlant(Object plant){
		if(plant != null){
			this.plant = (Plant) plant;
		}
	}
	/*
	 * Removes and returns the plant on the current grid cell 
	 * @return the plant that was removed
	 */
	public Plant removePlant() {
		Plant temp = plant;
		plant = null;
		return temp;
	}

	/*
	 * Removes and returns the zombie if it needs to move to a new grid cell
	 * @return the zombie that needs to be returned
	 */
	public Zombie removeZombie() {
		return zombies.removeFirst();
	}

	/**
	 * Removes the zombie at the start of the list as if it died
	 */
	public void removeAndKillZombie() {
		zombies.removeFirst();
	}

	//returns true if neither plant or zombie is on the tile
	public boolean isEmpty()
	{

		if (plant == null && zombies.isEmpty())
		{
			return true;
		}
		return false;
	}
	/**
	 * Returns the piece's row position
	 */
	public int getRow()
	{
		return row;
	}
	/**
	 * Returns the piece's column position
	 */
	public int getCol()
	{
		return col;
	}

	/**
	 * Sets the piece's row number to variable r
	 */
	public void setRow(int r)
	{
		row = r;
	}

	/** 
	 * Sets the piece's column number to variable c
	 */
	public void setCol (int c)
	{
		col = c;
	}

	/**
	 * Sets the piece's location to a new GridCell
	 */
	public void setLocation(int r, int c)
	{
		this.row = r;
		this.col = c;
	}

	/**
	 * Checks if the passed GridCell is the same as this unit's GridCell
	 */
	public boolean equals(GridCell ul)
	{
		if (ul.getRow() == row && ul.getCol() == col)
			return true;
		return false;
	}
}
