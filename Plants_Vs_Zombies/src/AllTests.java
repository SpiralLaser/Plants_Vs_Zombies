import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ TestCaseGridCell.class, TestCasePeashooter.class,
		TestCasePlant.class, TestCasePvZModel.class, TestCaseSunflower.class,
		TestCaseZombie.class, TestCaseBossZombie.class, TestCaseRepeater.class, 
		TestCaseWallnut.class, TestCaseViewController.class})
public class AllTests {

}