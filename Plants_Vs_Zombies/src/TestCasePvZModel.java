import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCasePvZModel {
	
	// Variables and Objects used for Testing
	PvZModel model;
	
		/**
		 * @author Adam Labelle
		 */
	
		@Before
		public void setUp() throws Exception {
			this.model = new PvZModel();
		}

		@After
		public void tearDown() throws Exception {
			this.model = null;
		}
	
		/**
		 * Checks get Sunlight
		 */
		@Test
		public void testGetSunlight() {
			assertEquals(model.getSunlight(), "8");
		}
		
		/**
		 * Checks get increase Sunlight
		 */
		@Test
		public void testIncreaseSunlight() {
			model.increaseSunlight();
			assertEquals(model.getSunlight(), "9");
		}
		
		/**
		 * Checks get decrease Sunlight
		 */
		@Test
		public void testdecreaseSunlight() {
			model.decreaseSunlight(4);
			assertEquals(model.getSunlight(), "4");
		}
}
