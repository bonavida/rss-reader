package rss;

import java.io.IOException;
import java.net.MalformedURLException;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import model.Entry;
import model.Feed;
import rome.RssParser;

public class MyWebView extends Application {
    private Scene scene;
    @Override public void start(Stage stage) {
        // create the scene
        stage.setTitle("Web View");
        scene = new Scene(new Browser(),750,500, Color.web("#666970"));
        stage.setScene(scene);
        scene.getStylesheets().add("webviewsample/BrowserToolbar.css");        
        stage.show();
    }
 
    public static void main(String[] args){
        launch(args);
    }
}
class Browser extends Region {
 
    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
     
    public Browser() {
    	
    	//On load event
    	webEngine.getLoadWorker().stateProperty().addListener(
    	        new ChangeListener<State>() {
    	            public void changed(ObservableValue ov, State oldState, State newState) {
    	                if (newState == State.SUCCEEDED) {
    	                    System.out.println(webEngine.getDocument().toString());
    	                }
    	            }
    	        });
    	        
        String html = "";
        RssParser parser = new RssParser();
		SyndFeed feeder = null;
		try {
			feeder = parser.getSyndFeedFromUrl("http://www.microsiervos.com/index.xml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Feed feed = parser.setFeedAttributes(feeder);

		webEngine.loadContent(feed.getEntryList().get(0).getContent());
		
		
		//webEngine.loadContent("<h1>HOLA</h1>");
        //add the web view to the scene
        getChildren().add(browser);
        
        
       
        
       
 
    }
    /*
    private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }
 
    @Override protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }
 
    @Override protected double computePrefWidth(double height) {
        return 750;
    }
 
    @Override protected double computePrefHeight(double width) {
        return 500;
    }*/


}
