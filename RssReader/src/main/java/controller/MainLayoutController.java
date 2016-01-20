package controller;

import javafx.fxml.FXML;
import view.Main;

public class MainLayoutController {
	private Main main;
	
	@FXML
	private void handleExit() {
		main.exit();
	}
	
	public void setMain(Main main) {
		this.main = main;
	}

}
