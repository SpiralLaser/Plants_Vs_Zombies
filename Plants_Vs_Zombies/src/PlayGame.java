import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class PlayGame
{
	private static Scanner scan;

	/**
	 * Constructor for objects of class PlayGame
	 * @author Kevin Sun, Tri Nhan
	 */
	public PlayGame()
	{

	}

	public static int randomRowNum()
	{
		Random rn = new Random();
		int row = rn.nextInt(4 - 0 + 1);		
		return row;
	}
	/**
	 * Main method that creates a new Plants vs Zombies game and will ask the user to plant a plant, end their turn, restart the program or quit 
	 * the program. If they choose to plant, they must input a destination coordinate to plant
	 */
	public static void main(String[] args)
	{
		System.out.println("Welcome to Plants vs Zombies! \nThe first position on the board is 0 and the last position is 7\n(e.g, 0,0 is in the top left corner of the board)");
		scan = new Scanner(System.in);
		ArrayList<Plant> plantList = new ArrayList<Plant>();
		ArrayList<Zombie> zombieList = new ArrayList<Zombie>();

		int numTurns = 1;
		boolean repeat = true;
		boolean playerWin = false;
		GridCell GridCell;
		PvZModel game = new PvZModel();
		Plant unit = null;
		String plantID = "";
		int zombieCount = 0;
		
		while (repeat)
		{

			
			System.out.println("It is currently Turn " + numTurns + ". You have " + game.getSunlight() + " sunlight available");

			game.printBoard();

			System.out.println("Do you want to 'place' a piece down, 'end' your turn, 'restart' the game, or 'quit' the program? ");
			String s = scan.next();

			if (!s.equals("quit") && !s.equals("place") && !s.equals("restart") && !s.equals("end")) //if the input is not 'quit' or 'move', it is an invalid command
			{
				System.out.println("That is an invalid command.");
				continue;
			}

			if (s.equals("quit"))
			{
				repeat = false;
				System.out.println("See you next time!");
				break;
			}

			if (s.equals("restart"))
			{
				System.out.println("Restarting the game!");
				game = new PvZModel();
				continue;
			}

			//end current turn and move on to next turn
			if (s.equals("end"))
			{
				game.endTurn();
				
			
				continue;
			}

			//not enough sunlight to plant a plant
			if (game.getSunlight() < 4)
			{
				System.out.println("It costs 4 sunlight to place down a plant. You only have " + game.getSunlight() + " sunlight");
				continue;
			}
			System.out.println("Do you want to place down a (S)unflower plant or a (P)eashooter? ");
			s = scan.next();
			if (!s.equals("S") && !s.equals("P"))
			{
				System.out.println("That is an invalid plant");
				continue;
			}
			plantID = s;
			
			System.out.println("Pick a position:");
			s = scan.next();

			String[] GridCellStrings;

			//input must be within the board range. The board is a 5x8 board, so the first input must be between 0-4, and second 
			//input must be between 0-7
			if (s.matches("[0-4],[0-7]"))
			{
				GridCellStrings = s.split(",");
				GridCell destination = new GridCell(Integer.parseInt(GridCellStrings[0]), Integer.parseInt(GridCellStrings[1]));

				//if there is already a plant on that tile
				if (game.getCell(destination).getPlant() !=null)
				{
					System.out.println("You cannot plant more than 1 plant per tile");
					continue;
				}

				if (plantID.equals("S"))
				{
					unit = new Sunflower(destination, game);
				}

				else if (plantID.equals("P"))
				{
					unit = new Peashooter(destination, game);
				}
				game.getCell(destination).addPlant(unit);;
				plantList.add(unit);

			}
			else 
			{
				System.out.println("That is an invalid destination.");
				continue;
			}             

			numTurns++; //increase turn count by 1

			
			game.endTurn();

			//create a zombie at a random row only when a plant is placed
			GridCell loc = new GridCell(randomRowNum(),7);
			Zombie zombie = new Zombie(loc, game);
			game.spawnZombieAt(zombie, loc);
			zombieList.add(zombie);
			zombieCount++;


		}
		//game is over, either player has won or lost

		//player has lost
		if (!playerWin)
			System.out.println("Unfortunately a zombie has reached your house. You lose!");
		else
			System.out.println("You've cleared all the zombies of this wave. You have won!");
		scan.close(); //end the game

	}
}
