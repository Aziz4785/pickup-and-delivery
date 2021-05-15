package model;

import java.util.Comparator;

/**
 * A class representing a Segment in the Map graph. The segment is oriented
 *
 */
public class Segment implements Comparator<Segment> {
	/**
	 * The origin and destination of the segment
	 */
	private Node origin, destination;
	/**
	 * The name of the street
	 */
	private String name;
	/**
	 * The length of the street in meters
	 */
	private float distance;

	/**
	 * Constructor
	 * 
	 * @param origin
	 * @param destination
	 * @param name
	 * @param distance
	 */
	public Segment(Node origin, Node destination, String name, float distance) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.name = name;
		this.distance = distance;
	}

	/**
	 * Constructor used by the path calculation. Skips irrelevant informations
	 * 
	 * @param origin
	 * @param distance
	 */
	public Segment(Node origin, float distance) {
		this.origin = origin;
		this.distance = distance;
	}

	/**
	 * Constructor used by the path calculation. Skips irrelevant informations
	 * 
	 * @param origin
	 * @param destination
	 */
	public Segment(Node origin, Node destination) {
		super();
		this.origin = origin;
		this.destination = destination;
	}

	/**
	 * Constructor used by the path calculation. Skips irrelevant informations
	 */
	public Segment() {
	}

	public Node getOrigin() {
		return this.origin;
	}

	public Node getDestination() {
		return this.destination;
	}

	public float getDistance() {
		return this.distance;
	}

	public String getName() {
		return this.name;
	}

	@Override
	/**
	 * compares 2 segments by their length (Segment.distance)
	 */
	public int compare(Segment segment1, Segment segment2) {
		if (segment1.distance < segment2.distance)
			return -1;
		if (segment1.distance > segment2.distance)
			return 1;
		return 0;
	}
}
