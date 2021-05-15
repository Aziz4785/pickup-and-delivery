package model;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javafx.scene.paint.Color;

/**
 * 
 * A list of Requests. Is imported at the beginning and can be modified with
 * Commands.
 *
 */
public class ListOfRequest {
	private String xmlFilePath;

	/**
	 * the origin and ending of the tour
	 */
	private Stop depot;
	/**
	 * the time if the departure
	 */
	private Date departureTime;
	/**
	 * The unordered list of requests.
	 */
	ArrayList<Request> requests;
	/**
	 * The ordered list of stops that the Tour has to pass through
	 */
	ArrayList<Stop> orderedStops;

	private Color[] colors = { Color.MEDIUMVIOLETRED, Color.BLUE, Color.AQUA, Color.BLUEVIOLET, Color.CHARTREUSE,
			Color.HOTPINK, Color.DARKORANGE, Color.LIGHTGREY, Color.FUCHSIA,
			Color.ALICEBLUE, Color.AQUAMARINE, Color.BISQUE, Color.ANTIQUEWHITE, Color.AZURE, Color.BEIGE, Color.BLANCHEDALMOND,
			Color.DARKORANGE, Color.CYAN, Color.GOLDENROD, Color.KHAKI, Color.LAVENDERBLUSH};

	public ArrayList<Stop> getOrderedStops() {
		return orderedStops;
	}

	public void setOrderedStops(ArrayList<Stop> orderedStops) {
		this.orderedStops = orderedStops;
	}

	/**
	 * Constructor
	 * 
	 * @param xmlFilePath the path of the xml file containing the requests
	 */
	public ListOfRequest(String xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
	}

	public ListOfRequest(Stop depot, Date departureTime, ArrayList<Request> deliveries) {
		super();
		this.depot = depot;
		this.departureTime = departureTime;
		this.requests = deliveries;
	}

	public Stop getDepot() {
		return depot;
	}

	public void setDepot(Stop depot) {
		this.depot = depot;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public ArrayList<Request> getDeliveries() {
		return requests;
	}

	public void addRequest(Request request) {
		requests.add(request);
	}

	public void setDeliveries(ArrayList<Request> deliveries) {
		this.requests = deliveries;
	}

	public int getNumberOfRequests() {
		return requests.size();
	}
	
	public Color[] getColors() {
		return this.colors;
	}

	public ArrayList<Stop> getAllDeliveriesNodes() {
		ArrayList<Stop> allnodes = new ArrayList<Stop>();
		for (Request rqst : requests) {
			allnodes.add(rqst.getPickup());
			allnodes.add(rqst.getDelivery());
		}
		return allnodes;
	}

	/**
	 * Imports the list with the path indicated in the constructor
	 * 
	 * @return true if import was successful
	 */
	public boolean importList() {
		try {
			File xmlFile = new File(xmlFilePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);

			// optionnel mais reccommandé ?
			doc.getDocumentElement().normalize();

			NodeList requestList = doc.getElementsByTagName("request");
			NodeList depot = doc.getElementsByTagName("depot");
			requests = new ArrayList<Request>();

			for (int i = 0; i < requestList.getLength(); i++) {
				org.w3c.dom.Node w3cNode = requestList.item(i);
				Element e = (Element) w3cNode;
				model.Request modelRequest = new model.Request(
						Map.getSingletonMap().searchNodeByID(Long.parseLong(e.getAttribute("pickupAddress"))),
						Integer.parseInt(e.getAttribute("pickupDuration")),
						Map.getSingletonMap().searchNodeByID(Long.parseLong(e.getAttribute("deliveryAddress"))),
						Integer.parseInt(e.getAttribute("deliveryDuration")), colors[i]);
				this.requests.add(modelRequest);
			}

			org.w3c.dom.Node w3cNode = depot.item(0);
			Element e = (Element) w3cNode;
			this.depot = new Stop(Map.getSingletonMap().searchNodeByID(Long.parseLong(e.getAttribute("address"))),
					TypeOfStop.DEPOT, Color.WHITE, 0);
			DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
			this.departureTime = dateFormat.parse(e.getAttribute("departureTime"));
			System.out.println("Imported " + requests.size() + " requests !");
			return true;
		} catch (Exception e) {
			System.out.println("ERROR : " + e.getMessage());
			return false;
		}
	}
}
