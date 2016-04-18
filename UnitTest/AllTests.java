import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestAccessMap.class, TestActivityList.class, TestgetApplicationActivityByIDDouble.class,
		TestUpdate.class })
public class AllTests {

}
