import java.util.ArrayList;

public class PvZModel {

	/**
	 * Constructor method for Model. Creates an 5x8 board and a new PvZGame.
	 * @author Kevin Sun, Tri Nhan
	 */

	ArrayList<ArrayList<GridCell>> board = new ArrayList<ArrayList<GridCell>>(); 
	GameView view;
	ArrayList<Plant> plantList;
	ArrayList<Zombie> zombieList;
	GridCell gridCell;
	boolean lose;
	boolean playerWin;
	int numTurns, sunlight;

	public PvZModel()
	{
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

		view = new GameView();
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
	 * Places plant at the passed GridCell if the GridCell is not null
	 */
	public void placeSunflowerAt(int x, int y)
	{
		if (sunlight <4)
			System.out.println("Not enough sunlight");
		else {
			Sunflower plant = new Sunflower(new GridCell(x,y), this);
			board.get(x).get(y).addPlant(plant);
			plantList.add(plant);
			System.out.println("Plant added");
			decreaseSunlight(4);
		}
		
	}
	
	public void placePeashooterAt(int x, int y)
	{
		if (sunlight <4)
			System.out.println("Not enough sunlight");
		else {
			Peashooter plant = new Peashooter(new GridCell(x,y), this);
			board.get(x).get(y).addPlant(plant);
			plantList.add(plant);
			System.out.println("Plant added");
			decreaseSunlight(4);
		}
		
	}


	public void spawnZombieAt(Zombie zombie, GridCell destination)
	{
		board.get(destination.getRow()).get(destination.getCol()).addZombie(zombie);
		zombieList.add(zombie);
		System.out.println("Zombie added");
	}
	
	public void moveZombie(Zombie zombie, GridCell destination)
	{
		board.get(destination.getRow()).get(destination.getCol()).addZombie(zombie);
		System.out.println("Zombie moved");
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
		
		//iterate to perform end of turn procedure for all zombies on board
		for (int i=0; i < zombieList.size(); i++)
		{
			if (zombieList.get(i).checkLose())
			{
				lose = true;
			}
			else
			{
				zombieList.get(i).endTurn();
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
				gridCell = plantList.get(i).getGridCell();
				plantList.remove(this.getCell(gridCell).getPlant());
				this.getCell(gridCell).removePlant();
			}
		}

		//check all zombies to see if they are still alive
		for (int i=0; i < zombieList.size(); i++)
		{
			if (zombieList.get(i).getHealth() <= 0)
			{
				gridCell = zombieList.get(i).getGridCell();
				zombieList.remove(this.getCell(gridCell).removeZombie());
			}
		}
		
		//end of wave condition. Only if number of turns has reached a certain point and there are no more zombies on the board
		if (numTurns >= 5 && zombieList.isEmpty())
		{
			playerWin = true;
		}
		
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
	public int getSunlight()
	{
		return sunlight;
	}
	
	/**
	 * Print's the current board to the display using it's toString method
	 */
	public void printBoard()
	{
		System.out.println(" 0   1   2   3   4   5   6   7"); //prints out the column numbers above the board
		for (int i = 0; i < 5; i++) 
		{

			for (int j = 0; j < board.get(i).size(); j++)
			{

				if (board.get(i).get(j).getPlant() == null && board.get(i).get(j).getZombies().isEmpty())
				{
					System.out.print("--- "); //if there is no piece at the GridCell, print out a ---
				}
				else 
				{
					GridCell loc = new GridCell(i, j);
					int zAmount = this.getCell(loc).getZombies().size();
					String pCheck = "- ";
					if (this.getCell(loc).getPlant() != null) {
						pCheck = this.getCell(loc).getPlant().getID() + " ";
					}
					System.out.print(zAmount + "Z" + pCheck); // if a piece is at the GridCell, print a letter on the board instead
				}

			}
			System.out.println(" "+i); // print out the row numbers to the right of the board

		}
	}

}

