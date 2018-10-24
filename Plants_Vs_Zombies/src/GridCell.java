import java.util.*;

public class GridCell {
	
	private LinkedList<Zombie> zombies;
	
	private Plant plant;
	
	/* 
	 * Constructor for the grid class that takes a
	 * zombie or plant and placed it on the grid space 
	 **/
	public GridCell () {
		zombies = null;
		plant = null;
	}
	
	/* 
	 * Getter method to see the visitors on the current grid cell
	 * @return ArrayList of visitors on the grid 
	 **/
	public LinkedList<Object> getZombies(){
		return zombies;
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
	public void addZombie(Zombies zombie) {
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
		temp = plant;
		plant = null;
		return temp;
	}
	
	/*
	 * Removes and returns the zombie if it needs to move to a new grid cell
	 * @return the zombie that needs to be returned
	 */
	public Zombie removeZombie() {
		Zombie zomb = zombies.removeFirst();
		return zomb;
	}
	
	/**
	 * Removes the zombie at the beginning of the list as if it died
	 */
	public void removeAndKillZombie() {
		zombies.removeFirst();
	}
}
