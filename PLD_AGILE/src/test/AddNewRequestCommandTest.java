package test;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import controller.AddNewRequestCommand;
import model.ListOfRequest;
import model.Map;
import model.Request;
import model.Tour;
import view.Window;

/**
 * A test for the AddNewRequestCommand class. This test was developped before
 * the AddNewRequestCommand class
 *
 */
public class AddNewRequestCommandTest {
	private int requestsSize;

	@Before
	public void initialize() {
		Map.generateMap("../fichiersXML2020\\mediumMap.xml");
		ListOfRequest requests = new ListOfRequest("../fichiersXML2020\\requestsMedium3.xml");
		requests.importList();
		Map.getSingletonMap().setRequests(requests);
		Tour t = new Tour();
		t.generateBestTour();
		Map.getSingletonMap().setTour(t);
		requestsSize = Map.getSingletonMap().getRequests().getDeliveries().size();
	}

	@Test
	public void test() {
		Window win = new Window();
		AddNewRequestCommand command = new AddNewRequestCommand(new Request(Map.getSingletonMap().getNodes().get(0), 10,
				Map.getSingletonMap().getNodes().get(0), 10), win);
		command.execute(true);
		assertEquals(requestsSize + 1, Map.getSingletonMap().getRequests().getDeliveries().size());

		command.undo(true);
		assertEquals(requestsSize, Map.getSingletonMap().getRequests().getDeliveries().size());
	}

	@After
	public void finalize() {

	}
}