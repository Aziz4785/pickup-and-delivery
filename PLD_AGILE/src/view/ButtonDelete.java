package view;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ButtonDelete extends Button {
	final String pathToImage = "./../Pictures/delete.png";

	public ButtonDelete(Controller controller, int index) {
		// File fileToImage = new File(pathToImage);
		this.setText("delete");
		/*
		 * try { String absolute = "file://" + fileToImage.getCanonicalPath(); Image img
		 * = new Image(absolute); ImageView view = new ImageView(img);
		 * view.setPreserveRatio(true); this.setGraphic(view); } catch(Exception e) {
		 * System.out.println("erreur de chargement"); }
		 */
		this.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				controller.removeRequest(index);
			}
		});

	}

}
