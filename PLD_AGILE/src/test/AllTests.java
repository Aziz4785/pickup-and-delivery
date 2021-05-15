package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AddNewRequestCommandTest.class, antColonyOptTest.class, ChangeRequestOrderCommandTest.class,
		convertToSegmentListTest.class, DeleteCommandTest.class, dijkstraTest.class, generateBestTourTest.class,
		GenerateSPGraphTest.class, ListOfRequestTest.class, MapTest.class, NodeTest.class, updateStopTimesTest.class,
		updateTourTest.class })
public class AllTests {

}
