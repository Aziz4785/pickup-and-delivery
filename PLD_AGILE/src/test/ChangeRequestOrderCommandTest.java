package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.ChangeStopOrderCommand;
import model.ListOfRequest;
import model.Map;
import model.Request;
import model.Stop;
import model.Tour;
import view.Window;

/**
 * A test for the ChangeRequestOrderCommand class. This test was developped
 * before the ChangeRequestOrderCommand class
 *
 */
public class ChangeRequestOrderCommandTest {

	@Before
	public void initialize() {
		Map.generateMap("../fichiersXML2020\\mediumMap.xml");
		ListOfRequest requests = new ListOfRequest("../fichiersXML2020\\requestsMedium3.xml");
		requests.importList();
		Map.getSingletonMap().setRequests(requests);
		Tour t = new Tour();
		t.generateBestTour();
		Map.getSingletonMap().setTour(t);
	}

	@Test
	public void test() {
		Window win = new Window();
		Stop firstStop = Map.getSingletonMap().getRequests().getOrderedStops().get(1); //0 is the depot

		ChangeStopOrderCommand command = new ChangeStopOrderCommand(1, true, win);
		command.execute(true);
		assertNotEquals(firstStop, Map.getSingletonMap().getRequests().getOrderedStops().get(1));
		command.undo(true);
		assertEquals(firstStop, Map.getSingletonMap().getRequests().getOrderedStops().get(1));
	}

	@After
	public void finalize() {

	}
}
