import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCaseBossZombie {

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
		this.model = new PvZModel(2, 4);
		this.zombies = new BossZombie(4,gridCell, model);
	}

	@After
	public void tearDown() throws Exception {
		this.zombies = null;
	}
	/**
	 * Checks Get ID
	 */
	@Test
	public void TestgetID() {
		assertEquals(zombies.getID(), "B");
	}
}