
public class PvZGame {

		private boolean play1Turn;

		GameBoard board = new GameBoard(this);


		/**
		 * Constructor for objects of class PvZGame. Places a knight piece at default location 1,1
		 */
		public PvZGame()
		{
			play1Turn = true;
		}

		/**
		 * Returns the GameBoard object from this game
		 */
		public GameBoard getBoard()
		{
			return board;
		}

		/**
		 * Prints the board with all the pieces to the display using the board's toString method
		 */
		public void printBoard()
		{
			board.toString();
		}

		/**
		 * Gets the player's turn in the game
		 */ 
		public boolean getTurn()
		{
			return play1Turn;
		}

		/**
		 * Turn has ended, so change the turn to the other player's
		 */
		public void changeTurn()
		{
			play1Turn = !play1Turn;
		}


	}

