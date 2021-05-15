package controller;

import javafx.animation.FadeTransition;
import model.ListOfRequest;
import model.Map;
import view.Window;

/**
 * 
 * The state where map and requests are displayed
 *
 */
public class DisplayMapWithRequestsState extends State {

	private String requestsFilePath;

	public DisplayMapWithRequestsState(String requestsFilePath, Window window) {
		super(window);
		this.requestsFilePath = requestsFilePath;
	}

	@Override
	public boolean onEnterState() {
		ListOfRequest requests = new ListOfRequest(requestsFilePath);
		if (requests.importList() && requests.getDeliveries().size() > 0) {
			Map.getSingletonMap().setRequests(requests);
			
			if(Map.getSingletonMap().checkRequestValidity()) {
				window.eraseRequestsAndTour();
				window.displayListOfRequest(Map.getSingletonMap().getRequests(), Map.getSingletonMap());
				window.makeButtonLoadRequest2Visible();
				window.makeButtonCalculateTourVisible();
				window.displayTextRequest(requests);
			}else {
				return false;
			}

			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean onExitState() {
		FadeTransition f = this.window.getFadeTransition();
		if (f != null) {
			f.stop();
			f.getNode().setOpacity(1.);
		}
		return false;
	}

	@Override
	public String getState() {
		return "DisplayMapWithRequestsState";
	}
}
