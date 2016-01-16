package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import main.FeedManager;
import model.Feed;
import model.Folder;
import rss.RssReader;
import view.Main;

public class RssOverviewController implements Initializable {
	
	@FXML
	private TreeView<String> tree;
	
	private TreeItem<String> rootNode;
	private RssReader reader;
	private FeedManager fm;
	private Feed feed;
	private Main main;
	
	
	public void initialize(URL location, ResourceBundle resources) {
		rootNode = new TreeItem<>("Carpetas");
		reader = new RssReader();
		fm = new FeedManager();
		
		showFoldersAndFeeds();
	}
	
	public void showFoldersAndFeeds() {
		rootNode.setExpanded(true);
		
		try {
			feed = reader.readFeed("http://www.microsiervos.com/index.xml");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		TreeItem<String> folderNode = new TreeItem<String>("Tecnología"); // Tecnología es solo un ejemplo
		TreeItem<String> feedLeaf = new TreeItem<String>(feed.getName());
		rootNode.getChildren().add(folderNode);
		folderNode.getChildren().add(feedLeaf);
		folderNode.setExpanded(true);
		tree.setRoot(rootNode);
		//tree.setShowRoot(false);
		
		tree.getSelectionModel().selectedItemProperty()
        	.addListener((v, oldValue, newValue) -> {
        		if (newValue != null)
        			// TODO
        			// Buscar en el FeedManager el feed a partir del nombre y hacer un getEntryList para que el listView muestre entradas
        			System.out.println(newValue.getValue());
        	});
		
	}
	
	public void setMain(Main man) {
		this.main = main;
	}
}
