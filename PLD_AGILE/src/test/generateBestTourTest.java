package test;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalTime;
import java.util.ArrayList;


import model.ListOfRequest;
import model.Map;
import model.Segment;
import model.Tour;

public class generateBestTourTest {
	@Test
	public void test() {
		Map.generateMap("../testsXML\\generateBP\\map1.xml");
		Map testmap = Map.getSingletonMap();
		ListOfRequest requests = new ListOfRequest("../testsXML\\generateBP\\request1.xml");
		requests.importList();
		testmap.setRequests(requests);
		Tour tour = new Tour();
		tour.generateBestTour();
		ArrayList<Segment> expectedbestPath = new ArrayList<Segment>();
		expectedbestPath.add(new Segment(testmap.searchNodeByID(1),testmap.searchNodeByID(3)));
		expectedbestPath.add(new Segment(testmap.searchNodeByID(3),testmap.searchNodeByID(4)));
		expectedbestPath.add(new Segment(testmap.searchNodeByID(4),testmap.searchNodeByID(5)));
		expectedbestPath.add(new Segment(testmap.searchNodeByID(5),testmap.searchNodeByID(4)));
		expectedbestPath.add(new Segment(testmap.searchNodeByID(4),testmap.searchNodeByID(1)));
		assertEquals(expectedbestPath.size(),tour.getBestPath().size());
		for(int i = 0;i<tour.getBestPath().size();i++)
		{
			assertTrue(tour.getBestPath().get(i).getOrigin()==expectedbestPath.get(i).getOrigin());
			assertTrue(tour.getBestPath().get(i).getDestination()==expectedbestPath.get(i).getDestination());
		}
		//assertTrue(expectedbestPath.equals(tour.getBestPath()));
		//solution : 0 ->1 - >2
		
	}
	@Test
	public void test2() {
		Map.generateMap("../testsXML\\generateBP\\map1.xml");
		Map testmap = Map.getSingletonMap();
		ListOfRequest requests = new ListOfRequest("../testsXML\\generateBP\\request2.xml");
		requests.importList();
		testmap.setRequests(requests);
		Tour tour = new Tour();
		tour.generateBestTour();
		ArrayList<Segment> expectedbestPath = new ArrayList<Segment>();
		expectedbestPath.add(new Segment(testmap.searchNodeByID(4),testmap.searchNodeByID(1)));
		expectedbestPath.add(new Segment(testmap.searchNodeByID(1),testmap.searchNodeByID(3)));
		expectedbestPath.add(new Segment(testmap.searchNodeByID(3),testmap.searchNodeByID(1)));
		expectedbestPath.add(new Segment(testmap.searchNodeByID(1),testmap.searchNodeByID(2)));
		expectedbestPath.add(new Segment(testmap.searchNodeByID(2),testmap.searchNodeByID(0)));
		expectedbestPath.add(new Segment(testmap.searchNodeByID(0),testmap.searchNodeByID(4)));
		assertEquals(expectedbestPath.size(),tour.getBestPath().size());
		for(int i = 0;i<tour.getBestPath().size();i++)
		{
			assertTrue(tour.getBestPath().get(i).getOrigin()==expectedbestPath.get(i).getOrigin());
			assertTrue(tour.getBestPath().get(i).getDestination()==expectedbestPath.get(i).getDestination());
		}
		//assertTrue(expectedbestPath.equals(tour.getBestPath()));
		//result : 0 1 2 3 4
		
	}
	@Test
	public void test3() {
		Map.generateMap("../testsXML\\generateBP\\map1.xml");
		Map testmap = Map.getSingletonMap();
		ListOfRequest requests = new ListOfRequest("../testsXML\\generateBP\\request3.xml");
		requests.importList();
		testmap.setRequests(requests);
		Tour tour = new Tour();
		tour.generateBestTour();
		ArrayList<Segment> expectedbestPath = new ArrayList<Segment>();
		expectedbestPath.add(new Segment(testmap.searchNodeByID(4),testmap.searchNodeByID(1)));
		expectedbestPath.add(new Segment(testmap.searchNodeByID(1),testmap.searchNodeByID(3)));
		expectedbestPath.add(new Segment(testmap.searchNodeByID(3),testmap.searchNodeByID(1)));
		expectedbestPath.add(new Segment(testmap.searchNodeByID(1),testmap.searchNodeByID(2)));
		expectedbestPath.add(new Segment(testmap.searchNodeByID(2),testmap.searchNodeByID(0)));
		expectedbestPath.add(new Segment(testmap.searchNodeByID(0),testmap.searchNodeByID(4)));
		assertEquals(expectedbestPath.size(),tour.getBestPath().size());
		for(int i = 0;i<tour.getBestPath().size();i++)
		{
			assertTrue(tour.getBestPath().get(i).getOrigin()==expectedbestPath.get(i).getOrigin());
			assertTrue(tour.getBestPath().get(i).getDestination()==expectedbestPath.get(i).getDestination());
		}
		LocalTime[] expectedarrivaltimes = {LocalTime.of(8,17,40),LocalTime.of(8,1,32),LocalTime.of(8,3,12),LocalTime.of(8,8,34),LocalTime.of(8,11,39)};
		LocalTime[] expecteddeparturtimes= {LocalTime.of(8,0,0),LocalTime.of(8,3,12),LocalTime.of(8,6,32),LocalTime.of(8,10,34),LocalTime.of(8,16,39)};
		for(int i=0;i<expectedarrivaltimes.length;i++)
		{
			assertEquals(expectedarrivaltimes[i],testmap.getRequests().getOrderedStops().get(i).getArrivalTime());
			assertEquals(expecteddeparturtimes[i],testmap.getRequests().getOrderedStops().get(i).getDepartureTime());
		}
	}
	@Test
	public void test4() {
		Map.generateMap("../testsXML\\generateBP\\map4.xml");
		Map testmap = Map.getSingletonMap();
		ListOfRequest requests = new ListOfRequest("../testsXML\\generateBP\\request4.xml");
		requests.importList();
		testmap.setRequests(requests);
		Tour tour = new Tour();
		tour.generateBestTour();
		ArrayList<Segment> expectedbestPath = new ArrayList<Segment>();
		expectedbestPath.add(new Segment(testmap.searchNodeByID(3),testmap.searchNodeByID(1)));
		expectedbestPath.add(new Segment(testmap.searchNodeByID(1),testmap.searchNodeByID(0)));
		expectedbestPath.add(new Segment(testmap.searchNodeByID(0),testmap.searchNodeByID(2)));
		expectedbestPath.add(new Segment(testmap.searchNodeByID(2),testmap.searchNodeByID(4)));
		expectedbestPath.add(new Segment(testmap.searchNodeByID(4),testmap.searchNodeByID(6)));
		expectedbestPath.add(new Segment(testmap.searchNodeByID(6),testmap.searchNodeByID(5)));
		expectedbestPath.add(new Segment(testmap.searchNodeByID(5),testmap.searchNodeByID(3)));
		assertEquals(expectedbestPath.size(),tour.getBestPath().size());
		for(int i = 0;i<tour.getBestPath().size();i++)
		{
			assertTrue(tour.getBestPath().get(i).getOrigin()==expectedbestPath.get(i).getOrigin());
			assertTrue(tour.getBestPath().get(i).getDestination()==expectedbestPath.get(i).getDestination());
		}
		LocalTime[] expectedarrivaltimes = {LocalTime.of(8,9,46),LocalTime.of(8,0,0),LocalTime.of(8,3,18),LocalTime.of(8,6,13),LocalTime.of(8,7,44)};
		LocalTime[] expecteddeparturtimes= {LocalTime.of(8,0,0),LocalTime.of(8,2,0),LocalTime.of(8,4,18),LocalTime.of(8,6,43),LocalTime.of(8,8,14)};
		for(int i=0;i<expectedarrivaltimes.length;i++)
		{
			assertEquals(expectedarrivaltimes[i],testmap.getRequests().getOrderedStops().get(i).getArrivalTime());
			assertEquals(expecteddeparturtimes[i],testmap.getRequests().getOrderedStops().get(i).getDepartureTime());
		}
		//assertTrue(expectedbestPath.equals(tour.getBestPath()));
		//result : 0 1 2 3 4
		
	}
	@Test
	public void test11() {
		Map.generateMap("../testsXML\\generateBP\\map4.xml");
		Map testmap = Map.getSingletonMap();
		ListOfRequest requests = new ListOfRequest("../testsXML\\generateBP\\request11.xml");
		requests.importList();
		testmap.setRequests(requests);
		Tour tour = new Tour();
		tour.generateBestTour();
		ArrayList<Segment> expectedbestPath = new ArrayList<Segment>();
		assertEquals(expectedbestPath.size(),tour.getBestPath().size());
		assertEquals(0,tour.getBestPath().size());
		LocalTime[] expectedarrivaltimes = {LocalTime.of(8,0,0),LocalTime.of(8,0,0)};
		LocalTime[] expecteddeparturtimes= {LocalTime.of(8,0,0),LocalTime.of(8,0,0)};
		for(int i=0;i<expectedarrivaltimes.length;i++)
		{
			assertEquals(expectedarrivaltimes[i],testmap.getRequests().getOrderedStops().get(i).getArrivalTime());
			assertEquals(expecteddeparturtimes[i],testmap.getRequests().getOrderedStops().get(i).getDepartureTime());
		}
	}
	
}
