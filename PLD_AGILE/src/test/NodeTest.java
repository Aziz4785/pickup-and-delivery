package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Map;
import model.Node;
import model.NodeComparator;
import model.Segment;

public class NodeTest {
	@Before
	public void initialize() {
		Map.generateMap("../fichiersXML2020\\mediumMap.xml");
	}

	@Test
	public void test() {
		Map.getSingletonMap().getNodes().get(0);
		Node n = Map.getSingletonMap().getNodes().get(0);
		assertNotEquals(n, null);

		// constructor test
		Node newNode = new Node(n.getLatitude(), n.getLongitude(), n.getID());
		assertNotEquals(n, null);
		assertEquals(n.getLatitude(), newNode.getLatitude(), 0);
		assertEquals(n.getLongitude(), newNode.getLongitude(), 0);
		assertEquals(n.getID(), newNode.getID());

		// get neighbours test
		ArrayList<Segment> neighbours = n.getNeighbours();
		assertNotEquals(neighbours, null);

		// add neighbour test
		Segment s = new Segment();
		int sizePre = neighbours.size();
		n.addNeighbour(s);
		int sizePost = n.getNeighbours().size();
		assertEquals(sizePre + 1, sizePost);

		// comparator test
		ArrayList<Node> nodes = Map.getSingletonMap().getNodes();
		Collections.sort(nodes, new NodeComparator());
		for (int i = 0; i < nodes.size() - 1; i++) {
			assertTrue(nodes.get(i).getID() < nodes.get(i + 1).getID());
		}
	}

	@After
	public void finalize() {

	}
}
