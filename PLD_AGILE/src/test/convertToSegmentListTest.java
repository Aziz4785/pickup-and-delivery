package test;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.HashMap;


import model.Node;
import model.Segment;
import model.Tour;

public class convertToSegmentListTest {
	@Test
	public void test1() {
		System.out.println("----------test 1 --------------");
		Tour tour = new Tour();
		Node node0 = new Node(0,0,0);
		Node node1 = new Node(0,0,1);
		Node node2 = new Node(0,0,2);
		Node node3 = new Node(0,0,3);
		Node node4 = new Node(0,0,4);
		Node node5 = new Node(0,0,5);
		Node node6 = new Node(0,0,6);
		Node node7 = new Node(0,0,7);
		Node node8 = new Node(0,0,8);
		Node node9 = new Node(0,0,9);
		Node node10 = new Node(0,0,10);
		Node node11 = new Node(0,0,11);
		Node node12 = new Node(0,0,12);
		HashMap<Node,Node> Path = new HashMap<Node, Node>();

		Path.put(node0,node7 );
		Path.put(node1, node8);
		Path.put(node2,node5 );
		Path.put(node3, node12);
		Path.put(node4,node0 );
		Path.put(node5, node12);
		Path.put(node6,node4 );
		Path.put(node7, null);
		Path.put(node8,node10 );
		Path.put(node9, node3);
		Path.put(node10,node7 );
		Path.put(node11, node5);
		Path.put(node12, node0);
		ArrayList<Segment> result = tour.convertToSegmentList(node7, node5, Path);
		ArrayList<Segment> expectedlist = new ArrayList<Segment>();
		expectedlist.add(new Segment(node7,node0));
		expectedlist.add(new Segment(node0,node12));
		expectedlist.add(new Segment(node12,node5));
		System.out.println("expected : ");
		for(Segment sgm : expectedlist)
			System.out.println(sgm.getOrigin().getID()+" -> "+sgm.getDestination().getID());
		System.out.println("result : ");
		for(Segment sgm : result) 
			System.out.println(sgm.getOrigin().getID()+" -> "+sgm.getDestination().getID());
		assertEquals(result.size(),expectedlist.size());
		for(int i = 0 ;i<result.size();i++)
		{
			assertEquals(expectedlist.get(i).getOrigin().getID(),result.get(i).getOrigin().getID());
			assertEquals(expectedlist.get(i).getDestination().getID(),result.get(i).getDestination().getID());
		}


	}
	@Test
	public void test2() {
		System.out.println("----------test2 --------------");
		Tour tour = new Tour();
		Node node0 = new Node(0,0,0);
		Node node1 = new Node(0,0,1);
		Node node2 = new Node(0,0,2);
		Node node3 = new Node(0,0,3);
		Node node4 = new Node(0,0,4);
		Node node5 = new Node(0,0,5);
		Node node6 = new Node(0,0,6);
		Node node7 = new Node(0,0,7);
		Node node8 = new Node(0,0,8);
		Node node9 = new Node(0,0,9);
		Node node10 = new Node(0,0,10);
		Node node11 = new Node(0,0,11);
		Node node12 = new Node(0,0,12);
		HashMap<Node,Node> Path = new HashMap<Node, Node>();

		Path.put(node0,node7 );
		Path.put(node1, node8);
		Path.put(node2,node5 );
		Path.put(node3, node12);
		Path.put(node4,node0 );
		Path.put(node5, node12);
		Path.put(node6,node4 );
		Path.put(node7, null);
		Path.put(node8,node10 );
		Path.put(node9, node3);
		Path.put(node10,node7 );
		Path.put(node11, node5);
		Path.put(node12, node0);
		ArrayList<Segment> result = tour.convertToSegmentList(node7, node11, Path);
		ArrayList<Segment> expectedlist = new ArrayList<Segment>();

		expectedlist.add(new Segment(node7,node0));
		expectedlist.add(new Segment(node0,node12));
		expectedlist.add(new Segment(node12,node5));
		expectedlist.add(new Segment(node5,node11));
		System.out.println("expected : ");
		for(Segment sgm : expectedlist)
			System.out.println(sgm.getOrigin().getID()+" -> "+sgm.getDestination().getID());
		System.out.println("result : ");
		for(Segment sgm : result)
			System.out.println(sgm.getOrigin().getID()+" -> "+sgm.getDestination().getID());
		assertEquals(result.size(),expectedlist.size());
		for(int i = 0 ;i<result.size();i++)
		{
			assertEquals(expectedlist.get(i).getOrigin().getID(),result.get(i).getOrigin().getID());
			assertEquals(expectedlist.get(i).getDestination().getID(),result.get(i).getDestination().getID());
		}

	}

	@Test
	public void test4() {
		System.out.println("----------test4 --------------");
		Tour tour = new Tour();
		Node node0 = new Node(0,0,0);
		Node node1 = new Node(0,0,1);
		Node node2 = new Node(0,0,2);
		Node node3 = new Node(0,0,3);
		Node node4 = new Node(0,0,4);
		Node node5 = new Node(0,0,5);
		Node node6 = new Node(0,0,6);
		Node node7 = new Node(0,0,7);
		Node node8 = new Node(0,0,8);
		Node node9 = new Node(0,0,9);
		Node node10 = new Node(0,0,10);
		Node node11 = new Node(0,0,11);
		Node node12 = new Node(0,0,12);
		HashMap<Node,Node> Path = new HashMap<Node, Node>();
		ArrayList<Segment> result = tour.convertToSegmentList(node7, node5, Path);
		ArrayList<Segment> expectedlist = new ArrayList<Segment>();

		assertNull(tour.convertToSegmentList(node7, node5, Path));

	}
	@Test
	public void test5() {
		System.out.println("----------test 5 --------------");
		Tour tour = new Tour();
		Node node0 = new Node(0,0,0);
		Node node1 = new Node(0,0,1);
		Node node2 = new Node(0,0,2);
		Node node3 = new Node(0,0,3);
		Node node4 = new Node(0,0,4);
		Node node5 = new Node(0,0,5);
		Node node6 = new Node(0,0,6);
		Node node7 = new Node(0,0,7);
		Node node8 = new Node(0,0,8);
		Node node9 = new Node(0,0,9);
		Node node10 = new Node(0,0,10);
		Node node11 = new Node(0,0,11);
		Node node12 = new Node(0,0,12);
		HashMap<Node,Node> Path = new HashMap<Node, Node>();

		Path.put(node7, null);

		ArrayList<Segment> result = tour.convertToSegmentList(node7, node5, Path);
		assertNull(tour.convertToSegmentList(node7, node5, Path));
	}
	@Test
	public void test6() {
		System.out.println("----------test 6 --------------");
		Tour tour = new Tour();
		Node node0 = new Node(0,0,0);
		Node node1 = new Node(0,0,1);
		Node node2 = new Node(0,0,2);
		Node node3 = new Node(0,0,3);
		Node node4 = new Node(0,0,4);
		Node node5 = new Node(0,0,5);

		assertNull(tour.convertToSegmentList(node1, node5, null));
	}
	@Test
	public void test7() {
		System.out.println("----------test7 --------------");
		Tour tour = new Tour();
		Node node0 = new Node(0,0,0);
		Node node1 = new Node(0,0,1);
		Node node2 = new Node(0,0,2);
		Node node3 = new Node(0,0,3);
		Node node4 = new Node(0,0,4);
		Node node5 = new Node(0,0,5);
		Node node6 = new Node(0,0,6);
		Node node7 = new Node(0,0,7);
		Node node8 = new Node(0,0,8);
		Node node9 = new Node(0,0,9);
		Node node10 = new Node(0,0,10);
		Node node11 = new Node(0,0,11);
		Node node12 = new Node(0,0,12);
		Node node46 = new Node(0,0,46);
		HashMap<Node,Node> Path = new HashMap<Node, Node>();

		Path.put(node0,node7 );
		Path.put(node1, node8);
		Path.put(node2,node5 );
		Path.put(node3, node12);
		Path.put(node4,node0 );
		Path.put(node5, node12);
		Path.put(node6,node4 );
		Path.put(node7, null);
		Path.put(node8,node10 );
		Path.put(node9, node3);
		Path.put(node10,node7 );
		Path.put(node11, node5);
		Path.put(node12, node0);
		ArrayList<Segment> result = tour.convertToSegmentList(node7, node11, Path);
		ArrayList<Segment> expectedlist = new ArrayList<Segment>();

		assertNull(tour.convertToSegmentList(node46, node5, Path));
	}
	@Test
	public void test20() {
		System.out.println("----------test 20 --------------");
		Tour tour = new Tour();
		Node node0 = new Node(0,0,0);
		Node node1 = new Node(0,0,1);
		Node node2 = new Node(0,0,2);
		Node node3 = new Node(0,0,3);
		Node node4 = new Node(0,0,4);
		Node node5 = new Node(0,0,5);
		HashMap<Node,Node> Path = new HashMap<Node, Node>();

		Path.put(node0,null);
		Path.put(node1, node0);
		Path.put(node2,node0 );
		Path.put(node3, node2);
		Path.put(node4,null );
		Path.put(node5, node3);

		ArrayList<Segment> result = tour.convertToSegmentList(node0, node4, Path);
		ArrayList<Segment> expectedlist = new ArrayList<Segment>();
		System.out.println("expected : ");
		for(Segment sgm : expectedlist)
			System.out.println(sgm.getOrigin().getID()+" -> "+sgm.getDestination().getID());
		System.out.println("result : ");
		for(Segment sgm : result)
			System.out.println(sgm.getOrigin().getID()+" -> "+sgm.getDestination().getID());
		assertEquals(result.size(),expectedlist.size());
		assertEquals(result.size(),0);
		for(int i = 0 ;i<result.size();i++)
		{
			assertEquals(expectedlist.get(i).getOrigin().getID(),result.get(i).getOrigin().getID());
			assertEquals(expectedlist.get(i).getDestination().getID(),result.get(i).getDestination().getID());
		}


	}

	@Test
	public void test21() {
		System.out.println("----------test 21 --------------");
		Tour tour = new Tour();
		Node node0 = new Node(0,0,0);
		Node node1 = new Node(0,0,1);
		Node node2 = new Node(0,0,2);
		Node node3 = new Node(0,0,3);
		Node node4 = new Node(0,0,4);
		Node node5 = new Node(0,0,5);
		HashMap<Node,Node> Path = new HashMap<Node, Node>();

		Path.put(node0,null);
		Path.put(node1, null);
		Path.put(node2,null );
		Path.put(node3, null);
		Path.put(node4,null );
		Path.put(node5, null);

		ArrayList<Segment> result = tour.convertToSegmentList(node4, node0, Path);
		ArrayList<Segment> expectedlist = new ArrayList<Segment>();
		System.out.println("expected : ");
		for(Segment sgm : expectedlist)
			System.out.println(sgm.getOrigin().getID()+" -> "+sgm.getDestination().getID());
		System.out.println("result : ");
		for(Segment sgm : result)
			System.out.println(sgm.getOrigin().getID()+" -> "+sgm.getDestination().getID());
		assertEquals(result.size(),expectedlist.size());
		assertEquals(result.size(),0);
		for(int i = 0 ;i<result.size();i++)
		{
			assertEquals(expectedlist.get(i).getOrigin().getID(),result.get(i).getOrigin().getID());
			assertEquals(expectedlist.get(i).getDestination().getID(),result.get(i).getDestination().getID());
		}

	}

	@Test
	public void test10() {
		System.out.println("----------test 10 --------------");
		Tour tour = new Tour();
		Node node0 = new Node(0,0,0);
		Node node1 = new Node(0,0,1);
		Node node2 = new Node(0,0,2);
		Node node3 = new Node(0,0,3);
		Node node4 = new Node(0,0,4);
		Node node5 = new Node(0,0,5);
		Node node6 = new Node(0,0,6);
		Node node7 = new Node(0,0,7);
		Node node8 = new Node(0,0,8);
		Node node9 = new Node(0,0,9);
		Node node10 = new Node(0,0,10);
		Node node11 = new Node(0,0,11);
		Node node12 = new Node(0,0,12);
		HashMap<Node,Node> Path = new HashMap<Node, Node>();

		Path.put(node0,node7 );
		Path.put(node1, node8);
		Path.put(node2,node5 );
		Path.put(node3, node12);
		Path.put(node4,node0 );
		Path.put(node5, node12);
		Path.put(node6,node4 );
		Path.put(node7, null);
		Path.put(node8,node10 );
		Path.put(node9, node3);
		Path.put(node10,node7 );
		Path.put(node11, node5);
		Path.put(node12, node0);
		ArrayList<Segment> result = tour.convertToSegmentList(node7, node7, Path);
		ArrayList<Segment> expectedlist = new ArrayList<Segment>();
		System.out.println("expected : ");
		for(Segment sgm : expectedlist)
			System.out.println(sgm.getOrigin().getID()+" -> "+sgm.getDestination().getID());
		System.out.println("result : ");
		for(Segment sgm : result)
			System.out.println(sgm.getOrigin().getID()+" -> "+sgm.getDestination().getID());
		assertEquals(result.size(),expectedlist.size());
		assertEquals(result.size(),0);
		for(int i = 0 ;i<result.size();i++)
		{
			assertEquals(expectedlist.get(i).getOrigin().getID(),result.get(i).getOrigin().getID());
			assertEquals(expectedlist.get(i).getDestination().getID(),result.get(i).getDestination().getID());
		}

	}
}
