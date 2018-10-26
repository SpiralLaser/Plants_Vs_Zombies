
public class PvZGame {

		private boolean play1Turn;

		GameBoard board = new GameBoard(this);
		
		
		int sunlight = 4;						//starting sunlight. Currently costs 4 sunlight to plant a sunflower.

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
		 * Increases sunlight
		 */
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
	}

