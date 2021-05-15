package model;

import java.util.ArrayList;

/**
 * a node in the Map graph
 *
 */
public class Node {
	/**
	 * the actual lattitude of the node
	 */
	private double latitude;
	/**
	 * the actual longitude of the node
	 */
	private double longitude;
	/**
	 * The id of the node as given in the file
	 */
	private long ID;
	/**
	 * the segments that has this nose as origin
	 */
	private ArrayList<Segment> neighbours;

	/**
	 * Constructor
	 * 
	 * @param latitude
	 * @param longitude
	 * @param iD        The id of the node as given in the file
	 */
	public Node(double latitude, double longitude, long iD) {
		super();
		neighbours = new ArrayList<Segment>();
		this.latitude = latitude;
		this.longitude = longitude;
		ID = iD;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public long getID() {
		return ID;
	}

	public ArrayList<Segment> getNeighbours() {
		return neighbours;
	}

	public void addNeighbour(Segment s) {
		neighbours.add(s);
	}

	public void setNeighbours(ArrayList<Segment> neighbours) {
		this.neighbours = neighbours;
	}

}
