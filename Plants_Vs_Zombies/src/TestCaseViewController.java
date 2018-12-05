import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCaseViewController {

	// Variables and Objects used for Testing

	PvZModel model;
	GameView view;
	PvZController controller;

	/**
	 * @author Kevin Sun
	 */
	@Before
	public void setUp() throws Exception {
		this.model = new PvZModel(1,1);
		this.view = new GameView();
		this.controller = new PvZController(model, 1,1);

	}

	@After
	public void tearDown() throws Exception {
		this.view = null;
		this.model = null;
		this.controller = null;
	}

	/**
	 * Testing the input and view
	 */
	@Test
	public void TestgetInput() {
		//can remove next 2 lines and manually put in 1 for both inputs and will also work
		view.numZombies = 1;
		view.zombieHealth = 1;
		assertEquals(1, view.numZombies);
		assertEquals(1, view.zombieHealth);
	}
	
	@Test
	public void TestController() {
		assertEquals(1,controller.x);
		assertEquals(1,controller.y);
	}
	
	
}