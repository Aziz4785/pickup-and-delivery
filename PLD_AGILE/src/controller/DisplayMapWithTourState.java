package controller;

import javafx.animation.FadeTransition;
import model.Map;
import model.Tour;
import view.Window;

/**
 * 
 * The state where the map, the requests and the tour are displayed
 *
 */
public class DisplayMapWithTourState extends State {

	private boolean calculateTour;

	public DisplayMapWithTourState(Window window, boolean calculateTour) {
		super(window);
		this.calculateTour = calculateTour;
	}

	@Override
	public boolean onEnterState() {
		if (calculateTour) {
			Tour t = new Tour();
			t.generateBestTour();
			Map.getSingletonMap().setTour(t);
			window.eraseTour();
			window.displayTour();
			window.displayTextTour();
			window.makeButtonCalculateTourInvisible();
		}
		return true;
	}

	@Override
	public boolean onExitState() {
		FadeTransition f = this.window.getFadeTransition();
		if (f != null) {
			f.stop();
			f.getNode().setOpacity(1.);
		}
		return true;
	}

	@Override
	public String getState() {
		return "DisplayMapWithTourState";
	}
}
