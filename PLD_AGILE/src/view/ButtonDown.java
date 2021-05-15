package view;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ButtonDown extends Button {
	final String pathToImage = "../../../Pictures/down-chevron.png";

	public ButtonDown(Controller controller, int index) {
		this.setText("down");
		this.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				controller.changeStopOrder(index, true);
			}
		});
	}

	public ButtonDown(String text) {
		super(text);
	}

}
