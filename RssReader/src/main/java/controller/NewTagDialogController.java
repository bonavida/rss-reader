package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Tag;
import view.Main;

public class NewTagDialogController implements Initializable {

	@FXML
	private TextField tagNameField;
	@FXML
	private Label errorMessageLbl;
	
	private Tag tag;
	private Stage dialogStage;
	private boolean okClicked = false;
	private Main main;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setTag(Tag tag) {
		this.tag = tag;
	}
	
	@FXML
	public void handleOk() {
		if (main.getTags().contains(tagNameField.getText())) {
			errorMessageLbl.setText("La etiqueta ya existe.");
		} else {
			tag.setName(tagNameField.getText());
			okClicked = true;
			dialogStage.close();
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
