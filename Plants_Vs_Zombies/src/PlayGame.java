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
     * Main method that creates a new chess game and will ask the user to move a piece, restart the program or quit 
     * the program. If they move a piece, they must input a source position and then a new position.
     */
    public static void main(String[] args)
    {
        System.out.println("Welcome to Plants vs Zombies! \nThe first position on the board is 0 and the last position is 7\n(e.g, 0,0 is in the top left corner of the board)");
        scan = new Scanner(System.in);
        ArrayList<Unit> plantList = new ArrayList<Unit>();
        
        int numTurns = 1;
        boolean repeat = true;
        
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
            	//end of turn if user chooses to end their turn now. Continue would normally skip the end turn at the end of the while loop so it is 
            	//also used here
                for (int i=0; i < plantList.size(); i++)
                {
                	plantList.get(i).endTurn();
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
                UnitLocation destination = new UnitLocation(Integer.parseInt(locationStrings[0]), Integer.parseInt(locationStrings[1]));

                //if there is already a plant on that tile
                if (game.getBoard().getPieceAt(destination) != null && game.getBoard().getPieceAt(destination).getRace() == "Plant")
                {
                    System.out.println("You cannot plant more than 1 plant per tile");
                    continue;
                }
                
                Sunflower unit = new Sunflower(destination, game);
                game.getBoard().placeUnitAt(unit, destination);
                plantList.add(unit);
                game.decreaseSunlight(4);
                            
            }
            else 
            {
                System.out.println("That is an invalid destination.");
                continue;
            }             
           System.out.println("Test");
            numTurns++; //increase turn count by 1
            //end of turn
            for (int i=0; i < plantList.size(); i++)
            {
            	plantList.get(i).endTurn();
            }
            
        }

        scan.close(); //end the game

    }
}
