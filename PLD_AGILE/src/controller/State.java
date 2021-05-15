package controller;

import view.Window;

/**
 * 
 * abstract class for a State
 *
 */
public abstract class State {
	protected Window window;

	protected State(Window window) {
		this.window = window;
	}

	/**
	 * called directly after calling the constructor. Executes graphics changes and
	 * computation
	 * 
	 * @return true if successful
	 */
	public abstract boolean onEnterState();

	/**
	 * Called before changing to another state
	 * 
	 * @return true if exited successfully
	 */
	public abstract boolean onExitState();

	public abstract String getState();
}
