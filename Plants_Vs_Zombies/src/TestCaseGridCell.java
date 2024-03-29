import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCaseGridCell {

	GridCell gridCell;
	PvZModel model;
	Zombie zombie;
	Peashooter plant;
	int POSITION = 5;

	/**
	 * @author Adam Labelle
	 */

	@Before
	public void setUp() throws Exception {
		this.plant = new Peashooter(gridCell, model);
		this.model = new PvZModel(2, 4);
		this.gridCell = new GridCell(POSITION, POSITION);
		this.zombie = new Zombie(4, gridCell, model);
	}

	@After
	public void tearDown() throws Exception {
		this.gridCell = null;
		this.zombie = null;
		this.model = null;
	}

	/**
	 * Checks add Zombie
	 */
	@Test
	public void testAddZombies() {
		gridCell.addZombie(zombie);
		assertEquals(gridCell.getZombie(), zombie);
	}
	
	/**
	 * Checks Remove Zombie
	 */
	@Test
	public void testRemoveZombie() {
		try {
			gridCell.removeZombie();
		}
		catch (IllegalArgumentException e) {
			
		}
		gridCell.addZombie(zombie);
		assertEquals(gridCell.removeZombie(), zombie);
		

	}

	/**
	 * Checks addPlant
	 */
	@Test
	public void testPlant() {
		try {
			gridCell.removePlant();
		}
		catch (IllegalArgumentException e) {
			
		}
		gridCell.addPlant(plant);
		assertEquals(gridCell.getPlant(), plant);
	}

	/**
	 * Checks Set Location
	 */
	@Test
	public void testSetLocations() {
		gridCell.setLocation(4, 4);
		assertEquals(gridCell.getCol(), 4);
		assertEquals(gridCell.getRow(), 4);
	}

	/**
	 * Checks equals for Grid Cells
	 */
	@Test
	public void testEquals() {
		// Create Temp GridCell to compare cell
		GridCell temp = new GridCell(POSITION, POSITION);

		gridCell.setLocation(4, 4);
		assertEquals(gridCell.equals(gridCell), true);
		IllegalArgumentException a = new IllegalArgumentException();
		try {
			temp.setLocation(5, 5);
		}
		catch (IllegalArgumentException e) { 
			//succcessful error has been thrown
		}
		
	}
}