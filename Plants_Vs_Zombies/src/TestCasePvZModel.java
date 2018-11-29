import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCasePvZModel {
	
	// Variables and Objects used for Testing
	PvZModel model;
	GridCell gridCell;
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
			assertEquals(model.getSunlight(), "100");
		}
		
		/**
		 * Checks get increase Sunlight
		 */
		@Test
		public void testIncreaseSunlight() {
			model.increaseSunlight(25);
			assertEquals("125", model.getSunlight());
			
			try {
				model.increaseSunlight(-10);
			} catch (IllegalArgumentException e) {
				
			}
		}
		
		/**
		 * Checks if pushing and popping the stack works
		 */
		@Test
		public void testStack() {
			
			//checking undo stack before and after pushing
			assertEquals(true, model.undo.isEmpty());
			model.pushUndo();
			assertEquals(false, model.undo.isEmpty());
			assertEquals(true, model.redo.isEmpty());
			
			//checking stacks after popping undo

			model.popUndo();
			assertEquals(true, model.undo.isEmpty());
			assertEquals(false, model.redo.isEmpty());
			
			//checking stacks after popping redo
			model.popRedo();
			assertEquals(true, model.redo.isEmpty());
			assertEquals(false, model.undo.isEmpty());
		}
		
		@Test
		public void testFindZombie() {			
				gridCell = new GridCell(1,1);
				Zombie zombie = new Zombie(gridCell, model);
				model.spawnZombieAt(zombie, gridCell);
				
				assertEquals("Z", model.findZombie(1, 1).getZombie().getID());
				
				try{
					model.findZombie(10, 10);
				} catch (IllegalArgumentException e) {
					
				}
				try{
					model.findZombie(-1, -1);
				} catch (IllegalArgumentException e) {
					
				}
			
		}
		
		/**
		 * Checks get decrease Sunlight
		 */
		@Test
		public void testdecreaseSunlight() {
			model.decreaseSunlight(10);
			assertEquals(model.getSunlight(), "90");
			
			try {
				model.increaseSunlight(-10);
			} catch (IllegalArgumentException e) {
				
			}
		}
		
		/**
		 * Testing placing all plants down
		 */
		@Test 
		public void testPlacePlants() {
			model.increaseSunlight(1000);
			model.isClicked("S");
			gridCell = new GridCell(1,1);
			model.placePlantAt(1, 1);
			assertEquals("S", model.getCell(gridCell).getPlant().getID());
			
			model.isClicked("P");
			gridCell = new GridCell(1,2);
			model.placePlantAt(1, 2);
			assertEquals("P", model.getCell(gridCell).getPlant().getID());
			
			model.isClicked("R");
			gridCell = new GridCell(1,3);
			model.placePlantAt(1, 3);
			assertEquals("R", model.getCell(gridCell).getPlant().getID());
			
			model.isClicked("T");
			gridCell = new GridCell(1,4);
			model.placePlantAt(1, 4);
			assertEquals("T", model.getCell(gridCell).getPlant().getID());
			
			model.isClicked("W");
			gridCell = new GridCell(1,5);
			model.placePlantAt(1, 5);
			assertEquals("W", model.getCell(gridCell).getPlant().getID());
		}

		
		@Test
		public void testEndTurn() {

			gridCell = new GridCell(3,3);
			model.placeSunflowerAt(1, 1);

			Zombie zombie = new Zombie(gridCell, model);
			model.spawnZombieAt(zombie, gridCell);
			model.endTurn();
		}

}
