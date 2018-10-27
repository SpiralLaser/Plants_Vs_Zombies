public class GameBoard {

/**
 * Constructor method for GameBoard. Creates an 5x8 board and a new PvZGame.
 */

    GridCell[][] board = new GridCell[8][8]; 
    PvZGame game;
    
    public GameBoard(PvZGame newGame)
    {
        this.game = newGame; 
        
        for (int i=0; i < 5;i++)
        {
        	for (int j=0; j < board.length; j++)
        	{
        		board[i][j] = new GridCell(i,j);        		
        	}     	
        }
    }

    /**
     * Returns the unit at the given location
     */
    public GridCell getPieceAt(GridCell location)
    {
        int row = location.getRow();
        int col = location.getCol();
        if (board[row][col] != null)
            return board[row][col];
        return null;
    }

    /**
     * Places unit at the passed location if the location is not null
     */
    public void placePlantAt(Plant plant, GridCell location)
    {
        int r = location.getRow();
        int c = location.getCol();
        board[r][c].addPlant(plant);
    }
    
    public void placeZombieAt(Zombie zombie, GridCell location)
    {
        int r = location.getRow();
        int c = location.getCol();
        board[r][c].addZombie(zombie);
    }
    
    /**
     * Clears the entire GridCell completely
     */
    public void clear(GridCell gridCell)
    {
        board[gridCell.getRow()][gridCell.getCol()]=null;
    }
    
    public GridCell findZombie(int r, int c)
    {
    	for (; c<7;c++)
    	{
    		if (!board[r][c].getZombies().isEmpty())
    		{
    			return board[r][c];
    		}
    	}
    	return null;
    }
    
    /**
     * Print's the current board to the display using it's toString method
     */
    public void printBoard()
    {
        System.out.print(this);
    }

    /**
     * Override method that will print out an 8x8 board of '-'s with column numbers. If a piece is located on one of the tiles,
     * there will be a letter on the space instead
     */
    public String toString()
    {
    	String piece = "";
        System.out.println("0 1 2 3 4 5 6 7"); //prints out the column numbers above the board
        for (int i=0; i< 5; i++) 
        {
            for (int j=0; j<board.length;j++)
            {

                if (board[i][j].isEmpty())
                {
                    System.out.print("- "); //if there is no piece at the location, print out a -
                }
                else 
                {              	
                    GridCell loc = new GridCell(i,j);
                    if (game.getBoard().getPieceAt(loc).getPlant() != null)
                    {
                    	piece = game.getBoard().getPieceAt(loc).getPlant().getID();
                    }
                    
                    else
                    {
                    	piece = game.getBoard().getPieceAt(loc).getZombie().getID();
                    }
                    System.out.print(piece + " "); // if a piece is at the location, print a letter on the board instead
                }
            }
            System.out.println(" "+i); // print out the row numbers to the right of the board
        }
        return null;

    }
}

