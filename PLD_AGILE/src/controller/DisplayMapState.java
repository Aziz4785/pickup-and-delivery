package controller;

import model.Map;
import view.Window;

/**
 * 
 * the state where the map is displayed
 *
 */
public class DisplayMapState extends State {

	private String mapFilePath;

	public DisplayMapState(Window window) {
		super(window);
	}

	public DisplayMapState(String mapFilePath, Window window) {
		super(window);
		this.mapFilePath = mapFilePath;
	}

	@Override
	public boolean onEnterState() {
		if (Map.generateMap(mapFilePath)) {
			this.window.eraseMap();
			this.window.displayMap(Map.getSingletonMap());
			this.window.makeButtonLoadMap2Visible();
			this.window.makeGroupRequestVisibleAndReset();
			this.window.eraseAdress();
			this.window.hideTextualDisplay();
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean onExitState() {
		return false;
	}

	@Override
	public String getState() {
		return "DisplayMapState";
	}

}
