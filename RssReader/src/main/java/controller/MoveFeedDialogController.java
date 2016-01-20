package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import view.Main;

public class MoveFeedDialogController implements Initializable {

	@FXML
	private ComboBox<String> folderList;
	
	private String newFolderName;
	private Stage dialogStage;
	private Main main;
	private boolean okClicked;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setFolderList() {
		folderList.setItems(main.getFolders());
		folderList.setValue(main.getFolders().get(0));

		folderList.valueProperty().addListener(
		        (ObservableValue<? extends String> ov, String old_val, String new_val) -> {
		    folderList.setValue(new_val);
		});
	}
	
	public String getNewFolderName() {
		return this.newFolderName;
	}
	
	@FXML
	public void handleOk() {
		newFolderName = folderList.getValue();
		okClicked = true;
		dialogStage.close();
	}
	
	@FXML
	public void handleCancel() {
		dialogStage.close();
	}
	
    public boolean isOkClicked() {
        return okClicked;
    }
	
	public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
	
    public void setMain(Main main) {
    	this.main = main;
    }

}
