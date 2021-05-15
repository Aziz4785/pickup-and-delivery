package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.ListOfRequest;
import model.Map;

public class ListOfRequestTest {
	private ListOfRequest requests;

	@Before
	public void initialize() {
		Map.generateMap("../fichiersXML2020\\mediumMap.xml");
	}

	@Test
	public void test() {
		// constructor 1 test
		requests = new ListOfRequest("../fichiersXML2020\\requestsMedium5.xml");
		assertNotEquals(requests, null);

		// import test
		requests.importList();
		assertEquals(requests.getNumberOfRequests(), 5);

		// constructor 2 test
		ListOfRequest copy = new ListOfRequest(requests.getDepot(), requests.getDepartureTime(),
				requests.getDeliveries());
		assertNotEquals(requests, null);
		assertEquals(requests.getDepot(), copy.getDepot());
		assertEquals(requests.getDepartureTime(), copy.getDepartureTime());
		assertEquals(requests.getNumberOfRequests(), copy.getNumberOfRequests());
	}

	@After
	public void finalize() {

	}
}
