package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Feed;
import rss.RssReader;
import view.Main;

public class UrlFeedDialogController implements Initializable {

	@FXML
	private TextField urlField;
	@FXML
	private Label errorMessageLbl;
	
	private Feed feed;
	private Stage dialogStage;
	private Main main;
	private boolean okClicked = false;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public Feed getFeed() {
		return this.feed;
	}
	
	@FXML
	public void handleOk() {
		RssReader reader = new RssReader();
		try {
			feed = reader.readFeed(urlField.getText());
		} catch (Exception ex) {
			feed = null;
			ex.printStackTrace();
		}
		if (feed != null) {
			if (main.getFeedManager().getFeed(feed.getName()) != null) {
				errorMessageLbl.setText("Ya existe un feed con esa URL.");
			} else {
				okClicked = true;
				dialogStage.close();
			}
		} else {
			errorMessageLbl.setText("La URL del feed no es válida.");
		}
	}
	
	@FXML
	public void handleCancel() {
		dialogStage.close();
	}
	
	public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
	
    public boolean isOkClicked() {
        return okClicked;
    }
    
    public void setMain(Main main) {
    	this.main = main;
    }

}
