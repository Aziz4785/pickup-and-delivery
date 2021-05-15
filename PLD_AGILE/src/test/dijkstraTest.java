package test;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.paint.Color;
import model.Map;
import model.Node;
import model.Segment;
import model.Stop;
import model.Tour;
import model.TypeOfStop;

public class dijkstraTest {

	@Test
	public void test() {
		System.out.println("---------test1------");
		Map.generateMap("../testsXML\\testdijkstra\\casNormal1.xml");
		Map testmap =  Map.getSingletonMap();
		HashMap<Node,Node> expectedPath = new HashMap<Node, Node>();

		expectedPath.put(testmap.searchNodeByID(111111), null);
		expectedPath.put(testmap.searchNodeByID(222222), testmap.searchNodeByID(111111));
		expectedPath.put(testmap.searchNodeByID(333333333), testmap.searchNodeByID(111111));
		expectedPath.put(testmap.searchNodeByID(444444), testmap.searchNodeByID(6666666));
		expectedPath.put(testmap.searchNodeByID(5555555), testmap.searchNodeByID(222222));
		expectedPath.put(testmap.searchNodeByID(6666666), testmap.searchNodeByID(5555555));

		HashMap<Long, Float> distances = new HashMap<Long, Float>();
		HashMap<Node, Node> path =new HashMap<Node, Node>();
		Tour tour = new Tour();
		Stop stopDepot = new Stop(testmap.searchNodeByID(111111),TypeOfStop.DEPOT,Color.WHITE, 0);
		tour.dijkstra(stopDepot, distances, path);
		assertEquals(expectedPath.size(),path.size());
		assertTrue(expectedPath.equals(path));

	}

	@Test
	public void test2() {
		System.out.println("---------test 2------");
		Map.generateMap("../testsXML\\testdijkstra\\casNormal2.xml");
		Map testmap =  Map.getSingletonMap();
		HashMap<Node,Node> expectedPath = new HashMap<Node, Node>();

		expectedPath.put(testmap.searchNodeByID(111111),testmap.searchNodeByID(222222));
		expectedPath.put(testmap.searchNodeByID(222222), testmap.searchNodeByID(444444));
		expectedPath.put(testmap.searchNodeByID(333333333), testmap.searchNodeByID(5555555));
		expectedPath.put(testmap.searchNodeByID(444444), null);
		expectedPath.put(testmap.searchNodeByID(5555555),testmap.searchNodeByID(444444));

		HashMap<Long, Float> distances = new HashMap<Long, Float>();
		HashMap<Node, Node> path =new HashMap<Node, Node>();
		Tour tour = new Tour();
		Stop stopDepot = new Stop(testmap.searchNodeByID(444444),TypeOfStop.DEPOT,Color.WHITE,0);
		tour.dijkstra(stopDepot, distances, path);
		assertEquals(expectedPath.size(),path.size());
		assertTrue(expectedPath.equals(path));

	}
	@Test
	public void test3() {
		System.out.println("---------test33------");
		Map.generateMap("../testsXML\\testdijkstra\\casNormal3.xml");
		Map testmap =  Map.getSingletonMap();
		HashMap<Node,Node> expectedPath = new HashMap<Node, Node>();

		expectedPath.put(testmap.searchNodeByID(1),testmap.searchNodeByID(14));
		expectedPath.put(testmap.searchNodeByID(2), testmap.searchNodeByID(15));
		expectedPath.put(testmap.searchNodeByID(3), testmap.searchNodeByID(18));
		expectedPath.put(testmap.searchNodeByID(4), testmap.searchNodeByID(10));
		expectedPath.put(testmap.searchNodeByID(5),testmap.searchNodeByID(10));
		expectedPath.put(testmap.searchNodeByID(6),testmap.searchNodeByID(9));
		expectedPath.put(testmap.searchNodeByID(7), testmap.searchNodeByID(17));
		expectedPath.put(testmap.searchNodeByID(8), testmap.searchNodeByID(7));
		expectedPath.put(testmap.searchNodeByID(9), testmap.searchNodeByID(8));
		expectedPath.put(testmap.searchNodeByID(10),testmap.searchNodeByID(8));
		expectedPath.put(testmap.searchNodeByID(11),testmap.searchNodeByID(12));
		expectedPath.put(testmap.searchNodeByID(12), testmap.searchNodeByID(3));
		expectedPath.put(testmap.searchNodeByID(13), testmap.searchNodeByID(18));
		expectedPath.put(testmap.searchNodeByID(14), testmap.searchNodeByID(3));
		expectedPath.put(testmap.searchNodeByID(15),testmap.searchNodeByID(12));
		expectedPath.put(testmap.searchNodeByID(16),testmap.searchNodeByID(5));
		expectedPath.put(testmap.searchNodeByID(17),null);
		expectedPath.put(testmap.searchNodeByID(18),testmap.searchNodeByID(17));
		HashMap<Long, Float> distances = new HashMap<Long, Float>();
		HashMap<Node, Node> path =new HashMap<Node, Node>();
		Tour tour = new Tour();
		Stop stopDepot = new Stop(testmap.searchNodeByID(17),TypeOfStop.DEPOT,Color.WHITE,0);
		tour.dijkstra(stopDepot, distances, path);
		assertEquals(expectedPath.size(),path.size());
		assertTrue(expectedPath.equals(path));

	}
	@Test
	public void test4() { 
		Map.generateMap("../testsXML\\testdijkstra\\test5.xml");
		Map testmap =  Map.getSingletonMap();
		Node testnode = new Node(50,45,(long)1);
		ArrayList<Node> testnodes = new ArrayList<Node>();
		testnodes.add(testnode);
		testmap.setNodes(testnodes);

		HashMap<Node,Node> expectedPath = new HashMap<Node, Node>();

		expectedPath.put(testnode,null);
		System.out.println(testmap.searchNodeByID(1));
		HashMap<Long, Float> distances = new HashMap<Long, Float>();
		HashMap<Node, Node> path =new HashMap<Node, Node>();
		Tour tour = new Tour();
		Stop stopDepot = new Stop(testnode,TypeOfStop.DEPOT,Color.WHITE,0);
		tour.dijkstra(stopDepot, distances, path);
		assertEquals(expectedPath.size(),path.size());
		assertTrue(expectedPath.equals(path));

	}
	@Test
	public void test2noeuds() {
		Map.generateMap("../testsXML\\testdijkstra\\cas2noeuds.xml");
		Map testmap =  Map.getSingletonMap();
		HashMap<Node,Node> expectedPath = new HashMap<Node, Node>();
		Node node1 = new Node(45.6, 40.2,111111 );
		Node node2 = new Node(50.6, 10.2,222222 );
		Segment segment12=new Segment(node1,node2, "dfre", (float) 20.4);
		Segment segment21=new Segment(node2, node1, "frnegjkr", (float) 10.7);
		ArrayList<Segment> node1nei = new ArrayList<Segment>();
		node1nei.add(segment12);
		node1.setNeighbours(node1nei);
		ArrayList<Segment> node2nei = new ArrayList<Segment>();
		node2nei.add(segment21);
		node2.setNeighbours(node2nei);
		ArrayList<Node> listnode = new ArrayList<Node>();
		listnode.add(node1);
		listnode.add(node2);
		testmap.setNodes(listnode);
		expectedPath.put(node1,node2);
		expectedPath.put(node2,null);

		HashMap<Long, Float> distances = new HashMap<Long, Float>();
		HashMap<Node, Node> path =new HashMap<Node, Node>();
		Tour tour = new Tour();
		System.out.println("pour le test2noeuds : noeud 1 : "+node1 + " noeud 2 : "+node2);
		Stop stopDepot = new Stop(node2,TypeOfStop.DEPOT,Color.WHITE,0);
		if(stopDepot.getNode()==null)
			System.out.println("stopdepot es tnull");
		tour.dijkstra(stopDepot, distances, path);
		assertEquals(expectedPath.size(),path.size());
		assertTrue(expectedPath.equals(path));

	}
	@Test
	public void test2xMajx3() {
		System.out.println("---------test 2xmajx3------");
		Map.generateMap("../testsXML\\testdijkstra\\cas2xMajx3.xml");
		Map testmap =  Map.getSingletonMap();
		HashMap<Node,Node> expectedPath = new HashMap<Node, Node>();

		expectedPath.put(testmap.searchNodeByID(0),null);
		expectedPath.put(testmap.searchNodeByID(1),testmap.searchNodeByID(0));
		expectedPath.put(testmap.searchNodeByID(2), testmap.searchNodeByID(0));
		expectedPath.put(testmap.searchNodeByID(3), testmap.searchNodeByID(0));
		expectedPath.put(testmap.searchNodeByID(4),testmap.searchNodeByID(2));
		expectedPath.put(testmap.searchNodeByID(5),testmap.searchNodeByID(4));
		expectedPath.put(testmap.searchNodeByID(6), testmap.searchNodeByID(4));
		expectedPath.put(testmap.searchNodeByID(7), testmap.searchNodeByID(4));
		expectedPath.put(testmap.searchNodeByID(8),testmap.searchNodeByID(4));
		expectedPath.put(testmap.searchNodeByID(9),testmap.searchNodeByID(5));
		HashMap<Long, Float> distances = new HashMap<Long, Float>();
		HashMap<Node, Node> path =new HashMap<Node, Node>();
		Tour tour = new Tour();
		Stop stopDepot = new Stop(testmap.searchNodeByID(0),TypeOfStop.DEPOT,Color.WHITE,0);
		tour.dijkstra(stopDepot, distances, path);
		assertEquals(expectedPath.size(),path.size());
		assertTrue(expectedPath.equals(path));

	}
	@Test
	public void test5() {
		System.out.println("---------test5------");
		Map.generateMap("../testsXML\\testdijkstra\\test5.xml");
		Map testmap =  Map.getSingletonMap();
		HashMap<Node,Node> expectedPath = new HashMap<Node, Node>();

		expectedPath.put(testmap.searchNodeByID(0),testmap.searchNodeByID(4) );
		expectedPath.put(testmap.searchNodeByID(1), testmap.searchNodeByID(2));
		expectedPath.put(testmap.searchNodeByID(2),null);
		expectedPath.put(testmap.searchNodeByID(3), testmap.searchNodeByID(4));
		expectedPath.put(testmap.searchNodeByID(4), testmap.searchNodeByID(5));
		expectedPath.put(testmap.searchNodeByID(5), testmap.searchNodeByID(2));

		HashMap<Long, Float> distances = new HashMap<Long, Float>();
		HashMap<Node, Node> path =new HashMap<Node, Node>();
		Tour tour = new Tour();
		Stop stopDepot = new Stop(testmap.searchNodeByID(2),TypeOfStop.DEPOT,Color.WHITE,0);
		tour.dijkstra(stopDepot, distances, path);
		assertEquals(expectedPath.size(),path.size());
		assertTrue(expectedPath.equals(path));

	}
	@Test
	public void test6() {
		System.out.println("---------test6------");
		Map.generateMap("../testsXML\\testdijkstra\\test6.xml");
		Map testmap =  Map.getSingletonMap();
		HashMap<Node,Node> expectedPath = new HashMap<Node, Node>();

		expectedPath.put(testmap.searchNodeByID(0),null );
		expectedPath.put(testmap.searchNodeByID(1), testmap.searchNodeByID(2));
		expectedPath.put(testmap.searchNodeByID(2), testmap.searchNodeByID(0));
		expectedPath.put(testmap.searchNodeByID(3), testmap.searchNodeByID(1));

		HashMap<Long, Float> distances = new HashMap<Long, Float>();
		HashMap<Node, Node> path =new HashMap<Node, Node>();
		Tour tour = new Tour();
		Stop stopDepot = new Stop(testmap.searchNodeByID(0),TypeOfStop.DEPOT,Color.WHITE,0);
		tour.dijkstra(stopDepot, distances, path);
		path.entrySet().forEach(entry->{
			if(entry.getValue()==null)
				System.out.println(entry.getKey().getID() + " null");
			else
				System.out.println(entry.getKey().getID() + " " + entry.getValue().getID());  
		});
		assertEquals(expectedPath.size(),path.size());
		assertTrue(expectedPath.equals(path));

	}
	@Test
	public void test9() {
		System.out.println("---------test9-----");
		Map.generateMap("../testsXML\\testdijkstra\\test9.xml");
		Map testmap =  Map.getSingletonMap();
		HashMap<Node,Node> expectedPath = new HashMap<Node, Node>();

		expectedPath.put(testmap.searchNodeByID(0),null);
		expectedPath.put(testmap.searchNodeByID(1), testmap.searchNodeByID(0));
		expectedPath.put(testmap.searchNodeByID(2), null);
		expectedPath.put(testmap.searchNodeByID(3), testmap.searchNodeByID(1));
		expectedPath.put(testmap.searchNodeByID(4),testmap.searchNodeByID(1));
		expectedPath.put(testmap.searchNodeByID(5),testmap.searchNodeByID(4));
		HashMap<Long, Float> distances = new HashMap<Long, Float>();
		HashMap<Node, Node> path =new HashMap<Node, Node>();
		Tour tour = new Tour();
		Stop stopDepot = new Stop(testmap.searchNodeByID(0),TypeOfStop.DEPOT,Color.WHITE,0);
		tour.dijkstra(stopDepot, distances, path);
		assertEquals(expectedPath.size(),path.size());
		assertTrue(expectedPath.equals(path));

	}
}
