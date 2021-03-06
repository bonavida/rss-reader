package view;

import java.io.IOException;

import controller.AssignOrUnassignTagDialogController;
import controller.MoveFeedDialogController;
import controller.NewFeedDialogController;
import controller.NewFolderDialogController;
import controller.NewTagDialogController;
import controller.RssOverviewController;
import controller.UrlFeedDialogController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.FeedManager;
import model.Feed;
import model.Folder;
import model.Tag;

public class Main extends Application {
	private Stage primaryStage;
	private BorderPane mainLayout;
	private ObservableList<String> folders;
	private ObservableList<String> tags;
	private FeedManager fm;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("RssReader");
		
		initMainLayout();
	}
	
	public void initMainLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("MainLayout.fxml"));
			mainLayout = (BorderPane) loader.load();
			Scene scene = new Scene(mainLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			showRssOverview();
			
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
            dialogStage.setTitle("Nueva carpeta");
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
	
	public Feed showNewFeedDialog() {
		try {
			Feed newFeed = showUrlFeedDialog();
			if (newFeed != null) {
				FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Main.class.getResource("NewFeedDialog.fxml"));
	            AnchorPane newFeedDialog = (AnchorPane) loader.load();
	            
	            Stage dialogStage = new Stage();
	            dialogStage.setTitle("Nuevo Feed");
	            dialogStage.initModality(Modality.WINDOW_MODAL);
	            dialogStage.initOwner(primaryStage);
	            Scene scene = new Scene(newFeedDialog);
	            dialogStage.setScene(scene);
	            
	            NewFeedDialogController controller = loader.getController();
	            controller.setDialogStage(dialogStage);
	            controller.setMain(this);
	            controller.setFeed(newFeed);          

	            dialogStage.showAndWait();

	            if (controller.isOkClicked()) {
	            	return controller.getFeed();
	            } else {
	            	return null;
	            }
	            
			} else {
				return null;
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public Feed showUrlFeedDialog() {
		try {
			FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("UrlFeedDialog.fxml"));
            AnchorPane urlFeedDialog = (AnchorPane) loader.load();
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nuevo Feed");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(urlFeedDialog);
            dialogStage.setScene(scene);

            UrlFeedDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMain(this);
            
            dialogStage.showAndWait();
            
            if (controller.isOkClicked()) {
            	return controller.getFeed();
            } else {
            	return null;
            }
            
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public String showMoveFeedDialog() {
		try {
			FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("MoveFeedDialog.fxml"));
            AnchorPane moveFeedDialog = (AnchorPane) loader.load();
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Mover feed a la carpeta");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(moveFeedDialog);
            dialogStage.setScene(scene);
            
            MoveFeedDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMain(this);
            controller.setFolderList();
            
            dialogStage.showAndWait();
            
            if (controller.isOkClicked()) {
            	return controller.getNewFolderName();
            } else {
            	return null;
            }
            
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean showNewTagDialog(Tag tag) {
		try {
			FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("NewTagDialog.fxml"));
            AnchorPane newTagDialog = (AnchorPane) loader.load();
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nueva etiqueta");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(newTagDialog);
            dialogStage.setScene(scene);
            
            NewTagDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setTag(tag);
            controller.setMain(this);
            
            dialogStage.showAndWait();
            
            return controller.isOkClicked();
            
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public String showAssignOrUnassignTagDialog(String value) {
		try {
			FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("AssignOrUnassignTagDialog.fxml"));
            AnchorPane tagDialog = (AnchorPane) loader.load();
            
            Stage dialogStage = new Stage();
            String message = "";
            if (value.equals("a")) {
            	dialogStage.setTitle("Asignar etiqueta al feed");
            	message = "Asignar etiqueta";
            } else {
            	dialogStage.setTitle("Quitar etiqueta del feed");
            	message = "Quitar etiqueta";
            }
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(tagDialog);
            dialogStage.setScene(scene);
            
            AssignOrUnassignTagDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMain(this);
            controller.setTagList();
            controller.setLabel(message);
            
            dialogStage.showAndWait();
            
            if (controller.isOkClicked()) {
            	return controller.getTagName();
            } else {
            	return null;
            }
            
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
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
    
    public ObservableList<String> getTags() {
    	return tags;
    }
    
    public void setTags(ObservableList<String> tags) {
    	this.tags = tags;
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
