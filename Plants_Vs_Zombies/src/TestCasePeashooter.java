import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCasePeashooter {
	
	GridCell gridCell;
	PvZModel model;
	Peashooter peashooters;
	
		/**
		 * @author Adam Labelle
		 */
	
		@Before
		public void setUp() throws Exception {
			this.gridCell = new GridCell(5,5);
			this.model = new PvZModel(1,4);
			this.peashooters = new Peashooter(gridCell,model);
		}

		@After
		public void tearDown() throws Exception {
			this.peashooters = null;
		}
	
		@Test
		public void testGetID() {
			assertEquals(peashooters.getID(), "P");
		}
}