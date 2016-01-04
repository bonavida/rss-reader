package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.NotAllowedOperationException;

public class Folder {

	private String name;
	private Map<String, Feed> feedList;
	
	public Folder() {
		
	}
	
	public Folder(String name) {
		this.name = name;
		this.feedList = new HashMap<String, Feed>();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Feed> getFeedList() {
		List<Feed> list = new ArrayList<Feed>();
		for(Feed f: feedList.values()){
			list.add(f);
		}
		return list;
	}
	
	public void setFeedList(Map<String, Feed> feedList) {
		this.feedList = feedList;
	}
	
	public Feed getFeed(String name) {
		return feedList.get(name);
	}
	
	public void addFeed(Feed feed) throws NotAllowedOperationException {
		if ((feed != null && feed.getName() != null) && !feedList.containsKey(feed.getName())) {
			feed.setFolder(this);
			feedList.put(feed.getName(), feed);			
		} else {
			throw new NotAllowedOperationException("No se admiten fuentes repetidas.");
		}	
	}
	
	public void removeFeed(String name) {
		feedList.remove(name);
	}
}
