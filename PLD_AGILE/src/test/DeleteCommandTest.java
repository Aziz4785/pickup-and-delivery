package test;

import static org.junit.Assert.assertEquals;

import org.junit.*;

//import controller.Controller;
import controller.DeleteCommand;
import model.ListOfRequest;
import model.Map;
import model.Tour;
import view.Window;

/**
 * A test for the DeleteCommand class. This test was developped before the
 * DeleteCommand class
 *
 */
public class DeleteCommandTest {

	int requestsSize;

	@Before
	public void initialize() {
		// Window win = new Window();
		// Controller control = new Controller(win);
		Map.generateMap("../fichiersXML2020\\mediumMap.xml");
		ListOfRequest requests = new ListOfRequest("../fichiersXML2020\\requestsMedium5.xml");
		requests.importList();
		Map.getSingletonMap().setRequests(requests);
		requestsSize = Map.getSingletonMap().getRequests().getNumberOfRequests();
		Tour t = new Tour();
		t.generateBestTour();
		Map.getSingletonMap().setTour(t);
	}

	@Test
	public void test() {
		Window win =  new Window();
		DeleteCommand command = new DeleteCommand(1, win);
		command.execute(true);
		assertEquals(requestsSize - 1, Map.getSingletonMap().getRequests().getDeliveries().size());

		command.undo(true);
		assertEquals(requestsSize, Map.getSingletonMap().getRequests().getDeliveries().size());
	}

	@After
	public void finalize() {

	}
}
