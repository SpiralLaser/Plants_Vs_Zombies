public class Zombie {
	/* The zombie's movement speed */
	private int zombieSpeed;
	
	/* The zombie's health */
	private int zombieHealth;
	
	/* gameOver will let the game know when a zombie crosses the board*/
	private static boolean gameOver = false;
	
	/* How many zombies there are across the game */
	private static int zombieCount = 0;
	
	private GridCell GridCell;
	private PvZModel game;
	
	
	/** Constructor for the Zombie Class 
	 * @author Leo Paz
	 * */
	public Zombie (GridCell initialGridCell, PvZModel newGame) {		
		zombieSpeed = 1;
		zombieHealth = 3;
		zombieCount++;
		GridCell = initialGridCell;
		game = newGame;
	}
	/*
	 * Sets the movement speed of the zombie
	 */
	public void setSpeed(int speed) {
		if (speed > 0 && speed < 10) zombieSpeed = speed;
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
	
	/*
	 * Gets the amount of zombies alive
	 * @return the amount of zombies in the game
	 */
	public int getZombieCount() {
		return zombieCount;
	}
	
	/**
	 * Maybe add a gameover function if the zombie gets to the end
	 * using the gameOver variable
	 */
	
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
