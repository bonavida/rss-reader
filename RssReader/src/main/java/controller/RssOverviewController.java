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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import main.FeedManager;
import model.Entry;
import model.Feed;
import model.Folder;
import model.Tag;
import view.Main;

public class RssOverviewController implements Initializable {
	
	@FXML
	private TreeView<String> treeView;
	@FXML
	private ListView<String> entryListView;
	@FXML
	private Label tagLabel;
	@FXML
	private ImageView imageSeen;
	@FXML
	private Button seenButton;
	@FXML
	private WebView webView = new WebView();
	@FXML
	private ListView<String> tagListView;
	@FXML
	private ListView<String> feedListView;
	
	private TreeItem<String> rootNode;
	private Map<String, TreeItem<String>> nodeList = new HashMap<String, TreeItem<String>>();
	private ObservableList<String> folders = FXCollections.observableArrayList();
	private ObservableList<String> tags = FXCollections.observableArrayList();
	private ObservableList<String> feeds = FXCollections.observableArrayList();
	private ObservableList<String> entries = FXCollections.observableArrayList();
	private FeedManager fm;
	private Main main;
	
	
	public void initialize(URL location, ResourceBundle resources) {
		rootNode = new TreeItem<>("Carpetas");
		seenButton.setVisible(false);

		fm = new FeedManager();
		
		fm.load();
		loadData();
	}
	
	public void loadData() {
		
		rootNode.setExpanded(true);
		
		for (Folder folder: fm.getFolderList()){
			TreeItem<String> folderNode = new TreeItem<String>(folder.getName());
			rootNode.getChildren().add(folderNode);
			nodeList.put(folder.getName(), folderNode);
			folders.add(folder.getName());
			for(Feed feed: folder.getFeedList()){				
				TreeItem<String> feedLeaf = new TreeItem<String>(feed.getName());				
				folderNode.getChildren().add(feedLeaf);				
				
			}
			folderNode.setExpanded(true);
		}
		
		for(Tag tag: fm.getTagList()){
			tags.add(tag.getName());
		}
		
		treeView.setRoot(rootNode);
		entryListView.setItems(entries);
		treeView.setShowRoot(false);
		tagListView.setItems(tags);
		feedListView.setItems(feeds);
		tagLabel.setText("");
		
		treeView.getSelectionModel().selectedItemProperty()
        	.addListener((v, oldValue, newValue) -> {
        		if ((newValue != null) && (fm.getFeed(newValue.getValue()) != null)) {       			
        			Feed feed = fm.getFeed(newValue.getValue());
        			entries = FXCollections.observableArrayList();
        			entryListView.setItems(entries);
        			for (Entry entry : feed.getEntryList())
        				entries.add(entry.getTitle());
        			String t = "";
        			for (Tag tag : feed.getTagList()) {
        				t += tag.getName() + "  ";
        			}
        			tagLabel.setText(t);
        			
        		} else {
        			clearEntryList();
        		}
        	});
		
		entryListView.getSelectionModel().selectedItemProperty().addListener(
		        (ObservableValue<? extends String> ov, String old_val, 
		            String new_val) -> {
		            	if (new_val != null) {
			            	WebEngine webEngine = webView.getEngine();
			            	Entry entry = fm.getEntry(new_val);
			            	if (entry != null) {
			            		seenButton.setVisible(true);
			            		Image image;
			            		if (!entry.isSeen()) {			            			
			            			image = new Image("file:src/main/java/view/img/closed-eye.png", 0, 0, true, false);
			            		} else {
			            			image = new Image("file:src/main/java/view/img/opened-eye.png", 0, 0, true, false);
			            		}
			            		imageSeen.setImage(image);
			            		
			            		String html = "<h1>" + entry.getTitle() + "</h1>";
			            		html += "<h3>" + entry.getAuthor() + "</h3>";
			            		html += "<h3>" + entry.getPublicationDate() + "</h3>";
			            		html += entry.getContent();
			            		
			            		webEngine.loadContent(html);    
			            	} else {
			            		clearEntryList();
			            	}
		            	}
		    });
		
		tagListView.getSelectionModel().selectedItemProperty().addListener(
		        (ObservableValue<? extends String> ov, String old_val, 
			        String new_val) -> {
			        	if ((new_val != null) && (fm.getTag(new_val) != null)) {       			
		        			Tag tag = fm.getTag(new_val);
		        			feeds = FXCollections.observableArrayList();
		        			feedListView.setItems(feeds);
		        			clearEntryList();
		        			for (Feed f : tag.getAssignedFeedList())
		        				feeds.add(f.getName());
		        		}
			});
		
		feedListView.getSelectionModel().selectedItemProperty().addListener(
		        (ObservableValue<? extends String> ov, String old_val, 
			        String new_val) -> {
			        	if ((new_val != null) && (fm.getFeed(new_val) != null)) {       			
		        			Feed feed = fm.getFeed(new_val);
		        			entries = FXCollections.observableArrayList();
		        			entryListView.setItems(entries);
		        			for (Entry entry : feed.getEntryList())
		        				entries.add(entry.getTitle());
		        			String t = "";
		        			for (Tag tag : feed.getTagList()) {
		        				t += tag.getName() + "  ";
		        			}
		        			tagLabel.setText(t);
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
		int selectedIndex = treeView.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			TreeItem<String> node = treeView.getSelectionModel().getSelectedItem();
			if ((node != null) && (fm.getFolder(node.getValue()) != null)) {  // si es carpeta
				rootNode.getChildren().remove(node);
				nodeList.remove(node);
				folders.remove(node.getValue());
				fm.removeFolder(node.getValue());
				clearEntryList();
			}
		} else {
			// Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Folder Selected");
            alert.setContentText("Please select a folder in the list.");

            alert.showAndWait();
		}
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
		int selectedIndex = treeView.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			TreeItem<String> node = treeView.getSelectionModel().getSelectedItem();
			if ((node != null) && (fm.getFeed(node.getValue()) != null)) {  // si es feed
				TreeItem<String> parent = node.getParent();
				parent.getChildren().remove(node);
				nodeList.remove(node);
				fm.getFolder(parent.getValue()).removeFeed(node.getValue());
				clearEntryList();
			}
		} else {
			// Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Feed Selected");
            alert.setContentText("Please select a feed in the list.");

            alert.showAndWait();
		}
	}
	
	@FXML
	public void handleMoveFeed() {
		int selectedIndex = treeView.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			TreeItem<String> node = treeView.getSelectionModel().getSelectedItem();
			if ((node != null) && (fm.getFeed(node.getValue()) != null)) {  // si es feed
				main.setFolders(folders);							
				Feed f = fm.getFeed(node.getValue());
				String newFolderName = main.showMoveFeedDialog();
				if ((newFolderName != null) && (!newFolderName.equals(f.getFolder().getName()))) {  // si no es la misma carpeta
					fm.move(f, newFolderName);
					TreeItem<String> parent = node.getParent();
					parent.getChildren().remove(node);
					nodeList.get(newFolderName).getChildren().add(node);
					nodeList.get(newFolderName).setExpanded(true);
					clearEntryList();
				}
			}
		} else {
			// Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Feed Selected");
            alert.setContentText("Please select a feed in the list.");

            alert.showAndWait();
		}
	}
	
	@FXML
	public void handleNewTag() {
		Tag tag = new Tag();
		main.setTags(tags);
		boolean okClicked = main.showNewTagDialog(tag);
		if (okClicked) {
			try {
				fm.addTag(tag);
				tags.add(tag.getName());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	@FXML
	public void handleDeleteTag() {
		int selectedIndex = tagListView.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			String tagName = tagListView.getSelectionModel().getSelectedItem();
			Tag tag = fm.getTag(tagName);
			tagListView.getItems().remove(selectedIndex);
			tags.remove(tag);
			fm.removeTag(tagName);
			feeds = FXCollections.observableArrayList();
			feedListView.setItems(feeds);
			clearEntryList();
		} else {
			// Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Tag Selected");
            alert.setContentText("Please select a tag in the list.");

            alert.showAndWait();
		}
	}
	
	@FXML
	public void handleAssignTag() {
		int selectedIndex = treeView.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			TreeItem<String> node = treeView.getSelectionModel().getSelectedItem();
			Feed f = fm.getFeed(node.getValue());
			if ((node != null) && (f != null)) {  // si es feed
				ObservableList<String> unassignedTags = FXCollections.observableArrayList();
				for (String tagName : tags) {
					Tag tag = fm.getTag(tagName);
					if (!f.getTagList().contains(tag)) {
						unassignedTags.add(tagName);
					}
				}
				main.setTags(unassignedTags);
				String tagName = main.showAssignOrUnassignTagDialog("a");
				if (tagName != null) {
					Tag tag = fm.getTag(tagName);
					try {
						fm.getFeed(node.getValue()).addTag(tag);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		} else {
			// Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Feed Selected");
            alert.setContentText("Please select a feed in the list.");

            alert.showAndWait();
		}		
	}
	
	@FXML
	public void handleUnassignTag() {
		int selectedIndex = treeView.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			TreeItem<String> node = treeView.getSelectionModel().getSelectedItem();
			Feed f = fm.getFeed(node.getValue());
			if ((node != null) && (f != null)) {  // si es feed
				ObservableList<String> assignedTags = FXCollections.observableArrayList();
				for (Tag tag : f.getTagList()) {
					assignedTags.add(tag.getName());
				}
				main.setTags(assignedTags);
				String tagName = main.showAssignOrUnassignTagDialog("u");
				if (tagName != null) {
					try {
						fm.getFeed(node.getValue()).removeTag(tagName);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		} else {
			// Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Feed Selected");
            alert.setContentText("Please select a feed in the list.");

            alert.showAndWait();
		}	
	}

	@FXML
	public void handleFoldersTab() {
		if (entryListView != null) {
			clearEntryList();
		}
	}
	
	@FXML
	public void handleTagsTab() {
		clearEntryList();
		tagListView.getSelectionModel().select(0);
		
	}
	
	@FXML
	public void handleImageSeen() {
		String n = entryListView.getSelectionModel().getSelectedItem();
		Entry entry = fm.getEntry(n);
    	if (entry != null) {
    		
    		entry.setSeen(!entry.isSeen());
    		Image image;
    		if (!entry.isSeen()) {			            			
    			image = new Image("file:src/main/java/view/img/closed-eye.png", 0, 0, true, false);
    		} else {
    			image = new Image("file:src/main/java/view/img/opened-eye.png", 0, 0, true, false);
    		}    		
    		imageSeen.setImage(image);
    	}
	}
	
	public void clearEntryList() {
		entries = FXCollections.observableArrayList();
		entryListView.setItems(entries);
		WebEngine webEngine = webView.getEngine();
		webEngine.loadContent("");
		tagLabel.setText("");
		seenButton.setVisible(false);
	}
	
	public void setMain(Main main) {
		this.main = main;
		this.main.getPrimaryStage().setOnCloseRequest(e -> fm.save());
	}
}
