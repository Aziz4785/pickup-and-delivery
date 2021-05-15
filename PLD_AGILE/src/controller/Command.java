package controller;

import model.Map;

/**
 * 
 * A command in the interface. Interacts with the Model. Commands can be
 * executed and undone, and are stored ordered by precedence into Tour.commands.
 * Last command has highest index in this list.
 *
 */
public abstract class Command {

	/**
	 * Handles Tour.command list coherence. To be called at the end of execute().
	 */
	public void addSelfToCommandList() {
		if (Map.getSingletonMap().getTour().getUndoneModifications().contains(this)) {
			Map.getSingletonMap().getTour().getUndoneModifications().remove(this);
		} else {
			Map.getSingletonMap().getTour().getUndoneModifications().clear();
		}

		Map.getSingletonMap().getTour().getModifications().add(this);
	}

	/**
	 * Handles Tour.command list coherence. To be called at the end of undo().
	 */
	public void removeSelfFromCommandList() {
		Map.getSingletonMap().getTour().getModifications().remove(this);
		Map.getSingletonMap().getTour().getUndoneModifications().add(this);
	}

	/**
	 * Executes the command
	 */
	public abstract void execute(boolean test);

	/**
	 * Undoes the command. Reverses the operations of execute().
	 */
	public abstract void undo(boolean test);
}
