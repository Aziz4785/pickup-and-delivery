package controller;

import model.Map;
import model.Request;
import view.Window;

/**
 * 
 * Command that corresponds to the addition of a new Request in the
 * ListOfRequest.orderedStop and Map.listOfRequests lists. This command implies
 * to select 2 nodes : 1pickup and 1 delivery
 * 
 */
public class AddNewRequestCommand extends Command {

	private int addedRequestIndex;
	private Request added;
	private Window window;

	/**
	 * 
	 * @param toAdd the Request (pickup+delivery) to add to the list
	 */
	public AddNewRequestCommand(Request toAdd, Window window) {
		this.added = toAdd;
		this.window = window;
	}

	@Override
	public void execute(boolean test) {
		this.added.setColorRequest(Map.getSingletonMap().getRequests().getColors()[Map.getSingletonMap().getRequests().getNumberOfRequests()]);
		Map.getSingletonMap().getRequests().getDeliveries().add(added);
		Map.getSingletonMap().getRequests().getOrderedStops().add(Map.getSingletonMap().getRequests().getOrderedStops().size()-1,added.getPickup());
		Map.getSingletonMap().getRequests().getOrderedStops().add(Map.getSingletonMap().getRequests().getOrderedStops().size()-1,added.getDelivery());
		this.addedRequestIndex = Map.getSingletonMap().getRequests().getDeliveries().size() - 1;
		
		Map.getSingletonMap().getTour().updateTour(Map.getSingletonMap().getRequests().getOrderedStops());

		if(!test) {
			window.eraseRequestsAndTour();
			window.displayListOfRequest(Map.getSingletonMap().getRequests(), Map.getSingletonMap());
			window.displayTour();
			window.displayTextTour();
		}

		addSelfToCommandList();
	}

	@Override
	public void undo(boolean test) {
		// removing request
		Map.getSingletonMap().getRequests().getDeliveries().remove(addedRequestIndex);

		// removing stops
		Map.getSingletonMap().getRequests().getOrderedStops()
				.remove(Map.getSingletonMap().getRequests().getOrderedStops().size() - 2);
		Map.getSingletonMap().getRequests().getOrderedStops()
				.remove(Map.getSingletonMap().getRequests().getOrderedStops().size() - 2);

		Map.getSingletonMap().getTour().updateTour(Map.getSingletonMap().getRequests().getOrderedStops());

		if(!test) {
			window.eraseRequestsAndTour();
			window.displayListOfRequest(Map.getSingletonMap().getRequests(), Map.getSingletonMap());
			window.displayTour();
			window.displayTextTour();
		}
		
		removeSelfFromCommandList();
	}

}
