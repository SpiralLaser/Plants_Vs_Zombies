public class PvZGame {

		GameBoard board = new GameBoard(this);
		
		
		int sunlight = 8;						//starting sunlight. Currently costs 4 sunlight to plant a sunflower.

		/**
		 * Constructor for objects of class PvZGame.
		 * @author Kevin Sun
		 */
		public PvZGame()
		{
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

