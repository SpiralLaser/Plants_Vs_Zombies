	
/** Zombie superclass for all zombies
	 * @author Leo Paz
	 * */
public class Zombie {	
	/* The zombie's health */
	private int zombieHealth;
	
	protected GridCell GridCell;
	private PvZModel game;
	
	

	public Zombie (GridCell initialGridCell, PvZModel newGame) {		

		zombieHealth = 4;

		GridCell = initialGridCell;
		game = newGame;
	}
	
	/*
	 * Sets the health of the zombie
	 */
	public void setHealth(int health) {
		if (health > 0 && health < 20) zombieHealth = health;
	}
	
	/*
	 * Does damage to the zombie and returns how much health it has
	 * @param dmg How much damage will be done to the zombie
	 * @return The health of the zombie
	 */
	public int zombieHit(int dmg) {
		zombieHealth -= dmg;
		
		if (zombieHealth <= 0) {
			return 0;
		}
		return zombieHealth;
	}
	
	/*
	 * Gets the health of the zombie
	 * @return The zombie's health
	 */
	public int getHealth() {
		return zombieHealth;
	}

	/**
	 * Returns a string so the board knows what to print
	 * @return
	 */
	public String getID()
	{
		return "Z";
	}
	
	/**
	 * Returns this zombie's GridCell
	 * @return
	 */
	public GridCell getGridCell()
	{
		return GridCell;
	}
	
	public PvZModel getGame() {
		return game;
	}
	
	/**
	 * Updates this zombie's GridCell
	 * @param loc
	 */
	public void updateGridCell(GridCell loc)
	{
		GridCell = loc;
	}
	
	
	/**
	 * Actions that the zombie does at end of the turn
	 */
	public void endTurn()
	{
		
		int col = this.getGridCell().getCol();		
		int row = this.getGridCell().getRow();
		GridCell destination = new GridCell(row, col - 1);
		
		//there is a plant in the next space, so start damaging it
		if (game.getCell(destination).getPlant() != null)
		{
			Plant plant = game.getCell(destination).getPlant();
			plant.takeDamage(1);
		}
		//otherwise move to the next space
		else
		{
			Zombie zomb = game.getCell(GridCell).removeZombie();		
			game.moveZombie(zomb, destination);
			this.updateGridCell(destination);
		}
		
	}
	
	/**
	 * Checks if the zombie has reached the end, if it has the player loses.
	 * @return boolean if zombie has reached the end
	 */
	public boolean checkLose()
	{
		int col = this.getGridCell().getCol();		
		if (col == 0)
		{
			return true;
		}
		return false;
	}
}
