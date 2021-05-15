package controller;

import model.Map;
import view.Window;

/**
 * 
 * a temporary state to execute commands. The automaton (controller) switches
 * back to a DisplayMapWithTourState directly afterwards
 *
 */
public class ExecuteCommandState extends State {

	private Command command;

	protected ExecuteCommandState(Window window, Command command) {
		super(window);
		this.command = command;
	}

	@Override
	public boolean onEnterState() {
		command.execute(false);
		if (Map.getSingletonMap().getTour().getModifications().size() == 1) {
			window.enableUndo();
		}
		if (Map.getSingletonMap().getTour().getUndoneModifications().size() == 0) {
			window.disableRedo();
		}
		return true;
	}

	@Override
	public boolean onExitState() {
		return true;
	}

	@Override
	public String getState() {
		return "DisplayMapWithTourState";
	}
}
