package view;

import java.io.IOException;

import controller.RssOverviewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	private Stage primaryStage;
	private BorderPane mainLayout;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("RssReader");
		
		initMainLayout();
		showRssOverview();
	}
	
	private void initMainLayout() {
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

	
	private void showRssOverview() {
		try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("RssOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            mainLayout.setCenter(personOverview);
            
            RssOverviewController controller = loader.getController();
            controller.setMain(this);
            
		} catch (IOException ex) {
			ex.printStackTrace();
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

	public static void main(String[] args) {
		launch(args);
	}
	
}
