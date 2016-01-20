package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import view.Main;

public class AssignOrUnassignTagDialogController implements Initializable {

	@FXML
	private Label messageLbl;
	@FXML
	private ComboBox<String> tagList;
	
	private String tagName;
	private Stage dialogStage;
	private boolean okClicked = false;
	private Main main;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setTagList() {
		tagList.setItems(main.getTags());
		
		if (main.getTags().size() != 0) {
			tagList.setValue(main.getTags().get(0));
		}
		
		tagList.valueProperty().addListener(
		        (ObservableValue<? extends String> ov, String old_val, String new_val) -> {
		    tagList.setValue(new_val);
		});
	}
	
	public void setLabel(String message) {
		messageLbl.setText(message);
	}
	
	public String getTagName() {
		return this.tagName;
	}
	
	@FXML
	public void handleOk() {
		tagName = tagList.getValue();
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
