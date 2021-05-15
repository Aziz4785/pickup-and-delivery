package model;

import java.util.ArrayList;
import java.util.Collections;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

/**
 * 
 * Singleton class Represents the Map, including its nodes, segments, current
 * ListOfRequests and current Tour. A map is modelized by a mathematical graph
 *
 */
public class Map {

	// object part
	/**
	 * The graph nodes, ordered by ID
	 */
	private ArrayList<Node> nodes;
	/**
	 * The graph segments
	 */
	// private ArrayList<Segment> segments;
	/**
	 * The location of the file containing the Map data
	 */
	private String mapPath = null;
	/**
	 * The current list of requests of the Map
	 */
	private ListOfRequest requests = null;
	/**
	 * The current tour on the map.
	 */
	private Tour tour = null;
	/**
	 * The bounds of the map
	 */
	private double maxLatitude, maxLongitude, minLatitude, minLongitude;

	/**
	 * Constructor
	 * 
	 * @param XMLFilePath the path of the XML file containing the Map data
	 */
	private Map(String XMLFilePath) {
		this.mapPath = XMLFilePath;
	}

	private void calculateExtremeLongitudeAndLatitude() {
		this.maxLatitude = 0;
		this.minLatitude = Long.MAX_VALUE;
		this.maxLongitude = 0;
		this.minLongitude = Long.MAX_VALUE;
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getLatitude() > this.maxLatitude)
				this.maxLatitude = nodes.get(i).getLatitude();
			if (nodes.get(i).getLatitude() < this.minLatitude)
				this.minLatitude = nodes.get(i).getLatitude();
			if (nodes.get(i).getLongitude() > this.maxLongitude)
				this.maxLongitude = nodes.get(i).getLongitude();
			if (nodes.get(i).getLongitude() < this.minLongitude)
				this.minLongitude = nodes.get(i).getLongitude();
		}
	}

	private Map(ArrayList<Node> nodes, ArrayList<Segment> segments, double maxLatitude, double maxLongitude,
			double minLatitude, double minLongitude) {
		this.nodes = nodes;
		// this.segments = segments;
		this.maxLatitude = maxLatitude;
		this.maxLongitude = maxLongitude;
		this.minLatitude = minLatitude;
		this.minLongitude = minLongitude;
	}

	private void addNode(Node n) {
		this.nodes.add(n);
	}

	private void addSegment(Segment s) {
		// this.segments.add(s);
	}

	private void sortNodes() {

		Collections.sort(nodes, new NodeComparator());

	}

	/**
	 * Dichotomically searches a node by its ID
	 * 
	 * @param ID the ID of the searched Node
	 * @return the found Node
	 */
	public Node searchNodeByID(long ID) {
		// dichotomic search

		int maxIndex = nodes.size() - 1;
		int minIndex = 0;

		while (maxIndex - minIndex > 1) {
			int tmpLimit = (int) ((maxIndex + minIndex) / 2);

			if (this.nodes.get(tmpLimit).getID() == ID) {
				return this.nodes.get(tmpLimit);
			} else if (this.nodes.get(minIndex).getID() == ID) {
				return this.nodes.get(minIndex);
			} else if (this.nodes.get(maxIndex).getID() == ID) {
				return this.nodes.get(maxIndex);
			}

			else if (this.nodes.get(tmpLimit).getID() < ID) {
				minIndex = tmpLimit;
			} else if (this.nodes.get(tmpLimit).getID() > ID) {
				maxIndex = tmpLimit;
			}
		}

		// corresponding node not found !
		return null;

	}

	public double getMaxLatitude() {
		return this.maxLatitude;
	}

	public double getMaxLongitude() {
		return this.maxLongitude;
	}

	public double getMinLatitude() {
		return this.minLatitude;
	}

	public double getMinLongitude() {
		return this.minLongitude;
	}

	// public ArrayList<Segment> getSegments(){
	// return this.segments;
	// }

	public void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}

	/**
	 * imports the map using the filepath given in constructor
	 * 
	 * @return true if the import was successful
	 */
	private boolean importMap() {
		try {
			File xmlFile = new File(Map.singletonMap.mapPath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);

			// optionnel mais reccommandé ?
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("intersection");
			NodeList segmentList = doc.getElementsByTagName("segment");

			if (nodeList.getLength() == 0 || segmentList.getLength() == 0) {
				return false;
			}

			nodes = new ArrayList<Node>();
			// segments = new ArrayList<Segment>();

			for (int i = 0; i < nodeList.getLength(); i++) {
				org.w3c.dom.Node w3cNode = nodeList.item(i);
				Element e = (Element) w3cNode;
				model.Node modelNode = new model.Node(Double.parseDouble(e.getAttribute("latitude")),
						Double.parseDouble(e.getAttribute("longitude")), Long.parseLong(e.getAttribute("id")));
				this.addNode(modelNode);

			}

			sortNodes();

			for (int i = 0; i < segmentList.getLength(); i++) {
				org.w3c.dom.Node w3cNode = segmentList.item(i);
				Element e = (Element) w3cNode;
				model.Node n1 = searchNodeByID(Long.parseLong(e.getAttribute("origin")));
				model.Node n2 = searchNodeByID(Long.parseLong(e.getAttribute("destination")));

				Segment segment = new Segment(n1, n2, e.getAttribute("name"),
						Float.parseFloat(e.getAttribute("length")));
				n1.addNeighbour(segment);
				this.addSegment(segment);
			}
			calculateExtremeLongitudeAndLatitude();
			return true;

		} catch (Exception e) {
			System.out.println("ERROR : " + e.getMessage());
			return false;
		}
	}

	public void setRequests(ListOfRequest newRequests) {
		requests = newRequests;
	}

	public ListOfRequest getRequests() {
		return requests;
	}

	public void setTour(Tour newTour) {
		tour = newTour;
	}

	public Tour getTour() {
		return tour;
	}

	public String getMapPath() {
		return mapPath;
	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}
	
	/**
	 * Check if the requests loaded can fit in the currently displayed map
	 * 
	 * @return true if all request of the deliveries fit in the currently loaded map false if not
	 */
	public boolean checkRequestValidity() {
		ArrayList<Stop> stops = requests.getAllDeliveriesNodes();
		for(int i = 0; i < stops.size(); i++) {
			Node current = stops.get(i).getNode();
			
			if(current == null || current.getLatitude() < minLatitude || current.getLatitude() > maxLatitude || current.getLongitude() < minLongitude || current.getLongitude() > maxLatitude) {
				//Node doesn't fit on the current map
				return false;
			}			
		}
		return true;
	}
	
	
	
	
	// static part

	/**
	 * the unique instance of the Map class
	 * 
	 */
	private static Map singletonMap = null;

	public static boolean generateMap(String XMLFilePath) {
		Map.singletonMap = new Map(XMLFilePath);
		if (Map.singletonMap.importMap()) {
			System.out.println("Imported map with : " + singletonMap.nodes.size() + " nodes");
			return true;
		} else {
			Map.singletonMap.mapPath = null;
			return false;
		}
	}

	/**
	 * 
	 * @return the unique instance of the Map class
	 */
	public static Map getSingletonMap() {
		return Map.singletonMap;
	}

}
