import java.util.*;

public class GridCell {
	
	private LinkedList<Object> visitors;
	/* Constructor for the grid class
	 * **/
	public GridCell () {
		visitors = null;
	}
	
	/* 
	 * Constructor for the grid class that takes a
	 * zombie or plant and placed it on the grid space
	 * @param entity The entity that will start on the grid cell 
	 **/
	public GridCell(Object entity) {
		visitors.add(entity);
	}
	
	/* 
	 * Getter method to see the visitors on the current grid cell
	 * @return ArrayList of visitors on the grid 
	 **/
	public LinkedList<Object> getVisitors(){
		return visitors;
	}
	
	/* 
	 * Adds a new visitor to the current grid 
	 **/
	public void addVisitor(Object entity){
		if (entity instanceof Plant && !(visitors.get(0) instanceof Plant)) {
			visitors.addFirst(entity);
		}
		else if (entity instanceof Zombie){
			visitors.add(entity);
		}
		else {
			System.out.println("Already a plant in this grid cell.");
		}
		visitors.add(entity);
	}
	
	public void removePlant() {
		visitors.removeFirst();
	}
	
	public void removeZombie(Zombie type) {
		visitors.removeFirst();
	}
	
	
}
