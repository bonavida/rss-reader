package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Folder;
import view.Main;

public class NewFolderDialogController implements Initializable {
	
	@FXML
	private TextField folderNameField;
	@FXML
	private Label errorMessageLbl;
	
	private Folder folder;
	private Stage dialogStage;
	private boolean okClicked = false;
	private Main main;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void handleOk() {
		if (main.getFolders().contains(folderNameField.getText())) {
			errorMessageLbl.setText("La carpeta ya existe.");
		} else {
			folder.setName(folderNameField.getText());
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
    
    public void setFolder(Folder folder) {
    	this.folder = folder;
    }
    
    public void setMain(Main main) {
    	this.main = main;
    }

}
