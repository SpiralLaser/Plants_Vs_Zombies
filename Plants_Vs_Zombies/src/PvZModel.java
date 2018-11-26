import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

public class PvZModel {

	/**
	 * Constructor method for Model. Creates an 8x5 board and a new PvZGame.
	 * @author Kevin Sun
	 */
	private List<PvZListener> pvzListener;
	
	//A nested arraylist was used to simulate a 2D board.
	ArrayList<ArrayList<GridCell>> board = new ArrayList<ArrayList<GridCell>>();
	GameView view;
	String buttonClicked = new String();
	ArrayList<Plant> plantList;
	ArrayList<Zombie> zombieList;
	Zombie zombie;
	GridCell gridCell;
	
	//A stack was used for the undo and redo buttons because it best simulates what is needed. Push to stack whenever a move is made, pop from the stack 
	//if undo or redo is clicked
	Stack<ArrayList<ArrayList<GridCell>>> undo, redo;
	Stack<Integer> undoSunlight, redoSunlight;
	boolean spawnBoss;

	Status status;
	PvZEvent e;
	Plant plant;

	boolean lose;
	boolean playerWin;
	int numTurns, sunlight;

	public PvZModel()
	{
		pvzListener = new ArrayList<>();
		plantList = new ArrayList<Plant>();
		zombieList = new ArrayList<Zombie>();
		numTurns = 1;
		sunlight = 100;
		undo = new Stack<ArrayList<ArrayList<GridCell>>>();
		redo = new Stack<ArrayList<ArrayList<GridCell>>>();
		spawnBoss = true;
		undoSunlight = new Stack<Integer>();
		redoSunlight = new Stack<Integer>();
		for (int r = 0; r < 5; r++)
		{
			board.add(new ArrayList<GridCell>());
			for (int c = 0; c < 8; c++) 
			{
				board.get(r).add(new GridCell(r, c));
			}
		}


	}

	public void addPvZListener(PvZListener pvzl) {
		pvzListener.add(pvzl);
	}

	public GridCell getCell(GridCell l) {
		if (l.getRow() < board.size()){
			if (l.getCol() < board.get(0).size()) {
				return board.get(l.getRow()).get(l.getCol());
			}
		}
		return null;
	}

	/**
	 * Places a plant with given string identifier and x y coordinates
	 * @param s
	 * @param x
	 * @param y
	 */
	public void placePlantAt(int x, int y)
	{
		pushUndo();
		
		if (buttonClicked.equals("S")) {
			placeSunflowerAt(x,y);

		}

		else if (buttonClicked.equals("P")) {
			placePeashooterAt(x,y);

		}
		
		else if (buttonClicked.equals("R")) {
			placeRepeaterAt(x,y);

		}
		
		else if (buttonClicked.equals("T")) {
			placeTwinSunflowerAt(x,y);

		}
		else if (buttonClicked.equals("W")) {
			placeWallnutAt(x,y);

		}
	}

	/**
	 * Finishes the rest of the functionality when a plant is placed. Spawns a zombie and updates sunlight.
	 */
	public void placePlantHelper() {
		//create a zombie at a random row only when a plant is placed
		gridCell = new GridCell(randomRowNum(),7);
		
		//spawns boss once if past turn 15, otherwise spawn a normal zombie
		if (spawnBoss == true && numTurns >= 15) {
			zombie = new BossZombie(gridCell, this);
			spawnBoss = false;
		}
		else {
			zombie = new Zombie(gridCell, this);
		}
		
		this.spawnZombieAt(zombie, gridCell);

		status = Status.ZOMBIE_MOVING;
		e = new PvZEvent (this, status, gridCell, zombie.getID());
		for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);

		//update sunlight counter
		status = Status.UPDATE_SUNLIGHT;
		e = new PvZEvent (this, status, gridCell, getSunlight());
		for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
	}

	/**
	 * Sets the string to correspond to what button was pressed
	 * @param s
	 */
	public void isClicked(String s) {
		buttonClicked = s;
	}

	/**
	 * Places plant at the passed x and y coordinate if there is enough sunlight
	 */
	public void placeSunflowerAt(int x, int y)
	{
		if (sunlight <50)
			JOptionPane.showMessageDialog(null, "You need 50 sunlight to plant a sunflower", "Not enough sunlight", JOptionPane.INFORMATION_MESSAGE);

		else {
			gridCell = new GridCell(x,y);
			plant = new Sunflower(gridCell, this);
			board.get(x).get(y).addPlant(plant);
			plantList.add(plant);
			decreaseSunlight(50);
			status = Status.PLANT_PLACED;
			e = new PvZEvent (this, status, gridCell, "S");
			for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
			placePlantHelper();
		}	

	}

	public void placePeashooterAt(int x, int y)
	{
		if (sunlight <50)
			JOptionPane.showMessageDialog(null, "You need 50 sunlight to plant a peashooter", "Not enough sunlight", JOptionPane.INFORMATION_MESSAGE);

		else {

			gridCell = new GridCell(x,y);
			plant = new Peashooter(gridCell, this);
			board.get(x).get(y).addPlant(plant);
			plantList.add(plant);
			decreaseSunlight(50);
			status = Status.PLANT_PLACED;
			e = new PvZEvent (this, status, gridCell, "P");
			for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
			placePlantHelper();
		}

	}

	public void placeRepeaterAt(int x, int y) {
		if (sunlight <200)
			JOptionPane.showMessageDialog(null, "You need 200 sunlight to plant a repeater", "Not enough sunlight", JOptionPane.INFORMATION_MESSAGE);

		else {

			gridCell = new GridCell(x,y);
			plant = new Repeater(gridCell, this);
			board.get(x).get(y).addPlant(plant);
			plantList.add(plant);
			decreaseSunlight(200);
			status = Status.PLANT_PLACED;
			e = new PvZEvent (this, status, gridCell, "R");
			for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
			placePlantHelper();
		}
	}

	public void placeTwinSunflowerAt(int x, int y) {
		if (sunlight <150)
			JOptionPane.showMessageDialog(null, "You need 150 sunlight to plant a twin sunflower", "Not enough sunlight", JOptionPane.INFORMATION_MESSAGE);

		else {

			gridCell = new GridCell(x,y);
			plant = new TwinSunflower(gridCell, this);
			board.get(x).get(y).addPlant(plant);
			plantList.add(plant);
			decreaseSunlight(150);
			status = Status.PLANT_PLACED;
			e = new PvZEvent (this, status, gridCell, "T");
			for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
			placePlantHelper();
		}
	}

	public void placeWallnutAt(int x, int y) {
		if (sunlight <50)
			JOptionPane.showMessageDialog(null, "You need 50 sunlight to plant a wallnut", "Not enough sunlight", JOptionPane.INFORMATION_MESSAGE);

		else {
			gridCell = new GridCell(x,y);
			plant = new Wallnut(gridCell, this);
			board.get(x).get(y).addPlant(plant);
			plantList.add(plant);
			decreaseSunlight(50);
			status = Status.PLANT_PLACED;
			e = new PvZEvent (this, status, gridCell, "W");
			for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
			placePlantHelper();
		}
	}

	/**
	 * Random number generator used to pick a random row to spawn a zombie at
	 * @return
	 */
	public static int randomRowNum()
	{
		Random rn = new Random();
		int row = rn.nextInt(4 - 0 + 1);		
		return row;
	}

	public void spawnZombieAt(Zombie zombie, GridCell destination)
	{
		board.get(destination.getRow()).get(destination.getCol()).addZombie(zombie);
		zombieList.add(zombie);
	}

	public void moveZombie(Zombie zombie, GridCell destination)
	{
		board.get(destination.getRow()).get(destination.getCol()).addZombie(zombie);
	}

	/**
	 * Before a new move is made, push the current board state to undo so that the user can undo the move. 
	 * 1. If move is made, push current board state to undo stack, then update model and view
	 * 2. If undo is clicked, push current board state to redo stack, then set board state to popped undo
	 * 3. If redo is clicked, push current board state to undo stack, then set board state to popped redo
	 */
	public void pushUndo() {
		undo.push(board);
		undoSunlight.push(sunlight);
	}

	public void popUndo() {
		if (undo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "You have no moves to undo", "No undo moves", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			pushRedo();
			board = undo.pop();
			sunlight = undoSunlight.pop();
			updateWholeBoard();
			updateSunlight();
		}
	}

	public void pushRedo() {
		redo.push(board);
		redoSunlight.push(sunlight);
	}

	public void popRedo() {
		if (redo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "You have no moves to redo", "No redo moves", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			pushUndo();
			board = redo.pop();
			sunlight = redoSunlight.pop();
			updateWholeBoard();
			updateSunlight();
		}
	}
	
	//possibly need to switch i and j numbers, i.e. i = 5 and j = 8
	/**
	 * Called when  undo or redo is clicked. Iterates and updates the whole board
	 */
	public void updateWholeBoard() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 8; j++) {
				if (!board.get(i).get(j).zombieEmpty()) {
					status = Status.ZOMBIE_MOVING;
					e = new PvZEvent (this, status, gridCell, board.get(i).get(j).getZombie().getID() );
					for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
				}
							
				if (!board.get(i).get(j).plantEmpty()) {
					status = Status.PLANT_PLACED;
					e = new PvZEvent (this, status, gridCell, board.get(i).get(j).getPlant().getID() );
					for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
				}
				
				if (board.get(i).get(j).plantEmpty()) {
					status = Status.REMOVE_PLANT;
					e = new PvZEvent (this, status, gridCell, "");
					for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
				}
			}
		}
	}

	/**
	 * Finds if there is a zombie in that row
	 * @param r
	 * @param c
	 * @return
	 */
	public GridCell findZombie(int r, int c)
	{
		for (; c<7;c++)
		{
			if (!board.get(r).get(c).getZombies().isEmpty())
			{
				return board.get(r).get(c);
			}
		}
		return null;
	}

	/**
	 * Performs all end of turn actions on everything in the board
	 */
	public void endTurn()
	{
		status = Status.ZOMBIE_MOVING;

		//iterate to perform end of turn procedure for all zombies on board
		for (int i=0; i < zombieList.size(); i++)
		{
			if (zombieList.get(i).checkLose())
			{
				status = Status.LOST;
				e = new PvZEvent (this, status, zombieList.get(i).getGridCell(), zombieList.get(i).getID());
				for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
			}
			else
			{
				zombieList.get(i).endTurn();
				e = new PvZEvent (this, status, zombieList.get(i).getGridCell(), zombieList.get(i).getID());
				for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
			}

		}

		//iterate to perform end of turn procedure for all plants on board
		for (int i=0; i < plantList.size(); i++)
		{
			plantList.get(i).endTurn();
		}

		//check all plants to see if they are still alive
		for (int i=0; i < plantList.size(); i++)
		{
			if (!plantList.get(i).isAlive())
			{
				status = Status.REMOVE_PLANT;
				gridCell = plantList.get(i).getGridCell();
				e = new PvZEvent (this, status, gridCell, plantList.get(i).getID());
				for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
				board.get(plantList.get(i).getGridCell().getRow()).get(plantList.get(i).getGridCell().getCol()).removePlant();
				plantList.remove(i);			
			}
		}

		//check all zombies to see if they are still alive
		for (int i=0; i < zombieList.size(); i++)
		{
			if (zombieList.get(i).getHealth() <= 0)
			{
				status = Status.ZOMBIE_DIED;
				gridCell = zombieList.get(i).getGridCell();
				zombieList.remove(this.getCell(gridCell).removeZombie());
				e = new PvZEvent (this, status, gridCell, "");
				for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
			}
		}

		//end of wave condition. Only if number of turns has reached a certain point and there are no more zombies on the board
		if (numTurns >= 15 && zombieList.isEmpty())
		{
			status = Status.WON;
			e = new PvZEvent (this, status, gridCell, "Z");
			for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
		}

		increaseSunlight(25);
		updateSunlight();
		numTurns++;

	}

	public void updateSunlight() {
		status = Status.UPDATE_SUNLIGHT;
		e = new PvZEvent (this, status, gridCell, getSunlight());
		for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
	}
	public void increaseSunlight(int i)
	{
		if (i <1) {
			throw new IllegalArgumentException("Cannot increase sunlight by less than 1");
		}
		sunlight += i;
	}

	/**
	 * Decreases sunlight
	 */		
	public void decreaseSunlight(int i)
	{
		if (i <1) {
			throw new IllegalArgumentException("Cannot decrease sunlight by less than 1");
		}
		sunlight = sunlight - i;
	}

	/**
	 * Returns current sunflower amount
	 * @return sunlight
	 */
	public String getSunlight()
	{
		return Integer.toString(sunlight);
	}

}

