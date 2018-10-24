
public class Zombie {
	/* The zombie's movement speed */
	private int zombieSpeed;
	
	/* The zombie's health */
	private int zombieHealth;
	
	/* gameOver will let the game know when a zombie crosses the board*/
	private static boolean gameOver = false;
	
	/* How many zombies there are across the game */
	private static int zombieCount = 0;
	
	/* Constructor for the Zombie Class */
	public Zombie () {		
		zombieSpeed = 5;
		zombieHealth = 10;
		zombieCount++;
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
	
}
