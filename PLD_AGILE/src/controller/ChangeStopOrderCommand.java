package controller;

import model.Map;
import model.Stop;
import view.Window;

/**
 * 
 * Command that allows the user to higher or lower a Stop into the graphic list
 * by one step.
 *
 */
public class ChangeStopOrderCommand extends Command {
	// moving down is equivalent to higher the index in the array

	int initialStopIndex;
	boolean isMoveDown;
	Window window;

	/**
	 * 
	 * @param requestIndex index of the stop in the array
	 * @param isMoveDown   if true, moves the stop down in the graphic list (towards
	 *                     higher index in the ListOfRequest.OrderedStops list)
	 */
	public ChangeStopOrderCommand(int requestIndex, boolean isMoveDown, Window window) {
		this.initialStopIndex = requestIndex;
		this.isMoveDown = isMoveDown;
		this.window = window;
	}

	@Override
	public void execute(boolean test) {
		if (isMoveDown) {
			Stop tmp;
			tmp = Map.getSingletonMap().getRequests().getOrderedStops().get(initialStopIndex + 1);
			Map.getSingletonMap().getRequests().getOrderedStops().set(initialStopIndex + 1,
					Map.getSingletonMap().getRequests().getOrderedStops().get(initialStopIndex));
			Map.getSingletonMap().getRequests().getOrderedStops().set(initialStopIndex, tmp);

		} else {
			Stop tmp;
			tmp = Map.getSingletonMap().getRequests().getOrderedStops().get(initialStopIndex - 1);
			Map.getSingletonMap().getRequests().getOrderedStops().set(initialStopIndex - 1,
					Map.getSingletonMap().getRequests().getOrderedStops().get(initialStopIndex));
			Map.getSingletonMap().getRequests().getOrderedStops().set(initialStopIndex, tmp);
		}
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
		if (isMoveDown) {
			Stop tmp;
			tmp = Map.getSingletonMap().getRequests().getOrderedStops().get(initialStopIndex + 1);
			Map.getSingletonMap().getRequests().getOrderedStops().set(initialStopIndex + 1,
					Map.getSingletonMap().getRequests().getOrderedStops().get(initialStopIndex));
			Map.getSingletonMap().getRequests().getOrderedStops().set(initialStopIndex, tmp);
		} else {
			Stop tmp;
			tmp = Map.getSingletonMap().getRequests().getOrderedStops().get(initialStopIndex - 1);
			Map.getSingletonMap().getRequests().getOrderedStops().set(initialStopIndex - 1,
					Map.getSingletonMap().getRequests().getOrderedStops().get(initialStopIndex));
			Map.getSingletonMap().getRequests().getOrderedStops().set(initialStopIndex, tmp);
		}

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
