package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.NotAllowedOperationException;
import model.Entry;
import model.Feed;
import model.Folder;
import model.Tag;

public class FeedManager {
	private static StorageService storage = new StorageService();
	
	private Map<String, Folder> folderList;
	private Map<String, Tag> tagList;
	
	public FeedManager() {
		folderList = new HashMap<String, Folder>();
		tagList = new HashMap<String, Tag>();
	}

	public void addFolder(Folder folder) throws NotAllowedOperationException {
		if ((folder != null && folder.getName() != null) && !folderList.containsKey(folder.getName())) {
			folderList.put(folder.getName(), folder);
		} else {
			throw new NotAllowedOperationException("No se admiten carpetas repetidas.");
		}		
	}
	
	public Folder getFolder(String name) {
		return folderList.get(name);
	}
	
	public List<Folder> getFolderList() {
		List<Folder> list = new ArrayList<Folder>();
		for(Folder f: folderList.values()){
			list.add(f);
		}
		return list;
	}
	
	public void removeFolder(String name) {
		Folder folder = folderList.get(name);
		for (Feed f : folder.getFeedList()) {
			for (Tag t : f.getTagList()) {
				 t.removeAssignedFeed(f);
			}
			folder.removeFeed(f.getName());
		}
		folderList.remove(name);
	}
	
	public void addTag(Tag tag) throws NotAllowedOperationException {
		if ((tag != null && tag.getName() != null) && !tagList.containsKey(tag.getName())) {
			tagList.put(tag.getName(), tag);
		} else {
			throw new NotAllowedOperationException("No se admiten etiquetas repetidas.");
		}
	}
	
	public Tag getTag(String name) {
		return tagList.get(name);
	}
	
	public List<Tag> getTagList() {
		List<Tag> list = new ArrayList<Tag>();
		for(Tag t: tagList.values()){
			list.add(t);
		}
		return list;
	}
	
	public void removeTag(String name) {
		Tag tag = tagList.get(name);
		List<Feed> feedList = new ArrayList<Feed>(tag.getAssignedFeedList());
		for (Feed f : feedList) {
			f.removeTag(name);
		}
		tagList.remove(name);
	}
	
	public boolean move(Feed feed, String newFolderName) {
		try {
			Folder oldFolder = feed.getFolder();
			folderList.get(newFolderName).addFeed(feed);
			oldFolder.removeFeed(feed.getName());
			return true;
		} catch (NotAllowedOperationException e) {
			System.out.println(e);
			return false;
		}	
	}
	
	public Feed getFeed(String feedName) {
		List<Folder> folders = getFolderList();
		for (Folder f : folders) {
			Feed feed = f.getFeed(feedName);
			if (feed != null) {
				return feed;
			}
		}
		return null;
	}
	
	public Entry getEntry(String entryName) {
		List<Folder> folders = getFolderList();
		for (Folder f : folders) {
			for(Feed feed : f.getFeedList()) {
				Entry entry = feed.getEntry(entryName);
				if (entry != null) {
					return entry;
				}
			}
		}
		return null;
	}
	
	public void load(){
		folderList = storage.getFolderList();
		tagList = storage.getTagList();
	}
	
	public void save(){
		storage.saveFolderList(folderList);
		storage.saveTagList(tagList);
	}
}
