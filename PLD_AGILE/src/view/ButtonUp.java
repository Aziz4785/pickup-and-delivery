package view;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ButtonUp extends Button {
	final String pathToImage = "../../../Pictures/up-chevron.png";

	public ButtonUp(Controller controller, int index) {
		this.setText("up");
		this.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				controller.changeStopOrder(index, false);
			}
		});
	}

	public ButtonUp(String arg0) {
		super(arg0);
	}

}
