package controller;

import view.Window;

import java.util.ArrayList;

import model.Map;
import model.Request;

/**
 * 
 * The Controller class acts as an automaton. It has a current state and methods
 * that handle state changes. See State-Transition diagram.
 *
 */
public class Controller {
	private State currentState;
	private Window window;

	/**
	 * The constructor of the controller
	 * 
	 * @param window the graphics window. This is the link between view and
	 *               controller
	 */
	public Controller(Window window) {
		this.window = window;
		this.currentState = new InitialState(this.window);
		this.currentState.onEnterState();
	}

	/**
	 * Get the name of the subclass of currentState
	 * 
	 * @return the name of the subclass
	 */
	public String getState() {
		return currentState.getState();
	}

	/**
	 * Change currentState to DisplayMapState. Call loadNewMap if this is not the
	 * first importation
	 * 
	 * @param mapFilePath the path of the XML file containing the Map description
	 * @return true if import was successful
	 */
	public boolean importMapFromFile(String mapFilePath) {
		DisplayMapState newState = new DisplayMapState(mapFilePath, this.window);
		if (newState.onEnterState()) // If map generation is okay
		{
			this.currentState.onExitState();
			this.currentState = newState;
			return true;
		} else {
			String title = "xml import error";
			String content = "Check the file structure : \n" + "<map>\n"
					+ "\t<intersection id=\"123456\" latitude=\"45.75406\" longitude=\"4.857418\"/>\n"
					+ "\t<intersection id=\"456789\" latitude=\"45.750404\" longitude=\"4.8744674\"/>\n" + "...\n"
					+ "\t<segment destination=\"456789\" length=\"69.979805\" name=\"example st\" origin=\"123456\"/>\n"
					+ "...\n" + "</map>";
			window.displayPopup(title, content);
			window.eraseMap();
			window.makeGroupRequestInvisibleAndReset();
			window.makeButtonLoadMap1Visible();
			window.eraseAdress();
			window.hideTextualDisplay();
			return false;
		}
	}

	/**
	 * Change currentState to DisplayMapState. Call importMapFromFile if this is the
	 * first importation
	 * 
	 * @param mapFilePath the path of the XML file containing the Map description
	 * @return true if import was successful
	 */
	public boolean loadNewMap(String mapFilePath) {
		DisplayMapState newState = new DisplayMapState(mapFilePath, this.window);
		if (newState.onEnterState()) {
			this.currentState.onExitState();
			this.currentState = newState;
			return true;
		} else {
			this.currentState.onExitState();
			this.currentState = new InitialState(this.window);
			this.currentState.onEnterState();
			String title = "xml import error";
			String content = "Check the file structure : \n" + "<map>\n"
					+ "\t<intersection id=\"123456\" latitude=\"45.75406\" longitude=\"4.857418\"/>\n"
					+ "\t<intersection id=\"456789\" latitude=\"45.750404\" longitude=\"4.8744674\"/>\n" + "...\n"
					+ "\t<segment destination=\"456789\" length=\"69.979805\" name=\"example st\" origin=\"123456\"/>\n"
					+ "...\n" + "</map>";
			window.displayPopup(title, content);
			window.eraseMap();
			window.makeGroupRequestInvisibleAndReset();
			window.makeButtonLoadMap1Visible();
			window.eraseAdress();
			window.hideTextualDisplay();
			return false;
		}

	}

	/**
	 * Change currentState to DisplayMapWithRequestState. Call loadNewRequest if
	 * this is not the first importation
	 * 
	 * @param requestsFilePath the path of the XML file containing the Request
	 *                         description
	 * @return true if import was successful
	 */
	public boolean importRequestsFromFile(String requestsFilePath) {
		DisplayMapWithRequestsState newState = new DisplayMapWithRequestsState(requestsFilePath, this.window);
		if (newState.onEnterState()) {
			this.currentState.onExitState();
			this.currentState = newState;
			return true;
		} else {
			String title = "xml import error";
			String content = "Check the file structure : \n" + "<planningRequest>\r\n"
					+ "\t<depot address=\"123456\" departureTime=\"8:0:0\"/>\r\n"
					+ "\t<request pickupAddress=\"456789\" deliveryAddress=\"654321\" pickupDuration=\"600\" deliveryDuration=\"150\"/>\r\n"
					+ "...\n" + "</planningRequest>\r\n"
					+ "\r\n Also make sure that pick-ups and deliveries from this file fit in the currently loaded map";
			window.displayPopup(title, content);
			return false;
		}
	}

	/**
	 * Change currentState to DisplayMapWithRequestState. Call
	 * importRequestsFromFile if this is the first importation
	 * 
	 * @param requestsFilePath the path of the XML file containing the Requests
	 *                         description
	 * @return true if import was successful
	 */
	public boolean loadNewRequests(String requestsFilePath) {
		DisplayMapWithRequestsState newState = new DisplayMapWithRequestsState(requestsFilePath, this.window);
		if (newState.onEnterState()) {
			this.currentState.onExitState();
			this.currentState = newState;
			return true;
		} else {
			this.currentState.onExitState();
			this.currentState = new DisplayMapState(Map.getSingletonMap().getMapPath(), this.window);
			this.currentState.onEnterState();
			String title = "xml import error";
			String content = "Check the file structure : \n" + "<planningRequest>\r\n"
					+ "\t<depot address=\"123456\" departureTime=\"8:0:0\"/>\r\n"
					+ "\t<request pickupAddress=\"456789\" deliveryAddress=\"654321\" pickupDuration=\"600\" deliveryDuration=\"150\"/>\r\n"
					+ "...\n" + "</planningRequest>\r\n"
					+ "\r\n Also make sure that pick-ups and deliveries from this file fit in the currently loaded map";
			window.displayPopup(title, content);
			return false;
		}

	}

	/**
	 * Changes currentState to DisplayMapWithTourState.
	 * 
	 * @return true if the generation was successful
	 */
	public boolean generateTour() {
		DisplayMapWithTourState newState = new DisplayMapWithTourState(this.window, true);

		if (newState.onEnterState()) {
			this.currentState.onExitState();
			this.currentState = newState;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Create an AddNewRequestCommand and switch to ExecuteCommandState
	 * 
	 * @param request the request(pickup + delivery) to add
	 * @return true if the request was successfully added
	 */
	public boolean addRequest(Request request) {
		AddNewRequestCommand command = new AddNewRequestCommand(request,this.window);
		ExecuteCommandState newState = new ExecuteCommandState(this.window, command);

		if (newState.onEnterState()) {
			this.currentState.onExitState();
			this.currentState = newState;
			DisplayMapWithTourState displayState = new DisplayMapWithTourState(this.window, false);
			displayState.onEnterState();
			this.currentState.onExitState();
			this.currentState = displayState;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Create an DeleteCommand and switch to ExecuteCommandState
	 * 
	 * @param index the index of the request to remove
	 * @return true if the request was successfully removed
	 */
	public boolean removeRequest(int index) {
		DeleteCommand command = new DeleteCommand(index, this.window);
		ExecuteCommandState newState = new ExecuteCommandState(this.window, command);

		if (newState.onEnterState()) {
			this.currentState.onExitState();
			this.currentState = newState;
			DisplayMapWithTourState displayState = new DisplayMapWithTourState(this.window, false);
			displayState.onEnterState();
			this.currentState.onExitState();
			this.currentState = displayState;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Create an ChangeStopOrderCommand and switch to ExecuteCommandState
	 * 
	 * @param index       the index of the stop to move in
	 *                    ListOfRequest.orderedStops
	 * @param isMovedDown if true, the stop is to move down graphically, towards
	 *                    higher indexes in the Tour.orderedStops list
	 * @return true if the stop was successfully moved
	 */
	public boolean changeStopOrder(int index, boolean isMovedDown) {
		ChangeStopOrderCommand command = new ChangeStopOrderCommand(index, isMovedDown, this.window);
		ExecuteCommandState newState = new ExecuteCommandState(this.window, command);

		if (newState.onEnterState()) {
			System.out.println("changeRequestReussi");
			this.currentState.onExitState();
			this.currentState = newState;
			DisplayMapWithTourState displayState = new DisplayMapWithTourState(this.window, false);
			displayState.onEnterState();
			this.currentState.onExitState();
			this.currentState = displayState;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Undoes the last executed command
	 * 
	 * @return true if the command was successfully undone
	 */
	public boolean undoLastCommand() {
		ArrayList<Command> modifications = Map.getSingletonMap().getTour().getModifications();
		Command command = modifications.get(modifications.size() - 1);

		UndoCommandState newState = new UndoCommandState(this.window, command);

		if (newState.onEnterState()) {
			this.currentState.onExitState();
			this.currentState = newState;
			DisplayMapWithTourState displayState = new DisplayMapWithTourState(this.window, false);
			displayState.onEnterState();
			this.currentState.onExitState();
			this.currentState = displayState;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Redoes the last undone command
	 * 
	 * @return true if the command was successfully undone
	 */
	public boolean redoLastCommand() {
		ArrayList<Command> undoneModifications = Map.getSingletonMap().getTour().getUndoneModifications();
		Command command = undoneModifications.get(undoneModifications.size() - 1);

		ExecuteCommandState newState = new ExecuteCommandState(this.window, command);

		if (newState.onEnterState()) {
			this.currentState.onExitState();
			this.currentState = newState;
			DisplayMapWithTourState displayState = new DisplayMapWithTourState(this.window, false);
			displayState.onEnterState();
			this.currentState.onExitState();
			this.currentState = displayState;
			return true;
		} else {
			return false;
		}
	}

}
