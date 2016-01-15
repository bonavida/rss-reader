package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeView;
import view.Main;

public class RssOverviewController implements Initializable {
	
	@FXML
	TreeView<String> treeView;
	
	private Main main;
	
	
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void showFoldersAndFeeds() {
		
	}
	
	public void setMain(Main man) {
		this.main = main;
	}
}
