import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PvZModel {

	/**
	 * Constructor method for Model. Creates an 8x5 board and a new PvZGame.
	 * @author Kevin Sun, Tri Nhan
	 */
	private List<PvZListener> pvzListener;
	ArrayList<ArrayList<GridCell>> board = new ArrayList<ArrayList<GridCell>>();
	GameView view;
	String buttonClicked = new String();
	ArrayList<Plant> plantList;
	ArrayList<Zombie> zombieList;
	GridCell gridCell;
	boolean lose;
	boolean playerWin;
	int numTurns, sunlight;
	Status status;
	PvZEvent e;

	public PvZModel()
	{
		pvzListener = new ArrayList<>();
		plantList = new ArrayList<Plant>();
		zombieList = new ArrayList<Zombie>();
		numTurns = 1;
		sunlight = 8;

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
		if (sunlight <4)
			System.out.println("Not enough sunlight");
		else {
			if (buttonClicked.equals("S")) {
				placeSunflowerAt(x,y);
				placePlantHelper();
			}

			else if (buttonClicked.equals("P")) {
				placePeashooterAt(x,y);
				placePlantHelper();
			}
		}	

	}

	/**
	 * Finishes the rest of the functionality when a plant is placed. Spawns a zombie and updates sunlight.
	 */
	public void placePlantHelper() {
		//create a zombie at a random row only when a plant is placed
		gridCell = new GridCell(randomRowNum(),7);
		Zombie zombie = new Zombie(gridCell, this);
		this.spawnZombieAt(zombie, gridCell);

		status = Status.ZOMBIE_MOVING;
		e = new PvZEvent (this, status, gridCell, "Z");
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
		if (sunlight <4)
			System.out.println("Not enough sunlight");

		else {
			gridCell = new GridCell(x,y);
			Sunflower plant = new Sunflower(gridCell, this);
			board.get(x).get(y).addPlant(plant);
			plantList.add(plant);
			decreaseSunlight(4);
			status = Status.PLANT_PLACED;
			e = new PvZEvent (this, status, gridCell, "S");
			for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
		}	

	}

	public void placePeashooterAt(int x, int y)
	{
		if (sunlight <4)
			System.out.println("Not enough sunlight");

		else {
			gridCell = new GridCell(x,y);
			Peashooter plant = new Peashooter(gridCell, this);
			board.get(x).get(y).addPlant(plant);
			plantList.add(plant);
			decreaseSunlight(4);
			status = Status.PLANT_PLACED;
			e = new PvZEvent (this, status, gridCell, "P");
			for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
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
				e = new PvZEvent (this, status, zombieList.get(i).getGridCell(), "Z");
				for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
			}
			else
			{
				zombieList.get(i).endTurn();
				e = new PvZEvent (this, status, zombieList.get(i).getGridCell(), "Z");
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
		if (numTurns >= 5 && zombieList.isEmpty())
		{
			status = Status.WON;
			e = new PvZEvent (this, status, gridCell, "Z");
			for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
		}

		//update sunlight counter
		status = Status.UPDATE_SUNLIGHT;
		e = new PvZEvent (this, status, gridCell, getSunlight());
		for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
		
		
		numTurns++;

	}

	public void increaseSunlight()
	{
		sunlight++;
	}

	/**
	 * Decreases sunlight
	 */		
	public void decreaseSunlight(int i)
	{
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

