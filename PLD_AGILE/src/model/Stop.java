package model;

import java.time.LocalTime;

import javafx.scene.paint.Color;

/**
 * 
 * An enum representing the Type of a stop
 *
 */

/**
 * 
 * A stop in the path.
 *
 */
public class Stop {
	/**
	 * corresponding node
	 */
	private Node node;
	/**
	 * The type of the stop
	 */
	private TypeOfStop type;
	/**
	 * Calculated after generating the tour. The arrival time at this stop
	 */
	private LocalTime arrivalTime;
	/**
	 * Calculated after generating the tour. The departure time from this stop
	 */
	/**
	 * the duration of the stop
	 */
	private int duration;
	private LocalTime departureTime;

	private Color color;

	/**
	 * Constructor
	 * 
	 * @param node
	 * @param type
	 */
	public Stop(Node node, TypeOfStop type, Color color, int duration) {
		this.node = node;
		this.type = type;
		this.color = color;
		this.duration = duration;
	}
	public Stop(Node node, TypeOfStop type, int duration) {
		this.node = node;
		this.type = type;
		this.duration = duration;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime) {
		this.departureTime = departureTime;
	}

	public Node getNode() {
		return node;
	}

	public TypeOfStop getType() {
		return type;
	}

	public String getStringType() {
		return type.toString();
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Color getColor() {
		return this.color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Stop getAssociatedStop(ListOfRequest requests) {
		if (this.type.equals(TypeOfStop.PICKUP)) {
			for (int i = 0; i < requests.getDeliveries().size(); i++) {
				if (requests.getDeliveries().get(i).getPickup().equals(this)) {
					return requests.getDeliveries().get(i).getDelivery();
				}
			}
		} else if (this.type.equals(TypeOfStop.DELIVERY)) {
			for (int i = 0; i < requests.getDeliveries().size(); i++) {
				if (requests.getDeliveries().get(i).getDelivery().equals(this)) {
					return requests.getDeliveries().get(i).getPickup();
				}
			}
		}
		return this;
	}

}
