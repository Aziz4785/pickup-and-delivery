package model;

import javafx.scene.paint.Color;

/**
 * A request. Has a pickup Stop, a Delivery Stop, and associated durations
 *
 */
public class Request {
	/**
	 * the pickup Stop
	 */
	private Stop pickup;

	/**
	 * the delivery stop
	 * 
	 */
	private Stop delivery;

	private Color colorRequest;

	/**
	 * the constructor
	 * 
	 * @param pickupAddress
	 * @param pickupDuration
	 * @param deliveryAddress
	 * @param deliveryDuration
	 */
	public Request(Node pickupAddress, int pickupDuration, Node deliveryAddress, int deliveryDuration, Color color) {
		super();
		this.pickup = new Stop(pickupAddress, TypeOfStop.PICKUP, color, pickupDuration);
		this.delivery = new Stop(deliveryAddress, TypeOfStop.DELIVERY, color, deliveryDuration);
		this.colorRequest = color;
	}
	
	public Request(Node pickupAddress, int pickupDuration, Node deliveryAddress, int deliveryDuration) {
		super();
		this.pickup = new Stop(pickupAddress, TypeOfStop.PICKUP, pickupDuration);
		this.delivery = new Stop(deliveryAddress, TypeOfStop.DELIVERY, deliveryDuration);
	}

	public Stop getPickup() {
		return pickup;
	}

	public void setPickup(Stop pickup) {
		this.pickup = pickup;
	}

	public Stop getDelivery() {
		return delivery;
	}

	public void setDelivery(Stop delivery) {
		this.delivery = delivery;
	}

	public Color getColorRequest() {
		return this.colorRequest;
	}

	public void setColorRequest(Color colorRequest) {
		this.colorRequest = colorRequest;
		this.delivery.setColor(colorRequest);
		this.pickup.setColor(colorRequest);
	}

}
