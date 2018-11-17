import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PvZModel {

	/**
	 * Constructor method for Model. Creates an 5x8 board and a new PvZGame.
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
			}

			else if (buttonClicked.equals("P")) {
				placePeashooterAt(x,y);
			}
			
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
		}	

	}

	/**
	 * Sets the string to correspond to what button was pressed
	 * @param s
	 */
	public void isClicked(String s) {
		buttonClicked = s;
	}
	/**
	 * Places plant at the passed GridCell if the GridCell is not null
	 */
	public void placeSunflowerAt(int x, int y)
	{
		if (sunlight <4)
			System.out.println("Not enough sunlight");

			gridCell = new GridCell(x,y);
			Sunflower plant = new Sunflower(gridCell, this);
			board.get(x).get(y).addPlant(plant);
			plantList.add(plant);
			System.out.println("Plant added");
			decreaseSunlight(4);
			status = Status.PLANT_PLACED;
			e = new PvZEvent (this, status, gridCell, "S");
			for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
		

	}

	public void placePeashooterAt(int x, int y)
	{


			gridCell = new GridCell(x,y);
			Peashooter plant = new Peashooter(gridCell, this);
			board.get(x).get(y).addPlant(plant);
			plantList.add(plant);
			System.out.println("Plant added");
			decreaseSunlight(4);
			status = Status.PLANT_PLACED;
			e = new PvZEvent (this, status, gridCell, "P");
			for (PvZListener pvzEvent: pvzListener) pvzEvent.handlePvZEvent(e);
		

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

	public GridCell findZombie(int r, int c)
	{
		for (; c<7;c++)
		{
			if (!board.get(r).get(c).getZombies().isEmpty())	//!board[r][c].getZombies().isEmpty())
			{
				return board.get(r).get(c);
			}
		}
		return null;
	}

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
				this.getCell(gridCell).removePlant();
				plantList.remove(this.getCell(gridCell).getPlant());
				System.out.println("Plant removed");
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


		//clears what button has been pressed previously
		buttonClicked = "";

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

