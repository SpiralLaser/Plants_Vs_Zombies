import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCaseWallnut {

	// Variables and Objects used for Testing
	GridCell gridCell;
	PvZModel model;
	Wallnut wallnut;
	int POSTITON = 5;

	/**
	 * @author Adam Labelle
	 */

	@Before
	public void setUp() throws Exception {
		this.gridCell = new GridCell(POSTITON, POSTITON); //Grid Position
		this.model = new PvZModel();
		this.wallnut = new Wallnut(gridCell, model);
	}

	@After
	public void tearDown() throws Exception {
		this.wallnut = null;
	}
	/**
	 * Checks Get ID
	 */
	@Test
	public void testGetID() {
		assertEquals(wallnut.getID(), "W");
	}	
}