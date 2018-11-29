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
	ArrayList<ArrayList<GridCell>> board, temp;
	GameView view;
	String buttonClicked = new String();
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

	boolean lose, playerWin, zombiesAlive;
	int numTurns, sunlight;

	public PvZModel()
	{
		pvzListener = new ArrayList<>();


		numTurns = 1;
		sunlight = 100;

		undo = new Stack<ArrayList<ArrayList<GridCell>>>();
		redo = new Stack<ArrayList<ArrayList<GridCell>>>();


		board  = new ArrayList<ArrayList<GridCell>>();
		temp = new ArrayList<ArrayList<GridCell>>();

		spawnBoss = true;
		undoSunlight = new Stack<Integer>();
		redoSunlight = new Stack<Integer>();

		for (int r = 0; r < 5; r++)
		{
			board.add(new ArrayList<GridCell>());
			temp.add(new ArrayList<GridCell>());
			for (int c = 0; c < 8; c++) 
			{
				board.get(r).add(new GridCell(r, c));
				temp.get(r).add(new GridCell(r, c));
			}
		}

		zombiesAlive = false;

	}

	/**
	 * Deep copies the current board state into a temporary variable that will be pushed to the stack
	 * @param a
	 */
	public void createCopy(ArrayList<ArrayList<GridCell>> a) {
		temp = new ArrayList<ArrayList<GridCell>>();
		for (int r = 0; r < 5; r++) {
			temp.add(new ArrayList<GridCell>());
			for (int c = 0; c < 8; c++) {
				temp.get(r).add(new GridCell(r, c));
				if (!board.get(r).get(c).plantEmpty()) {
					plant = board.get(r).get(c).getPlant();

					if (plant.getID().equals("S")) {
						plant = new Sunflower(plant);
					}
					else if (plant.getID().equals("P")) {
						plant = new Peashooter(plant);
					}
					else if (plant.getID().equals("R")) {
						plant = new Repeater(plant);
					}
					else if (plant.getID().equals("T")) {
						plant = new TwinSunflower(plant);
					}
					else if (plant.getID().equals("W")) {
						plant = new Wallnut(plant);
					}
					temp.get(r).get(c).addPlant(plant);
				}
				if (!board.get(r).get(c).zombieEmpty()) {
					zombie = board.get(r).get(c).getZombie();

					if (zombie.getID().equals("B")) {
						zombie = new BossZombie(zombie);
					}
					else {
						zombie = new Zombie(zombie);
					}
					temp.get(r).get(c).addZombie(zombie);
				}

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
		
		else {
			throw new IllegalArgumentException("Not a valid plant");
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
			decreaseSunlight(50);
			status = Status.PLANT_PLACED;
			e = new PvZEvent (this, status, gridCell, "S");
			for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
			getCell(gridCell).addPlant(plant);

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
			decreaseSunlight(50);
			status = Status.PLANT_PLACED;
			e = new PvZEvent (this, status, gridCell, "P");
			for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
			getCell(gridCell).addPlant(plant);
			placePlantHelper();

		}

	}

	public void placeRepeaterAt(int x, int y) {
		if (sunlight <200)
			JOptionPane.showMessageDialog(null, "You need 200 sunlight to plant a repeater", "Not enough sunlight", JOptionPane.INFORMATION_MESSAGE);

		else {

			gridCell = new GridCell(x,y);
			plant = new Repeater(gridCell, this);
			decreaseSunlight(200);
			status = Status.PLANT_PLACED;
			e = new PvZEvent (this, status, gridCell, "R");
			for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
			getCell(gridCell).addPlant(plant);
			placePlantHelper();
		}
	}

	public void placeTwinSunflowerAt(int x, int y) {
		if (sunlight <150)
			JOptionPane.showMessageDialog(null, "You need 150 sunlight to plant a twin sunflower", "Not enough sunlight", JOptionPane.INFORMATION_MESSAGE);

		else {

			gridCell = new GridCell(x,y);
			plant = new TwinSunflower(gridCell, this);
			decreaseSunlight(150);
			status = Status.PLANT_PLACED;
			e = new PvZEvent (this, status, gridCell, "T");
			for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
			getCell(gridCell).addPlant(plant);
			placePlantHelper();
		}
	}

	public void placeWallnutAt(int x, int y) {
		if (sunlight <50)
			JOptionPane.showMessageDialog(null, "You need 50 sunlight to plant a wallnut", "Not enough sunlight", JOptionPane.INFORMATION_MESSAGE);

		else {
			gridCell = new GridCell(x,y);
			plant = new Wallnut(gridCell, this);
			decreaseSunlight(50);
			status = Status.PLANT_PLACED;
			e = new PvZEvent (this, status, gridCell, "W");
			for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
			getCell(gridCell).addPlant(plant);
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
		createCopy(board);
		undo.push(temp);

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
		createCopy(board);
		redo.push(temp);

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

	public ArrayList<ArrayList<GridCell>> getBoard() {
		return board;
	}

	/**
	 * Called when  undo or redo is clicked. Iterates and updates the whole board
	 */
	public void updateWholeBoard() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 8; j++) {
				if (board.get(i).get(j).zombieEmpty()) {
					status = Status.ZOMBIE_DIED;
					e = new PvZEvent (this, status, gridCell, "");
					for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
				}
				if (!board.get(i).get(j).zombieEmpty()) {
					status = Status.ZOMBIE_MOVING;
					e = new PvZEvent (this, status, board.get(i).get(j), board.get(i).get(j).getZombie().getID() );
					for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
				}



				if (!board.get(i).get(j).plantEmpty()) {
					status = Status.PLANT_PLACED;
					e = new PvZEvent (this, status, board.get(i).get(j), board.get(i).get(j).getPlant().getID() );
					for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
				}

				if (board.get(i).get(j).plantEmpty()) {
					status = Status.REMOVE_PLANT;
					e = new PvZEvent (this, status, board.get(i).get(j), "");
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
		if (r <0 || c <0 || r > 7 || c > 4) {
			throw new IllegalArgumentException("Row or column cannot be less than 1, row cannot be greater than 7, and column cannot be greater than 4");
		}
		
		for (; c<8;c++)
		{
			if (!board.get(r).get(c).zombieEmpty())
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
		//iterate through entire board
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 8; j++) {
				//perform end of turn procedure for all plants on board
				if (!board.get(i).get(j).plantEmpty()) {
					plant = board.get(i).get(j).getPlant();
					plant.endTurn();				
				}
				
				//check all zombies to see if they are still alive	
				if (!board.get(i).get(j).zombieEmpty()) {
					zombie = board.get(i).get(j).getZombie();
					if (zombie.getHealth() <=0) {
						status = Status.ZOMBIE_DIED;		
						board.get(board.get(i).get(j).getRow()).get(board.get(i).get(j).getCol()).removeZombie();
						e = new PvZEvent (this, status, zombie.getGridCell(), "");
						for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);										
					}
				}	
			}
		}
		for (int k = 0; k < 5; k++) {
			for (int l = 0; l < 8; l++) {

				//perform end of turn procedure for all zombies on board
				if (!board.get(k).get(l).zombieEmpty()) {

					status = Status.ZOMBIE_MOVING;
					zombie = board.get(k).get(l).getZombie();
					
					//System.out.println(zombie.getHealth());
					
					if (zombie.checkLose()) {
						status = Status.LOST;
						e = new PvZEvent (this, status, zombie.getGridCell(), zombie.getID());
						for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
					}
					else {
						zombie.endTurn();
						e = new PvZEvent (this, status, zombie.getGridCell(), zombie.getID());
						for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
					}

					zombiesAlive = true;
				}
				
				//check all plants to see if they are still alive
				if (!board.get(k).get(l).plantEmpty()) {

					if (!board.get(k).get(l).getPlant().isAlive()) {
						status = Status.REMOVE_PLANT;					
						e = new PvZEvent(this, status, plant.getGridCell(), plant.getID());
						for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
						board.get(gridCell.getRow()).get(gridCell.getCol()).removePlant();
					}
				}
			}
		}


		//end of wave condition. Only if number of turns has reached a certain point and there are no more zombies on the board
		if (numTurns >= 15 && !spawnBoss && !zombiesAlive )
		{
			status = Status.WON;
			e = new PvZEvent (this, status, gridCell, "Z");
			for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
		}

		increaseSunlight(25);
		updateSunlight();
		numTurns++;
		zombiesAlive = false;


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

