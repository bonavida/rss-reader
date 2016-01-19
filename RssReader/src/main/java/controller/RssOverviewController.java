package controller;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import main.FeedManager;
import model.Entry;
import model.Feed;
import model.Folder;
import rss.RssReader;
import view.Main;

public class RssOverviewController implements Initializable {
	
	@FXML
	private TreeView<String> tree;
	@FXML
	private ListView<String> listView;
	@FXML
	private WebView webView = new WebView();
	
	private TreeItem<String> rootNode;
	private Map<String, TreeItem<String>> nodeList = new HashMap<String, TreeItem<String>>();
	private ObservableList<String> folders = FXCollections.observableArrayList();
	private ObservableList<String> entries = FXCollections.observableArrayList();
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
		Folder folder = new Folder("Tecnología");
		try {
			feed = reader.readFeed("http://www.microsiervos.com/index.xml");
			fm.addFolder(folder);
			folders.add(folder.getName());
			fm.getFolder("Tecnología").addFeed(feed);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		TreeItem<String> folderNode = new TreeItem<String>(folder.getName());
		TreeItem<String> feedLeaf = new TreeItem<String>(feed.getName());
		rootNode.getChildren().add(folderNode);
		nodeList.put(folder.getName(), folderNode);
		folderNode.getChildren().add(feedLeaf);
		folderNode.setExpanded(true);
		tree.setRoot(rootNode);
		listView.setItems(entries);
		//tree.setShowRoot(false);
		
		tree.getSelectionModel().selectedItemProperty()
        	.addListener((v, oldValue, newValue) -> {
        		if ((newValue != null) && (fm.getFeed(newValue.getValue()) != null)) {       			
        			Feed feed = fm.getFeed(newValue.getValue());
        			entries = FXCollections.observableArrayList();
        			listView.setItems(entries);
        			for (Entry entry : feed.getEntryList())
        				entries.add(entry.getTitle());
        			System.out.println(newValue.getValue());
        		}
        	});
		
		listView.getSelectionModel().selectedItemProperty().addListener(
		        (ObservableValue<? extends String> ov, String old_val, 
		            String new_val) -> {
		            	WebEngine webEngine = webView.getEngine();
		            	Entry entry = fm.getEntry(new_val);
		            	if (entry != null) {
		            		String html = "<h1>" + entry.getTitle() + "</h1>";
		            		html += "<h3>" + entry.getAuthor() + "</h3>";
		            		html += "<h3>" + entry.getPublicationDate() + "</h3>";
		            		html += entry.getContent();
		            		
		            		webEngine.loadContent(html);    
		            	} else {
		            		entries = FXCollections.observableArrayList();
		        			listView.setItems(entries);
		            		webEngine.loadContent("");
		            	}
		    });
	}
	
	@FXML
	public void handleNewFolder() {
		Folder folder = new Folder();
		main.setFolders(folders);
		boolean okClicked = main.showNewFolderDialog(folder);
		if (okClicked) {
			try {
				fm.addFolder(folder);
				folders.add(folder.getName());
				TreeItem<String> folderNode = new TreeItem<String>(folder.getName());
				rootNode.getChildren().add(folderNode);
				nodeList.put(folder.getName(), folderNode);
				folderNode.setExpanded(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
	}
	
	@FXML
	public void handleDeleteFolder() {
		
	}
	
	@FXML
	public void handleNewFeed() {
		main.setFolders(folders);
		main.setFeedManager(fm);
		Feed feed = main.showNewFeedDialog();
		if (feed != null) {
			try {
				fm.getFolder(feed.getFolder().getName()).addFeed(feed);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			TreeItem<String> feedLeaf = new TreeItem<String>(feed.getName());
			nodeList.get(feed.getFolder().getName()).getChildren().add(feedLeaf);
			nodeList.get(feed.getFolder().getName()).setExpanded(true);
		}
	}

	@FXML
	public void handleDeleteFeed() {
		
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
}
