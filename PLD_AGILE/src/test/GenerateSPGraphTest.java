package test;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import model.ListOfRequest;
import model.Map;
import model.Node;
import model.Request;
import model.Stop;
import model.Tour;

public class GenerateSPGraphTest {

	@Test
	public void test1() {
		System.out.println("---------test1------");
		Map.generateMap("../testsXML\\testGraphePCC\\test1.xml");
		ListOfRequest requests = new ListOfRequest("../testsXML\\testGraphePCC\\requetes1.xml");
		requests.importList();
		Tour tour = new Tour();
		LocalTime departureFromDepot = LocalTime.parse("08:00:00", 
				DateTimeFormatter.ISO_TIME);
		requests.getDepot().setDepartureTime(departureFromDepot);
		requests.getDepot().setArrivalTime(departureFromDepot);
		System.out.println(requests.getDepot().toString());
		System.out.println(requests.getDeliveries().toString());
		Map map =  Map.getSingletonMap();
		HashMap<Long, Integer> simple_Id_Of = new HashMap<Long, Integer>();
		int number_of_request = requests.getNumberOfRequests();
		int number_of_nodes = (number_of_request*2);
		int[] durationOf = new int[number_of_nodes+1];
		Stop[] real_Id_Of = new Stop[number_of_nodes+1];
		HashMap<Long,HashMap<Node, Node>  > pathOf = new HashMap<Long,HashMap<Node, Node> >();
		double[][] graphe_plus_court_chemins = new double[number_of_nodes+1][number_of_nodes+1];
		tour.generateShortestPathGraph(requests, simple_Id_Of, durationOf, real_Id_Of, graphe_plus_court_chemins, pathOf);
		double[][] expected_graph= {{0,10.0/4.1667,17.0/4.1667,4.0/4.1667,18.0/4.1667},
				{12.0/4.1667+30,0,7.0/4.1667+30,14.0/4.1667+30,8.0/4.1667+30},
				{17.0/4.1667+54,13.0/4.1667+54,0,7.0/4.1667+54,1.0/4.1667+54},
				{10.0/4.1667+6,6.0/4.1667+6,13.0/4.1667+6,0,14.0/4.1667+6},
				{16.0/4.1667+42,12.0/4.1667+42,3.0/4.1667+42,6.0/4.1667+42,0}};

		System.out.println("expected : ");
		for(int i=0;i<expected_graph.length;i++)
		{
			for(int j=0;j<expected_graph.length;j++)
				System.out.print(expected_graph[i][j]+" ");
			System.out.println();
		}
		assertArrayEquals(expected_graph,graphe_plus_court_chemins);
	}
	@Test
	public void test2() {
		System.out.println("---------test2------");
		Map.generateMap("../testsXML\\testGraphePCC\\test1.xml");
		ListOfRequest requests = new ListOfRequest("../testsXML\\testGraphePCC\\requetes2.xml");
		requests.importList();
		Tour tour = new Tour();
		LocalTime departureFromDepot = LocalTime.parse("08:00:00", 
				DateTimeFormatter.ISO_TIME);
		requests.getDepot().setDepartureTime(departureFromDepot);
		requests.getDepot().setArrivalTime(departureFromDepot);
		System.out.println(requests.getDepot().toString());
		System.out.println(requests.getDeliveries().toString());
		Map map =  Map.getSingletonMap();
		HashMap<Long, Integer> simple_Id_Of = new HashMap<Long, Integer>();
		int number_of_request = requests.getNumberOfRequests();
		int number_of_nodes = (number_of_request*2);
		int[] durationOf = new int[number_of_nodes+1];
		Stop[] real_Id_Of = new Stop[number_of_nodes+1];
		HashMap<Long,HashMap<Node, Node>  > pathOf = new HashMap<Long,HashMap<Node, Node> >();
		double[][] graphe_plus_court_chemins = new double[number_of_nodes+1][number_of_nodes+1];
		tour.generateShortestPathGraph(requests, simple_Id_Of, durationOf, real_Id_Of, graphe_plus_court_chemins, pathOf);

		System.out.println("result : ");
		for(int i=0;i<graphe_plus_court_chemins.length;i++)
		{
			for(int j=0;j<graphe_plus_court_chemins.length;j++)
				System.out.print(graphe_plus_court_chemins[i][j]+" ");
			System.out.println();
		}
		double[][] expected_graph= {{0,12.0/4.1667,3.0/4.1667,3.0/4.1667,21.0/4.1667},
				{8.0/4.1667+30,0,7.0/4.1667+30,7.0/4.1667+30,17.0/4.1667+30},
				{1.0/4.1667+54,13.0/4.1667+54,0,0/4.1667+54,22.0/4.1667+54},
				{1.0/4.1667+6,13.0/4.1667+6,0.0/4.1667+6,0,22.0/4.1667+6},
				{16.0/4.1667+42,8.0/4.1667+42,15.0/4.1667+42,15.0/4.1667+42,0}};

		System.out.println("expected : ");
		for(int i=0;i<expected_graph.length;i++)
		{
			for(int j=0;j<expected_graph.length;j++)
				System.out.print(expected_graph[i][j]+" ");
			System.out.println();
		}
		assertArrayEquals(expected_graph,graphe_plus_court_chemins);
	}
	
	@Test
	public void test3() {
		System.out.println("---------test3------");
		Map.generateMap("../testsXML\\testGraphePCC\\test3.xml");
		ListOfRequest requests = new ListOfRequest("../testsXML\\testGraphePCC\\requetes3.xml");
		requests.importList();
		Tour tour = new Tour();
		LocalTime departureFromDepot = LocalTime.parse("08:00:00", 
				DateTimeFormatter.ISO_TIME);
		requests.getDepot().setDepartureTime(departureFromDepot);
		requests.getDepot().setArrivalTime(departureFromDepot);
		System.out.println(requests.getDepot().toString());
		System.out.println(requests.getDeliveries().toString());
		Map map =  Map.getSingletonMap();
		HashMap<Long, Integer> simple_Id_Of = new HashMap<Long, Integer>();
		int number_of_request = requests.getNumberOfRequests();
		int number_of_nodes = (number_of_request*2);
		int[] durationOf = new int[number_of_nodes+1];
		Stop[] real_Id_Of = new Stop[number_of_nodes+1];
		HashMap<Long,HashMap<Node, Node>  > pathOf = new HashMap<Long,HashMap<Node, Node> >();
		double[][] graphe_plus_court_chemins = new double[number_of_nodes+1][number_of_nodes+1];
		tour.generateShortestPathGraph(requests, simple_Id_Of, durationOf, real_Id_Of, graphe_plus_court_chemins, pathOf);

		System.out.println("result : ");
		for(int i=0;i<graphe_plus_court_chemins.length;i++)
		{
			for(int j=0;j<graphe_plus_court_chemins.length;j++)
				System.out.print(graphe_plus_court_chemins[i][j]+" ");
			System.out.println();
		}
		double[][] expected_graph= {{0,41.0/4.1667,10.0/4.1667},
				{18.0/4.1667+12,0,28.0/4.1667+12},
				{23.0/4.1667+8,31.0/4.1667+8,0}};

		System.out.println("expected : ");
		for(int i=0;i<expected_graph.length;i++)
		{
			for(int j=0;j<expected_graph.length;j++)
				System.out.print(expected_graph[i][j]+" ");
			System.out.println();
		}
		assertArrayEquals(expected_graph,graphe_plus_court_chemins);
	}
	
	@Test
	public void test4() {
		System.out.println("---------test4------");
		Map.generateMap("../testsXML\\testGraphePCC\\test4.xml");
		ListOfRequest requests = new ListOfRequest("../testsXML\\testGraphePCC\\requetes4.xml");
		requests.importList();
		Tour tour = new Tour();
		LocalTime departureFromDepot = LocalTime.parse("08:00:00", 
				DateTimeFormatter.ISO_TIME);
		requests.getDepot().setDepartureTime(departureFromDepot);
		requests.getDepot().setArrivalTime(departureFromDepot);
		System.out.println(requests.getDepot().toString());
		System.out.println(requests.getDeliveries().toString());
		Map map =  Map.getSingletonMap();
		HashMap<Long, Integer> simple_Id_Of = new HashMap<Long, Integer>();
		int number_of_request = requests.getNumberOfRequests();
		int number_of_nodes = (number_of_request*2);
		int[] durationOf = new int[number_of_nodes+1];
		Stop[] real_Id_Of = new Stop[number_of_nodes+1];
		HashMap<Long,HashMap<Node, Node>  > pathOf = new HashMap<Long,HashMap<Node, Node> >();
		double[][] graphe_plus_court_chemins = new double[number_of_nodes+1][number_of_nodes+1];
		tour.generateShortestPathGraph(requests, simple_Id_Of, durationOf, real_Id_Of, graphe_plus_court_chemins, pathOf);

		System.out.println("result : ");
		for(int i=0;i<graphe_plus_court_chemins.length;i++)
		{
			for(int j=0;j<graphe_plus_court_chemins.length;j++)
				System.out.print(graphe_plus_court_chemins[i][j]+" ");
			System.out.println();
		}
		double[][] expected_graph= {{0,135.0/4.1667,142.0/4.1667},
				{69.0/4.1667+12,0,62.0/4.1667+12},
				{267.0/4.1667+8,198.0/4.1667+8,0}};

		System.out.println("expected : ");
		for(int i=0;i<expected_graph.length;i++)
		{
			for(int j=0;j<expected_graph.length;j++)
				System.out.print(expected_graph[i][j]+" ");
			System.out.println();
		}
		assertArrayEquals(expected_graph,graphe_plus_court_chemins);
	}
	
	@Test
	public void test5() {
		System.out.println("---------test5------");
		Map.generateMap("../testsXML\\testGraphePCC\\test4.xml");
		ListOfRequest requests = new ListOfRequest("../testsXML\\testGraphePCC\\requetes5.xml");
		requests.importList();
		Tour tour = new Tour();
		LocalTime departureFromDepot = LocalTime.parse("08:00:00", 
				DateTimeFormatter.ISO_TIME);
		requests.getDepot().setDepartureTime(departureFromDepot);
		requests.getDepot().setArrivalTime(departureFromDepot);
		System.out.println(requests.getDepot().toString());
		System.out.println(requests.getDeliveries().toString());
		Map map =  Map.getSingletonMap();
		HashMap<Long, Integer> simple_Id_Of = new HashMap<Long, Integer>();
		int number_of_request = requests.getNumberOfRequests();
		int number_of_nodes = (number_of_request*2);
		int[] durationOf = new int[number_of_nodes+1];
		Stop[] real_Id_Of = new Stop[number_of_nodes+1];
		HashMap<Long,HashMap<Node, Node>  > pathOf = new HashMap<Long,HashMap<Node, Node> >();
		double[][] graphe_plus_court_chemins = new double[number_of_nodes+1][number_of_nodes+1];
		tour.generateShortestPathGraph(requests, simple_Id_Of, durationOf, real_Id_Of, graphe_plus_court_chemins, pathOf);

		System.out.println("result : ");
		for(int i=0;i<graphe_plus_court_chemins.length;i++)
		{
			for(int j=0;j<graphe_plus_court_chemins.length;j++)
				System.out.print(graphe_plus_court_chemins[i][j]+" ");
			System.out.println();
		}
		double[][] expected_graph= {{0,230.0/4.1667,60.0/4.1667,146.0/4.1667,267.0/4.1667},
				{108.0/4.1667+20,0,53.0/4.1667+20,139.0/4.1667+20,75.0/4.1667+20},
				{55.0/4.1667+8,170.0/4.1667+8,0,86.0/4.1667+8,207.0/4.1667+8},
				{59.0/4.1667+12,84.0/4.1667+12,59.0/4.1667+12,0,121.0/4.1667+12},
				{142.0/4.1667+8,167.0/4.1667+8,142.0/4.1667+8,83.0/4.1667+8,0}};

		System.out.println("expected : ");
		for(int i=0;i<expected_graph.length;i++)
		{
			for(int j=0;j<expected_graph.length;j++)
				System.out.print(expected_graph[i][j]+" ");
			System.out.println();
		}
		assertArrayEquals(expected_graph,graphe_plus_court_chemins);
	}

	
	@Test
	public void test6() {
		System.out.println("---------test6------");
		Map.generateMap("../testsXML\\testGraphePCC\\test6.xml");
		ListOfRequest requests = new ListOfRequest("../testsXML\\testGraphePCC\\requetes6.xml");
		requests.importList();
		Tour tour = new Tour();
		LocalTime departureFromDepot = LocalTime.parse("08:00:00", 
				DateTimeFormatter.ISO_TIME);
		requests.getDepot().setDepartureTime(departureFromDepot);
		requests.getDepot().setArrivalTime(departureFromDepot);
		System.out.println(requests.getDepot().toString());
		System.out.println(requests.getDeliveries().toString());
		Map map =  Map.getSingletonMap();
		HashMap<Long, Integer> simple_Id_Of = new HashMap<Long, Integer>();
		int number_of_request = requests.getNumberOfRequests();
		int number_of_nodes = (number_of_request*2);
		int[] durationOf = new int[number_of_nodes+1];
		Stop[] real_Id_Of = new Stop[number_of_nodes+1];
		HashMap<Long,HashMap<Node, Node>  > pathOf = new HashMap<Long,HashMap<Node, Node> >();
		double[][] graphe_plus_court_chemins = new double[number_of_nodes+1][number_of_nodes+1];
		tour.generateShortestPathGraph(requests, simple_Id_Of, durationOf, real_Id_Of, graphe_plus_court_chemins, pathOf);

		System.out.println("result : ");
		for(int i=0;i<graphe_plus_court_chemins.length;i++)
		{
			for(int j=0;j<graphe_plus_court_chemins.length;j++)
				System.out.print(graphe_plus_court_chemins[i][j]+" ");
			System.out.println();
		}
		double[][] expected_graph= {{0, 222.0/4.1667, 213.0/4.1667, 131.0/4.1667,220.0/4.1667,164.0/4.1667,141.0/4.1667},
				{51.0/4.1667+20,0,128.0/4.1667+20,46.0/4.1667+20,135.0/4.1667+20,79.0/4.1667+20,120.0/4.1667+20},
				{6.0/4.1667+8,9.0/4.1667+8,0, 55.0/4.1667+8, 144.0/4.1667+8,88.0/4.1667+8,129.0/4.1667+8},
				{88.0/4.1667+12,91.0/4.1667+12,82.0/4.1667+12,0,89.0/4.1667+12,61.0/4.1667+12,170.0/4.1667+12},
				{94.0/4.1667+8,97.0/4.1667+8,88.0/4.1667+8,90.0/4.1667+8,0,151.0/4.1667+8,217.0/4.1667+8},
				{69.0/4.1667+12,72.0/4.1667+12,63.0/4.1667+12,82.0/4.1667+12,76.0/4.1667+12,0,109.0/4.1667+12},
				{84.0/4.1667+8,141.0/4.1667+8,132.0/4.1667+8,50.0/4.1667+8,139.0/4.1667+8,83.0/4.1667+8,0}};

		System.out.println("expected : ");
		for(int i=0;i<expected_graph.length;i++)
		{
			for(int j=0;j<expected_graph.length;j++)
				System.out.print(expected_graph[i][j]+" ");
			System.out.println();
		}
		assertArrayEquals(expected_graph,graphe_plus_court_chemins);
	}

	
	@Test
	public void test8() {
		System.out.println("---------test8------");
		Map.generateMap("../testsXML\\testGraphePCC\\test6.xml");
		ListOfRequest requests = new ListOfRequest("../testsXML\\testGraphePCC\\requetes8.xml");
		requests.importList();
		Tour tour = new Tour();
		LocalTime departureFromDepot = LocalTime.parse("08:00:00", 
				DateTimeFormatter.ISO_TIME);
		requests.getDepot().setDepartureTime(departureFromDepot);
		requests.getDepot().setArrivalTime(departureFromDepot);
		System.out.println(requests.getDepot().toString());
		System.out.println(requests.getDeliveries().toString());
		Map map =  Map.getSingletonMap();
		HashMap<Long, Integer> simple_Id_Of = new HashMap<Long, Integer>();
		int number_of_request = requests.getNumberOfRequests();
		int number_of_nodes = (number_of_request*2);
		int[] durationOf = new int[number_of_nodes+1];
		Stop[] real_Id_Of = new Stop[number_of_nodes+1];
		HashMap<Long,HashMap<Node, Node>  > pathOf = new HashMap<Long,HashMap<Node, Node> >();
		double[][] graphe_plus_court_chemins = new double[number_of_nodes+1][number_of_nodes+1];
		tour.generateShortestPathGraph(requests, simple_Id_Of, durationOf, real_Id_Of, graphe_plus_court_chemins, pathOf);

		System.out.println("result : ");
		for(int i=0;i<graphe_plus_court_chemins.length;i++)
		{
			for(int j=0;j<graphe_plus_court_chemins.length;j++)
				System.out.print(graphe_plus_court_chemins[i][j]+" ");
			System.out.println();
		}
		double[][] expected_graph= {{0,222.0/4.1667,213.0/4.1667,131.0/4.1667,220.0/4.1667,164.0/4.1667,222.0/4.1667},
				{51.0/4.1667+20,0,128.0/4.1667+20,46.0/4.1667+20,135.0/4.1667+20,79.0/4.1667+20,20},
				{6.0/4.1667+8,9.0/4.1667+8,0,55.0/4.1667+8,144.0/4.1667+8,88.0/4.1667+8,9.0/4.1667+8},
				{88.0/4.1667+12,91.0/4.1667+12,82.0/4.1667+12,0,89.0/4.1667+12,61.0/4.1667+12,91.0/4.1667+12},
				{94.0/4.1667+8,97.0/4.1667+8,88.0/4.1667+8,90.0/4.1667+8,0,151.0/4.1667+8,97.0/4.1667+8},
				{69.0/4.1667+12,72.0/4.1667+12,63.0/4.1667+12,82.0/4.1667+12,76.0/4.1667+12,0,72.0/4.1667+12},
				{51.0/4.1667+8,8,128.0/4.1667+8,46.0/4.1667+8,135.0/4.1667+8,79.0/4.1667+8,0}};

		System.out.println("expected : ");
		for(int i=0;i<expected_graph.length;i++)
		{
			for(int j=0;j<expected_graph.length;j++)
				System.out.print(expected_graph[i][j]+" ");
			System.out.println();
		}
		assertArrayEquals(expected_graph,graphe_plus_court_chemins);
	}

	
	@Test
	public void test9() {
		System.out.println("---------test9------");
		Map.generateMap("../testsXML\\testGraphePCC\\test4.xml");
		ListOfRequest requests = new ListOfRequest("../testsXML\\testGraphePCC\\requetes9.xml");
		requests.importList();
		Tour tour = new Tour();
		LocalTime departureFromDepot = LocalTime.parse("08:00:00", 
				DateTimeFormatter.ISO_TIME);
		requests.getDepot().setDepartureTime(departureFromDepot);
		requests.getDepot().setArrivalTime(departureFromDepot);
		System.out.println(requests.getDepot().toString());
		System.out.println(requests.getDeliveries().toString());
		Map map =  Map.getSingletonMap();
		HashMap<Long, Integer> simple_Id_Of = new HashMap<Long, Integer>();
		int number_of_request = requests.getNumberOfRequests();
		int number_of_nodes = (number_of_request*2);
		int[] durationOf = new int[number_of_nodes+1];
		Stop[] real_Id_Of = new Stop[number_of_nodes+1];
		HashMap<Long,HashMap<Node, Node>  > pathOf = new HashMap<Long,HashMap<Node, Node> >();
		double[][] graphe_plus_court_chemins = new double[number_of_nodes+1][number_of_nodes+1];
		tour.generateShortestPathGraph(requests, simple_Id_Of, durationOf, real_Id_Of, graphe_plus_court_chemins, pathOf);

		System.out.println("result : ");
		for(int i=0;i<graphe_plus_court_chemins.length;i++)
		{
			for(int j=0;j<graphe_plus_court_chemins.length;j++)
				System.out.print(graphe_plus_court_chemins[i][j]+" ");
			System.out.println();
		}
		double[][] expected_graph= {{0, 230.0/4.1667, 60.0/4.1667, 230.0/4.1667, 267.0/4.1667},
				{108.0/4.1667+20, 0, 53.0/4.1667+20, 20, 75.0/4.1667+20},
				{55.0/4.1667+8, 170.0/4.1667+8, 0, 170.0/4.1667+8, 207.0/4.1667+8},
				{108.0/4.1667+12, 12, 53.0/4.1667+12, 0, 75.0/4.1667+12},
				{142.0/4.1667+8, 167.0/4.1667+8, 142.0/4.1667+8, 167.0/4.1667+8, 0}};

		System.out.println("expected : ");
		for(int i=0;i<expected_graph.length;i++)
		{
			for(int j=0;j<expected_graph.length;j++)
				System.out.print(expected_graph[i][j]+" ");
			System.out.println();
		}
		assertArrayEquals(expected_graph,graphe_plus_court_chemins);
	}
	
	@Test
	public void test11() {
		System.out.println("---------test11------");
		Map.generateMap("../testsXML\\testGraphePCC\\test4.xml");
		ListOfRequest requests = new ListOfRequest("../testsXML\\testGraphePCC\\requetes11.xml");
		requests.importList();
		Tour tour = new Tour();
		LocalTime departureFromDepot = LocalTime.parse("08:00:00", 
				DateTimeFormatter.ISO_TIME);
		requests.getDepot().setDepartureTime(departureFromDepot);
		requests.getDepot().setArrivalTime(departureFromDepot);
		System.out.println(requests.getDepot().toString());
		System.out.println(requests.getDeliveries().toString());
		Map map =  Map.getSingletonMap();
		HashMap<Long, Integer> simple_Id_Of = new HashMap<Long, Integer>();
		int number_of_request = requests.getNumberOfRequests();
		int number_of_nodes = (number_of_request*2);
		int[] durationOf = new int[number_of_nodes+1];
		Stop[] real_Id_Of = new Stop[number_of_nodes+1];
		HashMap<Long,HashMap<Node, Node>  > pathOf = new HashMap<Long,HashMap<Node, Node> >();
		double[][] graphe_plus_court_chemins = new double[number_of_nodes+1][number_of_nodes+1];
		tour.generateShortestPathGraph(requests, simple_Id_Of, durationOf, real_Id_Of, graphe_plus_court_chemins, pathOf);

		System.out.println("result : ");
		for(int i=0;i<graphe_plus_court_chemins.length;i++)
		{
			for(int j=0;j<graphe_plus_court_chemins.length;j++)
				System.out.print(graphe_plus_court_chemins[i][j]+" ");
			System.out.println();
		}
		double[][] expected_graph= {{0, 230.0/4.1667, 60.0/4.1667, 230.0/4.1667, 60.0/4.1667},
				{108.0/4.1667+20, 0, 53.0/4.1667+20, 20, 53.0/4.1667+20},
				{55.0/4.1667+8, 170.0/4.1667+8, 0, 170.0/4.1667+8, 8},
				{108.0/4.1667+12, 12, 53.0/4.1667+12, 0, 53.0/4.1667+12},
				{55.0/4.1667+8, 170.0/4.1667+8, 8, 170.0/4.1667+8, 0}};

		System.out.println("expected : ");
		for(int i=0;i<expected_graph.length;i++)
		{
			for(int j=0;j<expected_graph.length;j++)
				System.out.print(expected_graph[i][j]+" ");
			System.out.println();
		}
		assertArrayEquals(expected_graph,graphe_plus_court_chemins);
	}
	
	@Test
	public void test12() {
		System.out.println("---------test12------");
		Map.generateMap("../testsXML\\testGraphePCC\\test6.xml");
		ListOfRequest requests = new ListOfRequest("../testsXML\\testGraphePCC\\requetes12.xml");
		requests.importList();
		Tour tour = new Tour();
		LocalTime departureFromDepot = LocalTime.parse("08:00:00", 
				DateTimeFormatter.ISO_TIME);
		requests.getDepot().setDepartureTime(departureFromDepot);
		requests.getDepot().setArrivalTime(departureFromDepot);
		System.out.println(requests.getDepot().toString());
		System.out.println(requests.getDeliveries().toString());
		Map map =  Map.getSingletonMap();
		HashMap<Long, Integer> simple_Id_Of = new HashMap<Long, Integer>();
		int number_of_request = requests.getNumberOfRequests();
		int number_of_nodes = (number_of_request*2);
		int[] durationOf = new int[number_of_nodes+1];
		Stop[] real_Id_Of = new Stop[number_of_nodes+1];
		HashMap<Long,HashMap<Node, Node>  > pathOf = new HashMap<Long,HashMap<Node, Node> >();
		double[][] graphe_plus_court_chemins = new double[number_of_nodes+1][number_of_nodes+1];
		tour.generateShortestPathGraph(requests, simple_Id_Of, durationOf, real_Id_Of, graphe_plus_court_chemins, pathOf);

		System.out.println("result : ");
		for(int i=0;i<graphe_plus_court_chemins.length;i++)
		{
			for(int j=0;j<graphe_plus_court_chemins.length;j++)
				System.out.print(graphe_plus_court_chemins[i][j]+" ");
			System.out.println();
		}
		double[][] expected_graph= {{0, 0, 213.0/4.1667, 213.0/4.1667, 220.0/4.1667, 164.0/4.1667, 141.0/4.1667},
				{20, 0, 213.0/4.1667+20, 213.0/4.1667+20, 220.0/4.1667+20,  164.0/4.1667+20, 141.0/4.1667+20},
				{6.0/4.1667+8, 6.0/4.1667+8, 0, 8, 144.0/4.1667+8, 88.0/4.1667+8, 129.0/4.1667+8},
				{6.0/4.1667+12, 6.0/4.1667+12, 12, 0 , 144.0/4.1667+12, 88.0/4.1667+12, 129.0/4.1667+12},
				{94.0/4.1667+8, 94.0/4.1667+8, 88.0/4.1667+8, 88.0/4.1667+8,0,151.0/4.1667+8,217.0/4.1667+8},
				{69.0/4.1667+12,  69.0/4.1667+12, 63.0/4.1667+12, 63.0/4.1667+12,76.0/4.1667+12,0,109.0/4.1667+12},
				{84.0/4.1667+8,  84.0/4.1667+8, 132.0/4.1667+8, 132.0/4.1667+8, 139.0/4.1667+8,83.0/4.1667+8,0}};

		System.out.println("expected : ");
		for(int i=0;i<expected_graph.length;i++)
		{
			for(int j=0;j<expected_graph.length;j++)
				System.out.print(expected_graph[i][j]+" ");
			System.out.println();
		}
		assertArrayEquals(expected_graph,graphe_plus_court_chemins);
	}
	
	@Test
	public void test13() {
		System.out.println("---------test13------");
		Map.generateMap("../testsXML\\testGraphePCC\\test4.xml");
		ListOfRequest requests = new ListOfRequest("../testsXML\\testGraphePCC\\requetes13.xml");
		requests.importList();
		Tour tour = new Tour();
		LocalTime departureFromDepot = LocalTime.parse("08:00:00", 
				DateTimeFormatter.ISO_TIME);
		requests.getDepot().setDepartureTime(departureFromDepot);
		requests.getDepot().setArrivalTime(departureFromDepot);
		System.out.println(requests.getDepot().toString());
		System.out.println(requests.getDeliveries().toString());
		Map map =  Map.getSingletonMap();
		HashMap<Long, Integer> simple_Id_Of = new HashMap<Long, Integer>();
		int number_of_request = requests.getNumberOfRequests();
		int number_of_nodes = (number_of_request*2);
		int[] durationOf = new int[number_of_nodes+1];
		Stop[] real_Id_Of = new Stop[number_of_nodes+1];
		HashMap<Long,HashMap<Node, Node>  > pathOf = new HashMap<Long,HashMap<Node, Node> >();
		double[][] graphe_plus_court_chemins = new double[number_of_nodes+1][number_of_nodes+1];
		tour.generateShortestPathGraph(requests, simple_Id_Of, durationOf, real_Id_Of, graphe_plus_court_chemins, pathOf);

		System.out.println("result : ");
		for(int i=0;i<graphe_plus_court_chemins.length;i++)
		{
			for(int j=0;j<graphe_plus_court_chemins.length;j++)
				System.out.print(graphe_plus_court_chemins[i][j]+" ");
			System.out.println();
		}
		double[][] expected_graph= {{0, 0, 142.0/4.1667, 83.0/4.1667, 0},
				{20, 0, 142.0/4.1667+20, 83.0/4.1667+20, 20},
				{207.0/4.1667+8, 207.0/4.1667+8, 0,86.0/4.1667+8,207.0/4.1667+8},
				{121.0/4.1667+12, 121.0/4.1667+12,59.0/4.1667+12,0,121.0/4.1667+12},
				{8, 8, 142.0/4.1667+8, 83.0/4.1667+8, 0}};

		System.out.println("expected : ");
		for(int i=0;i<expected_graph.length;i++)
		{
			for(int j=0;j<expected_graph.length;j++)
				System.out.print(expected_graph[i][j]+" ");
			System.out.println();
		}
		assertArrayEquals(expected_graph,graphe_plus_court_chemins);
	}
	
	@Test
	public void test14() {
		System.out.println("---------test14------");
		Map.generateMap("../testsXML\\testGraphePCC\\test6.xml");
		ListOfRequest requests = new ListOfRequest("../testsXML\\testGraphePCC\\requetes14.xml");
		requests.importList();
		Tour tour = new Tour();
		LocalTime departureFromDepot = LocalTime.parse("08:00:00", 
				DateTimeFormatter.ISO_TIME);
		requests.getDepot().setDepartureTime(departureFromDepot);
		requests.getDepot().setArrivalTime(departureFromDepot);
		System.out.println(requests.getDepot().toString());
		System.out.println(requests.getDeliveries().toString());
		Map map =  Map.getSingletonMap();
		HashMap<Long, Integer> simple_Id_Of = new HashMap<Long, Integer>();
		int number_of_request = requests.getNumberOfRequests();
		int number_of_nodes = (number_of_request*2);
		int[] durationOf = new int[number_of_nodes+1];
		Stop[] real_Id_Of = new Stop[number_of_nodes+1];
		HashMap<Long,HashMap<Node, Node>  > pathOf = new HashMap<Long,HashMap<Node, Node> >();
		double[][] graphe_plus_court_chemins = new double[number_of_nodes+1][number_of_nodes+1];
		tour.generateShortestPathGraph(requests, simple_Id_Of, durationOf, real_Id_Of, graphe_plus_court_chemins, pathOf);

		System.out.println("result : ");
		for(int i=0;i<graphe_plus_court_chemins.length;i++)
		{
			for(int j=0;j<graphe_plus_court_chemins.length;j++)
				System.out.print(graphe_plus_court_chemins[i][j]+" ");
			System.out.println();
		}

		double[][] expected_graph= {{0, 222.0/4.1667, 0,  222.0/4.1667, 0,  222.0/4.1667 ,0},
				{51.0/4.1667+20, 0, 51.0/4.1667+20, 20, 51.0/4.1667+20, 20, 51.0/4.1667+20},
				{8,222.0/4.1667+8, 0, 222.0/4.1667+8, 8, 222.0/4.1667+8, 8},
				{51.0/4.1667+12, 12, 51.0/4.1667+12, 0,51.0/4.1667+12, 12, 51.0/4.1667+12},
				{8, 222.0/4.1667+8, 8, 222.0/4.1667+8, 0, 222.0/4.1667+8, 8},
				{51.0/4.1667+12, 12, 51.0/4.1667+12, 12, 51.0/4.1667+12, 0, 51.0/4.1667+12},
				{8, 222.0/4.1667+8, 8, 222.0/4.1667+8 ,8, 222.0/4.1667+8, 0}};


		System.out.println("expected : ");
		for(int i=0;i<expected_graph.length;i++)
		{
			for(int j=0;j<expected_graph.length;j++)
				System.out.print(expected_graph[i][j]+" ");
			System.out.println();
		}
		assertArrayEquals(expected_graph,graphe_plus_court_chemins);
	}
	
	@Test
	public void test15() {
		System.out.println("---------test15------");
		Map.generateMap("../testsXML\\testGraphePCC\\test6.xml");
		ListOfRequest requests = new ListOfRequest("../testsXML\\testGraphePCC\\requetes15.xml");
		requests.importList();
		Tour tour = new Tour();
		LocalTime departureFromDepot = LocalTime.parse("08:00:00", 
				DateTimeFormatter.ISO_TIME);
		requests.getDepot().setDepartureTime(departureFromDepot);
		requests.getDepot().setArrivalTime(departureFromDepot);
		System.out.println(requests.getDepot().toString());
		System.out.println(requests.getDeliveries().toString());
		Map map =  Map.getSingletonMap();
		HashMap<Long, Integer> simple_Id_Of = new HashMap<Long, Integer>();
		int number_of_request = requests.getNumberOfRequests();
		int number_of_nodes = (number_of_request*2);
		int[] durationOf = new int[number_of_nodes+1];
		Stop[] real_Id_Of = new Stop[number_of_nodes+1];
		HashMap<Long,HashMap<Node, Node>  > pathOf = new HashMap<Long,HashMap<Node, Node> >();
		double[][] graphe_plus_court_chemins = new double[number_of_nodes+1][number_of_nodes+1];
		tour.generateShortestPathGraph(requests, simple_Id_Of, durationOf, real_Id_Of, graphe_plus_court_chemins, pathOf);

		System.out.println("result : ");
		for(int i=0;i<graphe_plus_court_chemins.length;i++)
		{
			for(int j=0;j<graphe_plus_court_chemins.length;j++)
				System.out.print(graphe_plus_court_chemins[i][j]+" ");
			System.out.println();
		}

		double[][] expected_graph= {{0}};


		System.out.println("expected : ");
		for(int i=0;i<expected_graph.length;i++)
		{
			for(int j=0;j<expected_graph.length;j++)
				System.out.print(expected_graph[i][j]+" ");
			System.out.println();
		}
		assertArrayEquals(expected_graph,graphe_plus_court_chemins);
	}
	
	@Test
	public void test16() {
		System.out.println("---------test16------");
		Map.generateMap("../testsXML\\testGraphePCC\\test6.xml");
		//ListOfRequest requests = new ListOfRequest("C:\\Users\\kanoun\\Documents\\testGrahePCC\\requetes16.xml");
		ArrayList<Request> emptyrequests = new ArrayList<Request>();
		ListOfRequest requests = new ListOfRequest(null, null, emptyrequests);
		//requests.importList();
		Tour tour = new Tour();
		Map map =  Map.getSingletonMap();
		HashMap<Long, Integer> simple_Id_Of = new HashMap<Long, Integer>();
		int number_of_request = requests.getNumberOfRequests();
		int number_of_nodes = (number_of_request*2);
		int[] durationOf = new int[number_of_nodes+1];
		Stop[] real_Id_Of = new Stop[number_of_nodes+1];
		HashMap<Long,HashMap<Node, Node>  > pathOf = new HashMap<Long,HashMap<Node, Node> >();
		double[][] graphe_plus_court_chemins = new double[number_of_nodes+1][number_of_nodes+1];
		boolean result = tour.generateShortestPathGraph(requests, simple_Id_Of, durationOf, real_Id_Of, graphe_plus_court_chemins, pathOf);
		System.out.println("result : ");
		System.out.println(result);

		double[][] expected_graph= {{0}};
		assertFalse(result);
	}
	

}
