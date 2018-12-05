import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCasePlant {
	
	// Variables and Objects used for Testing
	GridCell gridCell;
	PvZModel model;
	Peashooter plant;
	
		/**
		 * @author Adam Labelle
		 */
	
		@Before
		public void setUp() throws Exception {
			this.gridCell = new GridCell(5,5);
			this.model = new PvZModel(1,4);
			this.plant = new Peashooter(gridCell,model);
		}

		@After
		public void tearDown() throws Exception {
			this.plant = null;
		}
	
		/**
		 * Checks get Grid Cell from plant
		 */
		@Test
		public void testGetGridCellfromPlant() {
			assertEquals(plant.getGridCell(), gridCell);
		}
		
		/**
		 * Checks Is Alive
		 */
		@Test
		public void testIsAlive() {
			assertEquals(plant.isAlive(), true);
			plant.takeDamage(2);
			assertEquals(plant.isAlive(), false);
		}
		
		/**
		 * Checks get Game
		 */
		@Test
		public void testgetGame() {
			assertEquals(plant.getGame(), model);
		}
		
}
