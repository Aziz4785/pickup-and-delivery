/*
 * Window
 * 
 * Version 1.0
 *
 * 09/12/2020
 * 
 * Copyright h4403 2020
 */

package view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.util.Pair;
import model.ListOfRequest;
import model.Map;
import model.Node;
import model.Request;
import model.Segment;
import model.Stop;
import model.Tour;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * Window
 * 
 * The graphics class of the application. Includes the Main() function.
 *
 */
public class Window extends Application {
	private Controller windowController;
	private Stage primaryStage;
	private List<Pair<Double, Double>> pospts = new ArrayList<Pair<Double, Double>>();
	private ArrayList<Pair<Node,Circle>> allMapNodes = new ArrayList<Pair<Node,Circle>>();
	private GridPane textPane;
	private List<javafx.scene.Node> bold = new ArrayList<javafx.scene.Node>();
	private Group groupMainMap;
	private Group groupRequests;
	private Group groupTour;
	private HBox boxUndoRedo;
	private Button addNewRequestButton;
	private Button cancelNewRequestButton;
	private FadeTransition fadeTransition;
	private BorderPane onTopOfGrid;
	private Label infoUser;
	private Pane map;
	private EventHandler<MouseEvent> mapEventHandler;
	private Node firstNodeNewRequest = null;
	private Node secondNodeNewRequest = null;
	private Stage secondStage;


	/**
	 * The override of the start method inherited from class Application (sets up all the main components)
	 * 
	 * @param primaryStage
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			System.out.println(this.primaryStage);
			primaryStage.setTitle("Delivery Application");
			primaryStage.initStyle(StageStyle.DECORATED);
			int height = 600;
			int width = 1250;

			Label adress = new Label();
			adress.setLayoutY(50);
			
			Label infoUser = new Label();
			infoUser.setLayoutX(200);
			infoUser.setLayoutY(adress.getLayoutY());
			infoUser.setTextFill(Color.RED);
			this.infoUser = infoUser;

			Button buttonDisplayMap = new Button("Choose file for map");
			FileChooser mapXmlChooser = new FileChooser();
			mapXmlChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
			buttonDisplayMap.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					File mapXml = mapXmlChooser.showOpenDialog(primaryStage);
					if (mapXml != null) {
						windowController.importMapFromFile(mapXml.toString());
					}
				}
			});

			Button newButtonDisplayMap = new Button("Load new map");
			newButtonDisplayMap.setVisible(false);
			newButtonDisplayMap.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					File mapXml = mapXmlChooser.showOpenDialog(primaryStage);
					if (mapXml != null) {
						windowController.loadNewMap(mapXml.toString());
					}
				}
			});

			Button buttonLoadQuery = new Button("Choose file for query");
			buttonLoadQuery.setLayoutY(80);
			FileChooser requestXmlChooser = new FileChooser();
			requestXmlChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
			buttonLoadQuery.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					File requestXml = requestXmlChooser.showOpenDialog(primaryStage);
					if (requestXml != null) {
						windowController.importRequestsFromFile(requestXml.toString());
					}
				}
			});

			Button newButtonLoadQuery = new Button("Load a new query");
			newButtonLoadQuery.setLayoutY(80);
			newButtonLoadQuery.setVisible(false);
			newButtonLoadQuery.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					File requestXml = requestXmlChooser.showOpenDialog(primaryStage);
					if (requestXml != null) {
						windowController.loadNewRequests(requestXml.toString());
					}
				}
			});

			Button buttonCalculateTour = new Button("Calculate tour");
			buttonCalculateTour.setLayoutY(110);
			buttonCalculateTour.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					windowController.generateTour();
				}
			});

			// Button groups
			Group groupLoadMap = new Group(buttonDisplayMap, newButtonDisplayMap);
			Group groupLoadQuery = new Group(buttonLoadQuery, newButtonLoadQuery);
			Group groupCalculateTour = new Group(buttonCalculateTour);
			groupCalculateTour.setVisible(false);
			Group groupDisplayTour = new Group(groupLoadQuery, groupCalculateTour);
			groupDisplayTour.setVisible(false);

			Group textualDisplay = new Group();
			textualDisplay.setVisible(false);
			textualDisplay.setLayoutY(180);

			Button buttonUndo = new Button("Undo");
			buttonUndo.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					windowController.undoLastCommand();
				}
			});
			buttonUndo.setDisable(true);

			Button buttonRedo = new Button("Redo");
			buttonRedo.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					windowController.redoLastCommand();
				}
			});
			buttonRedo.setDisable(true);

			HBox boxUndoRedo = new HBox(buttonUndo, buttonRedo);
			this.boxUndoRedo = boxUndoRedo;
			
			Button addNewRequestButton = new Button("Add new Request");
			addNewRequestButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					startCreateNewRequest();
				}
			});
			this.addNewRequestButton = addNewRequestButton;
			
			Button cancelNewRequestButton = new Button("Cancel");
			cancelNewRequestButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					cancelCreateNewRequest();
				}
			});
			this.cancelNewRequestButton = cancelNewRequestButton;
			this.cancelNewRequestButton.setVisible(false);
			
			Group groupNewRequest = new Group(addNewRequestButton, cancelNewRequestButton);
			
			BorderPane onTopOfGrid = new BorderPane();
			onTopOfGrid.setLayoutY(textualDisplay.getLayoutY() - 30);
			onTopOfGrid.setPrefWidth(width*0.45);
			onTopOfGrid.setLeft(boxUndoRedo);
			onTopOfGrid.setRight(groupNewRequest);
			this.onTopOfGrid = onTopOfGrid;
			this.onTopOfGrid.setVisible(false);

			Pane controls = new Pane(groupLoadMap, groupDisplayTour, textualDisplay, adress, onTopOfGrid, infoUser);
			controls.setPrefSize(0.45 * width, height);
			controls.getStyleClass().add("controls");

			// Map group
			Group groupMainMap = new Group(); // Contains the actual map
			groupMainMap.toBack();
			this.groupMainMap = groupMainMap;
			Group groupRequest = new Group(); // Contains the stops of the request
			groupRequest.toFront();
			this.groupRequests = groupRequest;
			Group groupTour = new Group(); // Contains the lines of the tour
			groupTour.toBack();
			this.groupTour = groupTour;
			Group groupMap = new Group(groupMainMap, groupRequest, groupTour); // Contains eveything in the map (back map, tour and request)
			Pane map = new Pane(groupMap);
			map.getStyleClass().add("map");
			map.setPrefSize(0.55 * width, height);
			
			this.mapEventHandler = e -> {
				handleClick(e);
			};
			map.addEventFilter(MouseEvent.MOUSE_CLICKED, this.mapEventHandler);
			this.map = map;
			
			HBox root = new HBox(controls, map);
			Scene scene = new Scene(root, width-10, height);
			this.primaryStage.setResizable(false);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setScene(scene);
			primaryStage.show();
			
			// The second stage used to select the pickup and delivery duration when a new request is created
			BorderPane root2 = new BorderPane();
			Scene secondScene = new Scene(root2, 300, 150);
		    Stage secondStage = new Stage();
		    secondStage.setResizable(false);
		    secondStage.setTitle("Pickup and Delivery Duration");
		    secondStage.setScene(secondScene);
		    secondStage.initStyle(StageStyle.DECORATED);
		    secondStage.initModality(Modality.NONE);
		    secondStage.initOwner(primaryStage);
		    secondStage.setOnCloseRequest(event -> {
		    	cancelCreateNewRequest();
		    });
		    this.secondStage = secondStage;
		    
		    HBox content = new HBox();
		    root2.setCenter(content);
		  
		    Label pickupLabel = new Label("Pickup Duration (min)");
		    TextField pickupDuration = new TextField("3");
		    pickupDuration.textProperty().addListener(new ChangeListener<String>() {
		        @Override
		        public void changed(ObservableValue<? extends String> observable, String oldValue, 
		            String newValue) {
		            if (!newValue.matches("\\d*")) {
		                pickupDuration.setText(newValue.replaceAll("[^\\d]", ""));
		            }
		        }
		    });
		    VBox pickup = new VBox(pickupLabel,pickupDuration);
		    content.getChildren().add(pickup);
		    
		    Label deliveryLabel = new Label("DeliveryDuration (min)");
		    TextField deliveryDuration = new TextField("3");
		    deliveryDuration.textProperty().addListener(new ChangeListener<String>() {
		        @Override
		        public void changed(ObservableValue<? extends String> observable, String oldValue, 
		            String newValue) {
		            if (!newValue.matches("\\d*")) {
		                deliveryDuration.setText(newValue.replaceAll("[^\\d]", ""));
		            }
		        }
		    });
		    VBox delivery = new VBox(deliveryLabel,deliveryDuration);
		    content.getChildren().add(delivery);
		    
		    Button confirmation = new Button("Confirm and save");
		    confirmation.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					if(!(pickupDuration.getText().isEmpty()) && !(deliveryDuration.getText().isEmpty())) {
						Request request = new Request(firstNodeNewRequest, Integer.parseInt(pickupDuration.getText())*60, secondNodeNewRequest, Integer.parseInt(deliveryDuration.getText())*60);
						secondStage.close();
						windowController.addRequest(request);
						cancelCreateNewRequest();
					}
				}
			});
		    
		    Button cancel = new Button("Cancel");
		    cancel.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					cancelCreateNewRequest();
					secondStage.close();
				}
			});
		    
		    HBox buttons = new HBox(confirmation,cancel);
		    root2.setBottom(buttons);
		    primaryStage.toFront();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the fade transition object used for the animation of the map
	 * 
	 * @return fadeTransition  the fade transition object
	 */
	public FadeTransition getFadeTransition() {
		return fadeTransition;
	}

	private void handleListClick(MouseEvent e, javafx.scene.Node n, boolean tour) {
		if ((windowController.getState() == "DisplayMapWithRequestsState"
				|| windowController.getState() == "DisplayMapWithTourState") && pospts.size() > 0) {
			;
			Pair<Double, Double> coords = tour ?
			conversionLongLatToWindow(Map.getSingletonMap().getRequests().getOrderedStops().get(GridPane.getRowIndex(n) - 1).getNode(), primaryStage.getScene(), Map.getSingletonMap())
			: pospts.get(GridPane.getRowIndex(n) - 1);
			getGroupMap(primaryStage.getScene())
					.fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED, coords.getKey() + 560, coords.getValue(), 0, 0,
							MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, true, null));
		}
	}

	/**
	 * Bold the row of the node clicked
	 * 
	 * @param e the mouseEvent which triggered the function
	 */
	private void handleClick(MouseEvent e) {
		if ((windowController.getState() == "DisplayMapWithRequestsState"
				|| windowController.getState() == "DisplayMapWithTourState") && pospts.size() > 0) {
			for (javafx.scene.Node n : bold) {
				n.setStyle("-fx-font-weight: normal");
			}
			bold.clear();

			double min = Double.POSITIVE_INFINITY;
			int res = 0;
			int posOnMap = 0;

			for (int i = 0; i < pospts.size(); ++i) {
				double distance = Math.pow((pospts.get(i).getKey() - e.getX()), 2)
						+ Math.pow((pospts.get(i).getValue() - e.getY()), 2);
				if (distance < min) {
					min = distance;
					if (windowController.getState() == "DisplayMapWithRequestsState") {
						res = i;

					} else {
						ArrayList<Stop> tmp = Map.getSingletonMap().getRequests().getOrderedStops();
						for (int j = 0; j < tmp.size(); ++j) {
							Pair<Double, Double> coords = conversionLongLatToWindow(tmp.get(j).getNode(),
									primaryStage.getScene(), Map.getSingletonMap());
							if (pospts.get(i).getKey().equals(coords.getKey())
									&& pospts.get(i).getValue().equals(coords.getValue())) {
								res = j;
							}
						}
					}
					posOnMap = i;
				}
			}
			
			if (fadeTransition != null) {
				fadeTransition.stop();
				fadeTransition.getNode().setOpacity(1.);
			}
			
			if(windowController.getState() == "DisplayMapWithTourState") {
				fadeTransition = new FadeTransition(Duration.seconds(0.3),
						groupRequests.getChildren().get(posOnMap));
					fadeTransition.setFromValue(1.0);
					fadeTransition.setToValue(0.0);
					fadeTransition.setCycleCount(Animation.INDEFINITE);
					fadeTransition.play();
			} else {
				fadeTransition = new FadeTransition(Duration.seconds(0.3),
						groupRequests.getChildren().get(res));
					fadeTransition.setFromValue(1.0);
					fadeTransition.setToValue(0.0);
					fadeTransition.setCycleCount(Animation.INDEFINITE);
					fadeTransition.play();
			}
			
			

			if (windowController.getState() == "DisplayMapWithTourState"
					&& res == Map.getSingletonMap().getRequests().getOrderedStops().size() - 1) {
				for (javafx.scene.Node n : textPane.getChildren()) {
					if (n.isManaged()) {
						if (GridPane.getRowIndex(n) - 1 == 0) {
							n.setStyle("-fx-font-weight: bold");
							bold.add(n);
						}
					}
				}
			}

			for (javafx.scene.Node n : textPane.getChildren()) {
				if (n.isManaged()) {
					if (GridPane.getRowIndex(n) - 1 == res) {
						n.setStyle("-fx-font-weight: bold");
						bold.add(n);
					}
				}
			}
		}
	}
	
	/**
	 * Sets the window in the mode used to select a pickup and a delivery point to create a new request
	 */
	private void startCreateNewRequest() {
		System.out.println("efrgreg");
		map.removeEventFilter(MouseEvent.MOUSE_CLICKED, this.mapEventHandler);
		textPane.setMouseTransparent(true);
		this.addNewRequestButton.setVisible(false);
		this.cancelNewRequestButton.setVisible(true);
		this.boxUndoRedo.setVisible(false);
		this.infoUser.setText("Please clik on a point on the map to set up the pickup point");
		for(int i = 0; i < this.allMapNodes.size(); i++) {
			this.allMapNodes.get(i).getValue().setDisable(false);
			this.allMapNodes.get(i).getValue().setRadius(4);
		}
	}
	
	/**
	 * Displays the popup used to select the pickup and delivery duration
	 */
	private void selectPickupAndDeliveryDuration() {
		this.secondStage.show();
		this.primaryStage.getScene().getRoot().setDisable(true);
	}
	
	/**
	 * Sets the widnow back into the display tour mode
	 */
	private void cancelCreateNewRequest() {
		this.primaryStage.getScene().getRoot().setDisable(false);
		map.addEventFilter(MouseEvent.MOUSE_CLICKED, this.mapEventHandler);
		textPane.setMouseTransparent(false);
		this.addNewRequestButton.setVisible(true);
		this.cancelNewRequestButton.setVisible(false);
		this.boxUndoRedo.setVisible(true);
		this.infoUser.setText("");
		this.firstNodeNewRequest = null;
		this.secondNodeNewRequest = null;
		for(int i = 0; i < this.allMapNodes.size(); i++) {
			this.allMapNodes.get(i).getValue().setDisable(true);
			this.allMapNodes.get(i).getValue().setRadius(1);
		}
	}
	
	/**
	 * Disable the button undo
	 */
	public void disableUndo() {
		Button buttonUndo = (Button) this.boxUndoRedo.getChildren().get(0);
		buttonUndo.setDisable(true);

	}

	/**
	 * Enable the button undo
	 */
	public void enableUndo() {
		Button buttonUndo = (Button) this.boxUndoRedo.getChildren().get(0);
		buttonUndo.setDisable(false);
	}

	/**
	 * Disable the button redo
	 */
	public void disableRedo() {
		Button buttonRedo = (Button) this.boxUndoRedo.getChildren().get(1);
		buttonRedo.setDisable(true);
	}

	/**
	 * Enable the button redo
	 */
	public void enableRedo() {
		Button buttonRedo = (Button) this.boxUndoRedo.getChildren().get(1);
		buttonRedo.setDisable(false);
	}

	/**
	 * Converts a lattitude and longitude to window space coordinates
	 * 
	 * @param node  the node at the lattitude and longitude
	 * @param scene
	 * @param map
	 * @return the window coordinates of the node
	 */
	private Pair<Double, Double> conversionLongLatToWindow(Node node, Scene scene, Map map) {
		double scaleX = (scene.getWidth() * 0.55) / (map.getMaxLongitude() - map.getMinLongitude());
		double scaleY = (scene.getHeight() * 1.0) / (map.getMaxLatitude() - map.getMinLatitude());
		double x = (node.getLongitude() - map.getMinLongitude()) * scaleX;
		double y = (map.getMaxLatitude() - node.getLatitude()) * scaleY;
		Pair<Double, Double> newCoordinates = new Pair<>(x, y);
		return newCoordinates;
	}

	/**
	 * Return a square centered in the coordinates given, with the size given
	 * 
	 * @param x  the x coordinate of the square
	 * @param y  the y coordinate of the square
	 * @param size  the width and length of the square
	 * 
	 * @return squareCentered
	 */
	private static Rectangle squareCentered(double x, double y, double size) {
		Rectangle toReturn = new Rectangle(x - size / 2.0, y - size / 2.0, size, size);
		return toReturn;
	}

	/**
	 * Return the group containing all the objects in the map
	 * 
	 * @param scene  the scene where the mapGroup is contained
	 * 
	 * @return groupMap
	 */
	private Group getGroupMap(Scene scene) {
		Group toReturn = new Group();
		Pane myPane = (Pane) scene.getRoot().getChildrenUnmodifiable().get(1);
		toReturn = (Group) myPane.getChildren().get(0);
		return toReturn;
	}

	/**
	 * Return the group containing the textual display contained in the scene
	 * 
	 * @param scene
	 * 
	 * @return textInfoGroup
	 */
	private Group getTextInfoGroup(Scene scene) {
		Pane controlPane = (Pane) scene.getRoot().getChildrenUnmodifiable().get(0);
		Group toReturn = (Group) controlPane.getChildren().get(2);
		return toReturn;
	}

	/**
	 * Clears the map contained in groupMap
	 * 
	 * @param groupMap
	 */
	private void clearMap(Group groupMap) { // Clean map's subgroups without removing them
		for (int i = 0; i < groupMap.getChildren().size(); i++) {
			((Group) groupMap.getChildren().get(i)).getChildren().clear();
		}
	}

	/**
	 * Makes button load new map invisible and initial button load map visible
	 */
	public void makeButtonLoadMap1Visible() {
		Scene scene = this.primaryStage.getScene();
		Pane controlPane = (Pane) scene.getRoot().getChildrenUnmodifiable().get(0);
		Group groupLoadMap = (Group) controlPane.getChildren().get(0);
		groupLoadMap.setVisible(true);
		groupLoadMap.getChildren().get(0).setVisible(true);
		groupLoadMap.getChildren().get(1).setVisible(false);
	}

	/**
	 * Makes button load new map visible and initial button load map invisible
	 */
	public void makeButtonLoadMap2Visible() {
		Scene scene = this.primaryStage.getScene();
		Pane controlPane = (Pane) scene.getRoot().getChildrenUnmodifiable().get(0);
		Group groupLoadMap = (Group) controlPane.getChildren().get(0);
		groupLoadMap.setVisible(true);
		groupLoadMap.getChildren().get(1).setVisible(true);
		groupLoadMap.getChildren().get(0).setVisible(false);
	}

	/**
	 * Makes the button for loading a request invisible, erase and hide everything depending on the load of a request
	 */
	public void makeGroupRequestInvisibleAndReset() {
		Scene scene = this.primaryStage.getScene();
		this.onTopOfGrid.setVisible(false);
		this.infoUser.setText("");
		Pane controlPane = (Pane) scene.getRoot().getChildrenUnmodifiable().get(0);
		Group groupDisplayTour = (Group) controlPane.getChildren().get(1);
		groupDisplayTour.setVisible(false);
		Group groupRequests = ((Group) groupDisplayTour.getChildren().get(0));
		groupRequests.setVisible(true);
		groupRequests.getChildren().get(0).setVisible(true);
		groupRequests.getChildren().get(1).setVisible(false);
		Group groupTour = ((Group) groupDisplayTour.getChildren().get(1));
		groupTour.setVisible(false);
		groupTour.getChildren().get(0).setVisible(true);
	}

	/**
	 * Makes the button for loading a request visible and erase and hide everything depending on the load of a request
	 */
	public void makeGroupRequestVisibleAndReset() {
		Scene scene = this.primaryStage.getScene();
		this.onTopOfGrid.setVisible(false);
		this.infoUser.setText("");
		Pane controlPane = (Pane) scene.getRoot().getChildrenUnmodifiable().get(0);
		Group groupDisplayTour = (Group) controlPane.getChildren().get(1);
		groupDisplayTour.setVisible(true);
		Group groupRequests = ((Group) groupDisplayTour.getChildren().get(0));
		groupRequests.setVisible(true);
		groupRequests.getChildren().get(0).setVisible(true);
		groupRequests.getChildren().get(1).setVisible(false);
		Group groupTour = ((Group) groupDisplayTour.getChildren().get(1));
		groupTour.setVisible(false);
		groupTour.getChildren().get(0).setVisible(true);
	}

	/**
	 * Makes button load new request invisible and initial button load request visible
	 */
	public void makeButtonLoadRequest1Visible() {
		Scene scene = this.primaryStage.getScene();
		Pane controlPane = (Pane) scene.getRoot().getChildrenUnmodifiable().get(0);
		Group groupDisplayTour = (Group) controlPane.getChildren().get(1);
		Group groupRequests = ((Group) groupDisplayTour.getChildren().get(0));
		groupRequests.getChildren().get(0).setVisible(true);
		groupRequests.getChildren().get(1).setVisible(false);
	}

	/**
	 * Makes button load new request visible and initial button load request invisible
	 */
	public void makeButtonLoadRequest2Visible() {
		Scene scene = this.primaryStage.getScene();
		Pane controlPane = (Pane) scene.getRoot().getChildrenUnmodifiable().get(0);
		Group groupDisplayTour = (Group) controlPane.getChildren().get(1);
		Group groupRequests = ((Group) groupDisplayTour.getChildren().get(0));
		groupRequests.getChildren().get(1).setVisible(true);
		groupRequests.getChildren().get(0).setVisible(false);
	}

	/**
	 * Makes button calculate tour visible
	 */
	public void makeButtonCalculateTourVisible() {
		Scene scene = this.primaryStage.getScene();
		Pane controlPane = (Pane) scene.getRoot().getChildrenUnmodifiable().get(0);
		Group groupDisplayTour = (Group) controlPane.getChildren().get(1);
		Group groupTour = ((Group) groupDisplayTour.getChildren().get(1));
		groupTour.setVisible(true);
		groupTour.getChildren().get(0).setVisible(true);
	}
	
	/**
	 * Makes button calculate tour invisible
	 */
	public void makeButtonCalculateTourInvisible() {
		Scene scene = this.primaryStage.getScene();
		Pane controlPane = (Pane) scene.getRoot().getChildrenUnmodifiable().get(0);
		Group groupDisplayTour = (Group) controlPane.getChildren().get(1);
		Group groupTour = ((Group) groupDisplayTour.getChildren().get(1));
		groupTour.setVisible(false);
	}

	/**
	 * Erase the adress label
	 */
	public void eraseAdress() {
		Scene scene = this.primaryStage.getScene();
		Pane controlPane = (Pane) scene.getRoot().getChildrenUnmodifiable().get(0);
		Label adresse = (Label) controlPane.getChildren().get(3);
		adresse.setText("");
	}

	/**
	 * Hide the textual display zone and erase the info user label
	 */
	public void hideTextualDisplay() {
		Scene scene = this.primaryStage.getScene();
		Group textGroup = getTextInfoGroup(scene);
		textGroup.setVisible(false);
		this.onTopOfGrid.setVisible(false);
		this.infoUser.setText("");
	}

	/**
	 *  Generate and display the textual view for the request display and erase the info user label
	 */
	public void displayTextRequest(ListOfRequest requests) {
		Scene scene = this.primaryStage.getScene();
		Group textGroup = getTextInfoGroup(scene);
		textGroup.setVisible(true);
		this.onTopOfGrid.setVisible(false);
		this.infoUser.setText("");

		// Table reset
		textGroup.getChildren().clear();
		textPane = new GridPane();
		textPane.setGridLinesVisible(true);
		textPane.setPrefHeight(this.primaryStage.getHeight() - textGroup.getLayoutY() - 50);
		textPane.getStyleClass().add("gridPane"); // Set the same background as controls
		ScrollPane scrollText = new ScrollPane();
		scrollText.setContent(textPane);
		textGroup.getChildren().add(scrollText);
		Label type = new Label("   type   ");
		Label adress = new Label("   adress   ");
		Label arrivalHour = new Label("   arrival hour   ");
		Label departureHour = new Label("   departure hour   ");
		textPane.add(type, 1, 0);
		textPane.add(adress, 2, 0);
		textPane.add(arrivalHour, 3, 0);
		textPane.add(departureHour, 4, 0);

		// Depot text print
		type = new Label("Depot");
		type.setTextFill(Color.WHITE);
		adress = new Label(requests.getDepot().getNode().getNeighbours().get(0).getName());
		adress.setTextFill(Color.WHITE);
		departureHour = new Label(requests.getDepartureTime().toString());
		departureHour.setTextFill(Color.WHITE);
		textPane.add(type, 1, 1);
		textPane.add(adress, 2, 1);
		textPane.add(departureHour, 4, 1);

		// pickup/deliveries text print
		int indexTabText = 1;
		for (int i = 0; i < requests.getDeliveries().size(); i++) {
			type = new Label("pickup");
			type.setTextFill(requests.getDeliveries().get(i).getColorRequest());
			adress = new Label(requests.getDeliveries().get(i).getPickup().getNode().getNeighbours().get(0).getName());
			adress.setTextFill(requests.getDeliveries().get(i).getColorRequest());
			textPane.add(type, 1, indexTabText + 1);
			textPane.add(adress, 2, indexTabText + 1);
			indexTabText++;

			type = new Label("delivery");
			type.setTextFill(requests.getDeliveries().get(i).getColorRequest());
			adress = new Label(
					requests.getDeliveries().get(i).getDelivery().getNode().getNeighbours().get(0).getName());
			adress.setTextFill(requests.getDeliveries().get(i).getColorRequest());
			textPane.add(type, 1, indexTabText + 1);
			textPane.add(adress, 2, indexTabText + 1);
			indexTabText++;
		}

		for (javafx.scene.Node n : textPane.getChildren()) {
			n.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
				handleListClick(e, n, false);
			});
		}
	}

	/**
	 * Generate and display the textual view for the tour display
	 */
	public void displayTextTour() {
		ListOfRequest requests = Map.getSingletonMap().getRequests();

		Scene scene = this.primaryStage.getScene();
		Group textGroup = getTextInfoGroup(scene);
		textGroup.setVisible(true);
		this.onTopOfGrid.setVisible(true);
		this.infoUser.setText("");

		// reset text display
		textGroup.getChildren().clear();
		textPane = new GridPane();
		textPane.setGridLinesVisible(true);
		textPane.setPrefHeight(this.primaryStage.getHeight() - textGroup.getLayoutY() - 50);
		textPane.getStyleClass().add("gridPane"); // Set the same background as controls
		ScrollPane scrollText = new ScrollPane();
		scrollText.setContent(textPane);
		textGroup.getChildren().add(scrollText);
		Label command = new Label("   commands   ");
		Label type = new Label("   type   ");
		Label adress = new Label("   adress   ");
		Label arrivalHour = new Label("   arrival hour   ");
		Label departureHour = new Label("   departure hour   ");
		textPane.add(command, 0, 0);
		textPane.add(type, 1, 0);
		textPane.add(adress, 2, 0);
		textPane.add(arrivalHour, 3, 0);
		textPane.add(departureHour, 4, 0);

		// depot/departure print
		type = new Label("Depot");
		type.setTextFill(Color.WHITE);
		adress = new Label(requests.getOrderedStops().get(0).getNode().getNeighbours().get(0).getName());
		adress.setTextFill(Color.WHITE);
		departureHour = new Label(requests.getOrderedStops().get(0).getDepartureTime().toString());
		departureHour.setTextFill(Color.WHITE);
		textPane.add(type, 1, 1);
		textPane.add(adress, 2, 1);
		textPane.add(departureHour, 4, 1);

		// pickups & deliveries print
		for (int i = 1; i < requests.getOrderedStops().size() - 1; i++) { // Depot 2 times in the table (0 and size -1)
																			// so stop 1 index earlier)
			ButtonDelete buttonDelete = new ButtonDelete(this.windowController, i);
			HBox commands = new HBox(buttonDelete);
			ButtonUp buttonUp = new ButtonUp("");
			ButtonDown buttonDown = new ButtonDown("");
			if (i == 1) {
				buttonDown = new ButtonDown(this.windowController, i);
				commands.getChildren().add(buttonDown);
			} else if (i == requests.getOrderedStops().size() - 2) {
				buttonUp = new ButtonUp(this.windowController, i);
				commands.getChildren().add(buttonUp);
			} else {
				buttonUp = new ButtonUp(this.windowController, i);
				buttonDown = new ButtonDown(this.windowController, i);
				commands.getChildren().add(buttonUp);
				commands.getChildren().add(buttonDown);
			}
			// Check if there is a pikcup above its delivery or vice-versa
			if (requests.getOrderedStops().get(i).getStringType().equals("PICKUP")) {
				for(int j = i; j >= 0; j--) {
					if(requests.getOrderedStops().get(i).equals(requests.getOrderedStops().get(j).getAssociatedStop(requests))) {
						this.infoUser.setText("Be careful, \nlooks like you have a delivery before its associated pickup !");
					}
				}
			} else if (requests.getOrderedStops().get(i).getStringType().equals("DELIVERY")) {
				for(int j = i; j < requests.getOrderedStops().size(); j++) {
					if(requests.getOrderedStops().get(i).equals(requests.getOrderedStops().get(j).getAssociatedStop(requests))) {
						this.infoUser.setText("Be careful, \nlooks like you have a delivery before its associated pickup !");
					}
				}
			}

			type = new Label(requests.getOrderedStops().get(i).getStringType());
			type.setTextFill(requests.getOrderedStops().get(i).getColor());
			adress = new Label(requests.getOrderedStops().get(i).getNode().getNeighbours().get(0).getName());
			adress.setTextFill(requests.getOrderedStops().get(i).getColor());
			arrivalHour = new Label(requests.getOrderedStops().get(i).getArrivalTime().toString());
			arrivalHour.setTextFill(requests.getOrderedStops().get(i).getColor());
			departureHour = new Label(requests.getOrderedStops().get(i).getDepartureTime().toString());
			departureHour.setTextFill(requests.getOrderedStops().get(i).getColor());
			textPane.add(commands, 0, i + 1);
			textPane.add(type, 1, i + 1);
			textPane.add(adress, 2, i + 1);
			textPane.add(arrivalHour, 3, i + 1);
			textPane.add(departureHour, 4, i + 1);
		}

		// Display arrival depot + add new request button 
		type = new Label("Depot");
		type.setTextFill(Color.WHITE);
		adress = new Label(requests.getOrderedStops().get(requests.getOrderedStops().size() - 1).getNode()
				.getNeighbours().get(0).getName());
		adress.setTextFill(Color.WHITE);
		arrivalHour = new Label(
				requests.getOrderedStops().get(requests.getOrderedStops().size() - 1).getArrivalTime().toString());
		arrivalHour.setTextFill(Color.WHITE);
		textPane.add(type, 1, requests.getOrderedStops().size());
		textPane.add(adress, 2, requests.getOrderedStops().size());
		textPane.add(arrivalHour, 3, requests.getOrderedStops().size());

		// Make the rows of the table clickable
		for (javafx.scene.Node n : textPane.getChildren()) {
			n.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
				handleListClick(e, n, true);
			});
		}
	}

	/**
	 * Erase everyhting in the map view 
	 * including map background, request points and tour
	 */
	public void eraseMap() { // Erase the map, all the requests and the tour
		Scene scene = primaryStage.getScene();
		Group groupMap = getGroupMap(scene);
		clearMap(groupMap);
	}

	/**
	 * Erase the map background 
	 */
	public void eraseMapBackground() { // Erase the map only (keeps the requests and tour)
		this.groupMainMap.getChildren().clear();
	}

	/**
	 * Erase the map view of the tour and the request points
	 */
	public void eraseRequestsAndTour() {
		this.groupRequests.getChildren().clear();
		this.groupTour.getChildren().clear();

	}
	/**
	 * Erase the map view of the tour
	 */
	public void eraseTour() {
		this.groupTour.getChildren().clear();
	}

	public static javafx.scene.Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
		javafx.scene.Node result = null;
		ObservableList<javafx.scene.Node> childrens = gridPane.getChildren();

		for (javafx.scene.Node node : childrens) {
			if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
				result = node;
				break;
			}
		}
		return result;
	}

	/**
	 * Displays the map
	 * 
	 * @param map the map to display (most likely the singleton map)
	 */
	public void displayMap(Map map) {
		Scene scene = this.primaryStage.getScene();
		final int CIRCLE_RADIUS = 1;

		Pane controlPane = (Pane) scene.getRoot().getChildrenUnmodifiable().get(0);

		// Placing points
		for (int i = 0; i < map.getNodes().size(); i++) {
			Pair<Double, Double> windowCoordinates = conversionLongLatToWindow(map.getNodes().get(i), scene, map);
			Circle circle = new Circle(windowCoordinates.getKey(), windowCoordinates.getValue(), CIRCLE_RADIUS);
			Node node = map.getNodes().get(i);
			Pair<Node,Circle> pairNodeCircle = new Pair<Node,Circle>(node,circle);
			this.allMapNodes.add(pairNodeCircle);
			EventHandler<MouseEvent> pickupEventHandler = new EventHandler<MouseEvent>() {
				public void handle(MouseEvent m) {
					if(firstNodeNewRequest == null) {
						firstNodeNewRequest = node;
						infoUser.setText("Please click on the place where you want to pu the delivery point");
					}
					else if(secondNodeNewRequest == null){
						secondNodeNewRequest = node;
						selectPickupAndDeliveryDuration();
					}
					else {} // Just to remind that if the map is clicked while the nodes have already been chosen, nothing happends
				}
			};
			if(map.getNodes().get(i).getNeighbours().size() > 0) {
				circle.addEventHandler(MouseEvent.MOUSE_CLICKED, pickupEventHandler);
				circle.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
					public void handle(MouseEvent m) {
						scene.setCursor(Cursor.HAND);
					};
				});
				circle.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
					public void handle(MouseEvent m) {
						scene.setCursor(Cursor.DEFAULT);
					};
				});
				circle.setDisable(true);
			}
			this.groupMainMap.getChildren().add(circle);
			
			// Placing segments that have this point as origin or destination
			for (int j = 0; j < map.getNodes().get(i).getNeighbours().size(); j++) {
				Pair<Double, Double> origin = conversionLongLatToWindow(map.getNodes().get(i), scene, map);
				Pair<Double, Double> destination = conversionLongLatToWindow(
						map.getNodes().get(i).getNeighbours().get(j).getDestination(), scene, map);
				Line line = new Line(origin.getKey(), origin.getValue(), destination.getKey(), destination.getValue());
				line.setId(map.getNodes().get(i).getNeighbours().get(j).getName());
				line.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
					public void handle(MouseEvent m) {
						Label adresse = (Label) controlPane.getChildren().get(3);
						adresse.setText(line.getId());
					};
				});
				this.groupMainMap.getChildren().add(line);
			}
		}
	}

	/**
	 * Displays an error pop-up
	 * 
	 * @param title   the title of the error
	 * @param content details on the error
	 */
	public void displayPopup(String title, String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText(title);
		alert.setContentText(content);
		alert.show();
	}

	/**
	 * Displays the list of requests on the map.
	 * 
	 * @param requests the list of requests
	 * @param map      the map (most likely the singleton map)
	 */
	public void displayListOfRequest(ListOfRequest requests, Map map) {
		Scene scene = this.primaryStage.getScene();
		final int PICKUP_SIZE = 5;
		final int DELIVERY_RADIUS = 3;

		// Clear the saved data about displayed stops
		pospts.clear();

		// depot print
		Node depot = requests.getDepot().getNode();
		Pair<Double, Double> depotCoordinates = conversionLongLatToWindow(depot, scene, map);
		Rectangle depotRectangle = squareCentered(depotCoordinates.getKey(), depotCoordinates.getValue(), PICKUP_SIZE);
		depotRectangle.setFill(Color.WHITE);
		this.groupRequests.getChildren().add(depotRectangle);
		pospts.add(depotCoordinates);

		// pickup/delivery points print
		for (int i = 0; i < requests.getDeliveries().size(); i++) {
			Node pickup = requests.getDeliveries().get(i).getPickup().getNode();
			Pair<Double, Double> pickupCoordinates = conversionLongLatToWindow(pickup, scene, map);
			Rectangle pickupRectangle = squareCentered(pickupCoordinates.getKey(), pickupCoordinates.getValue(),
					PICKUP_SIZE);

			Node delivery = requests.getDeliveries().get(i).getDelivery().getNode();
			Pair<Double, Double> deliveryCoordinates = conversionLongLatToWindow(delivery, scene, map);
			Circle deliveryCircle = new Circle(deliveryCoordinates.getKey(), deliveryCoordinates.getValue(),
					DELIVERY_RADIUS);

			pickupRectangle.setFill(requests.getDeliveries().get(i).getColorRequest());
			deliveryCircle.setFill(requests.getDeliveries().get(i).getColorRequest());

			this.groupRequests.getChildren().addAll(pickupRectangle, deliveryCircle);

			// Saving position
			pospts.add(pickupCoordinates);
			pospts.add(deliveryCoordinates);
		}
	}

	/**
	 * Displays the tour
	 */
	public void displayTour() {
		Tour tour = Map.getSingletonMap().getTour();
		Map map = Map.getSingletonMap();

		ArrayList<Segment> bestPath = tour.getBestPath();

		Scene scene = this.primaryStage.getScene();

		// Placing segments
		for (int i = 0; i < bestPath.size(); i++) {
			Pair<Double, Double> origin = conversionLongLatToWindow(bestPath.get(i).getOrigin(), scene, map);
			Pair<Double, Double> destination = conversionLongLatToWindow(bestPath.get(i).getDestination(), scene, map);
			Line line = new Line(origin.getKey(), origin.getValue(), destination.getKey(), destination.getValue());
			line.setStroke(new Color(1.0, (float) i / bestPath.size(), (float) i / bestPath.size(), 1.0));
			line.setStrokeWidth(3 + ((double) (bestPath.size() - i) / bestPath.size()) * 4);
			this.groupTour.getChildren().add(line);
		}
		this.groupTour.toBack();
	}
	
	public void setController(Controller controller) {
		this.windowController = controller;
	}

	@Override
	public void init() throws Exception {
		super.init();
		this.windowController = new Controller(this);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
