package controller;

import model.Map;
import view.Window;

/**
 * A temporary state to undo commands. The automaton (controller) switches back
 * to a DisplayMapWithTourState directly afterwards
 *
 */
public class UndoCommandState extends State {
	private Command command;

	/**
	 * 
	 * @param window  the graphics window
	 * @param command the command to undo. Should be the last executed command
	 */
	protected UndoCommandState(Window window, Command command) {
		super(window);
		this.command = command;
	}

	@Override
	public boolean onEnterState() {
		command.undo(false);
		if (Map.getSingletonMap().getTour().getUndoneModifications().size() == 1) {
			window.enableRedo();
		}
		if (Map.getSingletonMap().getTour().getModifications().size() == 0) {
			window.disableUndo();
		}
		return true;
	}

	@Override
	public boolean onExitState() {
		return true;
	}

	@Override
	public String getState() {
		return "UndoCommandState";
	}
}
