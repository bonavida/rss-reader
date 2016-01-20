package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Feed;
import view.Main;

public class NewFeedDialogController implements Initializable {

	@FXML
	private TextField feedNameField;
	@FXML
	private ComboBox<String> folderList;
	
	private Feed feed;
	private Stage dialogStage;
	private Main main;
	private boolean okClicked = false;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	public void setFeed(Feed feed) {
		this.feed = feed;
		feedNameField.setText(feed.getName());
		folderList.setItems(main.getFolders());
		if (main.getFolders().size() != 0) {
			folderList.setValue(main.getFolders().get(0));
		}

		folderList.valueProperty().addListener(
		        (ObservableValue<? extends String> ov, String old_val, String new_val) -> {
		    folderList.setValue(new_val);
		});
	}
	
	public Feed getFeed() {
		return this.feed;
	}
	
	@FXML
	public void handleOk() {
		feed.setName(feedNameField.getText());
		feed.setFolder(main.getFeedManager().getFolder(folderList.getValue()));
		okClicked = true;
		dialogStage.close();		
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
