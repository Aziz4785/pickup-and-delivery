package test;

import org.junit.*;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

import model.ListOfRequest;
import model.Map;
import model.Node;
import model.Stop;
import model.Tour;
import model.TypeOfStop;

public class updateTourTest {
	@Test
	public void test1() {
		Map.generateMap("../testsXML\\updateTour\\test1.xml");
		Map testmap =  Map.getSingletonMap();
		ListOfRequest requests = new ListOfRequest("../testsXML\\updateTour\\request1.xml");
		requests.importList();
		testmap.setRequests(requests);
		Tour tour = new Tour();
		HashMap<Long, HashMap<Node, Node>> pathOf = new HashMap<Long, HashMap<Node, Node>>();
		HashMap<Long, HashMap<Long, Float>> distanceOf=new HashMap<Long, HashMap<Long, Float>>();
		
		 HashMap<Node, Node> pathOf1 = new HashMap<Node, Node>();
		 HashMap<Node, Node> pathOf6 = new HashMap<Node, Node>();
		 HashMap<Node, Node> pathOf4 = new HashMap<Node, Node>();
		 
		 HashMap<Long, Float> distanceOf1 = new HashMap<Long, Float>();
		 HashMap<Long, Float> distanceOf6 = new HashMap<Long, Float>();
		 HashMap<Long, Float> distanceOf4 = new HashMap<Long, Float>();
		 
		 pathOf1.put(testmap.searchNodeByID(0),testmap.searchNodeByID(1));
		 pathOf1.put(testmap.searchNodeByID(1),null);
		 pathOf1.put(testmap.searchNodeByID(2),testmap.searchNodeByID(1));
		 pathOf1.put(testmap.searchNodeByID(3),testmap.searchNodeByID(2));
		 pathOf1.put(testmap.searchNodeByID(4),testmap.searchNodeByID(3));
		 pathOf1.put(testmap.searchNodeByID(5),testmap.searchNodeByID(1));
		 pathOf1.put(testmap.searchNodeByID(6),testmap.searchNodeByID(2));
		 
		 pathOf6.put(testmap.searchNodeByID(0),testmap.searchNodeByID(5));
		 pathOf6.put(testmap.searchNodeByID(1),testmap.searchNodeByID(6));
		 pathOf6.put(testmap.searchNodeByID(2),testmap.searchNodeByID(1));
		 pathOf6.put(testmap.searchNodeByID(3),testmap.searchNodeByID(2));
		 pathOf6.put(testmap.searchNodeByID(4),testmap.searchNodeByID(3));
		 pathOf6.put(testmap.searchNodeByID(5),testmap.searchNodeByID(6));
		 pathOf6.put(testmap.searchNodeByID(6),null);
		 
		 pathOf4.put(testmap.searchNodeByID(0),testmap.searchNodeByID(5));
		 pathOf4.put(testmap.searchNodeByID(1),testmap.searchNodeByID(6));
		 pathOf4.put(testmap.searchNodeByID(2),testmap.searchNodeByID(1));
		 pathOf4.put(testmap.searchNodeByID(3),testmap.searchNodeByID(2));
		 pathOf4.put(testmap.searchNodeByID(4),null);
		 pathOf4.put(testmap.searchNodeByID(5),testmap.searchNodeByID(6));
		 pathOf4.put(testmap.searchNodeByID(6),testmap.searchNodeByID(4));
		 
		 distanceOf1.put(testmap.searchNodeByID(0).getID(),(float) 10);
		 distanceOf1.put(testmap.searchNodeByID(1).getID(),(float) 0);
		 distanceOf1.put(testmap.searchNodeByID(2).getID(),(float) 5);
		 distanceOf1.put(testmap.searchNodeByID(3).getID(),(float) 8);
		 distanceOf1.put(testmap.searchNodeByID(4).getID(),(float) 15);
		 distanceOf1.put(testmap.searchNodeByID(5).getID(),(float) 10);
		 distanceOf1.put(testmap.searchNodeByID(6).getID(),(float) 10);
		 
		 distanceOf6.put(testmap.searchNodeByID(0).getID(),(float) 19);
		 distanceOf6.put(testmap.searchNodeByID(1).getID(),(float) 10);
		 distanceOf6.put(testmap.searchNodeByID(2).getID(),(float) 15);
		 distanceOf6.put(testmap.searchNodeByID(3).getID(),(float) 18);
		 distanceOf6.put(testmap.searchNodeByID(4).getID(),(float) 25);
		 distanceOf6.put(testmap.searchNodeByID(5).getID(),(float) 11);
		 distanceOf6.put(testmap.searchNodeByID(6).getID(),(float) 0);

		 distanceOf4.put(testmap.searchNodeByID(0).getID(),(float) 24);
		 distanceOf4.put(testmap.searchNodeByID(1).getID(),(float) 15);
		 distanceOf4.put(testmap.searchNodeByID(2).getID(),(float) 20);
		 distanceOf4.put(testmap.searchNodeByID(3).getID(),(float) 23);
		 distanceOf4.put(testmap.searchNodeByID(4).getID(),(float) 0);
		 distanceOf4.put(testmap.searchNodeByID(5).getID(),(float) 16);
		 distanceOf4.put(testmap.searchNodeByID(6).getID(),(float) 5);
		 
		 pathOf.put((long) 1, pathOf1);
		 pathOf.put((long) 6, pathOf6);
		 pathOf.put((long) 4, pathOf4);
		 
		 distanceOf.put((long) 1, distanceOf1);
		 distanceOf.put((long) 6, distanceOf6);
		 distanceOf.put((long) 4, distanceOf4);

		 tour.generateBestTour();
		 Stop depot=new Stop(testmap.searchNodeByID(1),TypeOfStop.DEPOT,0);
			depot.setDepartureTime(LocalTime.of(8,0,0));
			depot.setArrivalTime(LocalTime.of(8,0,0));
			Stop delivery1=new Stop(testmap.searchNodeByID(6),TypeOfStop.DELIVERY,100);
			Stop pickup1=new Stop(testmap.searchNodeByID(4),TypeOfStop.PICKUP,60);

			ArrayList<Stop> orderedStops = new ArrayList<Stop>();
			orderedStops.add(depot);
			orderedStops.add(delivery1);
			orderedStops.add(pickup1);
			orderedStops.add(depot);
		 tour.updateTour(orderedStops);
		 
		 tour.getPathOf().forEach((K,V)->{    
			 System.out.println(K + " -> ");// mapofmaps entries
	         V.forEach((X,Y)->{  
	        	 if(Y == null)
	        		 System.out.println(X.getID() + " null");
	        	 else
	             System.out.println(X.getID()+" "+Y.getID());       // print key and value of inner Hashmap 
	         });
	     });
		 assertEquals(tour.getPathOf().size(),pathOf.size());
		 assertEquals(tour.getPathOf(),pathOf);
		 assertEquals(tour.getDistanceOf().size(),distanceOf.size());
		 assertEquals(tour.getDistanceOf(),distanceOf);
	}
	
	@Test
	public void test4() {
		Map.generateMap("../testsXML\\updateTour\\test1.xml");
		Map testmap =  Map.getSingletonMap();
		ListOfRequest requests = new ListOfRequest("../testsXML\\updateTour\\request1.xml");
		requests.importList();
		testmap.setRequests(requests);
		Tour tour = new Tour();
		HashMap<Long, HashMap<Node, Node>> pathOf = new HashMap<Long, HashMap<Node, Node>>();
		HashMap<Long, HashMap<Long, Float>> distanceOf=new HashMap<Long, HashMap<Long, Float>>();
		
		 HashMap<Node, Node> pathOf1 = new HashMap<Node, Node>();
		 HashMap<Node, Node> pathOf6 = new HashMap<Node, Node>();
		 HashMap<Node, Node> pathOf4 = new HashMap<Node, Node>();
		 HashMap<Node, Node> pathOf3 = new HashMap<Node, Node>();
		 HashMap<Node, Node> pathOf2 = new HashMap<Node, Node>();
		 
		 HashMap<Long, Float> distanceOf1 = new HashMap<Long, Float>();
		 HashMap<Long, Float> distanceOf6 = new HashMap<Long, Float>();
		 HashMap<Long, Float> distanceOf4 = new HashMap<Long, Float>();
		 HashMap<Long, Float> distanceOf3 = new HashMap<Long, Float>();
		 HashMap<Long, Float> distanceOf2 = new HashMap<Long, Float>();
		 
		 pathOf1.put(testmap.searchNodeByID(0),testmap.searchNodeByID(1));
		 pathOf1.put(testmap.searchNodeByID(1),null);
		 pathOf1.put(testmap.searchNodeByID(2),testmap.searchNodeByID(1));
		 pathOf1.put(testmap.searchNodeByID(3),testmap.searchNodeByID(2));
		 pathOf1.put(testmap.searchNodeByID(4),testmap.searchNodeByID(3));
		 pathOf1.put(testmap.searchNodeByID(5),testmap.searchNodeByID(1));
		 pathOf1.put(testmap.searchNodeByID(6),testmap.searchNodeByID(2));
		 
		 pathOf6.put(testmap.searchNodeByID(0),testmap.searchNodeByID(5));
		 pathOf6.put(testmap.searchNodeByID(1),testmap.searchNodeByID(6));
		 pathOf6.put(testmap.searchNodeByID(2),testmap.searchNodeByID(1));
		 pathOf6.put(testmap.searchNodeByID(3),testmap.searchNodeByID(2));
		 pathOf6.put(testmap.searchNodeByID(4),testmap.searchNodeByID(3));
		 pathOf6.put(testmap.searchNodeByID(5),testmap.searchNodeByID(6));
		 pathOf6.put(testmap.searchNodeByID(6),null);
		 
		 pathOf4.put(testmap.searchNodeByID(0),testmap.searchNodeByID(5));
		 pathOf4.put(testmap.searchNodeByID(1),testmap.searchNodeByID(6));
		 pathOf4.put(testmap.searchNodeByID(2),testmap.searchNodeByID(1));
		 pathOf4.put(testmap.searchNodeByID(3),testmap.searchNodeByID(2));
		 pathOf4.put(testmap.searchNodeByID(4),null);
		 pathOf4.put(testmap.searchNodeByID(5),testmap.searchNodeByID(6));
		 pathOf4.put(testmap.searchNodeByID(6),testmap.searchNodeByID(4));
		 
		 pathOf3.put(testmap.searchNodeByID(0),testmap.searchNodeByID(1));
		 pathOf3.put(testmap.searchNodeByID(1),testmap.searchNodeByID(2));
		 pathOf3.put(testmap.searchNodeByID(2),testmap.searchNodeByID(3));
		 pathOf3.put(testmap.searchNodeByID(3),null);
		 pathOf3.put(testmap.searchNodeByID(4),testmap.searchNodeByID(3));
		 pathOf3.put(testmap.searchNodeByID(5),testmap.searchNodeByID(6));
		 pathOf3.put(testmap.searchNodeByID(6),testmap.searchNodeByID(2));

		 pathOf2.put(testmap.searchNodeByID(0),testmap.searchNodeByID(1));
		 pathOf2.put(testmap.searchNodeByID(1),testmap.searchNodeByID(2));
		 pathOf2.put(testmap.searchNodeByID(2),null);
		 pathOf2.put(testmap.searchNodeByID(3),testmap.searchNodeByID(2));
		 pathOf2.put(testmap.searchNodeByID(4),testmap.searchNodeByID(3));
		 pathOf2.put(testmap.searchNodeByID(5),testmap.searchNodeByID(6));
		 pathOf2.put(testmap.searchNodeByID(6),testmap.searchNodeByID(2));
		 
		 distanceOf1.put(testmap.searchNodeByID(0).getID(),(float) 10);
		 distanceOf1.put(testmap.searchNodeByID(1).getID(),(float) 0);
		 distanceOf1.put(testmap.searchNodeByID(2).getID(),(float) 5);
		 distanceOf1.put(testmap.searchNodeByID(3).getID(),(float) 8);
		 distanceOf1.put(testmap.searchNodeByID(4).getID(),(float) 15);
		 distanceOf1.put(testmap.searchNodeByID(5).getID(),(float) 10);
		 distanceOf1.put(testmap.searchNodeByID(6).getID(),(float) 10);
		 
		 distanceOf6.put(testmap.searchNodeByID(0).getID(),(float) 19);
		 distanceOf6.put(testmap.searchNodeByID(1).getID(),(float) 10);
		 distanceOf6.put(testmap.searchNodeByID(2).getID(),(float) 15);
		 distanceOf6.put(testmap.searchNodeByID(3).getID(),(float) 18);
		 distanceOf6.put(testmap.searchNodeByID(4).getID(),(float) 25);
		 distanceOf6.put(testmap.searchNodeByID(5).getID(),(float) 11);
		 distanceOf6.put(testmap.searchNodeByID(6).getID(),(float) 0);

		 distanceOf4.put(testmap.searchNodeByID(0).getID(),(float) 24);
		 distanceOf4.put(testmap.searchNodeByID(1).getID(),(float) 15);
		 distanceOf4.put(testmap.searchNodeByID(2).getID(),(float) 20);
		 distanceOf4.put(testmap.searchNodeByID(3).getID(),(float) 23);
		 distanceOf4.put(testmap.searchNodeByID(4).getID(),(float) 0);
		 distanceOf4.put(testmap.searchNodeByID(5).getID(),(float) 16);
		 distanceOf4.put(testmap.searchNodeByID(6).getID(),(float) 5);
		 
		 distanceOf3.put(testmap.searchNodeByID(0).getID(),(float) 18);
		 distanceOf3.put(testmap.searchNodeByID(1).getID(),(float) 8);
		 distanceOf3.put(testmap.searchNodeByID(2).getID(),(float) 1);
		 distanceOf3.put(testmap.searchNodeByID(3).getID(),(float) 0);
		 distanceOf3.put(testmap.searchNodeByID(4).getID(),(float) 7);
		 distanceOf3.put(testmap.searchNodeByID(5).getID(),(float) 17);
		 distanceOf3.put(testmap.searchNodeByID(6).getID(),(float) 6);
		 
		 distanceOf2.put(testmap.searchNodeByID(0).getID(),(float) 17);
		 distanceOf2.put(testmap.searchNodeByID(1).getID(),(float) 7);
		 distanceOf2.put(testmap.searchNodeByID(2).getID(),(float) 0);
		 distanceOf2.put(testmap.searchNodeByID(3).getID(),(float) 3);
		 distanceOf2.put(testmap.searchNodeByID(4).getID(),(float) 10);
		 distanceOf2.put(testmap.searchNodeByID(5).getID(),(float) 16);
		 distanceOf2.put(testmap.searchNodeByID(6).getID(),(float) 5);
		 
		 pathOf.put((long) 1, pathOf1);
		 pathOf.put((long) 6, pathOf6);
		 pathOf.put((long) 4, pathOf4);
		 pathOf.put((long) 3, pathOf3);
		 pathOf.put((long) 2, pathOf2);
		 
		 distanceOf.put((long) 1, distanceOf1);
		 distanceOf.put((long) 6, distanceOf6);
		 distanceOf.put((long) 4, distanceOf4);
		 distanceOf.put((long) 3, distanceOf3);
		 distanceOf.put((long) 2, distanceOf2);
		 
		 tour.generateBestTour();
		 Stop depot=new Stop(testmap.searchNodeByID(1),TypeOfStop.DEPOT,0);
			depot.setDepartureTime(LocalTime.of(8,0,0));
			depot.setArrivalTime(LocalTime.of(8,0,0));
			Stop delivery1=new Stop(testmap.searchNodeByID(6),TypeOfStop.DELIVERY,100);
			Stop pickup1=new Stop(testmap.searchNodeByID(4),TypeOfStop.PICKUP,60);
			Stop pickup2 = new Stop(testmap.searchNodeByID(3),TypeOfStop.DELIVERY,20);
			Stop delivery2 = new Stop(testmap.searchNodeByID(2),TypeOfStop.DELIVERY,240);
			ArrayList<Stop> orderedStops = new ArrayList<Stop>();
			orderedStops.add(depot);
			orderedStops.add(delivery1);
			orderedStops.add(pickup1);
			orderedStops.add(pickup2);
			orderedStops.add(delivery2);
			orderedStops.add(depot);
		 tour.updateTour(orderedStops);

		assertEquals(tour.getPathOf().size(),pathOf.size());
		 assertEquals(tour.getPathOf(),pathOf);
		 assertEquals(tour.getDistanceOf().size(),distanceOf.size());
		 assertEquals(tour.getDistanceOf(),distanceOf);
	}
	@Test
	public void test7() {
		Map.generateMap("../testsXML\\updateTour\\test1.xml");
		Map testmap =  Map.getSingletonMap();
		ListOfRequest requests = new ListOfRequest("../testsXML\\updateTour\\request1.xml");
		requests.importList();
		testmap.setRequests(requests);
		Tour tour = new Tour();
		HashMap<Long, HashMap<Node, Node>> pathOf = new HashMap<Long, HashMap<Node, Node>>();
		HashMap<Long, HashMap<Long, Float>> distanceOf=new HashMap<Long, HashMap<Long, Float>>();
		
		 HashMap<Node, Node> pathOf1 = new HashMap<Node, Node>();
		 HashMap<Node, Node> pathOf6 = new HashMap<Node, Node>();
		 HashMap<Node, Node> pathOf4 = new HashMap<Node, Node>();
		 
		 HashMap<Long, Float> distanceOf1 = new HashMap<Long, Float>();
		 HashMap<Long, Float> distanceOf6 = new HashMap<Long, Float>();
		 HashMap<Long, Float> distanceOf4 = new HashMap<Long, Float>();
		 
		 pathOf1.put(testmap.searchNodeByID(0),testmap.searchNodeByID(1));
		 pathOf1.put(testmap.searchNodeByID(1),null);
		 pathOf1.put(testmap.searchNodeByID(2),testmap.searchNodeByID(1));
		 pathOf1.put(testmap.searchNodeByID(3),testmap.searchNodeByID(2));
		 pathOf1.put(testmap.searchNodeByID(4),testmap.searchNodeByID(3));
		 pathOf1.put(testmap.searchNodeByID(5),testmap.searchNodeByID(1));
		 pathOf1.put(testmap.searchNodeByID(6),testmap.searchNodeByID(2));
		 
		 
		 distanceOf1.put(testmap.searchNodeByID(0).getID(),(float) 10);
		 distanceOf1.put(testmap.searchNodeByID(1).getID(),(float) 0);
		 distanceOf1.put(testmap.searchNodeByID(2).getID(),(float) 5);
		 distanceOf1.put(testmap.searchNodeByID(3).getID(),(float) 8);
		 distanceOf1.put(testmap.searchNodeByID(4).getID(),(float) 15);
		 distanceOf1.put(testmap.searchNodeByID(5).getID(),(float) 10);
		 distanceOf1.put(testmap.searchNodeByID(6).getID(),(float) 10);
		 
		 
		 pathOf.put((long) 1, pathOf1);

		 distanceOf.put((long) 1, distanceOf1);

		 tour.generateBestTour();
		 Stop depot=new Stop(testmap.searchNodeByID(1),TypeOfStop.DEPOT,0);
			depot.setDepartureTime(LocalTime.of(8,0,0));
			depot.setArrivalTime(LocalTime.of(8,0,0));
			Stop delivery1=new Stop(testmap.searchNodeByID(6),TypeOfStop.DELIVERY,100);
			Stop pickup1=new Stop(testmap.searchNodeByID(4),TypeOfStop.PICKUP,60);

			ArrayList<Stop> orderedStops = new ArrayList<Stop>();
			orderedStops.add(depot);
			orderedStops.add(depot);
		 tour.updateTour(orderedStops);
		 
		assertEquals(tour.getPathOf().size(),pathOf.size());
		 assertEquals(tour.getPathOf(),pathOf);
		 assertEquals(tour.getDistanceOf().size(),distanceOf.size());
		 assertEquals(tour.getDistanceOf(),distanceOf);
	}
}
