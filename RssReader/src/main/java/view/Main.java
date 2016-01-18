package view;

import java.io.IOException;

import controller.NewFolderDialogController;
import controller.RssOverviewController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.FeedManager;
import model.Folder;

public class Main extends Application {
	private Stage primaryStage;
	private BorderPane mainLayout;
	private ObservableList<String> folders;
	private FeedManager fm;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("RssReader");
		
		initMainLayout();
		showRssOverview();
	}
	
	public void initMainLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("MainLayout.fxml"));
			mainLayout = (BorderPane) loader.load();
			Scene scene = new Scene(mainLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	
	public void showRssOverview() {
		try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("RssOverview.fxml"));
            AnchorPane rssOverview = (AnchorPane) loader.load();

            // Set rss overview into the center of main layout.
            mainLayout.setCenter(rssOverview);
            
            RssOverviewController controller = loader.getController();
            controller.setMain(this);
            
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public boolean showNewFolderDialog(Folder folder) {
		try {
			FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("NewFolderDialog.fxml"));
            AnchorPane newFolderDialog = (AnchorPane) loader.load();
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("New Folder");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(newFolderDialog);
            dialogStage.setScene(scene);
            
            NewFolderDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setFolder(folder);
            controller.setMain(this);
            
            dialogStage.showAndWait();
            
            return controller.isOkClicked();
            
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public void exit() {
		primaryStage.close();
	}
	
    public Stage getPrimaryStage() {
        return this.primaryStage;
    }
    
    public void setPrimaryStage(Stage primaryStage) {
    	this.primaryStage = primaryStage;
    }
    
    public ObservableList<String> getFolders() {
    	return folders;
    }
    
    public void setFolders(ObservableList<String> folders) {
    	this.folders = folders;
    }
    
    public FeedManager getFeedManager() {
    	return fm;
    }
    
    public void setFeedManager(FeedManager fm) {
    	this.fm = fm;
    }

	public static void main(String[] args) {
		launch(args);
	}
	
}
