
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
        
        int numTurns = 0;
        boolean repeat = true;
        
        PvZGame game = new PvZGame();

        while (repeat)
        {
        	numTurns++; //increase turn count by 1
        	
            game.printBoard();
              
            System.out.println("Do you want to 'place' a piece down, 'restart' the game, or 'quit' the program? ");
            String s = scan.next();
            
            if (!s.equals("quit") && !s.equals("place") && !s.equals("restart")) //if the input is not 'quit' or 'move', it is an invalid command
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
                            
            }
            else 
            {
                System.out.println("That is an invalid destination.");
                continue;
            }                     
        }

        scan.close(); //end the game

    }
}
