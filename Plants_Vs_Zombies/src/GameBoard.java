
public class GameBoard {

/**
 * Constructor method for GameBoard. Creates an 5x8 board and a new PvZGame.
 */

    Unit[][] board = new Unit[8][8]; 
    PvZGame game;
    
    public GameBoard(PvZGame newGame)
    {
        this.game = newGame; 
    }

    /**
     * Returns the unit at the given location
     */
    public Unit getPieceAt(UnitLocation location)
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
    public void placeUnitAt(Unit piece, UnitLocation location)
    {
        int r = location.getRow();
        int c = location.getCol();
        removePiece(piece.getLocation());
        removePiece(location);
        board[r][c] = piece;

    }
    /**
     * Removes the unit from the passed location
     */
    public void removePiece(UnitLocation location)
    {
        board[location.getRow()][location.getCol()]=null;

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
        System.out.println("0 1 2 3 4 5 6 7"); //prints out the column numbers above the board
        for (int i=0; i< 5; i++) 
        {

            for (int j=0; j<board.length;j++)
            {

                if (board[i][j] == null)
                {
                    System.out.print("- "); //if there is no piece at the location, print out a -
                }
                else 
                {
                    UnitLocation loc = new UnitLocation(i,j);
                    String piece = game.getBoard().getPieceAt(loc).getID();
                    System.out.print(piece + " "); // if a piece is at the location, print a letter on the board instead
                }

            }
            System.out.println(" "+i); // print out the row numbers to the right of the board

        }
        return null;

    }
}

