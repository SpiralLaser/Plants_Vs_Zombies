import java.util.ArrayList;

public class GameBoard {

	/**
	 * Constructor method for GameBoard. Creates an 5x8 board and a new PvZGame.
	 * @author Kevin Sun, Tri Nhan
	 */

	ArrayList<ArrayList<GridCell>> board = new ArrayList<ArrayList<GridCell>>(); 
	PvZGame game;

	public GameBoard(PvZGame newGame)
	{
		this.game = newGame; 
		for (int r = 0; r < 5; r++)
		{
			board.add(new ArrayList<GridCell>());
			for (int c = 0; c < 8; c++) 
			{
				board.get(r).add(new GridCell(r, c));
			}
		}
	}

	public GridCell getCell(Location l) {
		if (l.getRow() < board.size()){
			if (l.getCol() < board.get(0).size()) {
				return board.get(l.getRow()).get(l.getCol());
			}
		}
		return null;
	}

	/**
	 * Places unit at the passed location if the location is not null
	 */
	public void placePlantAt(Plant plant, Location location)
	{
		board.get(location.getRow()).get(location.getCol()).addPlant(plant);
	}

	public void placeZombieAt(Zombie zombie, Location destination)
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
        System.out.println(" 0   1   2   3   4   5   6   7"); //prints out the column numbers above the board
        for (int i = 0; i < 5; i++) 
        {

            for (int j = 0; j < board.get(i).size(); j++)
            {

                if (board.get(i).get(j).getPlant() == null && board.get(i).get(j).getZombies().isEmpty())
                {
                    System.out.print("--- "); //if there is no piece at the location, print out a ---
                }
                else 
                {
                    Location loc = new Location(i, j);
                    int zAmount = game.getBoard().getCell(loc).getZombies().size();
                    String pCheck = "- ";
                    if (game.getBoard().getCell(loc).getPlant() != null) {
                    	pCheck = game.getBoard().getCell(loc).getPlant().getID() + " ";
                    }
                    System.out.print(zAmount + "Z" + pCheck); // if a piece is at the location, print a letter on the board instead
                }

            }
            System.out.println(" "+i); // print out the row numbers to the right of the board

        }
        return null;

}
}

