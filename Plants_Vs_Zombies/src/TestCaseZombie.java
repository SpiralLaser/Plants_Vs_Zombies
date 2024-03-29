import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCaseZombie {

	// Variables and Objects used for Testing
	GridCell gridCell;
	PvZModel model;
	Zombie zombies;
	int POSTITON = 5;

	/**
	 * @author Adam Labelle
	 */
	@Before
	public void setUp() throws Exception {
		this.gridCell = new GridCell(POSTITON, POSTITON);
		this.model = new PvZModel(1,4);
		this.zombies = new Zombie(4, gridCell, model);
	}

	@After
	public void tearDown() throws Exception {
		this.zombies = null;
	}

	/**
	 * Checks set Health
	 */
	@Test
	public void testsetHealth() {
		zombies.setHealth(10); // Set Health
		assertEquals(zombies.getHealth(), 10);
		
		try {
			zombies.setHealth(-10);
		} catch (IllegalArgumentException e) {
			
		}
	}

	/**
	 * Checks Get Health
	 */
	@Test
	public void TestgetHealth() {
		assertEquals(zombies.getHealth(), 4);
	}

	/**
	 * Checks TakeDamage
	 */
	@Test
	public void TestTakeDamage() {
		zombies.zombieHit(2); //Take two away
		assertEquals(zombies.getHealth(), 2);
		
		try {
			zombies.zombieHit(-1);
		} catch (IllegalArgumentException e) {
			
		}
	}

	/**
	 * Checks Get ID
	 */
	@Test
	public void TestgetID() {
		assertEquals(zombies.getID(), "Z");
	}

	/**
	 * Checks Lose
	 */
	@Test
	public void TestcheckLose() {
		assertEquals(zombies.checkLose(), false);

		// Losing Zombie
		this.gridCell = new GridCell(0, 0); // Lose Cords
		this.zombies = new Zombie(4, gridCell, model);
		assertEquals(zombies.checkLose(), true);
	}

	/**
	 * Checks Get GridCell
	 */
	@Test
	public void TestgetGridCell() {
		assertEquals(zombies.getGridCell(), gridCell);
	}
}