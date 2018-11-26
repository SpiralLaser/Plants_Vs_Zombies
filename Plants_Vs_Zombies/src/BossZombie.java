
/**
 * Boss of the game. Has 10 hp and moves every 2 turns. Is a child of the parent class Zombie.
 * @author Kevin Sun
 *
 */
public class BossZombie extends Zombie {

	private int moveTurn;
	private PvZModel game;
	
	public BossZombie(GridCell initialGridCell, PvZModel newGame) {
		super(initialGridCell, newGame);
		this.setHealth(10);
		game = this.getGame();
		moveTurn = 0;
	}
	
	/**
	 * Returns a string so the board knows what to print
	 * @return
	 */
	public String getID()
	{
		return "B";
	}

	/**
	 * Actions that the zombie does at end of the turn
	 */
	public void endTurn()
	{
		//only move once every 2 turns
		if (moveTurn != 1) {
			moveTurn++;
			
		}
		else {
			int col = this.getGridCell().getCol();		
			int row = this.getGridCell().getRow();
			GridCell destination = new GridCell(row, col - 1);
			
			//there is a plant in the next space, so start damaging it
			if (game.getCell(destination).getPlant() != null)
			{
				Plant plant = game.getCell(destination).getPlant();
				plant.takeDamage(10);
			}
			//otherwise move to the next space
			else
			{
				Zombie zomb = game.getCell(GridCell).removeZombie();		
				game.moveZombie(zomb, destination);
				this.updateGridCell(destination);
			}
			moveTurn = 0;
		}
		
		
	}
}
