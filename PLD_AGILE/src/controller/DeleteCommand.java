package controller;

import model.ListOfRequest;
import model.Map;
import model.Request;
import model.Stop;
import view.Window;

/**
 * Command that allows to remove a stop and its corresponding delivery/pickup
 * from the ListOfRequest.orderedStop list
 *
 */
public class DeleteCommand extends Command {

	private Request deleted;
	int indexInListOfRequest;
	int deletedPickupIndex;
	int deletedDeliveryIndex;
	Window window;

	/**
	 * Constructor
	 * 
	 * @param requestIndex the index of the Request to delete in Map.listOfRequest
	 */
	public DeleteCommand(int stopIndex, Window window) {
		Stop deletedStop = Map.getSingletonMap().getRequests().getOrderedStops().get(stopIndex);
		// Searching for the corresponding request
		ListOfRequest requests = Map.getSingletonMap().getRequests();
		for (int i = 0; i < requests.getNumberOfRequests(); i++) {
			if (requests.getDeliveries().get(i).getDelivery().equals(deletedStop)
					|| requests.getDeliveries().get(i).getPickup().equals(deletedStop)) {
				this.deleted = requests.getDeliveries().get(i);
				this.indexInListOfRequest = i;
			}
		}
		this.window = window;
	}

	@Override
	public void execute(boolean test) {
		Map.getSingletonMap().getRequests().getDeliveries().remove(indexInListOfRequest);

		deletedPickupIndex = Map.getSingletonMap().getRequests().getOrderedStops().indexOf(deleted.getPickup());
		deletedDeliveryIndex = Map.getSingletonMap().getRequests().getOrderedStops().indexOf(deleted.getDelivery());
		Map.getSingletonMap().getRequests().getOrderedStops().remove(deleted.getPickup());
		Map.getSingletonMap().getRequests().getOrderedStops().remove(deleted.getDelivery());

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
		Map.getSingletonMap().getRequests().getDeliveries().add(indexInListOfRequest, deleted);
		Map.getSingletonMap().getRequests().getOrderedStops().add(deletedPickupIndex, deleted.getPickup());
		Map.getSingletonMap().getRequests().getOrderedStops().add(deletedDeliveryIndex, deleted.getDelivery());

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
