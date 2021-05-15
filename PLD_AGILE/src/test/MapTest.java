package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Map;
import model.Node;

public class MapTest {
	@Before
	public void initialize() {
		Map.generateMap("../fichiersXML2020\\mediumMap.xml");
	}

	@Test
	public void test() {
		//map import test
		assertNotEquals(Map.getSingletonMap(), null);
		assertNotEquals(Map.getSingletonMap().getNodes(), 0);
		
		//dichotomic search node method test
		Node n = Map.getSingletonMap().getNodes().get(0);
		Node nSearched = Map.getSingletonMap().searchNodeByID(n.getID());
		assertEquals(n.getLatitude(), nSearched.getLatitude(), 0);
		assertEquals(n.getLongitude(), nSearched.getLongitude(), 0);
		assertEquals(n.getID(), nSearched.getID());
		
		//nodes sorted test
		ArrayList<Node> nodes = Map.getSingletonMap().getNodes();
		for(int i = 0; i < nodes.size() - 1; i++) {
			assertTrue(nodes.get(i).getID() < nodes.get(i + 1).getID());
		}
		
		//error handling test on invalid path
		boolean result = Map.generateMap("../aaa.xml");
		assertFalse(result);
	}

	@After
	public void finalize() {

	}
}
