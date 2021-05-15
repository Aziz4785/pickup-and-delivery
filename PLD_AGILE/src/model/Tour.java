package model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import controller.Command;

/**
 * 
 * A class representing the tour followed by the delivery man
 *
 */
public class Tour {
	/**
	 * The path corresponding to the tour
	 */
	private ArrayList<Segment> bestPath;
	/**
	 * The Commands operated on this Tour
	 */
	private ArrayList<Command> commands;
	/**
	 * The undone Commands on this Tour
	 */
	private ArrayList<Command> undoneCommands;
	/**
	 * The list of best paths from every node to any other node
	 */
	private HashMap<Long, HashMap<Node, Node>> pathOf;
	/**
	 * The list of distances from every node to any other node
	 */
	private HashMap<Long, HashMap<Long, Float>> distanceOf;

	public ArrayList<Command> getModifications() {
		return commands;
	}

	public void setModifications(ArrayList<Command> commands) {
		this.commands = commands;
	}

	public ArrayList<Command> getUndoneModifications() {
		return undoneCommands;
	}

	public void setUndoneModifications(ArrayList<Command> commands) {
		this.undoneCommands = commands;
	}

	/**
	 * The main constructor
	 */
	public Tour() {
		bestPath = new ArrayList<Segment>();
		commands = new ArrayList<Command>();
		undoneCommands = new ArrayList<Command>();
		distanceOf = new HashMap<Long, HashMap<Long, Float>>();
	}

	public ArrayList<Segment> getBestPath() {
		return this.bestPath;
	}

	public Tour(ArrayList<Segment> path) {
		super();
		this.bestPath = path;
		this.commands = new ArrayList<Command>();
		distanceOf = new HashMap<Long, HashMap<Long, Float>>();
	}

	/**
	 * generate a complete directed graph where each vertex represents a "Stop" Node
	 * (delivery , pikup or depot) and each edge (i,j) represents the total duration
	 * of the path between the node i and j + the duration of Node i
	 * 
	 * @param requests                  : list of requests
	 * @param simple_Id_Of              : associative hashtable between NodeId and
	 *                                  its simpler version
	 * @param durationOf                :
	 * @param real_Id_Of                : associative hashtable between NodeId and
	 *                                  its simpler version
	 * @param shortestPathGraph : the array where the generated graph will
	 *                                  be stored
	 * @param pathOf
	 * @return true if the operation was successfull , else false
	 */
	public boolean generateShortestPathGraph(ListOfRequest requests, HashMap<Long, Integer> simple_Id_Of,
			int[] durationOf, Stop[] real_Id_Of, double[][] shortestPathGraph,			
			HashMap<Long, HashMap<Node, Node>> pathOf) {
		Map map = Map.getSingletonMap();
		double speed = 4.1667;
		int number_of_request = requests.getNumberOfRequests();
		int number_of_nodes = (number_of_request * 2);
		if (requests == null || requests.getDepot() == null || requests.getDepot().getNode() == null)
			return false;
		simple_Id_Of.put(requests.getDepot().getNode().getID(), 0);
		durationOf[0] = 0;
		real_Id_Of[0] = requests.getDepot();
		int i = 1;
		Request request;
		for (int requestindex = 0; requestindex < number_of_request; requestindex++) {
			request = requests.getDeliveries().get(requestindex);

			simple_Id_Of.put(request.getPickup().getNode().getID(), i);
			durationOf[i] = request.getPickup().getDuration();
			real_Id_Of[i] = request.getPickup();
			i++;
			simple_Id_Of.put(request.getDelivery().getNode().getID(), i);
			durationOf[i] = request.getDelivery().getDuration();
			real_Id_Of[i] = request.getDelivery();
			i++;
		}
		HashMap<Long, Float> distances = new HashMap<Long, Float>(map.getNodes().size());
		HashMap<Node, Node> path =new HashMap<Node, Node>(map.getNodes().size());
		dijkstra(requests.getDepot(),distances,path); //reinitialiser dans dijstra distance et path
		pathOf.put(requests.getDepot().getNode().getID(),path);
		for(int line = 0; line < number_of_nodes + 1 ; line++)
		{
			distances = new HashMap<Long, Float>(map.getNodes().size());
			path =new HashMap<Node, Node>(map.getNodes().size());
			dijkstra(real_Id_Of[line],distances,path);
			pathOf.put(real_Id_Of[line].getNode().getID(),path);
			distanceOf.put(real_Id_Of[line].getNode().getID(),distances);
			for(int col=0;col<number_of_nodes+1;col++)
			{
				if(col != line)
					shortestPathGraph[line][col]=distances.get(real_Id_Of[col].getNode().getID())/speed + durationOf[line];
				else
					shortestPathGraph[line][col]=0;
			}
		}
		return true;
	}

	/**
	 * converts the given path starting from origin to dest into an ArrayList of
	 * Segment
	 * 
	 * @param origin : starting Node
	 * @param dest   : desired destination Node
	 * @param :path  path from origin
	 * @return an ordered list of Segment
	 */
	public ArrayList<Segment> convertToSegmentList(Node origin, Node dest, HashMap<Node, Node> path) {
		if (path == null || path.size() <= 1 || !path.containsKey(dest) || !path.containsKey(origin))
			return null;
		Node selectedNode = dest;
		ArrayList<Segment> shortestPath = new ArrayList<Segment>();
		Segment segment;
		while (path.get(selectedNode) != null) {
			segment = new Segment(path.get(selectedNode), selectedNode);// origin dest
			shortestPath.add(0, segment);
			selectedNode = path.get(selectedNode);

		}
		return shortestPath;
	}

	public void generateBestTour() {
		ListOfRequest requests = Map.getSingletonMap().getRequests();
		LocalTime departureFromDepot = LocalTime.parse("08:00:00", DateTimeFormatter.ISO_TIME);
		requests.getDepot().setDepartureTime(departureFromDepot);
		requests.getDepot().setArrivalTime(departureFromDepot);
		HashMap<Long, Integer> simple_Id_Of = new HashMap<Long, Integer>();
		int number_of_request = requests.getNumberOfRequests();
		int number_of_nodes = (number_of_request * 2);
		int[] durationOf = new int[number_of_nodes + 1];
		Stop[] real_Id_Of = new Stop[number_of_nodes + 1];
		pathOf = new HashMap<Long, HashMap<Node, Node>>();
		double[][] shortestPathGraph = new double[number_of_nodes + 1][number_of_nodes + 1];
		generateShortestPathGraph(requests, simple_Id_Of, durationOf, real_Id_Of, shortestPathGraph, pathOf);
		int[] tspSolution = antColonyOpt(0.8, 1 , 5 ,2000,(int)(number_of_request*1.6+1) ,shortestPathGraph,real_Id_Of);
		for(int i=0;i<tspSolution.length-1;i++)
		{
			Node node1 = real_Id_Of[tspSolution[i]].getNode();
			Node node2 = real_Id_Of[tspSolution[i + 1]].getNode();
			ArrayList<Segment> path_From_i = convertToSegmentList(node1, node2, pathOf.get(node1.getID()));
			bestPath.addAll(path_From_i);
		}
		updateStopTimes(requests.getOrderedStops());
	}

	/**
	 * finds shortest paths from source to all other nodes in the graph, producing a
	 * shortest-path tree.
	 * 
	 * @param start     : the source node
	 * @param distances
	 * @param path      : the shortest-path tree.
	 */
	public void dijkstra(Stop start, HashMap<Long, Float> distances, HashMap<Node, Node> path) {
		Map map = Map.getSingletonMap();
		for (Node node : map.getNodes()) {
			distances.put(node.getID(), Float.MAX_VALUE);
			path.put(node, null);
		}
		PriorityQueue<Segment> priorityQ = new PriorityQueue<Segment>(map.getNodes().size(), new Segment());
		Set<Long> blackNodes = new HashSet<Long>();
		priorityQ.add(new Segment(start.getNode(), 0)); 
		distances.put(start.getNode().getID(), (float) 0);
		while (priorityQ.size() > 0) {
			Node min_node = priorityQ.remove().getOrigin();
			if (blackNodes.contains(min_node.getID())) {
				continue;
			}
			blackNodes.add(min_node.getID());
			for (Segment neighbour : min_node.getNeighbours()) {
				if (!blackNodes.contains(neighbour.getDestination().getID())) {
					if (distances.get(neighbour.getDestination().getID()) >= (distances.get(min_node.getID())
							+ neighbour.getDistance())) {
						distances.put(neighbour.getDestination().getID(),
								distances.get(min_node.getID()) + neighbour.getDistance());
						path.put(neighbour.getDestination(), min_node);
					}
					priorityQ.add(
							new Segment(neighbour.getDestination(), distances.get(neighbour.getDestination().getID())));
				}
			}
		}
	}

	/**
	 * method used for the ACO algorithm : generates an array containing random
	 * NodeIDs
	 * 
	 * @param numberOfAnts
	 * @param ants
	 * @param graphe
	 */
	private void generateRandomAnts(int numberOfAnts, int[] ants, double[][] graphe) {
		int i = 0;
		int random = 0;
		ants[i] = random;
		i = 1;
		while (i < numberOfAnts) { 
			//random = (int) (Math.random() * ((graphe.length - 3) / 2 + 1));
			//random = random * 2 + 1;
			random = i;
			ants[i] = random;
			i++;
		}

	}

	/**
	 * fills an array with a given number
	 * 
	 * @param tab
	 * @param nbr
	 */
	private void fill(double[][] tab, int nbr) {
		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab.length; j++)
				tab[i][j] = nbr;
		}
	}

	/**
	 * method used for the ACO algorithm : given a starting node , this method
	 * calculates the next node to visit , using probability formulas
	 * 
	 * @param startingNode
	 * @param visited           : array representing the visited nodes
	 * @param pheromones
	 * @param currentNode
	 * @param graphe
	 * @param alpha             : parameter of ACO
	 * @param beta:             parameter of ACO
	 * @param totalVisitedNodes
	 * @param nbrSingleDelivery
	 * @return the ID of the next Node to visit
	 */
	private int calculateNextNode(int startingNode, boolean[] visited, double[][] pheromones, int currentNode,
			double[][] graphe, int alpha, int beta, int totalVisitedNodes, int nbrSingleDelivery) {
		int nextNode = -1;
		double[] proba = new double[graphe.length];
		double[] cumul = new double[graphe.length + 1];
		int numberOfAvailable = 0;
		Arrays.fill(cumul, 0);
		boolean[] available = new boolean[graphe.length];
		Arrays.fill(available, false);
		double denominatorProba = 0;

		for (int node = 0; node < graphe.length; node++) 
		{

			if ((node != currentNode || graphe.length == 1) && visited[0]) {

				if ((node % 2 != 0 && !visited[node])
						|| (node % 2 == 0 && node != 0 && visited[node - 1] && !visited[node])
						|| (visited[node] && node == startingNode && totalVisitedNodes == graphe.length)) {
					available[node] = true;
					denominatorProba+=(Math.pow(pheromones[currentNode][node], alpha) * Math.pow(1.0/(graphe[currentNode][node]+1), beta)); 
					numberOfAvailable++;
				}
			}
			else if (node != currentNode && !visited[0]) {

				if ((node != 0 && !visited[node] && node % 2 != 0 && !visited[node + 1])
						|| (node % 2 == 0 && node != 0 && !visited[node])
						|| (node == 0 && nbrSingleDelivery == 0 && currentNode % 2 == 0)
						|| (visited[node] && node == startingNode && totalVisitedNodes == graphe.length)) {
					available[node] = true;
					denominatorProba+=(Math.pow(pheromones[currentNode][node], alpha) * Math.pow(1.0/(graphe[currentNode][node]+1), beta)); 
					numberOfAvailable++;
				}
			}
		}
		double somme = 0;
		for (int node = graphe.length - 1; node >= 0; node--) {
			if (available[node]) {
				if (denominatorProba == 0)
					proba[node] = 1.0 / numberOfAvailable;
				else
					proba[node] = (Math.pow(pheromones[currentNode][node], alpha)
							* Math.pow(1.0 / (graphe[currentNode][node] + 1), beta)) / (denominatorProba);
			} else
				proba[node] = 0;

			somme += proba[node];
			cumul[node] += somme;
		}
		cumul[cumul.length - 1] = 0; 
		double random = Math.random();
		for (int i = 0; i < cumul.length - 1; i++) {
			if (random <= cumul[i] && random > cumul[i + 1]) {
				nextNode = i;
			}
		}

		return nextNode;
	}

	private double edge_exist(int k, int[][] paths, int i, int j) {
		if (paths[k][i] == j)
			return 1.0;
		return 0.0;
	}

	/**
	 * copy source content to dest
	 * 
	 * @param dest
	 * @param source
	 */
	private void copy(int[] dest, int[] source) {
		int j = 0;
		int i = 0;
		dest[j] = i;
		j++;
		while (j < dest.length) {
			dest[j] = source[i];
			i = source[i];
			j++;
		}
	}

	/**
	 * ACO algorithm : probabilistic algorithm for solving the travelling salesman
	 * problem
	 * 
	 * @param ro                        : the pheromone evaporation coefficient
	 * @param alpha                     : parameter to control the influence of
	 *                                  pheromones
	 * @param beta                      : a parameter to control the influence of
	 *                                  edges length
	 * @param maxIteration              : maximum number of allowed iteration
	 * @param numberOfAnts
	 * @param shortestPathGraph : shortestPathGraph
	 * @param real_Id_Of
	 * @return an array containing the ordered nodes to follow
	 */
	public int[] antColonyOpt(double ro, int alpha, int beta, int maxIteration, int numberOfAnts,

			double[][] shortestPathGraph, Stop[] real_Id_Of) {
		int numberOfNodes = shortestPathGraph.length;
		int[] besttour = new int[numberOfNodes + 1];
		int[] ants = new int[numberOfAnts];
		double[][] pheromones = new double[numberOfNodes][numberOfNodes];
		fill(pheromones, 1000);
		int currentNode = -1;
		int nextNode = -1;
		double[] totalPathLength = new double[numberOfAnts];
		Arrays.fill(totalPathLength, 0);
		int[][] paths = new int[numberOfAnts][numberOfNodes];
		double min_tour = 9999999;
		double somme;
		boolean[] visited;
		int totalVisitedNodes = 1;
		int ant = 0;
		for (int iteration = 0; iteration < maxIteration; iteration++) {
			generateRandomAnts(numberOfAnts, ants, shortestPathGraph);
			for (int antIndex = 0; antIndex < ants.length; antIndex++) {

				totalVisitedNodes = 1;
				ant = ants[antIndex];
				visited = new boolean[numberOfNodes];
				Arrays.fill(visited, false);
				currentNode = ant;
				nextNode = -1;
				totalPathLength[antIndex] = 0;
				int nbrSingleDelivery = 0;
				while (nextNode != ant) {
					visited[currentNode] = true;
					if (currentNode != 0 && currentNode % 2 != 0 && !visited[currentNode + 1])
						nbrSingleDelivery++;
					if (currentNode != 0 && currentNode % 2 == 0 && visited[currentNode - 1])
						nbrSingleDelivery--;

					nextNode = calculateNextNode(ant, visited, pheromones, currentNode, shortestPathGraph,
							alpha, beta, totalVisitedNodes, nbrSingleDelivery);
					totalVisitedNodes++;
					paths[antIndex][currentNode] = nextNode;
					if (currentNode == -1 || nextNode == -1)
						System.out.println("current node : " + currentNode + " nextNode = " + nextNode);
					totalPathLength[antIndex] += shortestPathGraph[currentNode][nextNode];
					currentNode = nextNode;
				}
				if (totalPathLength[antIndex] < min_tour) {
					copy(besttour, paths[antIndex]);
					min_tour = totalPathLength[antIndex];
				}

			}
			double totallength=0;
			for(int i = 0;i<pheromones.length;i++)
			{
				for(int j = 0 ;j<pheromones[i].length;j++)
				{
					if(i!=j)
					{
						somme=0;
						for(int antIndex = 0 ; antIndex <ants.length;antIndex++)
						{
							if(totalPathLength[antIndex]==0)
								totallength=99999999.9;
							else
								totallength=1/totalPathLength[antIndex];
							somme+=(totallength * edge_exist(antIndex,paths,i,j));
						}
						pheromones[i][j]=(ro*pheromones[i][j]+somme);

					}

				}
			}

		}
		updateOrderedStops(besttour, real_Id_Of);
		System.out.println("total distance : " + min_tour);
		return besttour;
	}

	private void updateOrderedStops(int[] besttour, Stop[] real_Id_Of) {
		Map map = Map.getSingletonMap();
		if (map != null) {
			ListOfRequest requests = map.getRequests();
			ArrayList<Stop> ordered = new ArrayList<Stop>();
			for (int i = 0; i < besttour.length; i++) {
				ordered.add(real_Id_Of[besttour[i]]);
			}
			requests.setOrderedStops(ordered);
		}

	}

	public void updateStopTimes(ArrayList<Stop> orderedstops)
	{

		for(int i=1;i< orderedstops.size();i++)
		{
			orderedstops.get(i).setArrivalTime(orderedstops.get(i-1).getDepartureTime().plusSeconds( (long) (distanceOf.get(orderedstops.get(i-1).getNode().getID()).get(orderedstops.get(i).getNode().getID())-0)));//.truncatedTo(ChronoUnit.MINUTES));
			if(orderedstops.get(i).getType() != TypeOfStop.DEPOT)
				orderedstops.get(i).setDepartureTime(orderedstops.get(i).getArrivalTime().plusSeconds(orderedstops.get(i).getDuration()));

		}
	}
	/**
	 * updates the arrival and departure hours of each stop in orderedstops
	 * @param orderedstops :  the updated stops list
	 * @return the best path that goes through all the stops in orderedstops
	 */
	public ArrayList<Segment> updateTour(ArrayList<Stop> orderedstops) {
		updatePathOf(orderedstops);
		ArrayList<Segment> finalResult = new ArrayList<Segment>();
		for (int i = 0; i < orderedstops.size() - 1; i++) {
			finalResult.addAll(convertToSegmentList(orderedstops.get(i).getNode(),
					orderedstops.get((i + 1) % orderedstops.size()).getNode(),
					pathOf.get(orderedstops.get(i).getNode().getID())));
		}
		bestPath=finalResult;
		bestPath = finalResult;
		updateStopTimes(orderedstops);
		return finalResult;
	}

	private void updatePathOf(ArrayList<Stop> orderedstops) {
		pathOf.clear();
		distanceOf.clear();
		HashMap<Node, Node> path;
		HashMap<Long, Float> distances;
		for (int i = 0; i < orderedstops.size(); i++) {
			distances = new HashMap<Long, Float>();
			path = new HashMap<Node, Node>();
			dijkstra(orderedstops.get(i), distances, path);
			pathOf.put(orderedstops.get(i).getNode().getID(), path);
			distanceOf.put(orderedstops.get(i).getNode().getID(), distances);
		}
	}

	public HashMap<Long, HashMap<Node, Node>> getPathOf() {
		return pathOf;
	}

	public void setPathOf(HashMap<Long, HashMap<Node, Node>> pathOf) {
		this.pathOf = pathOf;
	}

	public HashMap<Long, HashMap<Long, Float>> getDistanceOf() {
		return distanceOf;
	}

	public void setDistanceOf(HashMap<Long, HashMap<Long, Float>> distanceOf) {
		this.distanceOf = distanceOf;
	}

}
