package controller;

import view.Window;

/**
 * 
 * The initial state of the automaton (controller)
 *
 */
public class InitialState extends State {
	public InitialState(Window window) {
		super(window);
	}

	void importMapFromFile() {

	}

	@Override
	public boolean onEnterState() {
		return false;
	}

	@Override
	public boolean onExitState() {
		return false;
	}

	@Override
	public String getState() {
		return "InitialState";
	}
}
