import java.util.ArrayList;
import java.util.Scanner;

public class PlayGame
{
	private static Scanner scan;

	/**
	 * Constructor for objects of class PlayGame
	 */
	public PlayGame()
	{

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
		int rowNum = 0;
		boolean repeat = true;
		boolean playerWin = false;

		PvZGame game = new PvZGame();

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
				game = new PvZGame();
				continue;
			}

			//end current turn and move on to next turn
			if (s.equals("end"))
			{
				//end of turn if user chooses to immediately end their turn. 
				//Continue would normally skip the end turn at the end of the while loop so it is included here
				
				//iterate to perform end of turn procedure for all zombies on board
				for (int i=0; i < zombieList.size(); i++)
				{
					if (zombieList.get(i).checkLose())
					{
						repeat = false;
					}
					else
					{
						zombieList.get(i).endTurn();
					}
					
				}
				
				//iterate to perform end of turn procedure for all plants on board
				for (int i=0; i < plantList.size(); i++)
				{
					if (plantList.get(i).isAlive())
					{
						plantList.get(i).endTurn();
					}
					
					else
					{
						GridCell location = plantList.get(i).getLocation();
						game.getBoard().getPieceAt(location).removePlant();
					}

				}
				numTurns++; //increase turn count by 1
				continue;
			}

			if (game.getSunlight() < 4)
			{
				System.out.println("It costs 4 sunlight to place down a sunflower plant. You only have " + game.getSunlight() + " sunlight");
				continue;
			}

			System.out.println("Pick a position:");
			s = scan.next();

			String[] locationStrings;

			//input must be within the board range. The board is a 5x8 board, so the first input must be between 0-4, and second 
			//input must be between 0-7
			if (s.matches("[0-4],[0-7]"))
			{
				locationStrings = s.split(",");
				GridCell destination = new GridCell(Integer.parseInt(locationStrings[0]), Integer.parseInt(locationStrings[1]));

				//if there is already a plant on that tile
				if (game.getBoard().getPieceAt(destination) != null && game.getBoard().getPieceAt(destination).getPlant() !=null)
				{
					System.out.println("You cannot plant more than 1 plant per tile");
					continue;
				}

				Sunflower unit = new Sunflower(destination, game);
				game.getBoard().placePlantAt(unit, destination);
				plantList.add(unit);
				game.decreaseSunlight(4);

			}
			else 
			{
				System.out.println("That is an invalid destination.");
				continue;
			}             

			numTurns++; //increase turn count by 1

			//end of turn procedure for all zombies on board
			for (int i=0; i < zombieList.size(); i++)
			{
				zombieList.get(i).endTurn();
			}
			
			//end of turn procedure for all plants on board
			for (int i=0; i < plantList.size(); i++)
			{
				if (plantList.get(i).isAlive())
				{
					plantList.get(i).endTurn();
				}
				
				else
				{
					GridCell location = plantList.get(i).getLocation();
					game.getBoard().getPieceAt(location).removePlant();
				}

			}
			
			//create a zombie only when a plant is placed
			GridCell loc = new GridCell(rowNum,7);
			Zombie zombie = new Zombie(loc, game);
			game.getBoard().placeZombieAt(zombie, loc);
			zombieList.add(zombie);
			rowNum++;

		}
		//game is over, either player has won or lost
		
		//player has lost
		if (!playerWin)
			System.out.println("Unfortunately a zombie has reached your house. You lose!");
		else
			System.out.println("You have won!");
		scan.close(); //end the game

	}
}
